package moim.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import moim.common.common.CommandMap;
import moim.common.service.CommonService;
import moim.user.vo.UserVO;

@Controller
public class CommonController{
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="commonService")
	private CommonService commonService;
	
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
	
	@RequestMapping(value="/common/error{error_code}.do")
	public ModelAndView error(HttpServletRequest request, @PathVariable String error_code) {
		ModelAndView mv = new ModelAndView("/common/error");
		String msg = (String) request.getAttribute("javax.servlet.error.message"); 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
		map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
		map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
		map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
		map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));
		
		try {
			int status_code = Integer.parseInt(error_code);
			switch (status_code) {
			case 400: msg = "잘못된 요청입니다."; break;
			case 403: msg = "접근이 금지되었습니다."; break;
			case 404: msg = "페이지를 찾을 수 없습니다."; break;
			case 405: msg = "요청된 메소드가 허용되지 않습니다."; break;
			case 500: msg = "서버에 오류가 발생하였습니다."; break;
			case 503: msg = "서비스를 사용할 수 없습니다."; break;
			default: msg = "알 수 없는 오류가 발생하였습니다."; break;
			}
		} catch(Exception e) {
			msg = "기타 오류가 발생했습니다.: "+error_code;
		} finally {
			map.put("MESSAGE", msg);
		}
		
		//logging
    	if(map.isEmpty() == false ) {
    		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
    		Entry<String,Object> entry = null;
    		while(iterator.hasNext()) {
    			entry = iterator.next();
    			log.info("key : "+entry.getKey()+", value : "+entry.getValue());
    		}
    	}
		
    	mv.addObject("error", map);
		return mv;
	}
	

}
