<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<link href="/resources/js/exts/com/code/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/resources/js/exts/com/code/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/resources/js/exts/com/code/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="/resources/js/exts/com/code/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="/resources/js/exts/com/code/manage.js"></script>

<c:forEach items="${resultList}" var="result" varStatus="i">
	<input type="hidden" id="grps${i }" data-id="<c:out value="${result.groupCd }" />" data-pid="<c:out value="${result.upGroupCd }" />" data-name="<c:out value="${result.groupCdNm }" />"/>
</c:forEach>
				<div class="cell_wrap half">

					<div class="cell_box">
						<h4 class="s_tit">전체 코드그룹 목록</h4>
						<table class="board_view">
							<tr><td>
								<ul class="ztree" id="grpDiv">
								</ul>
							</td></tr>
						</table>
					</div>
					<div class="cell_box" id="listDiv">
					</div>
				</div>
<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>