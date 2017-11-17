package moim.user.interceptor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor  extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
		HashMap<String, Object> user = (HashMap<String,Object>) request.getSession().getAttribute("user");
		
		if (user==null) {
			log.debug("return fals2e");
			response.sendRedirect(request.getContextPath()+"/user/login.do");
			return false;
		}
		
		return true;
		/*
		@SuppressWarnings("unchecked")
		HashMap<String,Object> user = (HashMap<String,Object>) request.getSession().getAttribute("user");
		String userId = "";
		try {
			if (userId==null||userId.equals("")) {
				response.sendRedirect("/");;
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		*/
	}
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
 
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
