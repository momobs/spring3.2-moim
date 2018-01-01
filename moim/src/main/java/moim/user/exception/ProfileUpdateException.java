package moim.user.exception;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.servlet.ModelAndView;

import moim.common.util.PathUtils;

public class ProfileUpdateException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private ModelAndView mv = null;	
	
	public ProfileUpdateException(String message) {
		mv = new ModelAndView(PathUtils.AUTH_PROFILE);
		mv.addObject("MESSAGE", message);
		
	}
	
	public ProfileUpdateException(String message, Map<String,Object> map) {
		mv = new ModelAndView(PathUtils.AUTH_PROFILE);
		mv.addObject("MESSAGE", message);
		
		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String,Object> entry = iterator.next();
			mv.addObject(entry.getKey(), entry.getValue());
		}
		
	} 
	
}
