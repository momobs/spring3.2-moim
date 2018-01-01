package moim.common.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import moim.common.common.CommandMap;
import moim.common.service.CommonService;
import moim.common.util.LogUtils;
import moim.common.util.MessageUtils;
import moim.common.util.PathUtils;
import moim.user.service.UserService;
import moim.user.vo.UserVO;

@SuppressWarnings("unchecked")
@Controller
public class CommonController{
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/test")
	public void test(HttpServletRequest request) throws Exception{
	}

	@RequestMapping(value="/init")
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirectAttributes, CommandMap comMap) throws Exception{
		ModelAndView mv = new ModelAndView(PathUtils.getPath("MAIN"));		
		Map<String,Object> map = comMap.getMap();
		LogUtils.logDebugMap(log, map);
		//Test request.getSession().setAttribute("login", getLogin());
		
		mv.addObject("overview", (Map<String,Object>)commonService.selectOverview());
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		if (user != null) {
			session.setAttribute("groupList", (List<Map<String,Object>>)commonService.selectGroupList(user));
		}
		log.info(mv.getView());
		log.info(mv.getViewName());
		return mv;
	}
	
	
	// Login
	@RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView login(RedirectAttributes redirectAttributes, HttpServletRequest request, CommandMap comMap){
		ModelAndView mv = new ModelAndView(PathUtils.getPath("LOGIN"));
		
    	HttpSession session = request.getSession(); 
    	Map<String,Object> param = comMap.getMap();
    	Map<String,Object> login = commonService.getLoginInfo(param);
    	
    	if (login!=null ) {
    		if(passwordEncoder.matches((String)param.get("USER_PWD"), (String)login.get("USER_PWD"))) {
    			session.setAttribute("LOGIN", login);
    			mv.setViewName("redirect:/");
    		} else {
    			mv.addObject("MESSAGE", MessageUtils.getMessage("login.notmatch"));
    		}
    	} else {
    		mv.addObject("MESSAGE", MessageUtils.getMessage("login.notfound"));
    	}
    	
    	return mv;
	}

	// Logout
	@RequestMapping(value="/logout/auth")
    public ModelAndView logout(HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	request.getSession().invalidate();
    	return mv;
	}
	
	
	@RequestMapping(value="/call/{page}")
	public ModelAndView pageCall(HttpServletRequest request, @PathVariable String page, CommandMap commandMap){
		ModelAndView mv = new ModelAndView(PathUtils.getPath(page.toUpperCase()));
		
		log.info("Request Page = '"+mv.getViewName()+"'");
			
		Map<String, Object> inputFlashMap = (Map<String,Object>) RequestContextUtils.getInputFlashMap(request);
		if(inputFlashMap!=null && inputFlashMap.isEmpty()==false) {
			Iterator<Entry<String,Object>> iterator = inputFlashMap.entrySet().iterator();
			Entry<String,Object> entry = null;
			while(iterator.hasNext()) {
				entry = iterator.next();
				log.debug("ADD PARAMETER '"+entry.getKey()+"' : "+entry.getValue());
				mv.addObject(entry.getKey(), entry.getValue());
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/call/{page}/auth")
	public ModelAndView pageAuthCall(HttpServletRequest request, @PathVariable String page, CommandMap commandMap){
		ModelAndView mv = new ModelAndView(PathUtils.getPath("AUTH_"+page.toUpperCase()));		
		log.info("Request Page = '"+mv.getViewName()+"'");			
		Map<String, Object> inputFlashMap = (Map<String,Object>) RequestContextUtils.getInputFlashMap(request);
		if(inputFlashMap!=null && inputFlashMap.isEmpty()==false) {
			Iterator<Entry<String,Object>> iterator = inputFlashMap.entrySet().iterator();
			Entry<String,Object> entry = null;
			while(iterator.hasNext()) {
				entry = iterator.next();
				log.debug("ADD PARAMETER '"+entry.getKey()+"' : "+entry.getValue());
				mv.addObject(entry.getKey(), entry.getValue());
			}
		}
		return mv;
	}
	
	
	/*
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
		Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
		String storedFileName = (String)map.get("STORED_FILE_NAME");
		String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("D:\\Dev Files\\file\\"+storedFileName));
		
		response.setContentType("aaplication/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	*/
	
}
