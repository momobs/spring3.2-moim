package moim.user.vo;

import lombok.Data;
import moim.common.common.CommonVO;

@Data
public class UserVO extends CommonVO{
	private String user_id;
	private String user_pwd;
	private String user_name;
	private int user_photo;
	private String email;
	private String phone;
	private String address;
	private String intro;
	private String create_date;
	private String create_user;
}
