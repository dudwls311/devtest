$(document).ready(function(){
	$('#listBtn').on('click',fnList);
	$('#modifyBtn').on('click',fnModify);
	$('#deleteBtn').on('click',fnDelete);
});

//리스트
function fnList(){
	$("#listPageForm").submit();
	return false;
}

//수정
function fnModify(){
	$("#csMode").val('write');
	$("#writePageForm").submit();
	return false;
}

//삭제
function fnDelete(){
	if(confirm(Msg.com.confirmDelete)){
		ComFns.ajax(actionUrl, {csMode:'deleteActionJson',smplId:$('#smplId').val()}, fnList);
	}
}

function fnDownload(docNo, atchFileSno){
	$("#downloadForm [name=docNo]").val(docNo);
	$("#downloadForm [name=atchFileSno]").val(atchFileSno);
	$("#downloadForm").attr('target', 'downloadFrame');
	$("#downloadForm").submit();
}