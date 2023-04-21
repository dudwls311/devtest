<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<script type="text/javascript" src="/resources/js/exts/com/smpl/view.js"></script>

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
	<input type="hidden" name="csMode" value="write" />
	<input type="hidden" id="smplId" name="smplId" value="<c:out value="${result.smplId }" />" />
</form>
<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
</form>


					<h4 class="dot_tit">상세 정보</h4>
					<table class="board_view tbl_layout_fix">
						<colgroup>
							<col style="width:140px;" />
							<col />
							<col style="width:140px;" />
							<col />
						</colgroup>
						<tr>
							<th scope="row">ID</th>
							<td>
								<c:out value="${result.smplId}" />
							</td>
							<th scope="row">명칭</th>
							<td>
								<c:out value="${result.smplNm}" />
							</td>
						</tr>
					</table>

					<%@ include file="/WEB-INF/jsp/exts/com/view_bottom.jsp" %>
<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>