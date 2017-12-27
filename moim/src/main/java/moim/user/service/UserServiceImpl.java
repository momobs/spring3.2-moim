package moim.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import moim.common.dao.CommonDAO;
import moim.common.util.FileUtils;
import moim.common.util.MessageUtils;
import moim.common.vo.CommandMap;
import moim.user.dao.UserDAO;
import moim.user.vo.LoginVO;
import moim.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	// 로그인
	@Override
	public LoginVO selectLoginInfo(String user_id) throws Exception{		
		return userDAO.selectLoginInfo(user_id);
	}
	
	// 아이디 검색
	@Override
	public String selectUserId(String user_id) throws Exception{
		return userDAO.selectUserId(user_id);
	}
	
	// 회원가입
	@Override
	public Map<String,Object> insertUser(HttpServletRequest request, UserVO user) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		
		if (user.getUser_id().length()==0 || user.getUser_name().length()==0 ||
			user.getUser_pwd().length()==0 || user.getEmail().length()==0) {
			map.put("success", "no");
			map.put("message", MessageUtils.getMessage("joinus.required"));
		} else {
			userDAO.insertUser(user);
			map.put("login", userDAO.selectLoginInfo(user.getUser_id()));
			map.put("success", "ok");
			map.put("message", MessageUtils.getMessage("common.success"));
		}
		
		return map;
	}
	
	// 회원정보: 기본정보 변경
	@Override
	public Map<String,Object> updateUser(HttpServletRequest request, UserVO user) throws Exception{
		int row = userDAO.updateUser(user);
		Map<String,Object> map = new HashMap<String,Object>();
		
		if (row==1) {
			map.put("success", "ok");
			map.put("message", MessageUtils.getMessage("common.success"));
		} else {
			map.put("success", "no");
			map.put("message", MessageUtils.getMessage("common.failure"));
		}

		return map;
	}
	
	
	// 회원정보: 비밀번호 변경
	@Override
	public Map<String,Object> updateUserPwd(Map<String,Object> map) throws Exception{
		
		int result = userDAO.updateUserPwd(map);
		
		map.clear();
		
		if (result==1) {
			map.put("success", true);
			map.put("message", MessageUtils.getMessage("common.success"));
		} else {
			map.put("success", false);
			map.put("message", MessageUtils.getMessage("common.failure"));
		}

		return map;
		
	}
	
	// 프로필 사진 업로드
	@Override
	public Map<String,Object> insertUserPhoto(HttpServletRequest request) throws Exception{	
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile file = multipart.getFile("file-photo");
		Map<String, Object> map = new HashMap<String,Object>();
		String success = null;
		String message = null;
		
		if (file != null) {
			map.put("GROUP_IDX", MessageUtils.getMessage("file.group.profile_photo"));
			map = commonDAO.selectFileIndex(map); // GET INDEX
			map = fileUtils.parsetSingleFileInfo(file, map); // PARSE FILE INFO
			map.put("USER_ID", ((UserVO)request.getSession().getAttribute("login")).getUser_id()); // SET CREATE_USER
			commonDAO.insertFileInfo(map); // INSERT FILE TABLE
			map.put("FILE_PATH", FileUtils.userFilePath); // SET UPLOAD PATH
			fileUtils.transferSingleFile(file, map); // UPLOAD FILE
			success = "OK";
			message = MessageUtils.getMessage("file.upload.success");
		} else {
			success = "NO";
			message = MessageUtils.getMessage("file.upload.empty");	
		}
		map.put("SUCCESS", success);
		map.put("MESSAGE", message);		
		return map;
	}
	
}
