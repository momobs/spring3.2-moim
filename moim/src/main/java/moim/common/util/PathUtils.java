package moim.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@SuppressWarnings("unused")
public class PathUtils {
	private static PathUtils t_instance;
	public static final String MAIN = "/common/main";
	public static final String LOGIN = "/common/login";
	public static final String ERROR = "/common/error";
	public static final String JOINUS = "/user/joinus";
	
	public static final String AUTH_PROFILE = "/user/profile";
	
	public static PathUtils getInstance(){
		if (t_instance==null) {
			t_instance = new PathUtils();
		}
		return t_instance;
	}
	
	public static String getPath(String key){
		getInstance();
		
		String path = null;
		try {
			path = (String) t_instance.getClass().getDeclaredField(key).get(t_instance);
		} catch(Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return path;
	}
}
