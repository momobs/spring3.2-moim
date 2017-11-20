package moim.user.dao;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import moim.common.dao.AbstractDAO;
import moim.user.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public UserVO selectUser(UserVO user) throws Exception{
		return (UserVO)selectOne("user.selectUser", user);
	}
	
	
	public void insertUser(UserVO user) throws Exception{
		insert("user.insertUser", user);
	}
}
