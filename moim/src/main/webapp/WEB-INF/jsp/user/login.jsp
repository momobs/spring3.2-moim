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
        <link href="<c:url value='/resources/css/page/login.css'/>" rel="stylesheet" type="text/css" />
        <!-- PAGE LEVEL STYLES:END -->
    <!-- END HEAD -->

    <body class=" login">

        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="<c:url value='/'/>">
                <img src="<c:url value='/resources/img/logo/logo.png'/>" alt="" /> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- LOGIN FORM:BEGIN -->
            <form class="login-form" action="<c:url value='/user/login.do'/>" method="post">
                <h3 class="form-title font-green">로그인</h3>
				
				<div class="alert alert-danger <c:if test='${msg eq null }'>display-hide</c:if>">
	                    <button class="close" data-close="alert"></button>
	                    <span id="alertMsg">${msg}</span>
				</div>                	
               	
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">아이디</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="ID" name="user_id" /> 
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">비밀번호</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="PASSWORD" name="user_pwd" />
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
                        <a href="javascript:;" id="register-btn" class="uppercase">회원가입</a>
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
            <form class="register-form" action="<c:url value='/user/joinus.do'/>" method="post">
                <h3 class="font-green">회원가입</h3>
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
                    <button type="button" id="register-back-btn" class="btn green btn-outline">뒤로</button>
                    <button type="submit" id="register-submit-btn" class="btn btn-success uppercase pull-right">가입하기</button>
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
        <script src="<c:url value='/resources/js/global/validate.js'/>" type="text/javascript"> </script>
        <!-- END PAGE LEVEL SCRIPTS -->

        <script>
            $(document).ready(function()
            {
            	var valObj = new ValidateUser();
            	var alert = "";
           	// 화면전환:START
           		// LOGIN TO REGISTER FORM
        		$("#register-btn").click(function() { $('.login-form').hide(); $('.register-form').show(); });
           		// REGISTER TO LOGIN FORM
        		$("#register-back-btn").click(function() { jQuery('.login-form').show(); jQuery('.register-form').hide(); });
       		// 화면전환:END
       		
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
            
       		// REGISTER EVENT:START
       			// 아이디 유효성 검사
				$("input[name='user_id']", $(".register-form")).blur(function(){
					var user_id = $("input[name='user_id']", $(".register-form")).val();
					var result = valObj.checkId(user_id, "Y");
				   	result.get("success")==true ? alert = "<font color='green' size='2'>" : alert="<font color='red' size='2'>";
				   	alert += result.get("message")+"</font>";
				   	$("#alertId", $(".register-form")).html(alert);
				});
       		
       			// 비밀번호 유효성 검사
            	$("input[name='user_pwd']", $(".register-form")).blur(function(){
            		var user_pwd = $("input[name='user_pwd']", $(".register-form")).val();
            		var result = valObj.checkPwd(user_pwd);
            		result.get("success")==true ? alert = "<font color='green' size='2'>" : alert="<font color='red' size='2'>";
                	alert += result.get("message")+"</font>";
                	$("#alertPwd", $(".register-form")).html(alert);
            	});
       			
       			// 비밀번호(재입력) 유효성 검사
            	$("input[name='user_pwd2']", $(".register-form")).blur(function(){
            		var user_pwd = $("input[name='user_pwd']", $(".register-form")).val();
            		var user_pwd2 = $("input[name='user_pwd2']", $(".register-form")).val();
					var result = valObj.checkPwd2(user_pwd, user_pwd2);
					result.get("success")==true ? alert = "<font color='green' size='2'>" : alert="<font color='red' size='2'>";
				   	alert += result.get("message")+"</font>";
				   	$("#alertPwd2", $(".register-form")).html(alert);
            	});
       			
            	// 이름 유효성 검사
            	$("input[name='user_name']", $(".register-form")).blur(function(){
            		var user_name = $("input[name='user_name']", $(".register-form")).val();
					var result = valObj.checkName(user_name);
					result.get("success")==true ? alert = "<font color='green' size='2'>" : alert="<font color='red' size='2'>";
				   	alert += result.get("message")+"</font>";
				   	$("#alertName", $(".register-form")).html(alert);
            	});
       			
            	// 이메일 유효성 검사
            	$("input[name='email']", $(".register-form")).blur(function(){
            		var email = $("input[name='email']", $(".register-form")).val();
					var result = valObj.checkEmail(email);
					result.get("success")==true ? alert = "<font color='green' size='2'>" : alert="<font color='red' size='2'>";
				   	alert += result.get("message")+"</font>";
				   	$("#alertEmail", $(".register-form")).html(alert);
            	});
       			
       			
       		// REGISTER EVENT:END
       		
       		
            })
            

        </script>
    </body>

</html>