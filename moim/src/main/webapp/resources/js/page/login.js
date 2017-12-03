var Login = function(){
	var handleLogin = function(){		
		var idChk = false;
		var pwdChk = false;

		// Login Form Submit
		$("input[name='user_pwd']", $(".login-form")).keydown(function (key){
			if(key.keyCode ==13){
				$("button[type='submit']", $(".login-form")).focus();
			}
		});
		$("button[type='submit']", $(".login-form")).click(function(){
			var user_id = $("input[name='user_id']", $(".login-form")).val();
			var user_pwd = $("input[name='user_pwd']", $(".login-form")).val();
			var msg = "";
			if (user_id==""){
				$("input[name='user_id']", $(".login-form")).focus();
				msg = "ID를 입력하시기 바랍니다.";
			}
			if (msg=="" && user_pwd==""){
				$("input[name='user_pwd']", $(".login-form")).focus();
				msg ="PWD를 입력하시기 바랍니다."
			}
			if (msg!=""){
				$('#alertMsg').text(msg);
            	$('.alert-danger', $('.login-form')).show();
            	return false;
			}
			return true;
		});
		
		// CREATE AN ACCOUNT
		jQuery('#register-btn').click(function() {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });
		
	}

	// 회원가입 Form
	var handleRegister = function(){

    	
		$("button[type='submit']", $(".register-form")).click(function(){
			if (validate_register()==false){
				return false;
			}
			return true;
		});
		
		// BACK 
		jQuery('#register-back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });
	}
	
	return {
		init: function(){
			handleLogin();
			handleRegister();
		}
	}
}();

jQuery(document).ready(function() {
    Login.init();
});

//회원가입Form 유효성검사
function validate_register(){
	var user_id = $("input[name='user_id']", $(".register-form")).val();
	var user_pwd = $("input[name='user_pwd']", $(".register-form")).val();
	var user_pwd2 =$("input[name='user_pwd2']", $(".register-form")).val();
	var user_name = $("input[name='user_name']", $(".register-form")).val();
	var email = $("input[name='email']", $(".register-form")).val();
	var result = true;

	if (chkId(user_id)==false){ result = false; }
	
	if (chkPwd(user_pwd)==false){ result = false; }
	
	if (chkPwd2(user_pwd, user_pwd2)==false) { result = false; }
	
	if (chkName(user_name)==false){ result = false; }
	
	if (chkEmail(email)==false){ result = false; }
	
	if (result==false){	return false;}
	
	return true;
}

// 유효성 검사: 아이디
function chkId(str){
	var reg_id =  /^[a-z0-9_-]{3,16}$/;
	$("#alertId", $(".register-form")).html("");
	if (!reg_id.test(str)){
		$("#alertId", $(".register-form")).html("<font color='red' size='2'>3~16자의 영문/숫자 조합 아이디를 입력하십시오.</font>");
		return false;
	}
	return true;
}

// 유효성검사: 비밀번호
function chkPwd(str){
	//영문, 숫자 혼합하여 6~12자리 이내
	var reg_pwd = /^.*(?=.{6,12})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	$("#alertPwd", $(".register-form")).html("");
	if(!reg_pwd.test(str)){
		$("#alertPwd", $(".register-form")).html("<font color='red' size='2'>6~12자의 영문/숫자 조합의 비밀번호를 입력하십시오.</font>");
		return false;
	}
	return true;
}

// 유효성검사: 비밀번호 재확인
function chkPwd2(str1, str2){
	$("#alertPwd2", $(".register-form")).html("");
	if (str1 != str2){
		alert(str1+"?"+ str2);
		$("#alertPwd2", $(".register-form")).html("<font color='red' size='2'>비밀번호가 일치하지 않습니다.</font>");
		return false;
	}
	return true;
}

// 유효성 검사: 이름
function chkName(str){
	$("#alertName", $(".register-form")).html("");
	if (str.length<2) {
		$("#alertName", $(".register-form")).html("<font color='red' size='2'>이름을 입력하세요.</font>");
		return false;
	}
	return true;
}

// 유효성 검사: 이메일
function chkEmail(str){
	var reg_email =  /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	$("#alertEmail", $(".register-form")).html("");
	if(!reg_email.test(str)){
		$("#alertEmail", $(".register-form")).html("<font color='red' size='2'>이메일을 올바른 형식으로 입력하세요.</font>");
		return false;
	}
	return true;
}



