<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<script type="text/javascript" src="/resources/js/exts/com/indvlzMenuAuth/list.js"></script>
<form id="listForm" action="?">
	<input type="hidden" name="searchCondition" value="<c:out value="${param.searchCondition }" />" />
	<input type="hidden" name="searchKeyword" value="<c:out value="${param.searchKeyword }" />" />
</form>


				<div class="cell_wrap">

					<div class="cell_box left_group">
					<form name="searchForm" id="searchForm" action="?" >
						<div class="search_box">
							<input type="text" name="searchKeyword" id="searchKeyword" value="<c:out value="${param.searchKeyword }" />"/>
							<button class="btn_search" type="button" id="searchBtn">검색</button>
						</div>
					</form>						
						<div class="search_glist">
							<ul>
							<c:forEach items="${userList }" var="user">
								<c:set var="grpNm" value="" />
								<c:forEach items="${groupList }" var="group"><c:if test="${group.typeId == user.typeId }"><c:set var="grpNm" value="${group.typeNm }" /></c:if></c:forEach>
								<li data-mbrid="<c:out value="${user.mbrId }" />" data-grpnm="<c:out value="${grpNm }" />" data-mbrnm="<c:out value="${user.mbrNm }" />">
									<c:out value="${user.mbrNm }" />
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>

  <form id="writeForm" action="?" method="post">
  <input type="hidden" id="mbrId" name="mbrId" value="" />
  <input type="hidden" name="cmumaMode" value="writeActionJson" />
					<div class="cell_box">
						<table class="c_tit">
							<tr>
								<th scope="row">그룹명</th>
								<td id="grpNmTd"></td>
								<th scope="row">사용자명</th>
								<td id="mbrNmTd"></td>
								<td><button class="btn_bggreen_35 btn_st btn_c_gr" type="button" id="saveBtn">저장</button></td>
							</tr>
						</table>
						<table class="board_list table_style">
							<colgroup>
								<col style="width:22%;" />
								<col style="width:22%;" />
								<col style="width:22%;" />
<!-- 								<col style="width:22%;" /> -->
<!-- 								<col /> -->
								<col style="width:300px;" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col">1뎁스</th>
									<th scope="col">2뎁스</th>
									<th scope="col">3뎁스</th>
<!-- 									<th scope="col">4뎁스</th> -->
<!-- 									<th scope="col"></th> -->
									<th scope="col">권한</th>
								</tr>
							</thead>
							<tbody>
							
                                                      <c:forEach items="${menuList }" var="lmenu"><c:if test="${lmenu.menuLvlVl == '1' }">
                                                      	<c:forEach items="${resultList }" var="result"><c:if test="${smenu.menuSn == result.menuSn }">
											            	<c:if test="${!empty result.redngAuthYn }"><c:set var="lcheckedRedng" value=" checked" /></c:if>
											            	<c:if test="${!empty result.delAuthYn }"><c:set var="lcheckedDel" value=" checked" /></c:if>
											            	<c:if test="${!empty result.streAuthYn }"><c:set var="lcheckedStre" value=" checked" /></c:if>
											            	<c:if test="${!empty result.prntgAuthYn }"><c:set var="lcheckedPrntg" value=" checked" /></c:if>
											            </c:if></c:forEach>
											            <c:set var="lpre" value="${lmenu.menuSn }_" />
                                                                    <tr>
                                                                        <td><c:out value="${lmenu.menuNm }" /></td>
                                                                        <td></td>
                                                                        <td></td>
