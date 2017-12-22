package moim.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import moim.common.common.CommandMap;
import moim.common.service.CommonService;
import moim.common.util.MessageUtils;
import moim.user.vo.UserVO;

@Controller
public class CommonController{
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="commonService")
	private CommonService commonService;

	@RequestMapping(value="/common/test.do")
	public ModelAndView test(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/common/test");
		String a = null;
		log.debug(a.length());
		log.debug("error.common: "+MessageUtils.getMessage("error.common"));
		log.debug("error.minlength: "+MessageUtils.getMessage("error.minlength", new String[] {"테스트글자", "2"}));

		return mv;
	}	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/common/init.do")
	public ModelAndView init(HttpServletRequest request) throws Exception{		
		ModelAndView mv = new ModelAndView("/common/main");
				
		mv.addObject("overview", (Map<String,Object>)commonService.selectOverview());
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		if (user != null) {
			session.setAttribute("groupList", (List<Map<String,Object>>)commonService.selectGroupList(user));
		}
		return mv;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/common/call/{page}.do")
	public ModelAndView call(HttpServletRequest request, @PathVariable String page) {
		ModelAndView mv = new ModelAndView(MessageUtils.getMessage("path."+page));
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
