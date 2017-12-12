package moim.user.service;

import java.sql.SQLException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
