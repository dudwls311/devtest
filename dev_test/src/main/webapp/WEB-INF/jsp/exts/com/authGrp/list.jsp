<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>

<script type="text/javascript" src="/html/exts/com/authGrp/list.js"></script>

<form id="listForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${searchVO.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${searchVO.searchKeyword }" />" />
</form>


				<div class="cell_wrap">

					<div class="cell_box left_group">
					<form name="searchForm" id="searchForm" action="?" >
					<input type="hidden" name="searchCondition" value="1" />
						<div class="search_box">
							<input type="text" name="searchKeyword" id="searchKeyword" value="<c:out value="${param.searchKeyword }" />"/>
							<button class="btn_search" type="button" id="searchBtn" type="submit">검색</button>
						</div>
					</form>
						<div class="search_glist">
							<ul>
								<c:forEach items="${resultList }" var="result">
                                    <li data-authgrpid="<c:out value="${result.authGrpId }" />" data-cogname="<c:out value="${result.grpNm }" />">
                                        <c:out value="${result.grpNm }" />
                                    </li>
                                </c:forEach>
							</ul>
						</div>
					</div>

					<div class="cell_box">
						
<form id="writeForm" action="?">
<input type="hidden" name="coagMode" value="writeActionJson" />
<input type="hidden" id="authGrpId" name="authGrpId" value="" />
						<div class="btn_right_hd mgt30">
							<button class="btn_bggreen_25" type="button" id="btnAdd">그룹 추가</button>
						</div>
						<table class="board_view">
							<colgroup>
								<col style="width:140px;" />
								<col />
							</colgroup>
							<tr>
								<th scope="row">그룹명</th>
								<td><input type="text" class="text" style="width:210px;" name="grpNm" id="grpNm" value="" /></td>
							</tr>
							<tr>
								<th scope="row">그룹설명</th>
								<td><input type="text" class="text" style="width:210px;" name="grpExplnt" id="grpExplnt" value="" /></td>
							</tr>
							<tr>
								<th scope="row">사용여부</th>
								<td>
									<div class="clear_box">
										<input type="radio" class="radiobox" name="delYn" id="delYn_N" value="N"/><label for="delYn_N">사용</label>
										<input type="radio" class="radiobox" name="delYn" id="delYn_Y" value="Y"/><label for="delYn_Y">사용안함</label>
									</div>
								</td>
							</tr>
						</table>
						<div class="btn_center">
							<button type="button" class="btn_bgblackgreen_35" id="btnSave">저장</button>
						</div>
</form>
					</div>
				</div>



<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>