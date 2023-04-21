<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/init.jsp" %>

<script type="text/javascript">
<c:choose>
	<c:when test="${exception.message == 'user'}">
	location.replace("/support/login.jsp");
	</c:when>
	<c:otherwise>
	location.replace("<c:url value="/exts/com/mbr/login.do" />");
	</c:otherwise>
</c:choose>
</script>
