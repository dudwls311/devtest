<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/header.jsp" %>
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
		$("#[mode_name]Mode").val('write');
		$("#writePageForm").submit();
		return false;
	}
	
	//삭제
	function fnDelete(){
		if(confirm(Msg.com.confirmDelete)){
			ComFns.ajax(actionUrl, {[mode_name]Mode:'deleteActionJson',[firstVo]:$('#[firstVo]').val()}, fnList);
		}
	}
</script>

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
	<input type="hidden" name="[mode_name]Mode" value="write" />
	<input type="hidden" id="[firstVo]" name="[firstVo]" value="<c:out value="${result.[firstVo] }" />" />
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
[JSP_VIEW_TR]
						</tr>
					</table>

					<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/view_bottom.jsp" %>
<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/footer.jsp" %>