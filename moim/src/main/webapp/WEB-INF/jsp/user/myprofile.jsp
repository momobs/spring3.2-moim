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
 
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<link href="<c:url value='/resources/plugin/bootstrap-fileinput/bootstrap-fileinput.css'/>" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<c:url value='/resources/css/page/profile.css'/>" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL STYLES -->
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-content-white">
	<div class="page-wrapper">
		<!-- BEGIN HEADER -->
		<%@ include file="/WEB-INF/include/include-topbar.jspf" %>
		<!-- END HEADER -->
		
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->

		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- SIDEBAR: START -->
			<%@ include file="/WEB-INF/include/include-sidebar.jspf" %>
			<!-- SIDEBAR: END -->

			<!-- CONTENT: START -->
			<div class="page-content-wrapper">
    			<!-- CONTENT BODY: START -->
				<div class="page-content">
	    			
	    			<!-- BEGIN PAGE HEADER-->
					<!-- BEGIN PAGE BAR -->
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li>
								<a href="index.html">Home</a>
								<i class="fa fa-circle"></i>
							</li>
							<li>
								<span>User</span>
							</li>
						</ul>
						<div class="page-toolbar">
							<div class="btn-group pull-right">
							</div>
						</div>
					</div>
					<!-- END PAGE BAR -->

					<!-- BEGIN PAGE TITLE-->
					<h1 class="page-title"> 프로필 정보
						<small>Privacy</small>
					</h1>
					<!-- END PAGE TITLE-->
					<!-- END PAGE HEADER-->
					
					<div class="row">
						<div class="col-md-12">
							<!-- PROFILE SIDEBAR: START -->
							<div class="profile-sidebar">
								<!-- PORTLET MAIN: START -->
								<div class="portlet light profile-sidebar-portlet ">
									<!-- SIDEBAR USERPIC: START -->
									<div class="profile-userpic">
										<img src="<c:url value='/resources/img/common/no-user.png'/>" class="img-responsive" alt="">
									</div>
									<!-- SIDEBAR USERPIC: END -->
									
									<!-- SIDEBAR USER TITLE: START -->
									<div class="profile-usertitle">
										<div class="profile-usertitle-name"> ${sessionScope.login.user_name } </div>
										<div class="profile-usertitle-job"> </div>
									</div>
									<!-- SIDEBAR USER TITLE: END -->

									<!-- SIDEBAR BUTTONS: START -->
									<div class="profile-userbuttons">
										<button type="button" class="btn btn-circle green btn-sm">Follow</button>
										<button type="button" class="btn btn-circle red btn-sm">Message</button>
									</div>
									<!-- SIDEBAR BUTTONS: END -->

									<!-- SIDEBAR MENU: START -->
									<div class="profile-usermenu">
									    <ul class="nav">
									        <li class="active">
									            <a href="#">
									                <i class="icon-settings"></i> 기본정보 수정 </a>
									        </li>
									    </ul>
									</div>
									<!-- SIDEBAR MENU: END -->
								</div>
								<!-- PORTLET MAIN: END -->
							</div>
							<!-- PROFILE SIDEBAR: END -->

							<!-- PROFILE CONTENT: START -->
							<div class="profile-content">
								<div class="row">
									<div class="col-md-12">
										<div class="portlet light ">
											<div class="portlet-title tabbable-line">
												<div class="caption caption-md">
													<i class="icon-globe theme-font hide"></i>
													<span class="caption-subject font-blue-madison bold uppercase">프로필 수정</span>
												</div>
												<ul class="nav nav-tabs">
												    <li class="<c:if test="${active eq 'tab_1_1' }">active</c:if>">
														<a class="tab" href="#tab_1_1" data-toggle="tab">기본정보</a>
													</li>
													<li class="<c:if test="${active eq 'tab_1_2' }">active</c:if>">
													    <a class="tab" href="#tab_1_2" data-toggle="tab">사진 변경</a>
													</li>
													<li class="<c:if test="${active eq 'tab_1_3' }">active</c:if>">
											            <a class="tab" href="#tab_1_3" data-toggle="tab">비밀번호 변경</a>
											        </li>
											    </ul>
											</div>
											<div class="portlet-body">
												<div class="tab-content">
													<%@ include file="/WEB-INF/include/include-profile-basic.jspf" %>
													
													<%@ include file="/WEB-INF/include/include-profile-photo.jspf" %>
													
  													<%@ include file="/WEB-INF/include/include-profile-password.jspf" %>                                    
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</div>
							<!-- PROFILE CONTENT: END -->
						</div>
					</div>
				</div>
				<!-- CONTENT BODY: END -->
			</div>
			<!-- END CONTENT -->

			<!-- START QUICK SIDEBAR -->
			<!-- END QUICK SIDEBAR -->

		</div>
		<!-- END CONTAINER -->
		<!-- BEGIN FOOTER -->
		<div class="page-footer">
			<div class="page-footer-inner"> 
				<spring:message code="common.copyright"/>
			</div>
			<div class="scroll-to-top">
				<i class="icon-arrow-up"></i>
			</div>
		</div>
		<!-- END FOOTER -->
	</div>
        
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
        
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value='/resources/plugin/bootstrap-fileinput/bootstrap-fileinput.js'/>" type="text/javascript"></script>
	<script src="<c:url value='/resources/plugin/jquery.sparkline.min.js'/>" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
        
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- <script src="<c:url value='/resources/js/page/profile.js'/>" type="text/javascript"></script>-->
	<script src="<c:url value='/resources/js/global/validate.js?${nowTime }'/>" type="text/javascript"> </script>
	<!-- END PAGE LEVEL SCRIPTS -->
	
	<script>
		$(document).ready(function()
		{
			var valObj = new ValidateUser();
			var flagName = false;
			var flagEmail = false;
			var flagPhone = false;
			var flagAddress = false;
			var flagPwd = false;
			var flagPwd2 = false;
			
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

        	// 기본정보 저장
        	$("#btn-info-save").click(function(){
				checkName();
				checkEmail();
				checkPhone();
				checkAddress();
				if (flagName==true && flagEmail==true && flagPhone==true && flagAddress==true){
					if (!confirm("저장하시겠습니까?")){
	        			return false;
	        		}	
					$("#form-info").submit();
				}
        	});
        	
        	// 프로필 사진 저장
        	$("#btn-photo-save").click(function(){
        		$("#form-photo").submit();
        	});
        	
        	// 비밀번호 변경
        	$("#btn-password-save").click(function(){
        		
        		var curPwd = $("input[name='current_password']").val();
        		var newPwd = $("input[name='new_password']").val();
        		var rePwd = $("input[name='re_password']").val();
        		
        		var result = valObj.checkPwd(curPwd);
        		printAlert("current-password", result);
        		if (result.get("success")==false){
        			$("input[name='current_password']").focus();
        			return false;
        		}
        		
        		result = valObj.checkPwd(newPwd);
        		printAlert("new-password", result);
        		if (result.get("success")==false){
        			$("input[name='new_password']").focus();
        			return false;
        		}
        		
        		result = valObj.checkPwd2(newPwd, rePwd);
        		printAlert("re-password", result);
        		if (result.get("success")==false){
        			$("input[name='re_password']").focus();
        			return false;
        		}
        		
        		if (!confirm("비밀번호를 변경하시겠습니까?")){
        			return false;
        		}
        		$("#form-password").submit();
        		return true;
        	});
        	
			
		    $('#clickmewow').click(function()
		    {
		        $('#radio1003').attr('checked', 'checked');
		    });
		    
		    $('.sidebar-toggler').trigger('click');
	       	
		    function checkName(){
	       		var user_name = $("input[name='user_name']").val();
				var result = valObj.checkName(user_name);
				result.get("success")==true?flagName=true:flagName=false;
				printAlert("name", result);
	       	}
		    
        	function checkPwd(password){
        		var user_pwd = $("input[name='user_pwd']").val();
        		var result = valObj.checkPwd(user_pwd);
        		result.get("success")==true?flagPwd=true:flagPwd=false;
        		printAlert("pwd", result);
        	}
        	
        	function checkPwd2(password, password2){
        		var user_pwd = $("input[name='user_pwd']").val();
        		var user_pwd2 = $("input[name='user_pwd2']").val();
				var result = valObj.checkPwd2(user_pwd, user_pwd2);
				result.get("success")==true?flagPwd2=true:flagPwd2=false;
				printAlert("pwd2", result);
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