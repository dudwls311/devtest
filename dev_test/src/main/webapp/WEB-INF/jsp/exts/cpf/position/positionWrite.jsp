<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/cpf/com/header.jsp" %>
<script>
	$(document).ready(function(){
		$('#cancelBtn').on('click',fnCancel);
		$('#saveBtn').on('click',fnSave);
	
		var ajform = new ComAjaxForm('writeForm','listPageForm', {});
		ajform.init();
		
	});
	
	//리스트
	function fnList(){
		$("#listPageForm").submit();
		return false;
	}
	
	//취소
	function fnCancel(){
		if($('#positionId').val() != ''){
			$("#viewPageForm").submit();	
		}else{
			fnList();
		}
		return false;
	}
	
	
	//저장
	function fnSave(){
		if(confirm(Msg.com.confirmSave)){
			$('#writeForm').submit();
			return false;
		}
	}
</script>

<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
</form>
<form id="viewPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
	<input type="hidden" name="cpMode" value="view" />
	<input type="hidden" name="positionId" value="<c:out value="${result.positionId }" />" />
</form>

<form id="writeForm" action="?" method="post" enctype="multipart/form-data">
	<input type="hidden" name="cpMode" value="writeActionJson" />
	<input type="hidden" id="positionId" name="positionId" value="<c:out value="${result.positionId }" />" />

					<h4 class="tit">정보</h4>
					<table class="table_style table_t_left th_v_m">
						<colgroup>
							<col style="width:140px;" />
							<col />
							<col style="width:140px;" />
							<col />
						</colgroup>
							<tr>
								<th scope="row">직급명</th>
								<td>
									<input type="text" class="text" style="width:180px;" id="positionNm" name="positionNm" value="<c:out value="${result.positionNm}" />" />
								</td>
							</tr>

					</table>

					<%@ include file="/WEB-INF/jsp/exts/cpf/com/write_bottom.jsp" %>
</form>
<%@ include file="/WEB-INF/jsp/exts/cpf/com/footer.jsp" %>
