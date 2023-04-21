<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/init.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('#indivCdSortSn').number(true);

	var ajform = new ComAjaxForm('writeForm','listPageForm', {reload:'Y'});
	ajform.init();

});
function fnModify(indivCd){
	$.ajax({
		url:actionUrl,
		data:{ccMode:'viewJson',indivCd:indivCd},
		success:function(resultData){
			$('#indivCd').val(resultData.indivCd);
			$('#upIndivCd').val(resultData.upIndivCd);
			$('#indivCdNm').val(resultData.indivCdNm);
			$('#indivCdExpln').val(resultData.indivCdExpln);
			$('#indivCdSortSn').val(resultData.indivCdSortSn);
			$('#indivCdChgYn').val(resultData.indivCdChgYn);
			$('#indivCdVlFrst').val(resultData.indivCdVlFrst);
			$('#indivCdVlScndry').val(resultData.indivCdVlScndry);
		}
	});
}

function fnFormReset(){
	$('#indivCd').val('');
	$('#upIndivCd').val('');
	$('#indivCdNm').val('');
	$('#indivCdExpln').val('');
	$('#indivCdSortSn').val('');
	$('#indivCdChgYn').val('');
	$('#indivCdVlFrst').val('');
	$('#indivCdVlScndry').val('');
}
</script>
<form id="writeForm" method="post" action="${actionUrl }">
<input type="hidden" id="ccMode" name="ccMode" value="writeActionJson" />
<input type="hidden" id="indivCd" name="indivCd" value="" />
<input type="hidden" id="groupCd" name="groupCd" value="<c:out value="${param.groupCd }" />" />
					<div class="cell_box">
						<h4 class="s_tit" id="writeTitle">코드관리</h4>
						<table class="board_view">
							<colgroup>
								<col style="width:140px;" />
								<col />
							</colgroup>
							<tr>
								<th scope="row">상위코드</th>
								<td>
								<c:if test="${!empty uprCodeList }">
									<select id="upIndivCd" name="upIndivCd" class="select">
									<c:forEach items="${uprCodeList }" var="uicd">
										<option value="<c:out value="${uicd.indivCd }" />"><c:out value="${uicd.indivCdNm }" /></option>
									</c:forEach>
									</select>
								</c:if>
								</td>
							</tr>
							<tr>
								<th scope="row">코드명</th>
								<td><input type="text" class="text" id="indivCdNm" name="indivCdNm" value="" /></td>
							</tr>
							<tr>
								<th scope="row">코드설명</th>
								<td><input type="text" class="text" id="indivCdExpln" name="indivCdExpln" value="" /></td>
							</tr>
							<tr>
								<th scope="row">순서</th>
								<td><input type="text" class="text" id="indivCdSortSn" name="indivCdSortSn" value="" /></td>
							</tr>
							<tr>
								<th scope="row">사용여부</th>
								<td>
								<select id="indivCdChgYn" name="indivCdChgYn" class="select">
									<option value="Y">Y</option>
									<option value="N">N</option>
								</select>
								</td>
							</tr>
							<tr>
								<th scope="row">추가코드값1</th>
								<td><input type="text" class="text" id="indivCdVlFrst" name="indivCdVlFrst" value="" /></td>
							</tr>
							<tr>
								<th scope="row">추가코드값2</th>
								<td><input type="text" class="text" id="indivCdVlScndry" name="indivCdVlScndry" value="" /></td>
							</tr>
						</table>
						<div class="btn_center">				
						<c:if test="${isStreAuth }">			
							<button class="btn_bgblackgreen_35" type="button" id="newBtn" onclick="fnFormReset();return false;" >신규</button>
							<button class="btn_bgblackgreen_35" type="submit" id="saveBtn" >저장</button>
						</c:if>
						</div>
					</div>
</form>
				
					<div class="table_vertical_scroll_wrap">
						<h4 class="s_tit">코드내역</h4>
                        <table class="board_list">
                            <thead>
                                <tr>
                                    <th scope="col">NO.</th>
                                    <th scope="col">코드명</th>
                                    <th scope="col">상위코드명</th>
                                    <th scope="col">사용여부</th>
                                    <th scope="col">순서</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${resultList }" var="result" varStatus="status">
                            	<tr>
                            		<td><a href="#" onclick="fnModify('${result.indivCd}');return false;">${status.index + 1 }</a></td>
                            		<td><a href="#" onclick="fnModify('${result.indivCd}');return false;"><c:out value="${result.indivCdNm }" /></a></td>
                            		<td>
                            		<c:if test="${result.indivCd != result.upIndivCd }">
                            			<a href="#" onclick="fnModify('${result.indivCd}');return false;"><c:forEach items="${uprCodeList }" var="uicd"><c:if test="${uicd.indivCd == result.upIndivCd }"><c:out value="${uicd.indivCdNm }" /></c:if></c:forEach></a>
                            		</c:if>
                            		</td>
                            		<td><a href="#" onclick="fnModify('${result.indivCd}');return false;"><c:out value="${result.indivCdChgYn }" /></a></td>
                            		<td><a href="#" onclick="fnModify('${result.indivCd}');return false;"><c:out value="${result.indivCdSortSn }" /></a></td>
                            	</tr>
                            </c:forEach>
                           	</tbody>
                         </table>
					</div>