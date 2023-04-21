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
	$("#commMode").val('write');
	$("#writePageForm").submit();
	return false;
}

//삭제
function fnDelete(){
	if(confirm(Msg.com.confirmDelete)){
		ComFns.ajax(actionUrl, {commMode:'deleteActionJson',mbrId:$('#mbrId').val()}, fnList);
	}
}

//로그인  차단 해제
function fnUnLock(){
	if(confirm('로그인 차단 해제하시겠습니까?')){
		ComFns.ajax(actionUrl, {commMode:'unlockActionJson',mbrId:$('#mbrId').val()}, fnList);
	}
}