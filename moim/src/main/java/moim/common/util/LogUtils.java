package moim.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.log4j.Logger;

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
