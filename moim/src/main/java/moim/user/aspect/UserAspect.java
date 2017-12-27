package moim.user.aspect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import moim.user.service.UserService;
import moim.user.vo.LoginVO;

@Aspect
public class UserAspect {
	protected Log log = LogFactory.getLog(UserAspect.class);
	static String name = "";
	static String type = "";
	
	@Resource(name="userService")
	private UserService userService;
	
	@Around("execution(* moim..controller.*Controller.*(..))")
	public Object userUpdate(ProceedingJoinPoint joinPoint) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		
		type = joinPoint.getSignature().getDeclaringTypeName();
		if (type.indexOf("Controller") > -1) {name = "Controller    \t" ;}
		
		if (login != null && login.isUpdate()) {
			session.setAttribute("login", userService.selectLoginInfo(login.getUser_id()));
			log.debug(name + type + "." + joinPoint.getSignature().getName() + "(): Session Update.");
		}
		
		return joinPoint.proceed();
	}
}
