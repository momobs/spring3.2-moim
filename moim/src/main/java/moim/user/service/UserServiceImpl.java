package moim.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import moim.common.util.FileUtils;
import moim.common.util.MessageUtils;
import moim.user.dao.UserDAO;
import moim.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

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
		List<Map<String,Object>> list = null;
		
		try {
			list = fileUtils.parseInsertFileInfo(null, request);
		
			for (int i=0, size=list.size(); i<size; i++) {
				list.get(i).put("user_id", ((UserVO)request.getSession().getAttribute("user")).getUser_id());
				userDAO.insertUserFile((Map<String,Object>)list.get(i));
				userDAO.updateUserPhoto((Map<String,Object>)list.get(i));
			}
		} catch (Exception e) {
			fileUtils.parseDeleteFileInfo(list, request);
			throw e;
		}
	}
	
}
