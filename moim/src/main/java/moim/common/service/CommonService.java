package moim.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {
	List<Map<String, Object>> selectGroupList(Map<String, Object> map) throws Exception;
	Map<String,Object> selectOverview() throws Exception;
	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
}
