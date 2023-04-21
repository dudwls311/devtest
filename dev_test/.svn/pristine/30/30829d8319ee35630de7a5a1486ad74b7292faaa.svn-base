<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/exts/com/header.jsp" %>
<script>
$(document).ready(function(){
	$('#saveBtn').on('click',fnSave);

	//var ajform = new ComAjaxForm('writeForm','listPageForm', {});
	//ajform.init();
	
	$('[name=dbColumn]').on('paste', function(e){
		var obj = $('[name=dbColumn]');
		fnSetPasteData(obj, e);
	});
	
	$('[name=dbComment]').on('paste', function(e){
		var obj = $('[name=dbComment]');
		fnSetPasteData(obj, e);
	});
	
});

//클립보드 데이터를 가져와서 text 설정
function fnSetPasteData(obj, e){
	var pastedText = undefined;
    if (window.clipboardData && window.clipboardData.getData) { // IE
	    pastedText = window.clipboardData.getData('Text');
    } else {
        if (!e) e = event;
        if (!e) return null;
        if (e.clipboardData && e.clipboardData.getData) {
  	        pastedText = e.clipboardData.getData('text/plain');
        } else if(e.originalEvent && e.originalEvent.clipboardData) {
  	        pastedText = e.originalEvent.clipboardData.getData('text/plain');
    	}
    }
    
    pastedText = pastedText.replace(/\t/gi,"");
	
    var pastedTextLineArr = pastedText.split('\r\n');
    $(pastedTextLineArr).each(function(fnIdx, fnObj){
    	if(obj[fnIdx] != undefined) obj.eq(fnIdx).val(fnObj);
    });
}

var rowCnt = 1;
function fnAddRow(){
	
	var trLength = $("[id*=dbColumnTr]").length;
	
	rowCnt++;
	var html ='';
		html += '<tr id="dbColumnTr'+rowCnt+'">';
		html += '	<td>';
		html += '		<input type="text" class="text" name="dbColumn" value="" />';
		html += '	</td>';
		html += '	<td>';
		html += '		<input type="text" class="text" name="dbColumnType" value="" placeholder="기본값(<c:out value='${defaultColumnType}' />)" />';
		html += '	</td>';
		html += '	<td>';
		html += '		<input type="text" class="text" name="dbColumnSize" value="" placeholder="기본값(<c:out value='${defaultColumnSize}' />)" />';
		html += '	</td>';
		html += '	<td>';
		html += '		<select name="dbColumnIsNotNull"><option value="Y" selected="selected">NOT NULL</option><option value="N">NULL</option></select>';
		html += '	</td>';
		html += '	<td>';
		html += '		<input type="text" class="text" name="dbComment" value="" />';
		html += '	</td>';
		html += '	<td>';
		html += '		<button onclick="fnDelRow(\'dbColumnTr'+rowCnt+'\');return false;">제거</button>';
		html += '	</td>';
		html += '</tr>';
	
	if(trLength == 0){
		$("#tbodyBase").append(html);
	}else{
		$("#tbodyBase").find('tr').last().after(html);
	}
}

function fnDelRow(dbColumnId){
	var curObj = $(this);
	$("#"+dbColumnId).remove();
}

//저장
function fnSave(){
	
	if(fnValidation() && confirm(Msg.com.confirmSave)){
		$('#writeForm').submit();
		return false;
	}
}

function fnValidation(){
	var dbmsType = $("#dbmsType");
	var inPkg = $("#inPkg");
	var inFileName = $("#inFileName");
	var inDescription = $("#inDescription");
	var tableName = $("#tableName");
	
	var columnValidateCnt = 0;
	$("[name=dbColumn]").each(function(fnIdx, fnObj){
		if(fnObj.value != '') columnValidateCnt++;
	});
	
	if(dbmsType.val() == ''){
		alert('DBMS TYPE을 선택해주세요.');
		dbmsType.focus();
		return false;
	}else if(inPkg.val() == ''){
		alert('패키지명을 입력해주세요.');
		inPkg.focus();
		return false;
	}else if(fnGetPrefixStrCount(inPkg.val(), '.') < 1){
		alert('.(점)이 1개 이상 포함되어야 합니다.');
		inPkg.focus();
		return false;
	}else if(inFileName.val() == ''){
		alert('JAVA 파일명을 입력해주세요.');
		inFileName.focus();
		return false;
	}else if(fnGetUpperStrCount(inFileName.val()) < 2){
		alert('JAVA 파일명에 대문자 2개 이상 포함되어야 합니다.');
		inFileName.focus();
		return false;
	}else if(inDescription.val() == ''){
		alert('프로그램 설명을 입력해주세요.');
		inDescription.focus();
		return false;
	}else if(tableName.val() == ''){
		alert('테이블 명을 입력해주세요.');
		tableName.focus();
		return false;
	}else if(columnValidateCnt < 2){
		alert('DB 컬럼을 2개 이상 입력해주세요.');
		return false;
	}
	
	return true;
}

