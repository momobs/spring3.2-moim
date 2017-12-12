package moim.user.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.servlet.support.RequestContextUtils;

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
    public ModelAndView login(RedirectAttributes redirectAttributes, HttpServletRequest request, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView("");
    	HttpSession session = request.getSession();
    	UserVO loginUser = (UserVO) session.getAttribute("user");
    	String inputPwd = "";
    	
    	if (loginUser!=null) { user = loginUser; }
    	inputPwd = user.getUser_pwd();
    	
    	user = userService.selectUser(user);
    	
    	if (user!=null&&passwordEncoder.matches(inputPwd, user.getUser_pwd())) {
    		session.setAttribute("user", user);
    		mv.setViewName("redirect:/");
    	} else {
    		redirectAttributes.addFlashAttribute("msg", MessageUtils.getMessage("login.notmatch"));
    		mv.setViewName("redirect:/common/call/login.do");
    	}
    	
    	return mv;
	}
	
	@RequestMapping(value="/user/joinus.do")
    public ModelAndView joinus(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
    	ModelAndView mv = new ModelAndView();
    	String decPwd = user.getUser_pwd();
    	String encPwd = passwordEncoder.encode(user.getUser_pwd());
    	user.setUser_pwd(encPwd);
    	user = userService.insertUser(user);
    	
    	if (user.getResult()==true) {
    		user.setUser_pwd(decPwd);
    		request.getSession().setAttribute("user", user);
    		mv.setViewName("redirect:/user/login.do");
    	} else {
    		redirectAttributes.addFlashAttribute("inputData", user);
    		redirectAttributes.addFlashAttribute("msg", user.getMessage());
    		
    		mv.setViewName("redirect:/common/call/joinus.do");
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
	
	// 프로필: View 초기화
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/user/auth/getProfile.do")
    public ModelAndView getProfile(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/user/myprofile");
    	Map<String,Object> inputFlashMap = (Map<String,Object>) RequestContextUtils.getInputFlashMap(request);
    	UserVO loginUser = (UserVO)request.getSession().getAttribute("user");
    	request.getSession().setAttribute("user", userService.selectUser(loginUser));
    	
    	mv.addObject("active", "tab_1_1");
    	
    	if (inputFlashMap!=null && inputFlashMap.isEmpty()==false) {
    		Iterator<Entry<String,Object>> iterator = inputFlashMap.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		while(iterator.hasNext()) {
    			entry = iterator.next();
    			mv.addObject(entry.getKey(), entry.getValue());
    		}
    	} 

    	return mv;
	}
	

	// 프로필: 기본정보 변경 요청
	@RequestMapping(value="/user/auth/setProfile.do")
    public ModelAndView setProfile(RedirectAttributes redirectAttributes, HttpServletRequest request, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/user/auth/getProfile.do");
		redirectAttributes.addFlashAttribute("active",  (String)"tab_1_1");
		redirectAttributes.addFlashAttribute("tab_1_1", userService.updateUser(request, commandMap.getMap()));
    	
    	return mv;
	}

	// 미완성
	@RequestMapping(value="/user/auth/setProfilePhoto.do")
    public ModelAndView setUserPhoto(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, UserVO user) throws Exception{
		log.debug("123");
    	ModelAndView mv = new ModelAndView("/user/myprofile");
    	userService.insertUserPhoto(request, user);
    	return mv;
	}

	// 프로필: 비밀번호 변경 요청
	@RequestMapping(value="/user/auth/setPassword.do")
    public ModelAndView setPassword(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/user/auth/getProfile.do");
    	UserVO loginUser = (UserVO) request.getSession().getAttribute("user");
    	String curPwd = (String) commandMap.get("current_password");
    	String newPwd = (String) commandMap.get("new_password");
    	String rePwd = (String) commandMap.get("re_password");
    	
    	if (passwordEncoder.matches(curPwd, loginUser.getUser_pwd()) && newPwd.equals(rePwd)) {
    		commandMap.put("user_id", loginUser.getUser_id());
    		commandMap.put("new_password", passwordEncoder.encode((String)commandMap.get("new_password")));
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
	

}
;