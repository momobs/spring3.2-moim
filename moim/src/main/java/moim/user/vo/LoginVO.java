package moim.user.vo;

import lombok.Data;

@Data
public class LoginVO extends UserVO{
	private String photo_name;
	private boolean update = true; //true일 경우, Aspect에 의해 login 세션이 업데이트 됨 (컨트롤러)
}
