<%
/**
 *@version 3.2.0.1
 **/
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "egovframework.com.cmm.service.EgovProperties,org.apache.log4j.*" %>
<%
	Logger log = Logger.getLogger("egovHttpSessionException.jsp");
	Throwable ex = (Throwable)request.getAttribute("exception");
	log.error(ex.getMessage());
%>
<!DOCTYPE html>
<html>
<head>
    <title>하나재단</title>
    <meta charset="UTF-8"/>
    <link type="text/css" rel="stylesheet" href="/html/css/style.css"/>
    <script type="text/javascript" src="/html/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="/html/js/design.js"></script>

</head>
<body>

	<div class="fullwrap">
	
		<div class="whiteblack error">
			<p class="error_msg">죄송합니다.<br> 에러가 발생하였습니다.</p>
			<button class="btn_bggreen_35" type="button" onclick="history.back();">이전으로</button>
		</div>

		
	
	</div>

</body>
</html>