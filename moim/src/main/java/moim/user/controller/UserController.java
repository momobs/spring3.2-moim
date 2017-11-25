package moim.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.common.common.CommandMap;
import moim.user.service.UserService;
import moim.user.vo.UserVO;

@Controller
public class UserController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping(value="/login.do")
    public ModelAndView login(HttpServletRequest request, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/login");
    	HttpSession session = request.getSession();
    	String rtnMsg = (String) session.getAttribute("msg");
    	
    	if ( user.getUser_id()!=null ) {
    		user = userService.selectUser(user);
    		if (user!=null) {
    			request.getSession().setAttribute("user", user);
    			mv.setViewName("redirect:/");
    			rtnMsg = null;
    		} else {
        		rtnMsg = "유효하지 않은 계정입니다.";
        	} 
    	} 
    	mv.addObject("msg", rtnMsg);
    	return mv;
	}
	
	@RequestMapping(value="/joinus.do")
    public ModelAndView joinus(HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/login.do");

    	userService.insertUser(user);
    	
    	request.getSession().setAttribute("user", user);
    	
    	return mv;
	}
	
	
	@RequestMapping(value="/user/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	request.getSession().invalidate();
    	
    	return mv;
	}
	

	
	@RequestMapping(value="/user/myprofile.do")
    public ModelAndView myprofile(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/myprofile");
    	
    	return mv;
	}
}
