$(document).ready(function(){
	$('#cancelBtn').on('click',fnCancel);
	$('#saveBtn').on('click',fnSave);

	var ajform = new ComAjaxForm('writeForm','listPageForm', {});
	ajform.init();

	$('input[name="typeId"]').on('click',fnChangeTypeId);
	fnChangeTypeId();
	
	$('#mbrLogin').on('keydown',function(){$('#chkId').val('');});
});

//리스트
function fnList(){
	$("#listPageForm").submit();
	return false;
}

//취소
function fnCancel(){
	if($('#mbrId').val() != ''){
		$("#viewPageForm").submit();	
	}else{
		fnList();
	}
	return false;
}


//저장
function fnSave(){
	if(confirm(Msg.com.confirmSave)){
		$('#writeForm').submit();
		return false;
	}
}

//권한체크 
function fnChangeTypeId(){
	//var _typeId = $('#mbrId').val() != ''?$('input[name="typeId"]').val():$('input[name="typeId"]:checked').val();
	var _typeId = $('input[name="typeId"]:checked').val();
	$('#orgIdTr').hide();
	//$('#orgId').val('');		//센터상담사가 아닌 최고관리자 또는 재단담당자가 수정하면 센터값이 없어지면서 값이 사라짐
	if(_typeId == $('#enumMbrTypeFOU').val() || _typeId == $('#enumMbrTypeCEN').val()){
		if(_typeId == $('#enumMbrTypeFOU').val())$('#orgId').html($('#orgIdFOU').html());
		if(_typeId == $('#enumMbrTypeCEN').val())$('#orgId').html($('#orgIdCEN').html());
		$('#orgIdTr').show();
	}
}

//아이디 중복체크
function fnIdCheck(){
	var _id = $('#mbrLogin').val();
	if(_id == ''){
		alert('아이디를 입력해 주세요');
		return false;
	}
	$.ajax({
		url:ComFns.getContextUrl()+'user/exts/koreahana/mbr/duplicateIdActionJson.do',
		data:{mbrLogin:_id},
		success:function(resultData){
			if(resultData.isSuccess){
				$('#chkId').val('Y');
				alert('사용하실 수 있는 아이디입니다.');
			}else{
				if(resultData.msg != ''){
					alert(resultData.msg);	
				}else{
					alert('사용할 수 없는 아이디입니다. 다른 아이디을 입력해 주세요.');
				}
			}
		}
	});	
}


function fnAddr(){
	//공통 주소찾기 호출
	fnComAddressFind(function(data){
		$('#zip').val(data.zip);
		$('#addr').val(data.address);
		$('#daddr').val(data.addressDetail);
		
	});
	return false;
}