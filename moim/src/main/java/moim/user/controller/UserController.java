package moim.user.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.common.common.CommandMap;
import moim.user.service.UserService;

@Controller
public class UserController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping(value="/user/login.do")
    public ModelAndView login(HttpServletRequest request,  CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/login");
    	String inputId = request.getParameter("username");
    	String inputPwd = request.getParameter("password");
    	commandMap.put("USER_ID", inputId);
    	
    	if ( inputId!=null ) {
    		Map<String,Object> user = userService.selectUser(commandMap.getMap());
    		if (user!=null && user.get("USER_PWD").equals(inputPwd)) {
    			request.getSession().setAttribute("user", user);
    			mv.setViewName("redirect:/");
    			mv.addObject("msg", "");
    		} else {
        		mv.addObject("msg", "ID/PASSWORD를 확인하세요");
        	} 
    	} 
    	return mv;
	}
	
	@RequestMapping(value="/user/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	request.getSession().invalidate();
    	
    	return mv;
	}
	
	@RequestMapping(value="/user/joinus.do")
    public ModelAndView joinus(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	
    	commandMap.put("userId", request.getParameter("username"));
    	commandMap.put("userPwd", request.getParameter("password"));
    	commandMap.put("userName", request.getParameter("fullname"));
    	commandMap.put("userEmail", request.getParameter("email"));
    	commandMap.put("userAddr", request.getParameter("address"));
    	
    	log.debug(commandMap.get("userId")+"/"+commandMap.get("userPwd"));
    	
    	return mv;
	}
	
	@RequestMapping(value="/user/myprofile.do")
    public ModelAndView myprofile(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/myprofile");
    	
    	return mv;
	}
}
