package moim.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import moim.user.vo.UserVO;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectGroupList(UserVO user) throws Exception{
		return (List<Map<String,Object>>)selectList("common.selectGroupList", user);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectOverview() throws Exception{
		return (Map<String,Object>) selectOne("common.selectOverview");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectFileInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("common.selectFileInfo", map);
	}
}
