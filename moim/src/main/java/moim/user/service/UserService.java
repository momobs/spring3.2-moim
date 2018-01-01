package moim.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	
	String selectUserId(Map<String,Object> map) throws Exception;
	
	Map<String,Object> insertUserInfo(Map<String,Object> map);
	
	Map<String,Object> updateUserInfo(Map<String,Object> map);
	
	Map<String,Object> updateUserPwd(Map<String,Object> map);
	
	Map<String,Object> insertUserPhoto(HttpServletRequest request, Map<String,Object> map) throws Exception;
	
	
}
