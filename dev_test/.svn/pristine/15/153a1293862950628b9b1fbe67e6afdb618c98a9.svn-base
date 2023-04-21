<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<script type="text/javascript" src="/resources/js/exts/com/mbrLog/mbrLogList.js"></script>

<form id="writePageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
	<input type="hidden" id="cmlMode" name="cmlMode" value="" />
	<input type="hidden" id="mbrLogSn" name="mbrLogSn" value="" />
</form>
<form id="listPageForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value="${searchVO.pageIndex }" />" />
</form>
			<form action="?">
			
		<div class="box_w_gray">
			<div class="ig_wrap">
				<div class="ig_s">
					<label for="searchCondition">검색종류</label>
					<select id="searchCondition" name="searchCondition" class="st_select">
						<option value="1"${param.searchCondition == '1'?' selected="selected"':'' }>회원ID</option>
						<option value="2"${param.searchCondition == '2'?' selected="selected"':'' }>메뉴명</option>
						<option value="3"${param.searchCondition == '3'?' selected="selected"':'' }>로그내용</option>
					</select>
				</div>
				<div class="ig_l">	
					<label for="searchKeyword" class="comment">검색어 입력</label>
					<input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" class="st_input" placeholder="검색어를 입력하세요"/>
				</div>
				<div class="ig_s">
					<button type="submit" class="btn-input-search">조회</button>
				</div>
			</div>
		</div>
			</form>
			<div class="con_b_tp">
				<p class="b_total FloatLeft">총<span><fmt:formatNumber value="${resultCnt}" /></span>건</p>
			</div>

				<!-- 필요시 exceltemplate 추가 후 주석 제거 -->
				<!-- <div class="btn_right_hd">
					<button class="btn_bdgreen_25" type="button" onclick="ComFns.excelDownload()">엑셀저장</button>
				</div> -->
				
				<table class="table_style thd_v_m">
					<thead>
						<tr>
							<th scope="col">회원ID</th>
							<th scope="col">회원명</th>
							<th scope="col">로그일자</th>
							<th scope="col">메뉴명</th>
							<th scope="col">로그등록일시</th>
							<th scope="col">로그내용</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<fmt:formatDate var="logRegDt" value="${result.logRegDt}" pattern="yyyy-MM-dd HH:mm:ss" />
						<c:set var="no" value="${resultCnt - (((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage) + status.index)}" />
						<tr>
							<%-- <td><a href="#" onclick="javascript:fnView('<c:out value="${result.mbrLogSn}" />');return false;"><c:out value="${result.mbrLogSn}" /></a></td> --%>
							<td><c:out value="${result.mbrLogin}" /></td>
							<td><c:out value="${result.mbrNm}" /></td>
							<td><c:out value="${jtag:convertDateSplitString(result.logDate, '-')}" /></td>
							<td><c:out value="${result.menuNm}" /></td>
							<td><c:out value="${logRegDt}" /></td>
							<td><c:out value="${result.logCnts}" /></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

<div class="con_b_bt AlignCenter on">
	<div class="paging">
		<ul>
			<ui:pagination paginationInfo="${paginationInfo}"   type="koreahana"   jsFunction="fnPage"   />
		</ul>
	</div>
</div>


<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>
