package moim.common.service;

import java.util.List;
import java.util.Map;

import moim.user.vo.UserVO;

public interface CommonService {
	List<Map<String, Object>> selectGroupList(UserVO user) throws Exception;
	Map<String,Object> selectOverview() throws Exception;
	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
}
