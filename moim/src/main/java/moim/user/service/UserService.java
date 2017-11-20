package moim.user.service;

import moim.user.vo.UserVO;

public interface UserService {
	UserVO selectUser(UserVO user) throws Exception;
	
	void insertUser(UserVO user) throws Exception;
	
}
