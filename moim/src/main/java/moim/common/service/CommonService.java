package moim.common.service;

import java.util.List;
import java.util.Map;

import moim.user.vo.UserVO;

public interface CommonService {
	Map<String,Object> getLoginInfo(Map<String,Object> map);
	
	List<Map<String, Object>> selectGroupList(UserVO user) throws Exception;
	
	Map<String,Object> selectOverview() throws Exception;	
	
}
