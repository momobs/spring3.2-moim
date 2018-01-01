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
import moim.common.service.CommonService;
import moim.common.util.FileUtils;
import moim.common.util.LogUtils;
import moim.common.util.MessageUtils;
import moim.exception.custom.MessageException;
import moim.user.dao.UserDAO;
import moim.user.exception.ProfileUpdateException;
import moim.user.vo.LoginVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="commonService")
	private CommonService commonSerivce;
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	// 아이디 검색
	@Override
	public String selectUserId(Map<String,Object> map) throws Exception{
		return userDAO.selectUserId(map);
	}
	
	// 회원가입
	@Override
	public Map<String,Object> insertUserInfo(Map<String,Object> map){
		try {
			userDAO.insertUserInfo(map);
		} catch (Exception e) {
			String path = "/user/joinus";
			String message = MessageUtils.getMessage("joinus.failure");
			throw new MessageException(path, message, map);
		}
		
		return map;
	}
	
	// 프로필 - 기본정보 변경
	@Override
	public Map<String,Object> updateUserInfo(Map<String,Object> map){
		try {
			if(userDAO.updateUserInfo(map)==1) {
				map.clear();
				map.put("RESULT", "OK");
				map.put("MESSAGE", MessageUtils.getMessage("profile.tab1.success"));
			} else {
				map.clear();
				map.put("RESULT", "NO");
				map.put("MESSAGE", MessageUtils.getMessage("profile.tab1.failure"));
			}
		} catch(Exception e) {
			log.error("ERROR: "+e.getMessage());
			throw new RuntimeException(e.getMessage()); 
		}
		return map;
	}	
	
	// 프로필 - 비밀번호 변경
	@Override
	public Map<String,Object> updateUserPwd(Map<String,Object> map){
		String newPassword = (String) map.get("NEW_PASSWORD");
    	String retPassword = (String) map.get("RETYPE_PASSWORD");
    	
    	try {
	    	if (newPassword.equals(retPassword)){
	    		if(userDAO.updateUserPwd(map)==1) {
	    			map.clear();
	    			map.put("RESULT", "OK");
	    			map.put("MESSAGE", MessageUtils.getMessage("profile.tab3.success"));
	    		} else {
	    			map.clear();
	    			map.put("RESULT", "NO");
	    			map.put("MESSAGE", MessageUtils.getMessage("profile.tab3.failure"));
	    		};
	    	} else {
	    		map.clear();
	    		map.put("RESULT", "NO");
				map.put("MESSAGE", MessageUtils.getMessage("profile.tab3.notmatch"));
	    	}
    	} catch(Exception e) {
	    	log.error("ERROR: "+e.getMessage());
	    	throw new RuntimeException(e.getMessage());
	    }

		return map;
	}
	
	// 프로필 사진 업로드
	@Override
	public Map<String,Object> insertUserPhoto(HttpServletRequest request, Map<String,Object> map) throws Exception{	
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
		MultipartFile file = multipart.getFile("file-photo");
		
		String success = null;
		String message = null;
		
		if (file != null) {
			LoginVO login = (LoginVO) map.get("login");
			map = new HashMap<String, Object>();
			map.put("GROUP_IDX", MessageUtils.getMessage("file.group.profile_photo"));
			map = commonDAO.selectFileIndex(map); // GET INDEX
			map = fileUtils.parsetSingleFileInfo(file, map); // PARSE FILE INFO
			map.put("login", login);
			commonDAO.insertFileInfo(map); // INSERT FILE TABLE
			map.put("FILE_PATH", FileUtils.userFilePath); // SET UPLOAD PATH
			fileUtils.transferSingleFile(file, map); // UPLOAD FILE
			
			LogUtils.logDebugMap(log, map);
			success = "OK";
			message = MessageUtils.getMessage("file.upload.success");
		} else {
			success = "NO";
			message = MessageUtils.getMessage("file.upload.empty");	
		}
		map.clear();
		map.put("SUCCESS", success);
		map.put("MESSAGE", message);		
		return map;
	}
	
}
