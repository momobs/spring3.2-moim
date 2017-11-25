package moim.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import moim.user.vo.UserVO;

public class UserInterceptor  extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
		HttpSession session = request.getSession();
		UserVO user = (UserVO) request.getSession().getAttribute("user");
		
		if (user==null || user.getUser_id().equals("")) {
			log.debug("Invalid session.");
			session.setAttribute("msg", "로그인이 필요한 기능입니다.");
			response.sendRedirect(request.getContextPath()+"/login.do");
			return false;
		}
		
		return true;
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
