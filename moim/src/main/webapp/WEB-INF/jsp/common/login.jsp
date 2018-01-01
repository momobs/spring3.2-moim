<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->
<head>
	<%@ include file="/WEB-INF/include/include-main-header.jspf" %>
	
	<!-- PAGE LEVEL PLUGINS:BEGIN -->
	<link href="<c:url value='/resources/plugin/select2/css/select2.min.css'/>" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/plugin/select2/css/select2-bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
	<!-- PAGE LEVEL PLUGINS:END -->     
	
	<!-- PAGE LEVEL STYLES:BEGIN -->
	<link href="<c:url value='/resources/css/page/login.css?${nowTime }'/>" rel="stylesheet" type="text/css" />
	<!-- PAGE LEVEL STYLES:END -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
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
		<!-- LOGIN FORM:BEGIN -->
		<form class="login-form" action="<c:url value='/login'/>" method="post">
			<h3 class="form-title font-green">로그인</h3>
			<div class="alert alert-danger <c:if test='${MESSAGE eq null }'>display-hide</c:if>">
				<button class="close" data-close="alert"></button>
				<span id="alert-msg">${MESSAGE}</span>
			</div>                	
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">아이디</label>
				<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="ID" name="USER_ID" />
			</div> 
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">비밀번호</label>
				<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="PASSWORD" name="USER_PWD" />
			</div>
			<div class="form-actions">
				<button type="submit" class="btn green uppercase">로그인</button>
				<label class="rememberme check mt-checkbox mt-checkbox-outline">
					<input type="checkbox" name="remember" value="1" />아이디 기억하기
					<span></span>
				</label>
				<%--<a href="javascript:;" id="forget-password" class="forget-password">Forgot Password?</a> --%>
			</div>
			<%--
			<div class="login-options">
			    <h4>Or login with</h4>
			    <ul class="social-icons">
			        <li>
			            <a class="social-icon-color facebook" data-original-title="facebook" href="javascript:;"></a>
			        </li>
			        <li>
			            <a class="social-icon-color twitter" data-original-title="Twitter" href="javascript:;"></a>
			        </li>
			        <li>
			            <a class="social-icon-color googleplus" data-original-title="Goole Plus" href="javascript:;"></a>
			        </li>
			        <li>
			            <a class="social-icon-color linkedin" data-original-title="Linkedin" href="javascript:;"></a>
			        </li>
			    </ul>
			</div>
			 --%>
			<div class="create-account">
				<p>
					<a href="<c:url value='/call/joinus'/>" id="register-btn" class="uppercase">회원가입</a>
				</p>
			</div>
		</form>
		<!-- LOGIN FORM:END -->
	</div>
	<div class="copyright"> <spring:message code="common.copyright"/> </div>

	<%@ include file="/WEB-INF/include/include-main-body.jspf" %>

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value='/resources/plugin/jquery-validation/js/jquery.validate.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/plugin/jquery-validation/js/additional-methods.min.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/plugin/select2/js/select2.full.min.js'/>" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	
	<!-- BEGIN PAGE LEVEL SCRIPTS -->       
	<script src="<c:url value='/resources/js/global/validate.js?${nowTime }'/>" type="text/javascript"> </script>
	<!-- END PAGE LEVEL SCRIPTS -->
	
	<script>
		$(document).ready(function(){
			var valObj = new ValidateUser();
			var userId = getCookie("cookieUserId");
			
			$("input[name='USER_ID']").val(userId); 
         
        	if($("input[name='USER_ID']").val() != ""){ // Cookie에 만료되지 않은 아이디가 있어 입력됬으면 체크박스가 체크되도록 표시
	            $("input[name='remember']").attr("checked", true);
        	}
         
			$("button[type='submit']").click(function(){ // Login Form을 Submit할 경우,
	            if($("input[name='remember']").is(":checked")){ // ID 기억하기 체크시 쿠키에 저장
	                var userId = $("input[name='USER_ID']").val();
	                setCookie("cookieUserId", userId, 7); // 7일동안 쿠키 보관
	            } else {
	                deleteCookie("cookieUserId");
	            }
			});             

			$("input[name='USER_PWD']", $(".login-form")).keydown(function (key){
				if(key.keyCode ==13){ $("button[type='submit']", $(".login-form")).focus();	}				
			});

			$("button[type='submit']", $(".login-form")).click(function(){
				var user_id = $("input[name='USER_ID']", $(".login-form")).val();
				var user_pwd = $("input[name='USER_PWD']", $(".login-form")).val();
	
				$("input[name='USER_ID']", $(".login-form")).focus();
				var result = valObj.checkId(user_id, "N");
				if (result.get("SUCCESS")==true){
					$("input[name='USER_PWD']", $(".login-form")).focus();
					result = valObj.checkPwd(user_pwd);
				}

				if (result.get("SUCCESS")==true){
					$("#alert-msg").text("");
					$(".alert-danger", $(".login-form")).hide();
					return true;
				} else {
					$("#alert-msg").text(result.get("MESSAGE"));
					$(".alert-danger", $(".login-form")).show();
					return false;
				}					
			});       		
   		// LOGIN EVENT:END
		})
		
		function setCookie(cookieName, value, exdays){
			var exdate = new Date();
			exdate.setDate(exdate.getDate()+exdays);
			var cookieValue = escape(value)+((exdays==null)? "": "; expires="+exdate.toGMTString());
			document.cookie = cookieName+"="+cookieValue;
		}
		function deleteCookie(cookieName){
			var expireDate = new Date();
			expireDate.setDate(expireDate.getDate()-1);
			document.cookie = cookieName+"= "+"; expires="+expireDate.toGMTString();
		}
		function getCookie(cookieName){
			cookieName = cookieName + '=';
			var cookieData = document.cookie;
			var start = cookieData.indexOf(cookieName);
			var cookieValue = '';
			if(start != -1){
				start += cookieName.length;
				var end = cookieData.indexOf(';', start);
				if(end == -1) end = cookieData.length;
				cookieValue = cookieData.substring(start, end);
			}
			return unescape(cookieValue);
		}
	</script>
</body>
<!-- END BODY -->
</html>