package moim.common.resolver;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import moim.common.common.CommandMap;
import moim.common.util.LogUtils;

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver{
	Logger log = Logger.getLogger(this.getClass());
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CommandMap.class.isAssignableFrom(parameter.getParameterType());
    }
 
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
    	CommandMap commandMap = new CommandMap();
        
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Enumeration<?> enumeration = request.getParameterNames();
        Map<String,Object> login = (Map<String,Object>) request.getSession().getAttribute("LOGIN"); 
        commandMap.put("LOGIN", login);
        //LogUtils.logInfoMap(log, login);
        
        /*
        Map<String,Object> login = Map<String,Object>request.getSession().getAttribute("LOGIN");
        if (login != null) {
        	
        }
        */
        
        String key = null;
        String[] values = null;
        while(enumeration.hasMoreElements()){
            key = (String) enumeration.nextElement();
            values = request.getParameterValues(key);
            if(values != null){
                commandMap.put(key, (values.length > 1) ? values:values[0] );
            }
        }
        return commandMap;
    }
}
