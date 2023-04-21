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
		$("#cbeMode").val('write');
		$("#writePageForm").submit();
		return false;
	}
	
	//삭제
	function fnDelete(){
		if(confirm(Msg.com.confirmDelete)){
			ComFns.ajax(actionUrl, {cbeMode:'deleteActionJson',id:$('#id').val()}, fnList);
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

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${param.pageIndex }" />" />
	<input type="hidden" name="cbeMode" value="write" />
	<input type="hidden" id="id" name="id" value="<c:out value="${result.id }" />" />
	<input type="hidden" id="fileId" name="fileId" value="<c:out value="${result.fileId }" />" />
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
							<th scope="row">PK값</th>
							<td>
								<input type="text" value=" <c:out value="${result.id}" />" readonly />
							</td>
						</tr>
						<tr>
							<th scope="row">회사명</th>
							<td>
								<input type="text" value="<c:out value="${result.comNm}" />" readonly />
							</td>
						</tr>
						<tr>							
							<th scope="row">작성자</th>
							<td>
								<input type="text" value="<c:out value="${result.writer}"/>" readonly />
							</td>
						</tr>
						<tr>
							<th scope="row">관리자</th>
							<td>
								<input type="text" value="<c:out value="${result.manager}"/>" readonly />
							</td>
						</tr>
						<tr>
							<th scope="row">조회수</th>
							<td>
								<input type="text" value="<c:out value="${result.hit}"/>" readonly />
							</td>
						</tr>
						<tr>
							<th scope="row">직급</th>
							<td>
								<input type="text" value="<c:out value="${result.positionNm}"/>" readonly />
							</td>
						</tr>
						<tr>
							<th scope="row">부서</th>
							<td>
								<c:out value="${result.deptNm}"/>
							</td>
						</tr>
						<tr>
							<th scope="row">내용</th>
							<td>
								<textarea readonly name="content" id="content" rows="20" cols="70" value="<c:out value="${result.content}" />" >${result.content}</textarea>
							</td>
						</tr>
						<tr>
							<th class="file" scope="row">첨부파일 다운로드</th>
							<td>
							&nbsp;&nbsp;
								<span class="file">
							 		<c:forEach var="flist" items="${fresult}" varStatus="f">
							 		<c:choose>
							 			<c:when test="${flist.delYn eq 'N'}">
										 		<img src="/board/_common/img/icoFile2.gif" alt="파일 다운로드" />&nbsp;
										 		<a href="#" onclick="fnFileDownload(<c:out value="'${flist.id }','${flist.fileId}'" />); return false;">
										 			${flist.orgnlAtchFileNm}			
										 		</a>
										 		<br />
							 			</c:when>
							 			<c:otherwise>
							 			</c:otherwise>
							 		</c:choose>
											</c:forEach>
									  </span>
								</td>
						</tr>
						
					</table>

					<%@ include file="/WEB-INF/jsp/exts/cpf/com/view_bottom.jsp" %>
<%@ include file="/WEB-INF/jsp/exts/cpf/com/footer.jsp" %>
