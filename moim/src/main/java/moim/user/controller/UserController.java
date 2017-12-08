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
import moim.common.util.MessageUtils;
import moim.user.service.UserService;
import moim.user.vo.UserVO;

@Controller
public class UserController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value="/user/login.do")
    public ModelAndView login(HttpServletRequest request, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("");
    	HttpSession session = request.getSession();
    	String rawPwd = user.getUser_pwd();
    	String msg = null;

    	// Login Session이 존재하지 않고 입력된 PWD가 있을때 (Login 수행)
    	if (rawPwd!=null && session.getAttribute("user")==null) {
    		user = userService.selectUser(user);
    		// 조회된 User가 없거나 패스워드가 일치하지 않을 때
    		if(user==null||!passwordEncoder.matches(rawPwd, user.getUser_pwd())){
    			msg = MessageUtils.getMessage("login.notfound");
    		}
    	// Login Session이 존재할 때(회원가입 후 자동로그인)
    	} else {
    		user = (UserVO) session.getAttribute("user");
    	}
    	
    	if (user != null) {
    		mv.setViewName("redirect:/");
    		session.setAttribute("user", user);
    	} else {
    		mv.setViewName("/user/login");
    	}
    	
    	mv.addObject("msg", msg);
    	
    	return mv;
	}
	
	@RequestMapping(value="/user/joinus.do")
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
	
	@RequestMapping(value="/user/selectId.do")
    public ModelAndView selectid(HttpServletRequest request, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");

    	log.debug(user.getUser_id());
    	user = userService.selectUser(user);
    	
    	if ( user!= null) {
    		mv.addObject("result", "false");
    	} else {
    		mv.addObject("result", "true");
    	}
    	
    	return mv;
	}
	
	
	@RequestMapping(value="/user/auth/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	request.getSession().invalidate();
    	
    	return mv;
	}
	

	
	@RequestMapping(value="/user/auth/myprofile.do")
    public ModelAndView myprofile(HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/myprofile");
    	
    	return mv;
	}
	
	// 미완성
	@RequestMapping(value="/user/auth/updateInfo.do")
    public ModelAndView updateInfo(HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("/user/myprofile");
    	
    	return mv;
	}
	
	
	// 미완성
	@RequestMapping(value="/user/auth/insertUserPhoto.do")
    public ModelAndView insertUserPhoto(HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
		log.debug("123");
    	ModelAndView mv = new ModelAndView("/user/myprofile");
    	userService.insertUserPhoto(request, user);
    	return mv;
	}
}
