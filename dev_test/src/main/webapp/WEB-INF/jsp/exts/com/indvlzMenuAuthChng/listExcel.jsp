<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/init.jsp" %>
<%
response.setHeader("Content-Disposition", "attachment; filename=grouphistory.xls");
response.setHeader("Content-Description", "JSP Generated Data");
response.setContentType("application/vnd.ms-excel;charset=utf-8");
%>
                            <table summary="이 표는 그룹별권한변경 이력 관리을 나타내고 있다." border="1">
                                <thead>
                                    <tr>
                                        <th scope="col">그룹</th>
                                        <th scope="col">메뉴</th>
                                        <th scope="col">목록권한</th>
                                        <th scope="col">상세권한</th>
                                        <th scope="col">쓰기권한</th>
                                        <th scope="col">변경일자</th>
                                        <th scope="col">변경자</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${resultList }" var="result">
                                    <tr>
                                        <td><c:out value="${result.cogName }" /></td>
                                        <td>
                                        <c:set var="menuName" value="" />
                                        <c:set var="menuParentName" value="" />
                                        <c:set var="menuParentParentName" value="" />
                                        <c:forEach items="${menuList }" var="menu">
                                        	<c:if test="${result.cmmId == menu.cmmId }"><c:set var="menuName" value="${menu.cmmName }" /></c:if>
                                        	<c:if test="${result.cmmParentId == menu.cmmId }"><c:set var="menuParentName" value="${menu.cmmName }" /></c:if>
                                        	<c:if test="${result.cmmParentParentId == menu.cmmId }"><c:set var="menuParentParentName" value="${menu.cmmName }" /></c:if>
                                        </c:forEach>
                                        	<c:if test="${!empty menuParentParentName }"><c:out value="${menuParentParentName }" /> <span class="ml10 mr10">&gt;</span> </c:if>
                                        	<c:if test="${!empty menuParentName }"><c:out value="${menuParentName }" /> <span class="ml10 mr10">&gt;</span> </c:if>
                                        	<c:out value="${menuName }" />
                                        </td>
                                        <td>${result.cmgmaList == '1'?'O':'' }</td>
                                        <td>${result.cmgmaView == '1'?'O':'' }</td>
                                        <td>${result.cmgmaWrite == '1'?'O':'' }</td>
                                        <td><fmt:formatDate value="${result.created }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td><c:out value="${result.createdUserName }" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>