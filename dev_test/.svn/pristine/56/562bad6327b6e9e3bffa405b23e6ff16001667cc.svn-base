
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
	Logger log = Logger.getLogger("dataAccessFailure.jsp");
	Throwable ex = (Throwable)request.getAttribute("exception");
	log.error(ex.getMessage());
%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/jsp/exts/com/header_inc.jsp" %>
	</head>
<body>
	<div class="error_page">
		<div class="err_b">
			<p class="err_tit"><img src="/support/img/content/img_nopage.png" alt="" />데이터를 가져오는데 오류가 발생하였습니다.</p>
			<p class="btn_g_btm"><a class="btn_st btn_c_bk btn_s_big" href="#none" onclick="history.back();">이전 화면</a></p>
		</div>
	</div>	
</body>
</html>