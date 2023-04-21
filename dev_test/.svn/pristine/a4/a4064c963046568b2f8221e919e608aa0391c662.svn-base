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

<script type="text/javaScript" src="<c:url value="/resources/js/jnit/gstatic/charts/loader.js"/>" ></script>
<script type="text/javascript">
<!--

$(document).ready(function(){
	
	google.charts.load('current', {packages: ['corechart', 'bar']});
	google.charts.setOnLoadCallback(drawAxisTickColors);
	
	var dataArr = new Array();
	
	dataArr.push(['', '게시글 수', '조회수']);
	$(fnGetData()).each(function(fnIdx, fnObj){
		dataArr.push([fnObj['boardTitle'], fnObj['totalCount'], fnObj['totalView']]);
	});
	
	function drawAxisTickColors() {
		var data = google.visualization.arrayToDataTable(dataArr);

	        var options = {
	          chart: {
	            title: '게시물 통계 차트',
	            subtitle: '',
	          }
	        };
	        
	        var chart = new google.charts.Bar(document.getElementById('chart_div'));
	        chart.draw(data, google.charts.Bar.convertOptions(options));
	  }
});

function fnGetData(){
	var result = '';
	$.ajax({
		type:'post',
		url:'<c:url value="/cms/board/info/statistic/ajax.do" />',
		success:function(data){
			result = data;
		},
		error:function(error){
			result = null;
		},
		async:false
	});
	return $.parseJSON(result);
}

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
					<div id="chart_div"></div>
				</div>
				<div class="widget-box">
					<div class="widget-title">
						<h3>게시물 통계</h3>
					</div>
					<div class="widget-content nopadding">
						<table class="table table-bordered table-striped table-hover">				
							<thead>	  
								<tr>
									<th>게시판 ID</th>
									<th>게시판 제목</th>
									<th>게시글 수</th>
									<th>게시글 조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td><c:out value="${result.boardId}"/></td>
										<td><c:out value="${result.boardTitle}"/></td>
										<td><fmt:formatNumber value="${result.totalCount}"/></td>
										<td><fmt:formatNumber value="${result.totalView}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>										
					</div>
				</div>
			</div>
		</div>
		</form:form>
		<%-- Contents End --%>
	</div>
</div>
<%@ include file="/WEB-INF/jsp/jnit/_common/footer.jsp" %>