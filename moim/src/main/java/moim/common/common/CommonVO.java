package moim.common.common;

import lombok.Data;

@Data
public class CommonVO {
	private Boolean result;
	private String message;
	
	public CommonVO(Boolean result, String message) {
		this.result = result;
		this.message = message;
	}
}
