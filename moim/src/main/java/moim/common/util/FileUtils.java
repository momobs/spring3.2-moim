package moim.common.util;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileUtils")
public class FileUtils {
	public Logger log = Logger.getLogger(this.getClass());

	//private static final String filePath = "/volume1/upload/moim/";
	public static final String rootFilePath = "E:/upload/moim/";
	public static final String userFilePath = rootFilePath+"user/";
	//private static final String filePath = MessageUtils.getMessage("path.upload.local");
	
	
	public static final String typeCreate= "C";
	public static final String typeRead= "R";
	public static final String typeUpdate= "U";
	public static final String typeDelete= "D";
	
	public static final String cnCrud = "crud";
	public static final String cnFileGroup = "file_group";
	public static final String cnOriFileName = "original_file_name";
	public static final String cnStoFileName = "stored_file_name";
	public static final String cnFileSize = "file_size";

	public void transferSingleFile(MultipartFile multipartFile, Map<String, Object> map) throws Exception{
		String filePath = (String)map.get("FILE_PATH");
		String storedName = (String)map.get("STORED_NAME");
		File file = new File(filePath);
		if (!file.exists()) { file.mkdirs(); };
		
		file = new File(filePath+storedName);
		multipartFile.transferTo(file);
	}
	
	public Map<String,Object> parsetSingleFileInfo(MultipartFile multipartFile, Map<String,Object> map){
		String originalFullName = multipartFile.getOriginalFilename();
		String originalName = originalFullName.substring(0, originalFullName.lastIndexOf("."));
		String extension = originalFullName.substring(originalFullName.lastIndexOf(".")+1).toUpperCase();
		String storedName = map.get("GROUP_IDX")+"-"+map.get("FILE_IDX");
		Long fileSize = multipartFile.getSize();
		
		map.put("FILE_SIZE", fileSize);
		map.put("ORIGINAL_NAME", originalName);
		map.put("EXTENSION", extension);
		map.put("STORED_NAME", storedName);
		
		return map;
	}
	/*
	public List<Map<String,Object>> parseInsertFileInfo(String fileGroup, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		Long fileSize = null;
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> listMap = null;
		
		File file = new File(filePath);
		if(!file.exists()) { file.mkdirs(); }

		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(!multipartFile.isEmpty()) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toUpperCase();
				storedFileName = CommonUtils.getRandomString() + originalFileExtension; 
				fileSize = multipartFile.getSize(); 
						
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);

				listMap = parseFileMap(typeCreate, fileGroup, originalFileName, storedFileName, fileSize);
				list.add(listMap);
			}
		}
		return list;
	}
	
	
	public Map<String,Object> parseFileMap(String crud, String fileGroup, String oriFileName, String stoFileName, Long fileSize){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(cnCrud, crud);
		map.put(cnFileGroup, StringUtils.nvl(fileGroup, MessageUtils.getMessage("file.group.default")));
		map.put(cnOriFileName, oriFileName);
		map.put(cnStoFileName, stoFileName);
		map.put(cnFileGroup, fileSize);
		LogUtils.logInfoMap(log, map);
		return map;
	}

	
	// 단일 파일 업로드 파싱
	public Map<String,Object> parseInsertFile(Map<String,Object> map, HttpServletRequest request) throws Exception{
		
		
		
		
		return map;
	}
	
	

	
	public List<Map<String,Object>> parseInsertFileInfo(List<Map<String,Object>> param, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		String fileGroup = null;
		Long fileSize = null;
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> listMap = null;
		
		File file = new File(filePath);
		if(!file.exists()) { file.mkdirs(); }

		int i = 0;
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(!multipartFile.isEmpty()) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")).toUpperCase();
				fileGroup = (String) param.get(i).get(cnFileGroup);
				fileSize = multipartFile.getSize(); 
						
				try {
					storedFileName = (String) param.get(i).get(cnStoFileName);
				} catch(Exception e) {
					storedFileName = CommonUtils.getRandomString() + originalFileExtension; 
				} finally {
					i++;
				}
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				listMap = parseFileMap(typeCreate, fileGroup, originalFileName, storedFileName, fileSize);
				list.add(listMap);
			}
		}
		return list;
	}
	
	public List<Map<String,Object>> parseDeleteFileInfo(List<Map<String,Object>> target) throws Exception{
		String storedFileName = null;
		File file = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> listMap = null;
		
		for (int i=0, size=target.size(); i<size; i++) {
			listMap = target.get(i);
			storedFileName = (String)listMap.get(cnStoFileName);
			
			file = new File(filePath+storedFileName);
			
			if (file.exists()) {
				file.delete();
			}
			
			listMap = parseFileMap(typeDelete, null, null, storedFileName, null);
			
			list.add(listMap);
		}
		
		return list;
	}
	
	

	public void parseUpdateFileInfo(List<Map<String,Object>> list, HttpServletRequest request) throws Exception{
		Map<String,Object> listMap = null;
		File file = null;
		String request_type = null;
		String stored_file_name = null;
		
		for (int i=0, size=list.size(); i<size; i++) {
			listMap = (Map<String,Object>) list.get(i);
			request_type = (String)listMap.get("crud");
			stored_file_name = (String)listMap.get("stored_file_name");
			
			if(request_type.equals(typeCreate)) {
				
			} else if(request_type.equals(typeDelete)) {
				
			}
			
			
		}
	}
	
	
	
	// 파일 업로드 (with Naming)
	public List<Map<String,Object>> parseInsertFileInfo(List<String> naming, HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> listMap = null;
		
		File file = new File(filePath);
		if(!file.exists()) { file.mkdirs(); }
		
		int i = 0;
		
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if(!multipartFile.isEmpty()) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				
				// Naming 이 있을 경우,
				try {
					storedFileName = naming.get(i);
				} catch (Exception e) { // Naming이 없을 경우,
					storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				}
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				listMap = new HashMap<String,Object>();
				listMap.put("original_file_name", originalFileName);
				listMap.put("stored_file_name", storedFileName);
				listMap.put("file_size", multipartFile.getSize());
				list.add(listMap);
				i++;
			}
		}
		return list;
	}
	

	
	
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
	*/
}