<!--                                                                         <td></td> -->
<!--                                                                         <td></td> -->
                                                                        <td>
                                                                            <input${lcheckedRedng } type="checkbox" id="${lpre }redngAuthYn" name="${lpre }redngAuthYn" value="Y" data-id="<c:out value="${lmenu.menuSn }" />" data-parent="<c:out value="${lmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="" data-smenu="" data-auth="redngAuthYn" class="checkbox"><label for="${lpre }redngAuthYn">읽기</label>
                                                                            <input${lcheckedStre } type="checkbox" id="${lpre }streAuthYn" name="${lpre }streAuthYn" value="Y" data-id="<c:out value="${lmenu.menuSn }" />" data-parent="<c:out value="${lmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="" data-smenu="" data-auth="streAuthYn" class="checkbox"><label for="${lpre }streAuthYn">저장</label>
                                                                            <input${lcheckedDel } type="checkbox" id="${lpre }delAuthYn" name="${lpre }delAuthYn" value="Y" data-id="<c:out value="${lmenu.menuSn }" />" data-parent="<c:out value="${lmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="" data-smenu="" data-auth="delAuthYn" class="checkbox"><label for="${lpre }delAuthYn">삭제</label>
                                                                            <!-- <input${lcheckedDel } type="checkbox" id="${lpre }prntgAuthYn" name="${lpre }prntgAuthYn" value="Y" data-id="<c:out value="${lmenu.menuSn }" />" data-parent="<c:out value="${lmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="" data-smenu="" data-auth="prntgAuthYn" class="checkbox"><label for="${lpre }prntgAuthYn">출력</label> -->
                                                                        </td>
                                                                    </tr>
                                                        <c:forEach items="${menuList }" var="mmenu"><c:if test="${lmenu.menuSn == mmenu.uprMenuSn && mmenu.menuLvlVl == '2' }">
											            	<c:set var="mcheckedRedng" value="" /><c:set var="mcheckedDel" value="" /><c:set var="mcheckedStre" value="" /><c:set var="mcheckedMaster" value="" />
											            	<c:forEach items="${resultList }" var="result"><c:if test="${mmenu.menuSn == result.menuSn }">
											            		<c:if test="${!empty result.redngAuthYn }"><c:set var="mcheckedRedng" value=" checked" /></c:if>
											            		<c:if test="${!empty result.delAuthYn }"><c:set var="mcheckedDel" value=" checked" /></c:if>
											            		<c:if test="${!empty result.streAuthYn }"><c:set var="mcheckedStre" value=" checked" /></c:if>
											            		<c:if test="${!empty result.prntgAuthYn }"><c:set var="mcheckedPrntg" value=" checked" /></c:if>
											            	</c:if></c:forEach>				
											            	<c:set var="isMmenuViewable" value="${isAdmin?true:false }" /><%//각 부서에 따른 메뉴설정을 막아야 됨 %>
											            	<%//생활안전부 %>
										            		<c:if test="${loginVO.orgId == '20030' && fn:indexOf('1010000,1020000,2010000,2020000,2030000,4020000,4040000',mmenu.menuSn) >= 0}"><c:set var="isMmenuViewable" value="${true }" /></c:if>
										            		<%//교육지원부 %>
										            		<c:if test="${loginVO.orgId == '20029' && fn:indexOf('1030000,1040000,1050000,1060000,1070000,2040000,2050000,2060000,2070000,2080000,4020000,4040000',mmenu.menuSn) >= 0 }"><c:set var="isMmenuViewable" value="${true }" /></c:if>
										            		<%//자립지원부 %>
										            		<c:if test="${loginVO.orgId == '20031' && fn:indexOf('1080000,1090000,2090000,2100000,2110000,2120000,2130000,4020000,4040000',mmenu.menuSn) >= 0 }"><c:set var="isMmenuViewable" value="${true }" /></c:if>
										            		<c:if test="${isMmenuViewable }">
											            	<c:set var="mpre" value="${mmenu.menuSn }_" />							            			
                                                                    <tr>
                                                                        <td><c:out value="${lmenu.menuNm }" /></td>
                                                                        <td><c:out value="${mmenu.menuNm }" /></td>
                                                                        <td></td>
<!--                                                                         <td></td> -->
<!--                                                                         <td></td> -->
                                                                        <td>
                                                                            <input${mcheckedRedng } type="checkbox" id="${mpre }redngAuthYn" name="${mpre }redngAuthYn" value="Y" data-id="<c:out value="${mmenu.menuSn }" />" data-parent="<c:out value="${mmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="" data-auth="redngAuthYn" class="checkbox"><label for="${mpre }redngAuthYn">읽기</label>
                                                                            <input${mcheckedStre } type="checkbox" id="${mpre }streAuthYn" name="${mpre }streAuthYn" value="Y" data-id="<c:out value="${mmenu.menuSn }" />" data-parent="<c:out value="${mmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="" data-auth="streAuthYn" class="checkbox"><label for="${mpre }streAuthYn">저장</label>
                                                                            <input${mcheckedDel } type="checkbox" id="${mpre }delAuthYn" name="${mpre }delAuthYn" value="Y" data-id="<c:out value="${mmenu.menuSn }" />" data-parent="<c:out value="${mmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="" data-auth="delAuthYn" class="checkbox"><label for="${mpre }delAuthYn">삭제</label>
                                                                            <!-- <input${mcheckedDel } type="checkbox" id="${mpre }prntgAuthYn" name="${mpre }prntgAuthYn" value="Y" data-id="<c:out value="${mmenu.menuSn }" />" data-parent="<c:out value="${mmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="" data-auth="prntgAuthYn" class="checkbox"><label for="${mpre }prntgAuthYn">출력</label> -->
                                                                        </td>
                                                                    </tr>
                                                            </c:if>
											                <c:forEach items="${menuList }" var="smenu"><c:if test="${mmenu.menuSn == smenu.uprMenuSn && smenu.menuLvlVl == '3' }">
											                	<c:set var="scheckedRedng" value="" /><c:set var="scheckedDel" value="" /><c:set var="scheckedStre" value="" /><c:set var="scheckedMaster" value="" />
											            		<c:forEach items="${resultList }" var="result"><c:if test="${smenu.menuSn == result.menuSn }">
												            		<c:if test="${!empty result.redngAuthYn }"><c:set var="scheckedRedng" value=" checked" /></c:if>
												            		<c:if test="${!empty result.delAuthYn }"><c:set var="scheckedDel" value=" checked" /></c:if>
												            		<c:if test="${!empty result.streAuthYn }"><c:set var="scheckedStre" value=" checked" /></c:if>
												            		<c:if test="${!empty result.prntgAuthYn }"><c:set var="scheckedPrntg" value=" checked" /></c:if>
											            		</c:if></c:forEach> 	
											            		<c:set var="isSmenuViewable" value="${isAdmin?true:false }" />
											            	
											            		<c:set var="spre" value="${smenu.menuSn }_" />								            			
                                                                   <tr>
                                                                       <td><c:out value="${lmenu.menuNm }" /></td>
                                                                       <td><c:out value="${mmenu.menuNm }" /></td>
                                                                       <td><c:out value="${smenu.menuNm }" /></td>
<!--                                                                         <td></td> -->
<!--                                                                         <td></td> -->
                                                                       <td>
                                                                           <input${scheckedRedng } type="checkbox" id="${spre }redngAuthYn" name="${spre }redngAuthYn" value="Y" data-id="<c:out value="${smenu.menuSn }" />" data-parent="<c:out value="${smenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-auth="redngAuthYn" class="checkbox"><label for="${spre }redngAuthYn">읽기</label>
                                                                           <input${scheckedStre } type="checkbox" id="${spre }streAuthYn" name="${spre }streAuthYn" value="Y" data-id="<c:out value="${smenu.menuSn }" />" data-parent="<c:out value="${smenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-auth="streAuthYn" class="checkbox"><label for="${spre }streAuthYn">저장</label>
                                                                           <input${scheckedDel } type="checkbox" id="${spre }delAuthYn" name="${spre }delAuthYn" value="Y" data-id="<c:out value="${smenu.menuSn }" />" data-parent="<c:out value="${smenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-auth="delAuthYn" class="checkbox"><label for="${spre }delAuthYn">삭제</label>
                                                                           <!-- <input${scheckedDel } type="checkbox" id="${spre }prntgAuthYn" name="${spre }prntgAuthYn" value="Y" data-id="<c:out value="${smenu.menuSn }" />" data-parent="<c:out value="${smenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-auth="prntgAuthYn" class="checkbox"><label for="${spre }prntgAuthYn">출력</label> -->
                                                                       </td>
                                                                   </tr>
												                <c:forEach items="${menuList }" var="tmenu"><c:if test="${smenu.menuSn == tmenu.uprMenuSn && tmenu.menuLvlVl == '4' }">
												                	<c:set var="tcheckedRedng" value="" /><c:set var="tcheckedDel" value="" /><c:set var="tcheckedStre" value="" /><c:set var="tcheckedPrntg" value="" />
												            		<c:forEach items="${resultList }" var="result"><c:if test="${tmenu.menuSn == result.menuSn }">
													            		<c:if test="${!empty result.redngAuthYn }"><c:set var="tcheckedRedng" value=" checked" /></c:if>
													            		<c:if test="${!empty result.delAuthYn }"><c:set var="tcheckedDel" value=" checked" /></c:if>
													            		<c:if test="${!empty result.streAuthYn }"><c:set var="tcheckedStre" value=" checked" /></c:if>
													            		<c:if test="${!empty result.prntgAuthYn }"><c:set var="tcheckedPrntg" value=" checked" /></c:if>
												            		</c:if></c:forEach> 	
											            			<c:set var="isTmenuViewable" value="${isAdmin?true:false }" />
											            			<c:if test="${isMmenuViewable && isSmenuViewable && isTmenuViewable }">
												            		<c:set var="tpre" value="${tmenu.menuSn }_" />													            								            			
	                                                                    <tr>
	                                                                        <td><c:out value="${lmenu.menuNm }" /></td>
	                                                                        <td><c:out value="${mmenu.menuNm }" /></td>
	                                                                        <td><c:out value="${smenu.menuNm }" /></td>
<%-- 	                                                                        <td><c:out value="${tmenu.menuNm }" /></td> --%>
<!-- 	                                                                        <td></td> -->
	                                                                        <td>
	                                                                            <input${tcheckedRedng } type="checkbox" id="${tpre }redngAuthYn" name="${tpre }redngAuthYn" value="Y" data-id="<c:out value="${tmenu.menuSn }" />" data-parent="<c:out value="${tmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-tmenu="<c:out value="${tmenu.menuSn }" />" data-auth="redngAuthYn" class="checkbox"><label for="${tpre }redngAuthYn">읽기</label>
	                                                                            <input${tcheckedStre } type="checkbox" id="${tpre }streAuthYn" name="${tpre }streAuthYn" value="Y" data-id="<c:out value="${tmenu.menuSn }" />" data-parent="<c:out value="${tmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-tmenu="<c:out value="${tmenu.menuSn }" />" data-auth="streAuthYn" class="checkbox"><label for="${tpre }streAuthYn">저장</label>
	                                                                            <input${tcheckedDel } type="checkbox" id="${tpre }delAuthYn" name="${tpre }delAuthYn" value="Y" data-id="<c:out value="${tmenu.menuSn }" />" data-parent="<c:out value="${tmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-tmenu="<c:out value="${tmenu.menuSn }" />" data-auth="delAuthYn" class="checkbox"><label for="${tpre }delAuthYn">삭제</label>
	                                                                            <!-- <input${tcheckedDel } type="checkbox" id="${tpre }prntgAuthYn" name="${tpre }prntgAuthYn" value="Y" data-id="<c:out value="${tmenu.menuSn }" />" data-parent="<c:out value="${tmenu.uprMenuSn }" />" data-lmenu="<c:out value="${lmenu.menuSn }" />" data-mmenu="<c:out value="${mmenu.menuSn }" />" data-smenu="<c:out value="${smenu.menuSn }" />" data-tmenu="<c:out value="${tmenu.menuSn }" />" data-auth="prntgAuthYn" class="checkbox"><label for="${tpre }prntgAuthYn">출력</label> -->
	                                                                        </td>
	                                                                    </tr>
	                                                                </c:if>
												            	</c:if></c:forEach>
											            	</c:if></c:forEach>
											           	</c:if></c:forEach>
                                                      </c:if></c:forEach>
							</tbody>
						</table>
					</div>
</form>
				</div>

<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>