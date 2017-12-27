package moim.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import moim.common.dao.AbstractDAO;
import moim.user.vo.LoginVO;
import moim.user.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public LoginVO selectLoginInfo(String user_id) throws Exception{
		return (LoginVO)selectOne("user.selectLoginInfo", user_id);
	}
	
	public String selectUserId(String user_id) throws Exception{
		return (String)selectOne("user.selectUserId", user_id);
	}
	
	public String selectUserPwd(UserVO user) throws Exception{
		return (String)selectOne("user.selectUserPwd", user);
	}
	
	public void insertUser(UserVO user) throws Exception{
		insert("user.insertUser", user);
	}
	
	public void insertUserFile(Map<String,Object> map) throws Exception{
		insert("user.insertUserFile", map);
		log.debug("GEGE: "+ map.get("idx"));
	}
	
	public int updateUser(UserVO user) throws Exception{
		return (int)update("user.updateUser", user);
	}
	
	public int updateUserPwd(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserPwd", map);
	}
	
	public int updateUserPhoto(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserPhoto", map);
	}
}
