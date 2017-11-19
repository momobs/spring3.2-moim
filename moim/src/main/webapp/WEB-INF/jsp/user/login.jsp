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
            <form class="login-form" action="<c:url value='/user/login.do'/>" method="post">
                <h3 class="form-title font-green">Sign In</h3>
                <c:choose>
                <c:when test="${msg eq null }">
	                <div class="alert alert-danger display-hide">
	                    <button class="close" data-close="alert"></button>
	                    <span> 아이디와 비밀번호를 입력하세요. </span>
	                </div>                	
              	</c:when>
              	<c:otherwise>
	              	<div class="alert alert-danger">
	                    <button class="close" data-close="alert"></button>
	                    <span> ${msg} </span>
	               	</div>
               	</c:otherwise>
               	</c:choose>
               	
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">ID</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="ID" name="username" /> 
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">PASSWORD</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="PASSWORD" name="password" />
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn green uppercase">Login</button>
                    <label class="rememberme check mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="remember" value="1" />Remember
                        <span></span>
                    </label>
                    <a href="javascript:;" id="forget-password" class="forget-password">Forgot Password?</a>
                </div>
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
            <form class="register-form" action="<c:url value='/user/joinus.do'/>" method="post">
                <h3 class="font-green">Sign Up</h3>
                <p class="hint"> 필수입력사항 </p>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">아이디</label>
                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="아이디" name="username" />
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">비밀번호</label>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password" placeholder="비밀번호" name="password" />
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">비밀번호 재입력</label>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="비밀번호 재입력" name="rpassword" />
				</div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">사용자명</label>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="사용자명" name="fullname" /> 
				</div>
                
                <p class="hint"> 선택입력사항 </p>

                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">Email</label>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="E-MAIL" name="email" /> </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">주소</label>
                    <input class="form-control placeholder-no-fix" type="text" placeholder="주소" name="address" /> </div>
                
                
                <div class="form-group margin-top-20 margin-bottom-20">
                    <label class="mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="tnc" checked="checked"/> 약관에 동의합니다.   
                        <a href="javascript:;">Terms of Service </a> &
                        <a href="javascript:;">Privacy Policy </a>
                        <span></span>
                    </label>
                    <div id="register_tnc_error"> </div>
                </div>

                <div class="form-actions">
                    <button type="button" id="register-back-btn" class="btn green btn-outline">Back</button>
                    <button type="submit" id="register-submit-btn" class="btn btn-success uppercase pull-right">Submit</button>
                </div>
            </form>
            <!-- END REGISTRATION FORM -->
        </div>
        <div class="copyright"> 2014 © Metronic. Admin Dashboard Template. </div>

		<%@ include file="/WEB-INF/include/include-body.jspf" %>

        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<c:url value='/asset/plugin/jquery-validation/js/jquery.validate.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/asset/plugin/jquery-validation/js/additional-methods.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/asset/plugin/select2/js/select2.full.min.js'/>" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="<c:url value='/asset/js/page/login.js?ver=1'/>" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->

        <script>
            $(document).ready(function()
            {
                $('#clickmewow').click(function()
                {
                    $('#radio1003').attr('checked', 'checked');
                });
            })
        </script>
    </body>

</html>