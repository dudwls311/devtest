<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/cpf/com/header.jsp" %>
<script>
	$(document).ready(function(){
		$('#listBtn').on('click',fnList);
		$('#modifyBtn').on('click',fnModify);
		$('#deleteBtn').on('click',fnDelete);
	});
	
	//리스트
	function fnList(){
		$("#listPageForm").submit();
		return false;
	}
	
	//수정
	function fnModify(){
		$("#cdMode").val('write');
		$("#writePageForm").submit();
		return false;
	}
	
	//삭제
	function fnDelete(){
		if(confirm(Msg.com.confirmDelete)){
			ComFns.ajax(actionUrl, {cdMode:'deleteActionJson',deptId:$('#deptId').val()}, fnList);
		}
	}
</script>

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
	<input type="hidden" name="cdMode" value="write" />
	<input type="hidden" id="deptId" name="deptId" value="<c:out value="${result.deptId }" />" />
</form>
<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
</form>


					<h4 class="tit">상세 정보</h4>
					<table class="table_style table_t_left th_v_m">
						<colgroup>
							<col style="width:140px;" />
							<col />
							<col style="width:140px;" />
							<col />
						</colgroup>
						<tr>
							<th scope="row">부서코드</th>
							<td>
								<c:out value="${result.deptId}" />
							</td>
							<th scope="row">부서명</th>
							<td>
								<c:out value="${result.deptNm}" />
							</td>

						</tr>
					</table>

					<%@ include file="/WEB-INF/jsp/exts/cpf/com/view_bottom.jsp" %>
<%@ include file="/WEB-INF/jsp/exts/cpf/com/footer.jsp" %>
