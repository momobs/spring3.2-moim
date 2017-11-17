package moim.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import moim.user.dao.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
		return userDAO.selectFileInfo(map);
	}
	
}
