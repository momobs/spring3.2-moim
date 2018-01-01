package moim.user.vo;

import lombok.Data;

@Data
public class UserVO{
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_photo;
	private String email;
	private String phone;
	private String address;
	private String intro;
	private String del_yn;
	private String create_date;
	private String create_user;
	private String delete_date;
	private String delete_user;
}
