package moim.user.service;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import moim.common.common.CommonVO;
import moim.common.util.MessageUtils;
import moim.user.dao.UserDAO;
import moim.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userDAO")
	private UserDAO userDAO;

	@Override
	public UserVO selectUser(UserVO user) throws Exception{		
		return userDAO.selectUser(user);
	}
	
	@Override
	public UserVO insertUser(UserVO user) throws Exception{
		user.setResult(false);
		
		if (user.getUser_id().length()==0 || user.getUser_name().length()==0 ||
			user.getUser_pwd().length()==0 || user.getEmail().length()==0) {
			user.setMessage(MessageUtils.getMessage("joinus.required"));
		} else {
			userDAO.insertUser(user);
			user.setResult(true);
			user.setMessage(MessageUtils.getMessage("common.success"));
		}
		
		return user;
	}
	
	@Override
	public Map<String,Object> updateUser(HttpServletRequest request, Map<String,Object> map) throws Exception{
		UserVO loginUser = (UserVO) request.getSession().getAttribute("user");
		map.put("user_id", loginUser.getUser_id());
		int row = userDAO.updateUser(map);

		map.clear();

		if (row==1) {
			request.getSession().setAttribute("user", userDAO.selectUser(loginUser));
			map.put("success", true);
			map.put("message", MessageUtils.getMessage("common.success"));
		} else {
			map.put("success", false);
			map.put("message", MessageUtils.getMessage("common.failure"));
		}

		return map;
	}
	
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
	
	@Override
	public void insertUserPhoto(HttpServletRequest request, UserVO user) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		
		while(iterator.hasNext()){
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if (multipartFile.isEmpty()==false) {
				log.debug(multipartFile.getOriginalFilename());
			}
		}
	}
	
}
