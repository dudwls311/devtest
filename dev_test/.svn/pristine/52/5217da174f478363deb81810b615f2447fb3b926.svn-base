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
	Logger log = Logger.getLogger("egovException.jsp");
	Throwable ex = (Throwable)request.getAttribute("exception");
	ex.printStackTrace();
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
				<p class="err_tit"><img src="/support/img/content/img_nopage.png" alt="" />정상적인 접근이 아닙니다.</p>
				<!-- <p class="err_con">존재하지 않는 주소를 입력하셨거나,<br/>요청하신 페이지의 주소가 변경,삭제되어 찾을 수 없습니다.</p> -->
				<p class="btn_g_btm"><a class="btn_st btn_c_gr btn_s_big" href="/support/">첫화면</a>&nbsp;<a class="btn_st btn_c_bk btn_s_big" href="#none" onclick="history.back();">이전 화면</a></p>
			</div>
		</div>
	</body>
</html>