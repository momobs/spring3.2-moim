
function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

function phoneFormat(num,type){
    var formatNum = '';
    
    if(num.length==11){
        if(type==0){
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
        }else{
            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        }
    }else if(num.length==8){
        formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
    }else{
        if(num.indexOf('02')==0){
            if(type==0){
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
            }else{
                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            }
        }else{
            if(type==0){
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
            }else{
                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            }
        }
    }
    return formatNum;   
}


function ValidateUser(){	
	var success = false;
	var message = "";
	var result = new Map();
	
	// ID 정규식
	this.regularId = /^[a-z0-9_-]{3,16}$/; 
	// PWD 정규식(영문,숫자포함 6~12자)
	this.regularPwd = /^.*(?=.{6,12})(?=.*[0-9])(?=.*[a-zA-Z]).*$/; 
	// EMAIL 정규식(영문,숫자포함 6~12자)
	this.regularEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	// PHONE 정규식(하이픈)
	this.regularPhone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/; 
	
	
	
	// 아이디 유효성 검사
	this.checkId = function checkId(id, dupCheck){
		if (this.regularId.test(id)){
			success = true;
			message = "Verified";
			if (dupCheck=="Y"){
				$.ajax({
					url : getContextPath()+"/user/selectId.do",
					type : "POST",
					data : "USER_ID="+id,
					async : false,
					success : function(data){
						if(data.success=="no"){
							success = false;
							message = "중복된 아이디입니다.";
						}
					},
					error: function(){
						success = false;
						message = "중복 아이디 조회에 실패했습니다.";
					}
				});
			}
		} else {
			success = false;
			message = "ID는 3~16자 영문/숫자 조합입니다.";
		}
		result.set("SUCCESS", success);
		result.set("MESSAGE", message);
		return result;
	}
	
	// 비밀번호 유효성 검사
	this.checkPwd = function checkPwd(pwd){
		if(this.regularPwd.test(pwd)){
			result.set("SUCCESS", true);
			result.set("MESSAGE", "Verified");
		} else {
			result.set("SUCCESS", false);
			result.set("MESSAGE", "비밀번호는 영문/숫자 포함 6~12자입니다.");
		}
		return result;
	}
	
	// 비밀번호(재입력) 유효성 검사
	this.checkPwd2 = function checkPwd2(pwd1, pwd2){
		if (pwd1.length<=0){
			result.set("SUCCESS", false);
			result.set("MESSAGE", "비밀번호를 입력하세요.");
		} else if (pwd1==pwd2){
			result.set("SUCCESS", true); 
			result.set("MESSAGE", "Verified");
		} else {
			result.set("SUCCESS", false); 
			result.set("MESSAGE", "비밀번호가 일치하지 않습니다.");
		}
		return result;
	}
	
	// 이름 유효성 검사
	this.checkName = function checkName(name){
		if (name.length>0) {
			result.set("SUCCESS", true);
			result.set("MESSAGE", "Verified")
		} else {
			result.set("SUCCESS", false);
			result.set("MESSAGE", "올바른 이름을 입력하세요.")
		}
		return result;
	}

	// 이메일 유효성 검사
	this.checkEmail = function checkEmail(email){
		if(this.regularEmail.test(email)){
			result.set("SUCCESS", true);
			result.set("MESSAGE", "Verified");
		} else {
			result.set("SUCCESS", false);
			result.set("MESSAGE", "사용할 수 없는 이메일입니다.");
		}
		return result;
	}
	
	// 전화번호 유효성 검사 
	this.checkPhone = function checkPhone(phone){ 
		// var num = phone.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
		var num = phoneFormat(phone);
		
		if(this.regularPhone.test(num)){
			result.set("SUCCESS", true);
			result.set("MESSAGE", "Verified");
		} else {
			result.set("SUCCESS", false);
			result.set("MESSAGE", "올바른 전화번호가 아닙니다.");
		}
		result.set("data", num);
		return result;
	}
	
	// 전화번호 유효성 검사 
	this.checkAddress = function checkAddress(address){ 
		result.set("SUCCESS", true);
		result.set("MESSAGE", "Verified");
		return result;
	}
	
	
	
}
