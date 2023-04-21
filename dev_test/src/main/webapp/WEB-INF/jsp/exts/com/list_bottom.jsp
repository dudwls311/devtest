<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% /*   hideWrite = Y(추가버튼 숨김)*/ %>
<c:if test="${hideWrite != 'Y' && isStreAuth == true}">
				<div class="btn_right mgt_20 pdx10">
					<button class="btn_bgblackgreen_35" type="button" onclick="fnWrite('');"><spring:message code="com.btn.write" /></button>
				</div>
</c:if>