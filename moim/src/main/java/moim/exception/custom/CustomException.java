package moim.exception.custom;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.servlet.ModelAndView;

public class CustomException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private ModelAndView mv;
	
	public CustomException(ModelAndView mv) {
		this.mv = mv;
	}
	
	public CustomException(String path, String message, Map<String,Object> map) {
		Entry<String,Object> entry = null;
		mv = new ModelAndView(path);
		mv.addObject("MESSAGE", message);
		
		if (map!=null) {
			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				entry = iterator.next();
				mv.addObject(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public ModelAndView getMv() {
		return this.mv;
	}
	
	public void setMv(ModelAndView mv) {
		this.mv = mv;
	}
	
	public String getMessage() {
		return (String) mv.getModel().get("MESSAGE");
	}
	
	public void setMessage(String message) {
		mv.addObject("MESSAGE", message);
	}
}
