package moim.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import moim.common.dao.AbstractDAO;
import moim.user.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public UserVO selectUser(UserVO user) throws Exception{
		return (UserVO)selectOne("user.selectUser", user);
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
	
	public int updateUser(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUser", map);
	}
	
	public int updateUserPwd(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserPwd", map);
	}
	
	public int updateUserPhoto(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserPhoto", map);
	}
	

	
	
}
