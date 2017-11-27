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
        <link href="<c:url value='/asset/plugin/select2/css/select2.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/asset/plugin/select2/css/select2-bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <!-- PAGE LEVEL PLUGINS:END -->     
        
        <!-- PAGE LEVEL STYLES:BEGIN -->
        <link href="<c:url value='/asset/css/page/login.css'/>" rel="stylesheet" type="text/css" />
        <!-- PAGE LEVEL STYLES:END -->
    <!-- END HEAD -->

    <body class=" login">

        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="<c:url value='/'/>">
                <img src="<c:url value='/asset/img/logo/logo.png'/>" alt="" /> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- LOGIN FORM:BEGIN -->
            <form class="login-form" action="<c:url value='/login.do'/>" method="post">
                <h3 class="form-title font-green">Sign In</h3>
				
				<div class="alert alert-danger <c:if test='${msg eq null }'>display-hide</c:if>">
	                    <button class="close" data-close="alert"></button>
	                    <span id="alertMsg">${msg}</span>
				</div>                	
               	
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">ID</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="ID" name="user_id" /> 
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">PASSWORD</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="PASSWORD" name="user_pwd" />
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn green uppercase">Login</button>
                    <label class="rememberme check mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="remember" value="1" />Remember
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
                        <a href="javascript:;" id="register-btn" class="uppercase">Create an account</a>
                    </p>
                </div>
            </form>
            <!-- LOGIN FORM:END -->
            
            <!-- BEGIN FORGOT PASSWORD FORM -->
            <form class="forget-form" action="index.html" method="post">
                <h3 class="font-green">Forget Password ?</h3>
                <p> Enter your e-mail address below to reset your password. </p>
                <div class="form-group">
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" /> </div>
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn green btn-outline">Back</button>
                    <button type="submit" class="btn btn-success uppercase pull-right">Submit</button>
                </div>
            </form>
            <!-- END FORGOT PASSWORD FORM -->
            
            <!-- BEGIN REGISTRATION FORM -->
            <form class="register-form" action="<c:url value='/joinus.do'/>" method="post">
                <h3 class="font-green">Sign Up</h3>
                <p class="hint"> 필수입력사항 (Essential) </p>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">아이디</label>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="아이디" name="user_id" maxlength="16"/>
                    <span id="alertId"></span>
				</div>

                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">비밀번호</label>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password" placeholder="비밀번호" name="user_pwd" maxlength="20" />
                    <span id="alertPwd"></span>
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">비밀번호 재입력</label>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="비밀번호 재입력" name="user_pwd2" maxlength="20"/>
                    <span id="alertPwd2"></span>
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">사용자명</label>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="사용자명" name="user_name" maxlength="5"/>
                    <span id="alertName"></span> 
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Email</label>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="E-MAIL" name="email" maxlength="50"/>
                    <span id="alertEmail"></span> 
				</div>                
                <p class="hint"> 선택입력사항 (Optional)</p>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">연락처</label>
                    <input class="form-control placeholder-no-fix" type="tel" placeholder="연락처(00*-000*-0000)" name="phone" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" maxlength="15" />
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">주소</label>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="주소" name="address" maxlength="50"/>
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
                    <button type="button" id="register-back-btn" class="btn green btn-outline">Back</button>
                    <button type="submit" id="register-submit-btn" class="btn btn-success uppercase pull-right">Submit</button>
                </div>
            </form>
            <!-- END REGISTRATION FORM -->
        </div>
        <div class="copyright"> Copyright 2017. momobs. All rights reserved. </div>

		<%@ include file="/WEB-INF/include/include-body.jspf" %>

        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<c:url value='/asset/plugin/jquery-validation/js/jquery.validate.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/asset/plugin/jquery-validation/js/additional-methods.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/asset/plugin/select2/js/select2.full.min.js'/>" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
       
        
        <script src="<c:url value='/asset/js/page/login.js?${nowTime }'/>" type="text/javascript"></script> 
        <!-- END PAGE LEVEL SCRIPTS -->

        <script>
            $(document).ready(function()
            {
            	var userId = getCookie("cookieUserId");
            	$("input[name='user_id']", $(".login-form")).val(userId);
            	
            	if($("input[name='user_id']").val() != ""){ // 기억된 아이디가 있어 입력칸에 아이디가 표시된 상태
            		$("input[name='remember']").attr("checked", true);
            	}
            	
            	$("button[type='submit']", $('.login-form')).click(function(){ // Login Form의 Submit 실행시
            		if($("input[name='remember']").is(":checked")){ // ID 기억하기 체크시
            			var userId = $("input[name='user_id']").val();
            			setCookie("cookieUserId", userId, 7); // 7일동안 쿠키 보관
            		} else {
            			deleteCookie("cookieUserId");
            		}
            	});
            	
            	
            	// Blur: ID Check
            	$("input[name='user_id']", $(".register-form")).blur(function(){
            		var user_id = $("input[name='user_id']", $(".register-form")).val();
					if (chkId(user_id)==false){ return false; }
					fn_selectId(user_id);
            	});
            	// Blur: Pwd Check 
            	$("input[name='user_pwd']", $(".register-form")).blur(function(){
            		var user_pwd = $("input[name='user_pwd']", $(".register-form")).val();
					if (chkPwd(user_pwd)==false){ return false; }
            	});
            	// Blur: Pwd2 Check 
            	$("input[name='user_pwd2']", $(".register-form")).blur(function(){
            		var user_pwd = $("input[name='user_pwd']", $(".register-form")).val();
            		var user_pwd2 = $("input[name='user_pwd2']", $(".register-form")).val();
					if (chkPwd2(user_pwd, user_pwd2)==false){ return false; }
            	});
            	// Blur: Name Check 
            	$("input[name='user_name']", $(".register-form")).blur(function(){
            		var user_name = $("input[name='user_name']", $(".register-form")).val();
					if (chkName(user_name)==false){ return false; }
            	});
            	// Blur: Email Check 
            	$("input[name='email']", $(".register-form")).blur(function(){
            		var email = $("input[name='email']", $(".register-form")).val();
					if (chkEmail(email)==false){ return false; }
            	});
            	
            	
                $('#clickmewow').click(function()
                {
                    $('#radio1003').attr('checked', 'checked');
                }); 
            })
            
			// 중복 아이디 검사를 위한 Ajax
			function fn_selectId(user_id){
				var comAjax = new ComAjax();
				comAjax.setUrl("<c:url value='/selectId.do' />");
				comAjax.setCallback("fn_selectIdCallback");
				comAjax.addParam("user_id", user_id);
				comAjax.ajax();
			}
			
			//중복 아이디 검사를 위한 Ajax(Callback)
			function fn_selectIdCallback(data){
				if (data.result=="true"){
					$("#alertId").html("<font color='green' size='2'>※ 사용가능한 아이디입니다.</font>");
				} else {
					$("#alertId").html("<font color='red' szie='2'>※ 중복된 아이디입니다.</font>");
				}
			}
            

        </script>
    </body>

</html>