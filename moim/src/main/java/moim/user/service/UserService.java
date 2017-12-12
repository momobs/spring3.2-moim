package moim.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import moim.common.common.CommonVO;
import moim.user.vo.UserVO;

public interface UserService {
	UserVO selectUser(UserVO user) throws Exception;
	
	UserVO insertUser(UserVO user) throws Exception;
	
	Map<String,Object> updateUser(HttpServletRequest request, Map<String,Object> map) throws Exception;
	
	Map<String,Object> updateUserPwd(Map<String,Object> map) throws Exception;
	
	void insertUserPhoto(HttpServletRequest request, UserVO user) throws Exception;
	
	
}
