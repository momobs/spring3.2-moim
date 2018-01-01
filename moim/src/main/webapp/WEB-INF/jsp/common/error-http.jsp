<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<%@ include file="/WEB-INF/include/include-main-header.jspf" %>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<c:url value='/resources/css/page/error.css'/>" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL STYLES -->
</head>
<body class=" page-404-full-page">
    <div class="row">
        <div class="col-md-12 page-404">
            <div class="number font-red"> 
            	 ${STATUS_CODE }
            </div>
            <div class="details">
                <h3>${MESSAGE }</h3>
            	<a href="<c:url value='/'/>"> [HOME] </a>을 클릭하시면, 홈으로 돌아갑니다.<br/>    
        	</div>
    	</div>
	</div>
	
	<%@ include file="/WEB-INF/include/include-main-body.jspf" %>
</body>
</html>