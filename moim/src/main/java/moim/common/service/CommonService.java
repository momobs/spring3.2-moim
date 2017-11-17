package moim.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	Map<String, Object> selectUser(Map<String, Object> map) throws Exception;
	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
}
