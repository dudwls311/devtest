$(document).ready(function(){

	var ajform = new ComAjaxForm('writeForm','searchForm', {});
//	ajform.setListFormId = 'searchForm';
	ajform.init();
	
	/* 등록페이지 이동 */
	$("#writeBtn").on("click", function(){
		$("#authGrpId").val('');
		$("#coagMode").val('write');
		$("#listForm").submit();
		return false;
	});
	
	/* 검색 */
	$("#searchBtn").on("click", function(){
		$("#coagMode").val('list');
		$("#authGrpId").val('');
		$("#pageIndex").val('1');
		$("#listForm").submit();
		return false;
	});
	

	/* 그룹선택시 */
	$(".search_glist li").on("click",function(){
		$(".search_glist li").removeClass("active");
		$(this).addClass("active");
		getData($(this).data('authgrpid'));
	});
	
	$('#btnSave').click(function(){$('#writeForm').submit();return false;});//저장버튼
	$('#btnAdd').click(function(){fnWrite('');return false;});//추가버튼
	
});

function fnPage(p){
	$("#coagMode").val('list');
	$("#authGrpId").val('');
	$("#pageIndex").val(p);
	$("#listForm").submit();
}

function fnView(authGrpId){
	$("#coagMode").val('view');
	$("#authGrpId").val(authGrpId);
	$("#listForm").submit();
}


//쓰기
function fnWrite(authGrpId){
	fnInitWriteForm();
	if(authGrpId != ''){
		$('#' + authGrpId + '_tr').addClass('on');
		$('#btnSave').text('저장');
		getData(authGrpId);
	}else{		
		$('#btnSave').text('추가');
	}
}
//쓰기폼 초기화
function fnInitWriteForm(){
	$('#selectedGroupName').text('그룹명:');
	$('#writeForm #authGrpId').val('');
	$('#writeForm #grpNm').val('');
	$('#writeForm #grpExplnt').val('');
	$('#writeForm input[name="useYn"]').prop('check',false);
	$(".search_glist li").removeClass("active");
}

//상세데이터 가져오기
function getData(authGrpId){
	$.ajax({
		type:"post",
		url:actionUrl,
		dataType:'json',
		data:{coagMode:"viewJson",authGrpId:authGrpId}
	}).done(function(data){
		$('#selectedGroupName').text('그룹명:' + data.grpNm);
		$('#writeForm #authGrpId').val(data.authGrpId);
		$('#writeForm #grpNm').val(data.grpNm);
		$('#writeForm #grpExplnt').val(data.grpExplnt);
		if(data.useYn != undefined && data.useYn == 'Y'){
			$('#writeForm input[name="useYn"]:radio[value="Y"]').prop("checked",true);
		}else{
			$('#writeForm input[name="useYn"]:radio[value="N"]').prop("checked",true);
		}
	});
}