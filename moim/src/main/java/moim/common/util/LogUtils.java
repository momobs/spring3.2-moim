package moim.common.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class LogUtils {
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void logInfoMap(Logger log, Map<String, Object> map) {
    	if (!map.isEmpty()) {
    		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		while(iterator.hasNext()) {
    			entry = iterator.next();
    			log.info("key : "+entry.getKey()+", value : "+entry.getValue());
    		}
    	}	
	}
	
	public static void logDebugMap(Logger log, Map<String, Object> map) {
    	if(!map.isEmpty()) {
    		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		while(iterator.hasNext()) {
    			entry = iterator.next();
    			log.debug("key : "+entry.getKey()+", value : "+entry.getValue());
    		}
    	}	
	}
	
	public static void logErrorMap(Logger log, Map<String, Object> map) {
    	if(!map.isEmpty()) {
    		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		while(iterator.hasNext()) {
    			entry = iterator.next();
    			log.error("key : "+entry.getKey()+", value : "+entry.getValue());
    		}
    	}	
	}
}
