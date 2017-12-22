package moim.exception.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.common.util.MessageUtils;

@Controller
public class ExceptionController{
	Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value="/exception/test.do")
	public ModelAndView test(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/common/test");
		String a = null;
		log.debug(a.length());
		log.debug("error.common: "+MessageUtils.getMessage("error.common"));
		log.debug("error.minlength: "+MessageUtils.getMessage("error.minlength", new String[] {"테스트글자", "2"}));

		return mv;
	}	
	
	@RequestMapping(value="/exception/{error_code}.do")
	public ModelAndView error(HttpServletRequest request, @PathVariable String error_code) {
		ModelAndView mv = new ModelAndView("/common/error");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
		map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
		map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
		map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
		map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));
		
		try {
			map.put("MESSAGE", MessageUtils.getMessage("error."+error_code));
		} catch (Exception e) {
			map.put("MESSAGE", (String) request.getAttribute("javax.servlet.error.message"));
		}
		
		mv.addObject("error", map);
		
		//logging
    	if(map.isEmpty() == false ) {
    		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		while(iterator.hasNext()) {
    			entry = iterator.next();
    			log.info("key : "+entry.getKey()+", value : "+entry.getValue());
    		}
    	}
		
		return mv;
	}
}
