package moim.user.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import moim.common.util.LogUtils;
import moim.common.util.MessageUtils;
import moim.common.vo.CommandMap;
import moim.user.service.UserService;
import moim.user.vo.LoginVO;
import moim.user.vo.UserVO;

@Controller
public class UserController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	
	// 로그인
	@RequestMapping(value="/user/login.do")
    public ModelAndView login(RedirectAttributes redirectAttributes, HttpServletRequest request, LoginVO login) throws Exception{
		ModelAndView mv = new ModelAndView();
    	HttpSession session = request.getSession(); 
    	String inputPwd = login.getUser_pwd();
    	String path = null;
    	
    	login = userService.selectLoginInfo(login.getUser_id());
    	
    	if (login!=null && passwordEncoder.matches(inputPwd, login.getUser_pwd())) {
    		session.setAttribute("login", login);
    		path = "redirect:/";
    	} else {
    		redirectAttributes.addFlashAttribute("message", MessageUtils.getMessage("login.notmatch"));
    		path = "redirect:/common/call/login.do";
    	}
    	mv.setViewName(path);
    	return mv;
	}
	
	// 로그아웃	
	@RequestMapping(value="/user/auth/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	request.getSession().invalidate();
    	
    	return mv;
	}
	
	// 회원정보: 회원가입
	@RequestMapping(value="/user/joinus.do")
    public ModelAndView joinus(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView();    	
    	user.setUser_pwd(passwordEncoder.encode(user.getUser_pwd()));
    	
    	Map<String, Object> map = userService.insertUser(request, user);

    	if (map.get("success").equals("ok")) {
    		request.getSession().setAttribute("login", map.get("login"));
    		mv.setViewName("redirect:/");
    	} else {
    		redirectAttributes.addFlashAttribute("inputData", user);
    		redirectAttributes.addFlashAttribute("message", map.get("message"));
    		
    		mv.setViewName("redirect:/common/call/joinus.do");
    	}
		
    	return mv;
	}

	// 회원정보: 기본정보 호출
	@RequestMapping(value="/user/auth/getProfile.do")
    public ModelAndView getProfile(RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/common/call/profile.do");
		redirectAttributes.addFlashAttribute("active", "tab_1_1");
		((LoginVO) request.getSession().getAttribute("login")).setUpdate(false); // (Aspect) 중복 세션 업데이트 방지

    	return mv;
	}
		
	// 회원정보: 기본정보 변경 
	@RequestMapping(value="/user/auth/setProfile.do")
    public ModelAndView setProfile(RedirectAttributes redirectAttributes, HttpServletRequest request, UserVO user) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/common/call/profile.do");
		redirectAttributes.addFlashAttribute("active",  (String)"tab_1_1");
		redirectAttributes.addFlashAttribute("tab_1_1", userService.updateUser(request, user));
    	
    	return mv;
	}

	// 회원정보: 비밀번호 변경
	@RequestMapping(value="/user/auth/setPassword.do")
	//
    public ModelAndView setPassword(RedirectAttributes redirectAttributes, HttpServletRequest request, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/common/call/profile.do");
    	LogUtils.logDebugMap(log, commandMap.getMap());
    	
    	LoginVO login = (LoginVO) commandMap.getMap().get("login");
    	String curPwd = (String) commandMap.getMap().get("current_password");
    	String newPwd = (String) commandMap.getMap().get("new_password");
    	String rePwd = (String) commandMap.getMap().get("re_password");

    	if (passwordEncoder.matches(curPwd, login.getUser_pwd()) && newPwd.equals(rePwd)) {
    		commandMap.put("new_password", passwordEncoder.encode(newPwd));
    		redirectAttributes.addFlashAttribute("tab_1_3",  userService.updateUserPwd(commandMap.getMap()));	
    	} else {
    		commandMap.clear();
    		commandMap.put("success", false);
    		commandMap.put("message", MessageUtils.getMessage("password.failure"));
    		redirectAttributes.addFlashAttribute("tab_1_3", commandMap.getMap());
    	}	
    	redirectAttributes.addFlashAttribute("active",  (String)"tab_1_3");
    	
    	return mv;
	}	
	
	// 회원정보: 아이디 검색
	@RequestMapping(value="/user/selectId.do")
    public ModelAndView selectId(HttpServletRequest request, LoginVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");

    	log.debug(user.getUser_id());
    	String user_id = userService.selectUserId(user.getUser_id());  
    	
    	if ( user_id!=null && user_id.length()>0 ) {
    		mv.addObject("success", "no");
    	} else {
    		mv.addObject("success", "ok");
    	}
    	
    	return mv;
	}





	// 프로필: 사진 업로드
	@RequestMapping(value="/user/auth/insertUserPhoto.do")
    public ModelAndView insertUserPhoto(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/user/auth/getProfile.do");
		
    	Map<String,Object> map = userService.insertUserPhoto(request);
    	redirectAttributes.addFlashAttribute("active", (String)"tab_1_2");
   		redirectAttributes.addFlashAttribute("message", (String)map.get("message"));
    	
    	return mv;
	}


	

}
;