<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
	<%@ include file="/WEB-INF/include/include-header.jspf" %>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<c:url value='/resources/css/page/error.css'/>" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL STYLES -->
</head>
<body class=" page-404-full-page">
    <div class="row">
        <div class="col-md-12 page-404">
            <div class="number font-red"> <c:out value="${exception.status_code }"/> </div>
            <div class="details">
                <h3>${exception.message }</h3>
                <p>
                	- Request URI: ${exception.request_uri } <br/>
            		<a href="<c:url value='/'/>"> [HOME] </a>을 클릭하시면, 홈으로 돌아갑니다.   
            	</p>
            	
        	</div>
    	</div>
	</div>
	<!--
	Message
	<c:forEach items="${exception.stack_message }" var="esm"> 
		${esm } 
	</c:forEach>
	Print StackTrace 
	<c:forEach items="${exception.stack_trace }" var="est"> 
		${est } 
	</c:forEach>
	-->
	<%@ include file="/WEB-INF/include/include-body.jspf" %>
</body>
</html>