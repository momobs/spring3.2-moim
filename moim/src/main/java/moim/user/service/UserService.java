package moim.user.service;

import javax.servlet.http.HttpServletRequest;

import moim.user.vo.UserVO;

public interface UserService {
	UserVO selectUser(UserVO user) throws Exception;
	
	UserVO insertUser(UserVO user) throws Exception;
	
	void insertUserPhoto(HttpServletRequest request, UserVO user) throws Exception;
	
	
}
