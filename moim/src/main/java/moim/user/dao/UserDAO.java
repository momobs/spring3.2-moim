package moim.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import moim.common.dao.AbstractDAO;
import moim.user.vo.LoginVO;
import moim.user.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	

	
	public String selectUserId(Map<String, Object> map) throws Exception{
		return (String)selectOne("user.selectUserId", map);
	}
	
	public String selectUserPwd(UserVO user) throws Exception{
		return (String)selectOne("user.selectUserPwd", user);
	}
	
	public void insertUserInfo(Map<String,Object> map) throws Exception{
		insert("user.insertUserInfo", map);
	}
	
	public void insertUserFile(Map<String,Object> map) throws Exception{
		insert("user.insertUserFile", map);
		log.debug("GEGE: "+ map.get("idx"));
	}
	
	public int updateUserInfo(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserInfo", map);
	}
	
	public int updateUserPwd(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserPwd", map);
	}
	
	public int updateUserPhoto(Map<String,Object> map) throws Exception{
		return (int)update("user.updateUserPhoto", map);
	}
}
