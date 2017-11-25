package moim.user.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
		} catch (DuplicateKeyException e) {
			user.setResult(false);
			user.setMessage(e.getMessage());
			
			log.debug(e.getMessage());
		}
		
	}
	
}
