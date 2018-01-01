package moim.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import moim.common.dao.CommonDAO;
import moim.common.util.MessageUtils;
import moim.exception.custom.CustomException;
import moim.user.vo.UserVO;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public Map<String,Object> getLoginInfo(Map<String,Object> map){
		
		try {
			map = commonDAO.selectLoginInfo(map);
		} catch(Exception e) {
			log.error("EXCEPTION: "+e.getMessage());
		}
		
		return map;
	}

	
	@Override
	public List<Map<String, Object>> selectGroupList(UserVO user) throws Exception{
		return (List<Map<String,Object>>)commonDAO.selectGroupList(user);
	}
	
	@Override
	public Map<String, Object> selectOverview() throws Exception{
		return (Map<String, Object>) commonDAO.selectOverview();
	}
}
