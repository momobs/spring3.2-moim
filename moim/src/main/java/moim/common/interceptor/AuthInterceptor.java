package moim.common.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import moim.common.service.CommonService;
import moim.common.util.MessageUtils;
import moim.common.util.PathUtils;
import moim.exception.custom.MessageException;

@SuppressWarnings("unchecked")
public class AuthInterceptor  extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonSerivce;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		HttpSession session = request.getSession();
		Map<String,Object> login = (Map<String,Object>) session.getAttribute("LOGIN");
		
		if (login==null || ((String)login.get("USER_ID")).isEmpty()) {
			ModelAndView mv = new ModelAndView(PathUtils.LOGIN);
			mv.addObject("MESSAGE", MessageUtils.getMessage("login.expired"));			
			throw new MessageException(mv);			
		} else {
			login = commonSerivce.getLoginInfo(login);
			session.setAttribute("LOGIN", login);
			log.info("'LOGIN' Session Updated: "+request.getRequestedSessionId());
		}
		return true;
	}
	
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		Map<String,Object> login = (Map<String,Object>) session.getAttribute("LOGIN");
		if (login != null) {
			login = commonSerivce.getLoginInfo(login);
			session.setAttribute("LOGIN", login);
			log.info("'LOGIN' Session Updated: "+request.getRequestedSessionId());
		}
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
