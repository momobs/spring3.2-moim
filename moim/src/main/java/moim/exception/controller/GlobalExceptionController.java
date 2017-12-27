package moim.exception.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import moim.common.util.CommonUtils;
import moim.common.util.LogUtils;
import moim.common.util.MessageUtils;

@ControllerAdvice
public class GlobalExceptionController{
	Logger log = Logger.getLogger(this.getClass());
	public static final String default_error_page = "/common/error";

	@ExceptionHandler({Exception.class})
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
		ModelAndView mv = new ModelAndView(default_error_page);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("request_uri", request.getRequestURL());
		map.put("stack_message", e.getMessage());
		map.put("stack_trace", e.getStackTrace());
		map.put("message", MessageUtils.getMessage("exception.runtime.common"));
		mv.addObject("exception", map);
		
		//logging
		log.error(MessageUtils.getMessage("exception.log.start"));
		LogUtils.logInfoMap(log, map);
		log.error(MessageUtils.getMessage("exception.log.end"));
		
		return mv;
	}

	
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public ModelAndView sqlExceptionHandler(HttpServletRequest request, Exception e) throws Exception{
		ModelAndView mv = new ModelAndView(default_error_page);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("request_uri", request.getRequestURL());
		map.put("stack_message", e.getMessage());
		map.put("stack_trace", e.getStackTrace());
		map.put("message", MessageUtils.getMessage("exception.runtime.common"));
		mv.addObject("exception", map);
		
		//logging
		log.error(MessageUtils.getMessage("exception.log.start"));
		LogUtils.logInfoMap(log, map);
		log.error(MessageUtils.getMessage("exception.log.end"));
				
		return mv;
	}
	
	@ExceptionHandler({HTTPException.class})
	public ModelAndView sqlExce1ptionHandler(HttpServletRequest request, Exception e) throws Exception{
		ModelAndView mv = new ModelAndView();
		log.debug("ADFADFASDFSDFADS");
		return mv;
	}
	
	
	  
	
	
}
