package moim.exception.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.common.util.MessageUtils;

@Controller
public class HttpExceptionController{
	Logger log = Logger.getLogger(this.getClass());	

	@RequestMapping(value="/http/{error_code}")
	public ModelAndView error(HttpServletRequest request, @PathVariable String error_code) {
		ModelAndView mv = new ModelAndView("/common/error-http");		
		String message = MessageUtils.getMessage("exception.http."+error_code);
		mv.addObject("status_code", error_code);
		mv.addObject("message", message);
		return mv;
	}	
}
