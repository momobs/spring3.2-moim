package moim.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	private String reqURI = null;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
			reqURI = request.getRequestURI().toString();
			if (!reqURI.startsWith(request.getContextPath()+"/resources/") && log.isDebugEnabled()) {
				log.debug("= = = = = = = = = START = = = = = = = = = = ");
				log.debug("Request URI \t:    " + request.getRequestURI());			 	
			}
			
			return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws  Exception {
		if (!reqURI.startsWith(request.getContextPath()+"/resources/") && log.isDebugEnabled()) {
			log.debug("= = = = = = = = = E N D = = = = = = = = = = \n");
		}
	}
}