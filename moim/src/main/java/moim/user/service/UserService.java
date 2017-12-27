package moim.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import moim.common.vo.CommandMap;
import moim.user.vo.LoginVO;
import moim.user.vo.UserVO;

public interface UserService {
	LoginVO selectLoginInfo(String user_id) throws Exception;
	
	String selectUserId(String user_id) throws Exception;
	
	Map<String,Object> insertUser(HttpServletRequest request, UserVO user) throws Exception;
	
	Map<String,Object> updateUser(HttpServletRequest request, UserVO user) throws Exception;
	
	Map<String,Object> updateUserPwd(Map<String,Object> map) throws Exception;
	
	Map<String,Object> insertUserPhoto(HttpServletRequest request) throws Exception;
	
	
}
