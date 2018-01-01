package moim.exception.custom;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.servlet.ModelAndView;

public class MessageException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private ModelAndView mv;
	private Entry<String,Object> entry;
	
	public MessageException(String path) {
		super();
		mv = new ModelAndView(path);

	}
	
	public MessageException(String path, String message) {
		mv = new ModelAndView(path);
		mv.addObject("MESSAGE", message);
	}
	
	public MessageException(String path, String message, Map<String,Object> map) {
		mv = new ModelAndView(path);
		mv.addObject("MESSAGE", message);
		
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()) {
			entry = iterator.next();
			mv.addObject(entry.getKey(), entry.getValue());
		}
	}
	
	public MessageException(ModelAndView mv) {
		this.mv = mv;
	}
	
	public ModelAndView getMv() {
		return this.mv;
	}
	
	public void setMv(ModelAndView mv) {
		this.mv = mv;
	}

	
	/*
	public MessageException(String message) {
		super(message);
	}
	
	public MessageException(String message, String path) {
		super(message);
		this.path = path;
	}
	
	public MessageException(String message, String path, Map<String,Object> param) {
		super(message);
		this.path = path;
		this.param = param;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public Map<String,Object> getParam(){
		return this.param;
	}
	
	public void setParam(Map<String,Object> param) {
		this.param = param;
	}
	*/
}
