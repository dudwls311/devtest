<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<script type="text/javascript" src="/resources/js/exts/com/indvlzMenuAuthChng/list.js"></script>

<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
</form>
			<form action="?">
				<div class="search_field align_right">
					<div class="option_box">
						<label for="authGrpId">사용자그룹</label>
						<select id="authGrpId" name="authGrpId" class="select" style="width:103px;">
							<option value="">전체</option>
						<c:forEach items="${groupList }" var="group">
							<option value="<c:out value="${group.authGrpId }" />"${param.authGrpId == group.authGrpId?' selected="selected"':'' }><c:out value="${group.grpNm }" /></option>
							</c:forEach>
						</select>
						<input type="hidden" id="searchCondition" name="searchCondition" value="1"/>
						<input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" class="text" placeholder="검색어를 입력하세요" style="width:274px;" />
						<button class="btn_bggreen_29" type="submit">검색</button>
					</div>
				</div>
			</form>

				<p class="search_result_tit">검색결과 : <em><fmt:formatNumber value="${resultCnt}" /></em>건</p>

				
				<table class="board_list">
					<colgroup>
						<col style="width:12.73%" />
						<col />
						<col style="width:9.09%" />
						<col style="width:13.13%" />
						<col style="width:10.20%" />
						<col style="width:10.20%" />
						<col style="width:12.63%" />
						<col style="width:10.10%" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">그룹</th>
							<th scope="col">메뉴</th>
							<th scope="col">읽기권한</th>
							<th scope="col">저장권한</th>
							<th scope="col">삭제권한</th>
							<th scope="col">출력권한</th>
							<th scope="col">변경일자</th>
							<th scope="col">변경자</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<fmt:formatDate var="regDt" value="${result.regDt}" pattern="yyyy.MM.dd" />
						<tr>
							<td><c:out value="${result.grpNm}" /></td>
							<td><c:out value="${result.menuNm}" /></td>
							<td><c:out value="${result.redngAuthYn}" /></td>
							<td><c:out value="${result.streAuthYn}" /></td>
							<td><c:out value="${result.delAuthYn}" /></td>
							<td><c:out value="${result.prntgAuthYn}" /></td>
							<td><c:out value="${regDt}" /></td>
							<td><c:out value="${result.crtrNm}" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<div class="paging">
					<ui:pagination paginationInfo="${paginationInfo}"   type="image"   jsFunction="fnPage"   />
				</div>

<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>