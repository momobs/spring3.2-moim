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
                <!-- BEGIN SIDEBAR -->
				<%@ include file="/WEB-INF/include/include-sidebar.jspf" %>
                <!-- END SIDEBAR -->
                <!-- BEGIN CONTENT -->
                <div class="page-content-wrapper">
                    <!-- BEGIN CONTENT BODY -->
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
                        <h1 class="page-title"> 개인정보
                            <small></small>
                        </h1>
                        <!-- END PAGE TITLE-->
                        <!-- END PAGE HEADER-->
                        <div class="row">
                            <div class="col-md-12">
                                <!-- BEGIN PROFILE SIDEBAR -->
                                <div class="profile-sidebar">
                                    <!-- PORTLET MAIN -->
                                    <div class="portlet light profile-sidebar-portlet ">
                                        <!-- SIDEBAR USERPIC -->
                                        <div class="profile-userpic">
                                            <img src="<c:url value='/resources/img/common/no-user.png'/>" class="img-responsive" alt=""> </div>
                                        <!-- END SIDEBAR USERPIC -->
                                        <!-- SIDEBAR USER TITLE -->
                                        <div class="profile-usertitle">
                                            <div class="profile-usertitle-name"> ${sessionScope.user.user_name } </div>
                                            <div class="profile-usertitle-job"> </div>
                                        </div>
                                        <!-- END SIDEBAR USER TITLE -->
                                        <!-- SIDEBAR BUTTONS -->
                                        <div class="profile-userbuttons">
                                            <button type="button" class="btn btn-circle green btn-sm">Follow</button>
                                            <button type="button" class="btn btn-circle red btn-sm">Message</button>
                                        </div>
                                        <!-- END SIDEBAR BUTTONS -->
                                        <!-- SIDEBAR MENU -->
                                        <div class="profile-usermenu">
                                            <ul class="nav">
                                                <li class="active">
                                                    <a href="#">
                                                        <i class="icon-settings"></i> 기본정보 수정 </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <!-- END MENU -->
                                    </div>
                                    <!-- END PORTLET MAIN -->
                                </div>
                                <!-- END BEGIN PROFILE SIDEBAR -->
                                <!-- BEGIN PROFILE CONTENT -->
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

                                                        <!-- 기본정보 수정: START -->
                                                        <div class="tab-pane <c:if test="${active eq 'tab_1_1' }">active</c:if>" id="tab_1_1">
                                                            <form id="form-info" action="<c:url value='/user/auth/setProfile.do'/>" method="post">
																<div class="alert alert-danger <c:if test='${tab_1_1.message eq null }'>display-hide</c:if>">
																	<button class="close" data-close="alert"></button>
																	<span>${tab_1_1.message}</span>
																</div>     
                                                                <div class="form-group">
                                                                    <label class="control-label">이름</label>
                                                                    <span id="alert-name"></span>
                                                                    <input name="user_name" type="text" placeholder="이름" class="form-control" value="${sessionScope.user.user_name }"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="control-label">이메일</label>
                                                                    <span id="alert-email"></span>
                                                                    <input name="email" type="text" placeholder="이메일" class="form-control" value="${sessionScope.user.email }"/> </div>
                                                                <div class="form-group">
                                                                    <label class="control-label">연락처</label>
                                                                    <span id="alert-phone"></span>
                                                                    <input name="phone" type="text" placeholder="연락처" class="form-control" value="${sessionScope.user.phone }"/> </div>
                                                                <div class="form-group">
                                                                    <label class="control-label">주소</label>
                                                                    <span id="alert-address"></span>
                                                                    <input name="address" type="text" placeholder="주소" class="form-control" value="${sessionScope.user.address }"/> </div>
                                                                <div class="form-group">
                                                                    <label class="control-label">소개</label>
                                                                    <textarea name="intro" class="form-control" rows="3" placeholder="설명">${sessionScope.user.intro }</textarea>
                                                                </div>
                                                                <div class="margiv-top-10">
                                                                    <button id="btn-info-save" class="btn green"> 저장 </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <!-- 기본정보 수정: END -->
                                                        
<!-- 사진변경: START -->
<div class="tab-pane <c:if test="${active eq 'tab_1_2' }">active</c:if>" id="tab_1_2">
	<p> 프로필 사진을 변경합니다. </p>
	<form id="form-photo" action="<c:url value='/user/auth/setProfilePhoto.do'/>" role="form" enctype="multipart/form-data">
		<div class="alert alert-danger <c:if test='${tab_1_2.message eq null }'>display-hide</c:if>">
			<button class="close" data-close="alert"></button>
			<span>${tab_1_2.message}</span>
		</div>
		<div class="form-group">
			<div class="fileinput fileinput-new" data-provides="fileinput">
				<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
					<img src="<c:url value='/resources/img/common/no-img.png'/>" alt="" />
				</div>
				<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
				</div>
				<div>
					<span class="btn default btn-file">
					<span class="fileinput-new"> 이미지 선택 </span>
					<span class="fileinput-exists"> 이미지 변경 </span>
					<input type="file" name="..."> </span>
					<a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput"> 이미지 제거 </a>
				</div>
			</div>
			<div class="clearfix margin-top-10">
				<span class="label label-danger">NOTE! </span>
				<span>Attached image thumbnail is supported in Latest Firefox, Chrome, Opera, Safari and Internet Explorer 10 only </span>
			</div>
		</div>
		<div class="margin-top-10">
			<a href="javascript:alert('2'); $('#uPhotoForm').submit();" class="btn green"> 등록 </a>
			<a href="javascript:;" class="btn default"> 취소 </a>
		</div>
	</form>
