<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<script type="text/javascript" src="/resources/js/exts/com/smpl/list.js"></script>

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
	<input type="hidden" id="csMode" name="csMode" value="" />
	<input type="hidden" id="smplId" name="smplId" value="" />
</form>
<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
</form>
			<form action="?">
				<div class="search_field align_right">
					<div class="option_box">
						<label for="searchCondition">검색종류</label>
						<select id="searchCondition" name="searchCondition" class="select" style="width:103px;">
							<option value="">전체</option>
							<option value="1"${param.searchCondition == '1'?' selected="selected"':'' }>제목</option>
							<option value="2"${param.searchCondition == '2'?' selected="selected"':'' }>내용</option>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" class="text" placeholder="검색어를 입력하세요" style="width:274px;" />
						<button class="btn_bggreen_29" type="submit">검색</button>
					</div>
				</div>
			</form>

				<p class="search_result_tit">검색결과 : <em><fmt:formatNumber value="${resultCnt}" /></em>건</p>

				<div class="btn_right_hd">
					<button class="btn_bdgreen_25" type="button" onclick="ComFns.excelDownload()">엑셀저장</button>
				</div>
				
				<table class="board_list mgt5">
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">ID</th>
							<th scope="col">명칭</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<fmt:formatDate var="regDt" value="${result.regDt}" pattern="yyyy.MM.dd" />
						<c:set var="no" value="${resultCnt - (((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage) + status.index)}" />
						<tr>
							<td><a href="#" onclick="javascript:fnView('<c:out value="${result.smplId}" />');return false;">${no }</a></td>
							<td><c:out value="${result.smplId}" /></td>
							<td><c:out value="${result.smplNm}" /></td>
							<td><c:out value="${result.rgtrId}" /></td>
							<td><c:out value="${regDt}" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<div class="paging">
					<ui:pagination paginationInfo="${paginationInfo}"   type="image"   jsFunction="fnPage"   />
				</div>

					<%@ include file="/WEB-INF/jsp/exts/com/list_bottom.jsp" %>


<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>