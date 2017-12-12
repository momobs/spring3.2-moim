package moim.user.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import moim.common.util.MessageUtils;
import moim.user.vo.UserVO;

public class UserInterceptor  extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
		UserVO loginUser = (UserVO) request.getSession().getAttribute("user");
		FlashMap flashMap = new FlashMap();
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		
		if (loginUser==null || loginUser.getUser_id().equals("")) {
			flashMap.put("msg", MessageUtils.getMessage("login.invalidSession"));			
			flashMapManager.saveOutputFlashMap(flashMap, request, response);
			response.sendRedirect(request.getContextPath()+"/common/call/login.do");
			return false;
		} else {
			Map<String, Object> inputFlashMap = (Map<String, Object>) RequestContextUtils.getInputFlashMap(request);
			
			if(inputFlashMap!=null && inputFlashMap.isEmpty()==false) {
				Iterator<Entry<String,Object>> iterator = inputFlashMap.entrySet().iterator();
				Entry<String,Object> entry =null;
				while(iterator.hasNext()) {
					entry = iterator.next();
					flashMap.put(entry.getKey(), entry.getValue());
				}
				flashMapManager.saveOutputFlashMap(flashMap, request, response);
			}
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
