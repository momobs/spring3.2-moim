package moim.exception.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.common.util.CommonUtils;
import moim.common.util.LogUtils;
import moim.common.util.MessageUtils;

@Controller
public class HttpExceptionController{
	Logger log = Logger.getLogger(this.getClass());	

	@RequestMapping(value="/exception/{type}/{error_code}.do")
	public ModelAndView error(HttpServletRequest request, @PathVariable String type, @PathVariable String error_code) {
		ModelAndView mv = new ModelAndView("/common/error");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("status_code", request.getAttribute("javax.servlet.error.status_code"));
		map.put("request_uri", request.getAttribute("javax.servlet.error.request_uri"));
		map.put("exception_type", request.getAttribute("javax.servlet.error.exception_type"));
		map.put("exception", request.getAttribute("javax.servlet.error.exception"));
		map.put("servlet_name", request.getAttribute("javax.servlet.error.servlet_name"));
		try {
			map.put("message", MessageUtils.getMessage("exception."+type+"."+error_code));
		} catch (Exception e) {
			map.put("message", (String) request.getAttribute("javax.servlet.error.message"));
		}
		
		mv.addObject("exception", map);
		
		//logging
		log.error(MessageUtils.getMessage("exception.log.start"));
		LogUtils.logInfoMap(log, map);
		log.error(MessageUtils.getMessage("exception.log.end"));
		
		return mv;
	}	
}
