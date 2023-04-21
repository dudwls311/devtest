<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/header.jsp" %>
<script>
	$(document).ready(function(){
	});
	
	//페이징
	function fnPage(p){
		$("#pageIndex").val(p);
		$("#listPageForm").submit();
	}
	
	//상세
	function fnView([firstVo]){
		$("#[mode_name]Mode").val('view');
		$("#[firstVo]").val([firstVo]);
		$("#writePageForm").submit();
	}
	
	
	//추가/수정
	function fnWrite([firstVo]){
		$("#[mode_name]Mode").val('write');
		$("#[firstVo]").val([firstVo]);
		$("#writePageForm").submit();
	}
</script>

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
	<input type="hidden" id="[mode_name]Mode" name="[mode_name]Mode" value="" />
	<input type="hidden" id="[firstVo]" name="[firstVo]" value="" />
</form>
<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
</form>
			<form action="?">
			
		<div class="box_w_gray">
			<div class="search_wrap">
				<div class="search_left">
					<label for="searchCondition">검색종류</label>
					<select id="searchCondition" name="searchCondition" class="st_select">
						<option value="0">전체</option>
						<option value="1"${param.searchCondition == '1'?' selected="selected"':'' }>제목</option>
						<option value="2"${param.searchCondition == '2'?' selected="selected"':'' }>내용</option>
					</select>
				</div>
				<div class="search_right">
					<label for="searchKeyword" class="comment">검색어 입력</label>
					<input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" class="st_input" placeholder="검색어를 입력하세요"/>
					<button type="submit" class="btn-input-search">조회</button>
				</div>
			</div>
		</div>
			</form>
			<div class="con_b_tp">
				<p class="b_total FloatLeft">총<span><fmt:formatNumber value="${resultCnt}" /></span>건</p>
				<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/list_bottom.jsp" %>
			</div>

				<!-- 필요시 exceltemplate 추가 후 주석 제거 -->
				<!-- <div class="btn_right_hd">
					<button class="btn_bdgreen_25" type="button" onclick="ComFns.excelDownload()">엑셀저장</button>
				</div> -->
				
				<table class="table_style thd_v_m">
					<thead>
						<tr>
							[JSP_LIST_TH]
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<fmt:formatDate var="regDt" value="${result.regDt}" pattern="${dateFormat }" />
						<c:set var="no" value="${resultCnt - (((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage) + status.index)}" />
						<tr>
							[JSP_LIST_TD]
							<td><c:out value="${result.rgtrId}" /></td>
							<td><c:out value="${regDt}" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

<div class="con_b_bt AlignCenter on">
	<div class="paging">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}"   type="cpfDefault"   jsFunction="fnPage"   />
		</ul>
	</div>
</div>


<%@ include file="/WEB-INF/jsp/[first_package]/[second_package]/com/footer.jsp" %>