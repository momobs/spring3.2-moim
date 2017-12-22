package moim.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "E:/upload/moim/";
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			
			MultipartFile multipartFile = null;
			String originalFileName = null;
			String originalFileExtension = null;
			String storedFileName = null;
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			Map<String,Object> listMap = null;
			
			//String boardIdx = (String)map.get("IDX"); in Oracle
			//String boardIdx = String.valueOf(map.get("IDX")); // in MySQL
			
			File file = new File(filePath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			while(iterator.hasNext()) {
				multipartFile = multipartHttpServletRequest.getFile(iterator.next());
				if(!multipartFile.isEmpty()) {
					originalFileName = multipartFile.getOriginalFilename();
					originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
					storedFileName = CommonUtils.getRandomString() + originalFileExtension;
					
					file = new File(filePath + storedFileName);
					multipartFile.transferTo(file);
					
					listMap = new HashMap<String,Object>();
					listMap.put("original_file_name", originalFileName);
					listMap.put("stored_file_name", storedFileName);
					listMap.put("file_size", multipartFile.getSize());
					list.add(listMap);
				}
			}
			return list;
	}
	
	public void parseDeleteFileInfo(List<Map<String,Object>> list, HttpServletRequest request) throws Exception{
		Map<String,Object> listMap = null;
		File file = null;
		
		for (int i=0, size=list.size(); i<size; i++) {
			listMap = (Map<String, Object>)list.get(i);
			file = new File(filePath+listMap.get("stored_file_name"));
			if (file.exists()){
				file.delete();
			}
		}
	}
	
	public List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> listMap = null;
		
		String boardIdx = (String)map.get("IDX");
		String requestName = null;
		String idx = null;
		
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty()==false){
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString()+originalFileExtension;
				
				multipartFile.transferTo(new File(filePath + storedFileName));
				
				listMap = new HashMap<String,Object>();
				listMap.put("IS_NEW", "Y");
				listMap.put("BOARD_IDX", boardIdx);
				listMap.put("ORIGINAL_FILE_NAME", originalFileName);
				listMap.put("STORED_FILE_NAME", storedFileName);
				listMap.put("FILE_SIZE", multipartFile.getSize());
				list.add(listMap);
			}
			else {
				requestName = multipartFile.getName();
				idx = "IDX_"+requestName.substring(requestName.indexOf("_")+1);
				if(map.containsKey(idx)==true&&map.get(idx)!=null) {
					listMap = new HashMap<String,Object>();
					listMap.put("IS_NEW", "N");
					listMap.put("FILE_IDX", map.get(idx));
					list.add(listMap);
				}
			}
		}
		return list;
	}
}
