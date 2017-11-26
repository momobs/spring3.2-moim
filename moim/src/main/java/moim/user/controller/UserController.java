package moim.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value="/login.do")
    public ModelAndView login(HttpServletRequest request, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("");
    	HttpSession session = request.getSession();
    	String rawPwd = user.getUser_pwd();
    	String msg = null;
    	
    	if (session.getAttribute("user")==null) {
    		user = userService.selectUser(user);
    		if(user!=null && !passwordEncoder.matches(rawPwd, user.getUser_pwd())) {
    			msg = "유효하지 않은 계정입니다.";
    		}
    	} else {
    		user = (UserVO) session.getAttribute("user");
    	}
    	
    	if (user!=null&& msg==null) {
    		mv.setViewName("redirect:/");
    	} else {
    		mv.setViewName("/user/login");
    	}
    	
    	session.setAttribute("user", user);
    	mv.addObject("msg", msg);
    	
    	return mv;
	}
	
	@RequestMapping(value="/joinus.do")
    public ModelAndView joinus(HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/login.do");
    	
    	if (user.getUser_pwd()!=null) {
    		user.setUser_pwd(passwordEncoder.encode(user.getUser_pwd()));
    	}
    	
   		userService.insertUser(user);
   		if (user.getResult()==true) {
   			request.getSession().setAttribute("user", user);
   		} else {
   			mv.setViewName("redirect:/common/errorSQL.do");
   		}
    	
    	return mv;
	}
	
	@RequestMapping(value="/selectId.do")
    public ModelAndView selectid(HttpServletRequest request, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");

    	user = userService.selectUser(user);
    	
    	if ( user!= null) {
    		mv.addObject("result", "false");
    	} else {
    		mv.addObject("result", "true");
    	}
    	
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