</div>
<!-- 사진변경: END -->
                                                        
                                                        <!-- 비밀번호 변경: START -->
                                                        <div class="tab-pane <c:if test="${active eq 'tab_1_3' }">active</c:if>" id="tab_1_3">
                                                            <form id="form-password" action="<c:url value='/user/auth/setPassword.do'/>" method="post" >
																<div class="alert alert-danger <c:if test='${tab_1_3.message eq null }'>display-hide</c:if>">
																	<button class="close" data-close="alert"></button>
																	<span>${tab_1_3.message}</span>
																</div>
                                                                <div class="form-group">
                                                                    <label class="control-label">현재 비밀번호</label>
                                                                    <span id="alert-current-password"></span>
                                                                    <input name="current_password" type="password" class="form-control" /> </div>
                                                                <div class="form-group">
                                                                    <label class="control-label">신규 비밀번호</label>
                                                                    <span id="alert-new-password"></span>
                                                                    <input name="new_password" type="password" class="form-control" /> </div>
                                                                <div class="form-group">
                                                                    <label class="control-label">신규 비밀번호(재입력)</label>
                                                                    <span id="alert-re-password"></span>
                                                                    <input name="re_password" type="password" class="form-control" /> </div>
                                                                <div class="margin-top-10">
                                                                    <button id="btn-password-save" class="btn green"> 변경 </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <!-- 비밀번호 변경: END -->
                                                        
                                                        <!-- PRIVACY SETTINGS TAB -->
                                                        <div class="tab-pane" id="tab_1_4">
                                                            <form action="#">
                                                                <table class="table table-light table-hover">
                                                                    <tr>
                                                                        <td> Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus.. </td>
                                                                        <td>
                                                                            <div class="mt-radio-inline">
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios1" value="option1" /> Yes
                                                                                    <span></span>
                                                                                </label>
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios1" value="option2" checked/> No
                                                                                    <span></span>
                                                                                </label>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td> Enim eiusmod high life accusamus terry richardson ad squid wolf moon </td>
                                                                        <td>
                                                                            <div class="mt-radio-inline">
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios11" value="option1" /> Yes
                                                                                    <span></span>
                                                                                </label>
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios11" value="option2" checked/> No
                                                                                    <span></span>
                                                                                </label>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td> Enim eiusmod high life accusamus terry richardson ad squid wolf moon </td>
                                                                        <td>
                                                                            <div class="mt-radio-inline">
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios21" value="option1" /> Yes
                                                                                    <span></span>
                                                                                </label>
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios21" value="option2" checked/> No
                                                                                    <span></span>
                                                                                </label>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td> Enim eiusmod high life accusamus terry richardson ad squid wolf moon </td>
                                                                        <td>
                                                                            <div class="mt-radio-inline">
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios31" value="option1" /> Yes
                                                                                    <span></span>
                                                                                </label>
                                                                                <label class="mt-radio">
                                                                                    <input type="radio" name="optionsRadios31" value="option2" checked/> No
                                                                                    <span></span>
                                                                                </label>
                                                                            </div>
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                                <!--end profile-settings-->
                                                                <div class="margin-top-10">
                                                                    <a href="javascript:;" class="btn red"> Save Changes </a>
                                                                    <a href="javascript:;" class="btn default"> Cancel </a>
                                                                </div>
                                                            </form>
                                                        </div>
                                                        <!-- END PRIVACY SETTINGS TAB -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- END PROFILE CONTENT -->
                            </div>
                        </div>
                    </div>
                    <!-- END CONTENT BODY -->
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
        	
        	// 비밀번호 변경:START
        	$("#btn-password-save").click(function(){
        		
        		var curPwd = $("input[name='current_password']").val();
        		var newPwd = $("input[name='new_password']").val();
        		var rePwd = $("input[name='re_password']").val();
        		
        		var result = valObj.checkPwd(curPwd);
        		printAlert("current_password", result);
        		if (result.get("success")==false){
        			$("input[name='current_password']").focus();
        			return false;
        		}
        		
        		result = valObj.checkPwd(newPwd);
        		printAlert("new_password", result);
        		if (result.get("success")==false){
        			$("input[name='new_password']").focus();
        			return false;
        		}
        		
        		result = valObj.checkPwd2(newPwd, rePwd);
        		printAlert("re_password", result);
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
        	// 비밀번호 변경:END
        	
			
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