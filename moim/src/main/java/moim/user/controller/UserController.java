package moim.user.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import moim.common.common.CommandMap;
import moim.common.service.CommonService;
import moim.common.util.LogUtils;
import moim.common.util.MessageUtils;
import moim.common.util.PathUtils;
import moim.user.service.UserService;

@Controller
public class UserController{
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="commonService")
	private CommonService commonService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// User Registration
	@RequestMapping(value="/user/joinus")
    public ModelAndView joinus(RedirectAttributes redirectAttributes, HttpServletRequest request, CommandMap comMap) throws Exception{
		ModelAndView mv = new ModelAndView(PathUtils.JOINUS);
    	
    	Map<String,Object> param = comMap.getMap();
    	param.put("USER_PWD", passwordEncoder.encode((String)param.get("USER_PWD")));
    	
		userService.insertUserInfo(param);
		
		Map<String,Object> login = commonService.getLoginInfo(param);
		request.getSession().setAttribute("LOGIN", login);
    	
    	return mv;
	}

	// 프로필-기본정보 변경
	@RequestMapping(value="/user/updateUserInfo/auth")
    public ModelAndView updateUserInfo(HttpServletRequest request, CommandMap comMap){
		ModelAndView mv = new ModelAndView(PathUtils.AUTH_PROFILE);
		
		Map<String,Object> map = comMap.getMap();
		
		map =userService.updateUserInfo(map);
		
		mv.addObject("ACTIVE", "TAB1");
		mv.addObject("TAB1_MESSAGE", map.get("MESSAGE"));
		
    	return mv;
	}

	// 프로필-비멀번호 변경
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/user/updateUserPassword/auth")
    public ModelAndView updateUserPassword(RedirectAttributes redirectAttributes, CommandMap comMap) throws Exception{
    	ModelAndView mv = new ModelAndView(PathUtils.AUTH_PROFILE);
    	Map<String,Object> map = comMap.getMap();
    	Map<String,Object> login = (Map<String,Object>) map.get("LOGIN");
    	
    	String oriPassword = (String) login.get("USER_PWD");
    	String curPassword = (String) map.get("CURRENT_PASSWORD");

    	log.info("input: "+oriPassword+", "+curPassword);
    	if (passwordEncoder.matches(curPassword, oriPassword)) {
    		log.info("matched");
    		map.put("ENC_PASSWORD", passwordEncoder.encode((String) map.get("NEW_PASSWORD")));
    		map = userService.updateUserPwd(map); 
    	} else {
    		log.info("not matched");
    		map.put("MESSAGE", MessageUtils.getMessage("profile.tab3.notauth"));
    	}
    	
    	mv.addObject("ACTIVE", "TAB3");
    	mv.addObject("TAB3_MESSAGE", map.get("MESSAGE"));
    	return mv;
	}	
	
	// 회원정보: 아이디 검색
	@RequestMapping(value="/user/selectId.do")
    public ModelAndView selectId(HttpServletRequest request, CommandMap comMap) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	Map<String,Object> param = comMap.getMap();

    	String user_id = userService.selectUserId(param);  
    	
    	if ( user_id!=null && user_id.length()>0 ) {
    		mv.addObject("success", "no");
    	} else {
    		mv.addObject("success", "ok");
    	}
    	
    	return mv;
	}

	// 회원정보: 프로필 사진 전송
	@RequestMapping(value="/user/auth/insertUserPhoto.do")
    public ModelAndView insertUserPhoto(RedirectAttributes redirectAttributes, HttpServletRequest request, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/user/auth/getProfile.do");
		
    	Map<String,Object> map = userService.insertUserPhoto(request, commandMap.getMap());
    	redirectAttributes.addFlashAttribute("active", (String)"tab_1_2");
   		redirectAttributes.addFlashAttribute("message", (String)map.get("message"));
    	
    	return mv;
	}


	

}
;