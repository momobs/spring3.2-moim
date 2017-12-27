package moim.common.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import moim.common.service.CommonService;
import moim.common.util.MessageUtils;
import moim.user.service.UserService;
import moim.user.vo.LoginVO;
import moim.user.vo.UserVO;

@Controller
public class CommonController{
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping(value="/common/test.do")
	public void test(HttpServletRequest request) throws Exception{

	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/common/init.do")
	public ModelAndView init(HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{		
		ModelAndView mv = new ModelAndView("/common/main");		
		
		//Test request.getSession().setAttribute("login", getLogin());
		
		mv.addObject("overview", (Map<String,Object>)commonService.selectOverview());
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("loginUser");
		if (user != null) {
			session.setAttribute("groupList", (List<Map<String,Object>>)commonService.selectGroupList(user));
		}
		return mv;
	}
	
	// 개발용 로그인
	private LoginVO getLogin() throws Exception {
		LoginVO login = new LoginVO();
		login.setUser_id("snkym45");
		login.setUser_pwd("123456ab");
		
		login = userService.selectLoginInfo(login.getUser_id());
		
		return login;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/common/call/{page}.do")
	public ModelAndView call(HttpServletRequest request, @PathVariable String page) throws Exception {
		ModelAndView mv = new ModelAndView(MessageUtils.getMessage("path."+page));
		
		LoginVO user = (LoginVO)request.getSession().getAttribute("login");
		
		Map<String, Object> inputFlashMap = (Map<String,Object>) RequestContextUtils.getInputFlashMap(request);
		if(inputFlashMap!=null && inputFlashMap.isEmpty()==false) {
			Iterator<Entry<String,Object>> iterator = inputFlashMap.entrySet().iterator();
			Entry<String,Object> entry = null;
			while(iterator.hasNext()) {
				entry = iterator.next();
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
