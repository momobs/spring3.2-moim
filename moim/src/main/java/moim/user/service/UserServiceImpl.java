package moim.user.service;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public void insertUser(UserVO user) throws Exception{
		try {
			userDAO.insertUser(user);
			user.setResult(true);
		} catch (DuplicateKeyException e) {
			user.setResult(false);
			user.setMessage(e.getMessage());
			log.info(e.getMessage());
		}	
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
