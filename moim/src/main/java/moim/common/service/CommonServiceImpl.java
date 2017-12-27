package moim.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import moim.common.dao.CommonDAO;
import moim.user.vo.UserVO;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;
	
	@Override
	public List<Map<String, Object>> selectGroupList(UserVO user) throws Exception{
		return (List<Map<String,Object>>)commonDAO.selectGroupList(user);
	}
	
	@Override
	public Map<String, Object> selectOverview() throws Exception{
		return (Map<String, Object>) commonDAO.selectOverview();
	}
}
