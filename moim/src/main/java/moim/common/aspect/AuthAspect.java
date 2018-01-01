package moim.common.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;

@SuppressWarnings("unchecked")
@Aspect
public class AuthAspect {
	protected Logger log = Logger.getLogger(this.getClass());
	

	/*
	@Around("execution(* moim..controller.*Controller.*Auth*(..))")
	public Object sessionUpdate(ProceedingJoinPoint joinPoint) throws Throwable{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		log.info("123123");
		
		Map<String,Object> login = (Map<String,Object>) session.getAttribute("LOGIN");
		
		if (login==null || ((String)login.get("USER_ID")).isEmpty()) {
			ModelAndView mv = new ModelAndView(PathUtils.LOGIN);
			mv.addObject("MESSAGE", MessageUtils.getMessage("login.expired"));			
			throw new MessageException(mv);			
		} else {
			login = commonSerivce.getLoginInfo(login);
			log.info("'LOGIN' Session Updated: "+request.getRequestedSessionId());
		}
		
		return joinPoint.proceed();
	}*/
}
