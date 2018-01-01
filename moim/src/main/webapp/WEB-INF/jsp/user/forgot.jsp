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
	<link href="<c:url value='/resources/css/page/login.css'/>" rel="stylesheet" type="text/css" />
	<!-- PAGE LEVEL STYLES:END -->
</head>
<!-- END HEAD -->

<body class=" login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="<c:url value='/'/>">
			<img src="<c:url value='/resources/img/logo/logo.png'/>" alt="" />
		</a>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN FORGOT PASSWORD FORM -->
		<form class="forget-form" action="index.html" method="post">
		    <h3 class="font-green">비밀번호를 잃어버렸나요 ?</h3>
		    <p> 아래 당신의 이메일 주소를 입력해주시면 당신의 비밀번호를 초기화해드립니다.</p>
		    <div class="form-group">
		        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" />
		    </div>
		    <div class="form-actions">
		        <button type="button" id="back-btn" class="btn green btn-outline">뒤로</button>
		        <button type="submit" class="btn btn-success uppercase pull-right">승인</button>
		    </div>
		</form>
		<!-- END FORGOT PASSWORD FORM -->
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
            $(document).ready(function()
            {
            	var valObj = new ValidateUser();
            	var alert = "";
       		// LOGIN EVENT:START
				$("input[name='user_pwd']", $(".login-form")).keydown(function (key){
					if(key.keyCode ==13){ $("button[type='submit']", $(".login-form")).focus();	}				
				});
       		
				$("button[type='submit']", $(".login-form")).click(function(){
					var user_id = $("input[name='user_id']", $(".login-form")).val();
					var user_pwd = $("input[name='user_pwd']", $(".login-form")).val();
					
					$("input[name='user_id']", $(".login-form")).focus();
					var result = valObj.checkId(user_id, "N");
					if (result.get("success")==true){
						$("input[name='user_pwd']", $(".login-form")).focus();
						result = valObj.checkPwd(user_pwd);
					}

					if (result.get("success")==true){
						$("#alertMsg").text("");
						$(".alert-danger", $(".login-form")).hide();
						return true;
					} else {
						$("#alertMsg").text(result.get("message"));
						$(".alert-danger", $(".login-form")).show();
						return false;
					}					
				});       		
       		// LOGIN EVENT:END
            })
        </script>
    </body>

</html>