function fnGetUpperStrCount(str){
	var strings = str;
	
	var upStr=0;
	var i=0;
	var character='';
	while (i <= strings.length){
	    character = strings.charAt(i);
        if (character != '' && character != null && character == character.toUpperCase()){
        	upStr++;
        }
	    i++;
	}
	return upStr;
}

function fnGetPrefixStrCount(str, prefix){
	var strings = str;
	
	var upStr=0;
	var i=0;
	var character='';
	while (i <= strings.length){
	    character = strings.charAt(i);
        if (character != '' && character != null && character == prefix){
        	upStr++;
        }
	    i++;
	}
	return upStr;
}

function fncOnload() {
	var value = window.clipboardData.getData("text");
	value = value.split(",");
	console.log(value);
	/*
	for (i=0; i< value.length; i++) {
		switch(i) {
			case 0: Name2.value = value[i]; break;
			case 1: Addr2.value = value[i]; break;
			case 2: Telno2.value = value[i]; break;
		}
	}
	*/
}

</script>

<form id="writeForm" action="?" method="post" enctype="multipart/data" onload="fncOnload();">
	<input type="hidden" name="cssMode" value="writeActionJson" />

					<h4 class="dot_tit">샘플 소스</h4>
					<table class="board_view tbl_layout_fix">
						<colgroup>
							<col style="width:140px;" />
							<col />
						</colgroup>
							<tr>
								<th scope="row">DBMS 유형</th>
								<td>
									<select name="dbmsType">
										<c:forTokens var="dbms" items="mysql,cubrid,oracle,tibero" delims=",">
											<option value="<c:out value="${dbms }" />" ${dbms == defaultDbmsType ? 'selected' : '' }><c:out value="${dbms }" /></option>
										</c:forTokens>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="row">패키지명</th>
								<td>
									<input type="text" class="text" id="inPkg" name="inPkg" value="exts.cpf." placeholder="ex) exts.cpf.com" />
								</td>
							</tr>
							<tr>
								<th scope="row">JAVA 파일명</th>
								<td>
									<input type="text" class="text" id="inFileName" name="inFileName" value="Cpf" placeholder="ex) CpfCom" />
								</td>
							</tr>
							<tr>
								<th scope="row">프로그램 설명</th>
								<td>
									<input type="text" class="text" id="inDescription" name="inDescription" value="" placeholder="ex) 샘플" />
								</td>
							</tr>
							<tr>
								<th scope="row">테이블 명</th>
								<td>
									<input type="text" class="text" id="tableName" name="tableName" value="TB_" placeholder="ex) TB_COM_SMPL" />
								</td>
							</tr>
					</table>
					<br /><br /><button onclick="fnAddRow();return false;">추가</button>&nbsp;&nbsp;&nbsp;컬럼 입력시 첫번째는 PK값을 입력해주세요
					<table class="board_view tbl_layout_fix">
							<thead>
								<tr>
									<th>DB컬럼</th>
									<th>DB타입</th>
									<th>DB사이즈</th>
									<th>NOT NULL 여부</th>
									<th>컬럼설명</th>
								</tr>
							</thead>
							<tbody id="tbodyBase">
								<tr id="dbColumnTr1">
									<td>
										<input type="text" class="text" name="dbColumn" value="" />
									</td>
									<td>
										<input type="text" class="text" name="dbColumnType" value="" placeholder="기본값(<c:out value="${defaultColumnType}" />)" />
									</td>
									<td>
										<input type="text" class="text" name="dbColumnSize" value="" placeholder="기본값(<c:out value="${defaultColumnSize}" />)" />
									</td>
									<td>
										<select name="dbColumnIsNotNull">
											<option value="Y" selected="selected">NOT NULL</option>
											<option value="N">NULL</option>
										</select>
									</td>
									<td>
										<input type="text" class="text" name="dbComment" value="" />
									</td>
									<td>
										<button onclick="fnDelRow('dbColumnTr1');return false;">제거</button>
									</td>
								</tr>
							</tbody>
					</table>
</form>
<%@ include file="/WEB-INF/jsp/exts/com/write_bottom.jsp" %>
<%@ include file="/WEB-INF/jsp/exts/com/footer.jsp" %>

