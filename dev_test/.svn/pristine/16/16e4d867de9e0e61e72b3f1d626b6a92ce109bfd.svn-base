<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/header.jsp" %>
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
		if($('#[firstVo]').val() != ''){
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
	<input type="hidden" name="[mode_name]Mode" value="view" />
	<input type="hidden" name="[firstVo]" value="<c:out value="${result.[firstVo] }" />" />
</form>

<form id="writeForm" action="?" method="post" enctype="multipart/form-data">
	<input type="hidden" name="[mode_name]Mode" value="writeActionJson" />
	<input type="hidden" id="[firstVo]" name="[firstVo]" value="<c:out value="${result.[firstVo] }" />" />

					<h4 class="tit">정보</h4>
					<table class="table_style table_t_left th_v_m">
						<colgroup>
							<col style="width:140px;" />
							<col />
							<col style="width:140px;" />
							<col />
						</colgroup>
[JSP_WRITE_TR]
					</table>

					<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/write_bottom.jsp" %>
</form>
<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/footer.jsp" %>