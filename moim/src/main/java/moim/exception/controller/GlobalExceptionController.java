package moim.exception.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import moim.common.util.MessageUtils;
import moim.exception.custom.CustomException;
import moim.exception.custom.MessageException;

@ControllerAdvice
public class GlobalExceptionController{
	Logger log = Logger.getLogger(this.getClass());
	private static final String default_error_page = "/common/error-runtime";

	@ExceptionHandler({Exception.class})
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
		ModelAndView mv = new ModelAndView(default_error_page);
		
		Map<String,Object> exception = new HashMap<String,Object>();
		exception.put("MESSAGE", e.getMessage());
		exception.put("STACK_TRACE", e.getStackTrace());
		
		mv.addObject("STATUS_CODE", "ERROR");
		mv.addObject("MESSAGE", MessageUtils.getMessage("exception.common"));
		mv.addObject("EXCEPTION", exception); 
		
		return mv;
	}
	
	
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public ModelAndView sqlExceptionHandler(HttpServletRequest request, Exception e) throws Exception{
		ModelAndView mv = new ModelAndView(default_error_page);
		
		Map<String,Object> exception = new HashMap<String,Object>();
		exception.put("MESSAGE", e.getMessage());
		exception.put("STACK_TRACE", e.getStackTrace());
		
		mv.addObject("STATUS_CODE", "ERROR");
		mv.addObject("MESSAGE", MessageUtils.getMessage("exception.common"));
		mv.addObject("EXCEPTION", exception);
		
		return mv;
	}
	
	@ExceptionHandler({HttpClientErrorException.class})
	public ModelAndView httpExce1ptionHandler(HttpServletRequest request, HttpClientErrorException e) throws Exception{
		ModelAndView mv = new ModelAndView(default_error_page);
		
		Map<String,Object> exception = new HashMap<String,Object>();
		exception.put("MESSAGE", e.getMessage());
		exception.put("STACK_TRACE", e.getStackTrace());
		
		mv.addObject("STATUS_CODE", "ERROR");
		mv.addObject("MESSAGE", MessageUtils.getMessage("exception.common"));
		mv.addObject("EXCEPTION", exception);
		
		return mv;
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView runtimeExceptionHandler(HttpServletRequest request, MessageException e) throws Exception{
		log.info("Redirect To: "+e.getMv().getViewName());
		return e.getMv();
	}
	
	@ExceptionHandler({MessageException.class})
	public ModelAndView sessionExceptionHandler(HttpServletRequest request, MessageException e) throws Exception{
		log.info("Redirect To: "+e.getMv().getViewName());
		return e.getMv();
	}
	
	@ExceptionHandler({CustomException.class})
	public ModelAndView customExceptionHandler(HttpServletRequest request, CustomException e) throws Exception{
		log.info("Redirect To: "+e.getMv().getViewName());
		return e.getMv();
	}
/*
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
*/	
}
