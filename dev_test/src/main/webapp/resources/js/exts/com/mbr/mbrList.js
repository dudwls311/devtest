$(document).ready(function(){
});

//페이징
function fnPage(p){
	$("#pageIndex").val(p);
	$("#listPageForm").submit();
}

//상세
function fnView(mbrId){
	$("#commMode").val('view');
	$("#mbrId").val(mbrId);
	$("#writePageForm").submit();
}


//추가/수정
function fnWrite(mbrId){
	$("#commMode").val('write');
	$("#mbrId").val(mbrId);
	$("#writePageForm").submit();
}
