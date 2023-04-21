<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/init.jsp" %>
<html>
	<head>
		<title>JNITCMS</title>
		<%@include file="/WEB-INF/jsp/exts/com/header_inc.jsp" %>
		<script type="text/javascript">
			var isStreAuth = ${!empty isStreAuth?isStreAuth:'false'};
		    var isRedngAuth = ${!empty isRedngAuth?isRedngAuth:'false'};
		    var isDelAuth = ${!empty isDelAuth?isDelAuth:'false'};
		    var isPrntgAuth = ${!empty isPrntgAuth?isPrntgAuth:'false'};
		    
		    var actionUrl = '${actionUrl}';
		</script>
	</head>
	<body>
		<div class="content">