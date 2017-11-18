package moim.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import moim.common.common.CommandMap;
import moim.common.service.CommonService;

@Controller
public class CommonController{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/init.do")
	public ModelAndView init() throws Exception{
		ModelAndView mv = new ModelAndView("/common/dashboard");
		return mv;
	}
	
	@RequestMapping(value="/common/login.do")
    public ModelAndView login(HttpServletRequest request, CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/common/login");
    	String inputId = request.getParameter("username");
    	String inputPwd = request.getParameter("password");
    	commandMap.put("USER_ID", inputId);
    	
    	if ( inputId!=null ) {
    		Map<String,Object> user = commonService.selectUser(commandMap.getMap());
    		if (user.get("USER_PWD").equals(inputPwd)) {
    			request.getSession().setAttribute("user", user);
    			mv.setViewName("redirect:/");
    		}
    	}
    	return mv;
	}
	
	@RequestMapping(value="/common/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/");
    	request.getSession().invalidate();
    	
    	return mv;
	}
	
	
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
}
