<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<%@ include file="/WEB-INF/include/include-header.jspf" %>
	
	<!-- PAGE LEVEL PLUGINS:BEGIN -->
	<link href="<c:url value='/resources/plugin/select2/css/select2.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/plugin/select2/css/select2-bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<!-- PAGE LEVEL PLUGINS:END -->     
	
	<!-- PAGE LEVEL STYLES:BEGIN -->
	<link href="<c:url value='/resources/css/page/login.css?${nowTime }'/>" rel="stylesheet" type="text/css" />
	<!-- PAGE LEVEL STYLES:END -->
</head>
<!-- END HEAD -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="<c:url value='/'/>">
			<img src="<c:url value='/resources/img/logo/logo.png'/>" alt="" />
		</a>
	</div>
	<!-- END LOGO -->
	
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN REGISTRATION FORM -->
		<form class="register-form" action="<c:url value='/user/joinus.do'/>" method="post">
			<h3 class="font-green">회원가입</h3>
			<div class="alert alert-danger <c:if test='${message eq null }'>display-hide</c:if>">
				<button class="close" data-close="alert"></button>
				<span id="alertMsg">${message}</span>
			</div>        
			<p class="hint"> 필수입력사항 (Essential) </p>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">아이디</label>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="아이디" name="user_id" maxlength="16" value="${inputData.user_id }" />
                <span id="alert-id"></span>
			</div>

            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">비밀번호</label>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password" placeholder="비밀번호" name="user_pwd" maxlength="20" />
                <span id="alert-pwd"></span>
			</div>
			
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">비밀번호 재입력</label>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="비밀번호 재입력" name="user_pwd2" maxlength="20" />
                <span id="alert-pwd2"></span>
			</div>
			
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">사용자명</label>
                <input class="form-control placeholder-no-fix" type="text" placeholder="사용자명" name="user_name" maxlength="5" value="${inputData.user_name }"/>
                <span id="alert-name"></span> 
			</div>
			
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">Email</label>
                <input class="form-control placeholder-no-fix" type="text" placeholder="E-MAIL" name="email" maxlength="50" value="${inputData.email }"/>
                <span id="alert-email"></span> 
			</div>
			                
            <p class="hint"> 선택입력사항 (Optional)</p>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">연락처</label>
                <input class="form-control placeholder-no-fix" type="tel" placeholder="연락처(00*-000*-0000)" name="phone" maxlength="15" value="${inputData.phone }"/>
                <span id="alert-phone"></span>
			</div>
			
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">주소</label>
                <input class="form-control placeholder-no-fix" type="text" placeholder="주소" name="address" maxlength="50" value="${inputData.address }"/>
                <span id="alert-address"></span>
			</div>
            
            <!-- 
			<div class="form-group margin-top-20 margin-bottom-20">
    			<label class="mt-checkbox mt-checkbox-outline">
        		<input type="checkbox" name="tnc" checked="checked"/> 약관에 동의합니다.   
        		<a href="javascript:;">Terms of Service </a> &
        		<a href="javascript:;">Privacy Policy </a>
        		<span></span>
    		</label>
    		<div id="register_tnc_error"> </div>
			</div>
			-->
		    <div class="form-actions">
		        <!-- <button type="button" id="register-back-btn" class="btn green btn-outline">뒤로</button> -->
		        <a href="<c:url value='/common/call/login.do'/>" id="register-back-btn" class="btn green btn-outline">뒤로</a>
		        <button type="button" id="register-submit-btn" class="btn btn-success uppercase pull-right">가입하기</button>
		    </div>
		</form>
		<!-- END REGISTRATION FORM -->
	</div>
	<div class="copyright"> <spring:message code="common.copyright"/> </div>

	<%@ include file="/WEB-INF/include/include-body.jspf" %>

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value='/resources/plugin/jquery-validation/js/jquery.validate.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/plugin/jquery-validation/js/additional-methods.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/plugin/select2/js/select2.full.min.js'/>" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	
	<!-- BEGIN PAGE LEVEL SCRIPTS -->       
	<script src="<c:url value='/resources/js/global/validate.js?${nowTime }'/>" type="text/javascript"> </script>
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		$(document).ready(function() {
			var valObj = new ValidateUser();
			
			var flagId = false;
			var flagPwd = false;
			var flagPwd2 = false;
			var flagName = false;
			var flagEmail = false;
			var flagPhone = false;
			var flagAddress = false;
			
			// 아이디 유효성 검사
			$("input[name='user_id']").blur(function(){
				checkId();
			});
   		
   			// 비밀번호 유효성 검사
        	$("input[name='user_pwd']").blur(function(){
				checkPwd();
        	});
   			
   			// 비밀번호(재입력) 유효성 검사
        	$("input[name='user_pwd2']").blur(function(){
				checkPwd2();
        	});
   			
        	// 이름 유효성 검사
        	$("input[name='user_name']").blur(function(){
				checkName();
        	});
   			
        	// 이메일 유효성 검사
        	$("input[name='email']").blur(function(){
        		checkEmail();
        	});
        	
        	// 전화번호 유효성 검사
        	$("input[name='phone']").blur(function(){
        		checkPhone();
        	});
        	
        	// 전화번호 유효성 검사
        	$("input[name='address']").blur(function(){
        		checkAddress();
        	});
        	
        	// Submit
        	$("#register-submit-btn").click(function(){
        		checkId();
        		checkPwd();
        		checkPwd2();
        		checkName();
        		checkEmail();
        		checkPhone();
        		checkAddress();
				
        		if (flagId==true && flagPwd==true && flagPwd2==true && flagName==true && flagEmail==true && flagPhone==true && flagAddress==true){
        			if (confirm("가입하시겠습니까?")){
        				$(".register-form").submit();	
        			}
        		} 
        	});
        	
            
        	function checkId(){
    			var user_id = $("input[name='user_id']").val();
    			var result = valObj.checkId(user_id, "Y");
    			result.get("success")==true?flagId=true:flagId=false;
    			printAlert("id", result);
    		}
        	
        	function checkPwd(){
        		var user_pwd = $("input[name='user_pwd']").val();
        		var result = valObj.checkPwd(user_pwd);
        		result.get("success")==true?flagPwd=true:flagPwd=false;
        		printAlert("pwd", result);
        	}
        	
        	function checkPwd2(){
        		var user_pwd = $("input[name='user_pwd']").val();
        		var user_pwd2 = $("input[name='user_pwd2']").val();
				var result = valObj.checkPwd2(user_pwd, user_pwd2);
				result.get("success")==true?flagPwd2=true:flagPwd2=false;
				printAlert("pwd2", result);
        	}
        	
        	function checkName(){
        		var user_name = $("input[name='user_name']").val();
				var result = valObj.checkName(user_name);
				result.get("success")==true?flagName=true:flagName=false;
				printAlert("name", result);
        	}
        	
        	function checkEmail(){
        		var email = $("input[name='email']").val();
				var result = valObj.checkEmail(email);
				result.get("success")==true?flagEmail=true:flagEmail=false;
				printAlert("email", result);
        	}
        	
        	function checkPhone(){
        		var phone = $("input[name='phone']").val();
        		var result = valObj.checkPhone(phone);
        		result.get("success")==true?flagPhone=true:flagPhone=false;
        		printAlert("phone", result);
			   	$("input[name='phone']").val(result.get("data"));
        	}
        	
        	function checkAddress(){
        		var address = $("input[name='address']").val();
				var result = valObj.checkAddress(address);
				result.get("success")==true?flagAddress=true:flagAddress=false;
				printAlert("address", result);
        	}
        	
    		function printAlert(entity, result){
        		var message = "";
        		result.get("success")==true ? message = "<font color='green' size='2'>" : message="<font color='red' size='2'>";
        		message += result.get("message")+"</font>";
        		$("#alert-"+entity).html(message);
        	}
		})
    </script>
</body>
</html>