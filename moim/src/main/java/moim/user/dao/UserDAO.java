package moim.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import moim.common.dao.AbstractDAO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectFileInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("common.selectFileInfo", map);
	}
}
