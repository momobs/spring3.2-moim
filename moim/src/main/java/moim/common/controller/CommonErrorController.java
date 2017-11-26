package moim.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class CommonErrorController{
	Logger log = Logger.getLogger(this.getClass());
	String errorPage = "/common/error";
/*
	@RequestMapping(value="/error404.do")
	public ModelAndView error404(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(errorPage);
		log.debug("An error has occurred(404)");
		logError(request);
		return mv;
	}
	
	private void logError(HttpServletRequest request) {
		log.info("REQUEST_URI: " + request.getAttribute("javax.servlet.error.request_uri"));
		log.info("STATUS_CODE: " + request.getAttribute("javax.servlet.error.status_code"));
		log.info("EXCEPTION_TYPE: " + request.getAttribute("javax.servlet.error.exception_type"));
		log.info("MESSAGE: " + request.getAttribute("javax.servlet.error.message"));
		log.info("EXCEPTION: " + request.getAttribute("javax.servlet.error.exception"));
		log.info("SERVLET_NAME: " + request.getAttribute("javax.servlet.error.servlet_name"));
	}
*/
}
