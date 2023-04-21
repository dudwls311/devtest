<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/cpf/com/header.jsp" %>
<script>
var incName = 0;
var incId = 0;

	$(document).ready(function(){
		$('#cancelBtn').on('click',fnCancel);
		$('#saveBtn').on('click',fnSave);
		
		var ajform = new ComAjaxForm('writeForm','listPageForm', {});
		ajform.init();
		
		
		/* $(document).on('click',"button[name='fileDelete']",fnDelete2); */
					
	});
	$(document).on('click', "[name='minus']", function(){
		var tr = $(this).parent().parent().parent();
		tr.remove();
	});
	function fnTrAdd(){
		var innerHtml = "";
		
			incName += 1;
			incId   += 1;
			innerHtml += "<tr>";
			innerHtml += "<th scope='row'></th>"
			innerHtml += "<td><input type='file' name='abc"+incName+"' id='abc"+incId+"' value='' />&nbsp;<span style='color: mediumorchid;'>※파일 제거 <button name='minus' id='minus' value='-' >-</button></span></td>" ;
			innerHtml += "</tr>"
			 
			$("#trab").append(innerHtml);
			/* $("#abc").append("<td>
					 <input type="file" name="abc" id="abc" value="" />									
							&nbsp;<span style="color: mediumorchid;">※파일 추가.</span> --%>
						</td>"); */
	}
	function fnTrDelete(){
		console.log("ddd");
		$("#trab > tr").remove();
	}
	
	//리스트
	function fnList(){
		$("#listPageForm").submit();
		return false;
	}
	
	//취소
	function fnCancel(){
		if($('#id').val() != ''){
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
	
	
	
	//삭제
	function fnDelete2(id,fileId){
		console.log('dddd');
		var delConfirm = confirm("정말 삭제하시겠습니까?");
		if(delConfirm){
		var params = {
				 id : id
				,fileId : fileId
				,cbeMode : "deleteJson"
		}
		console.log(params);
		console.log(params.id.value);
		console.log(params.fileId.value);
		
		$.ajax({
			url : '/exts/cpf/emp/index.do',
			type : 'GET',
			dataType : 'text',
			data: params ,
			success : function(data){
		 		location.reload();
		 		alert("삭제완료");
			}
		});			
		}else{
			
		}
	}
	
	//첨부파일 다운로드 function
	//ex) fnFileDownload('54', '26');
	function fnFileDownload(id, file){
		$("#fileDownloadFirm #id").val(id);
		$("#fileDownloadFirm #fileId").val(file);
		$("#fileDownloadFirm").submit();
	}
	
	
</script>

<%//첨부파일 다운로드폼 %>
<form id="fileDownloadFirm" action="?">
	<input type="hidden" name="cbeMode" value="fileDownload" />
	<input type="hidden" id="id" name="id" />
	<input type="hidden" id="fileId" name="fileId" />
</form>

<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
</form>
<form id="viewPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
	<input type="hidden" name="cbeMode" value="view" />
	<input type="hidden" name="id" value="<c:out value="${result.id }" />" />
</form>

<form id="writeForm" action="?" method="post" enctype="multipart/form-data">
	<input type="hidden" name="cbeMode" value="writeActionJson" />
	<input type="hidden" id="id" name="id" value="<c:out value="${result.id }" />" />
	<input type="hidden" id="fileId" name="fileId" value="<c:out value="${flist.fileId }" />" />

					<h4 class="tit">정보</h4>
					<table class="table_style table_t_left th_v_m" id="trab">
				
					
					
	
	
						<colgroup>
							<col style="width:140px;" />
							<col />
							<col style="width:140px;" />
							<col />
						</colgroup>
							<tr>
								<th scope="row">회사명</th>
								<td>
									<input type="text" class="text" style="width:180px;" id="comNm" name="comNm" value="<c:out value="${result.comNm}" />" />
								</td>
							</tr>
							<tr>
								<th scope="row">작성자</th>
								<td>
									<input type="text" class="text" style="width:180px;" id="writer" name="writer" value="<c:out value="${result.writer}" />" />
								</td>
							</tr>
							<tr>
								<th scope="row">관리자</th>
								<td>
									<input type="text" class="text" style="width:180px;" id="manager" name="manager" value="<c:out value="${result.manager}" />" />
								</td>
							</tr>
							<tr>
								<th scope="row">조회수</th>
								<td>
									<input type="text" class="text" style="width:180px;" id="hit" name="hit" value="<c:out value="${result.hit}" />" />
								</td>
							</tr>
							<tr>
								<th scope="row">직급</th>
								<td>
									 <select name="positionId">
											<option value="">선택</option>
										<c:forEach var="plist" items="${pos}">
	     									<option value="${plist.positionId}" ${result.positionId == plist.positionId ? 'selected' : ''}>${plist.positionNm}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row">부서</th>
								<td>
									
									 <select name="deptId">
											<option value="">선택</option>
										<c:forEach var="list" items="${dpt}">
	     									<option value="${list.deptId}" ${result.deptId == list.deptId ? 'selected' : ''}>${list.deptNm}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							
							<tr>
								<th scope="row">내용</th>
								<td>
									<textarea name="content" id="content" rows="20" cols="70" value="<c:out value="${result.content}" />" >${result.content}</textarea>								
									<%-- <input type="text" class="text" style="width:500px; height:300px " id="content" name="content" value="<c:out value="${result.content}" />" /> --%>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td>
									<input type="file" name="abc" id="abc" value="${result.orgnlAtchFileNm}" />								
									<span style="color: mediumorchid;">※파일 추가 <input type="button" onclick="fnTrAdd();" name="plus" id="plus" value="+" /> 
									</span>
								</td>
							</tr>
							<%-- <tr id="trab">
								<th>첨부파일추가
									<button type="button" onclick="fnTrAdd();" name="plus" id="plus" value="+" />
									 <input type="button" onclick="fnTrAdd();" name="plus" id="plus" value="+" /> 
								</th>
								
								
								<td>
									<input type="file" name="abc" id="abc" value="${result.orgnlAtchFileNm}" />									
									&nbsp;<span style="color: mediumorchid;">※파일 추가.</span>
								</td>
							</tr> --%>
							
						</table>
						<table class="table_style table_t_left th_v_m" id="trab2">
						<colgroup>
							<col style="width:140px;" />
							<col />
							<col style="width:140px;" />
							<col />
						</colgroup>
							<tr>
								<th class="file" scope="row">첨부파일 다운로드</th>
								<td>
									 <span class="file">
									 		<%-- <a href="" class="btn btn-warning btn-small" onclick="fnFileDownload('<c:out value="${resultFile.fileId}" />');return false;"> --%>
									 		<c:forEach var="flist" items="${fresult}" varStatus="f">
										 		<c:choose>
										 			<c:when test="${flist.delYn eq 'N'}">
												 		<img src="/board/_common/img/icoFile2.gif" alt="파일 다운로드" />&nbsp;
												 		<a href="#" onclick="fnFileDownload(<c:out value="'${flist.id }','${flist.fileId}'" />);return false;">
												 			${flist.orgnlAtchFileNm}			
												 		</a>
												 		<button name="fileDelete" id="delYn"  onclick="fnDelete2(<c:out value="'${flist.id}','${flist.fileId}'" />);return false;" >첨부파일삭제</button>
										 			</c:when>
											 		<c:otherwise>
											 		</c:otherwise>
										 		</c:choose>
										 		<br />
											</c:forEach>
									  </span>
								</td>
							</tr>
							</table>
									 		 

					<%@ include file="/WEB-INF/jsp/exts/cpf/com/write_bottom.jsp" %>
</form>
 <c:choose>
 	<c:when test="${result.delYn eq 'N'}">
		<button name="fileDelete" id="delYn"  onclick="fnDelete2(<c:out value="'${result.id }','${result.fileId}'" />);" >첨부파일삭제</button>
    </c:when>
    <c:otherwise>
   		<button name="fileDelete" id="delYn" disabled onclick="fnDelete2(<c:out value="'${result.id }','${result.fileId}'" />);" >첨부파일삭제</button>
    </c:otherwise>
  </c:choose>
   
   <!-- <button onclick="fnTrAdd();" name="plus" id="plus">첨부파일추가</button> -->
<%@ include file="/WEB-INF/jsp/exts/cpf/com/footer.jsp" %>
