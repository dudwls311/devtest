<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% /*   hideModify = Y(수정버튼 숨김)
		hideDelete = Y(삭제버튼 숨김)
		hideList = Y(목록버튼 숨김)*/ %>
					<div class="btn_right mgt20 pdx10">
					<c:if test="${hideList != 'Y' }">
						<button class="btn_bgwhite_35" type="button" id="listBtn"><spring:message code="com.btn.list" /></button>
					</c:if>
				<c:if test="${isDelAuth == true}">
					<c:if test="${hideDelete != 'Y' }">
						<button class="btn_bggray_35" type="button" id="deleteBtn"><spring:message code="com.btn.delete" /></button>
					</c:if>
				</c:if>
				<c:if test="${isStreAuth == true}">
					<c:if test="${hideModify != 'Y' }">						
						<button class="btn_bgblackgreen_35" type="button" id="modifyBtn"><spring:message code="com.btn.modify" /></button>
					</c:if>
				</c:if>
					</div>