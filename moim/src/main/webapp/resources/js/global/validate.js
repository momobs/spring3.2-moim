/**
 * 
 */

function ValidateUser(){
	// ID 정규식
	this.regularId = /^[a-z0-9_-]{3,16}$/; 
	// PWD 정규식(영문,숫자포함 6~12자)
	this.regularPwd = /^.*(?=.{6,12})(?=.*[0-9])(?=.*[a-zA-Z]).*$/; 
	// EMAIL 정규식(영문,숫자포함 6~12자)
	this.regularEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	var success = false;
	var message = "";
	var result = new Map();
	
	// 아이디 유효성 검사
	this.checkId = function checkId(id, dupCheck){
		if (this.regularId.test(id)){
			success = true;
			message = "(ID) 사용가능한 아이디입니다.";
			if (dupCheck=="Y"){
				$.ajax({
					url : "selectId.do",
					type : "POST",
					data : "user_id="+id,
					async : false,
					success : function(data){
						if(data.result=="false"){
							success = false;
							message = "(ID) 중복된 아이디입니다.";
						}
					}
				});
			}
		} else {
			success = false;
			message = "(ID) 3~16자 영문/숫자 조합이 필요합니다.";
		}
		result.set("success", success);
		result.set("message", message);
		return result;
	}
	
	
	// 비밀번호 유효성 검사
	this.checkPwd = function checkPwd(pwd){
		if(this.regularPwd.test(pwd)){
			result.set("success", true);
			result.set("message", "(PWD) 사용가한 비밀번호입니다.");
		} else {
			result.set("success", false);
			result.set("message", "(PWD) 영문/숫자 포함 6~12자가 필요합니다.");
		}
		return result;
	}
	
	// 이메일 유효성 검사
	this.validateEmail = function validateEmail(email){
		if(regularEmail.test(email)){
			result.set("success", true);
			result.set("message", "사용가능한 이메일입니다.");
		} else {
			result.set("success", false);
			result.set("message", "이메일 형식이 올바르지 않습니다.");
		}
		return result;
	}
	
}
