package moim.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.user.service.UserService;

@Controller
public class UserController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping(value="/user/login.do")
	public ModelAndView userLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/user/user_login");
		/*
		ModelAndView mv = new ModelAndView("/user/user_login");
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("password");
		
		userId = "atmoragi";
		userPwd = "1234";
		
		log.debug(userId +"/"+ userPwd);
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		request.getSession().setAttribute("user", map);
		*/
		return mv;
	}
}
