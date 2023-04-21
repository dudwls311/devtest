<%
/**
 *@version 3.2.0.1
 **/
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/jnit/_common/header.jsp" %>

<script type="text/javascript">
<!--

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.getElementById("listForm").screenMode.value = "";
	document.getElementById("listForm").pageIndex.value = pageNo;
	document.getElementById("listForm").action = "<c:url value='/cms/board/info/history/list.do'/>";
   	document.getElementById("listForm").submit();
}

$(document).ready(function(){
	$("#fnSearch").on("click", function(){
		$("#listForm").submit();
	});
});
 // -->
</script>

<div id="content">
	<%@ include file="/WEB-INF/jsp/jnit/_common/content_header.jsp" %>
	<div class="content_view container-fluid">
		<form:form commandName="searchVO" name="listForm" id="listForm" method="post">
		<input type="hidden" name="screenMode" />
		
		<%-- Contents Start --%>
		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<h3>접속로그</h3>
					</div>
					<div class="widget-title">
						<select name="searchCondition" id="searchCondition" >
							<option value="0" ${searchVO.searchCondition == '0' ? 'selected' : searchVO.searchCondition == '' ? 'selected' : '' }>전체</option>
							<option value="1" ${searchVO.searchCondition == '1' ? 'selected' : '' } >게시판 ID</option>
							<option value="2" ${searchVO.searchCondition == '2' ? 'selected' : '' }>게시판 제목</option>
							<option value="3" ${searchVO.searchCondition == '3' ? 'selected' : '' }>회원ID</option>
							<option value="4" ${searchVO.searchCondition == '4' ? 'selected' : '' }>IP</option>
						</select>
						<input type='text' name='searchKeyword' id='searchKeyword' value="<c:out value="${searchVO.searchKeyword }" />" />
						<button type="button" id="fnSearch" class="btn btn-small">검색</button>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped table-hover">				
							<thead>	  
								<tr>
									<th>게시판 ID</th>
									<th>게시판 제목</th>
									<!-- <th>게시판 사이트</th> -->
									<th>회원ID</th>
									<th>처리정보</th>
									<th>처리날짜</th>
									<th>IP</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<fmt:formatDate var="actionDate" value="${result.actionDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
									<tr>
										<td><c:out value="${result.boardId}"/></td>
										<td><c:out value="${result.boardTitle}"/></td>
										<%-- <td><c:out value="${result.boardGroup}"/></td> --%>
										<td><c:out value="${result.mbrLogin}"/></td>
										<td><c:out value="${result.action == 'add' ? '추가' : result.action == 'edit' ? '수정' : result.action == 'delete' ? '삭제' : ''}"/></td>
										<td><c:out value="${actionDate}"/></td>
										<td><c:out value="${result.actionIp}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>										
					</div>
				</div>
			</div>
		</div>
		<div id="page_navi" class="pagination">
				<ui:pagination paginationInfo = "${paginationInfo}"
				   type="jnitDefault"
				   jsFunction="fn_egov_link_page" />
				<form:hidden path="pageIndex" />
		</div>
		</form:form>
		<%-- Contents End --%>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/jnit/_common/footer.jsp" %>