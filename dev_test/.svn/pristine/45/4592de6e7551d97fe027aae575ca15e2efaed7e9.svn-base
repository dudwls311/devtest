$(document).ready(function(){
	var ajform = new ComAjaxForm('writeForm','searchForm', {realod:'Y'});
	ajform.init();
	
	/* 그룹선택시 */
	$(".search_glist li").on("click",function(){
		$(".search_glist li").removeClass("active");
		$(this).addClass("active");
		$('#grpNmTd').text($(this).data('grpnm'));
		getData($(this).data('authgrpid'));
	});


	showhideResult();
	
	
	$('input[type="checkbox"]').click(function(e){		
		var _$this = $(e.currentTarget);
		var _checked = _$this.prop("checked");
		var _id = _$this.data('id');
		var _parent = _$this.data('parent');
		var _lmenu = _$this.data('lmenu');
		var _mmenu = _$this.data('mmenu');
		var _smenu = _$this.data('smenu');
		var _tmenu = _$this.data('tmenu');
		var _auth = _$this.data('auth');
		var _lmenuObj = $('#' + _lmenu + '_' + _auth);
		var _mmenuObj = $('#' + _mmenu + '_' + _auth);
		var _smenuObj = $('#' + _smenu + '_' + _auth);


		if(_mmenu == ''){//1뎁스클릭시
			$('input[type="checkbox"]').each(function(){
				var _obj = $(this);
				if(_obj.data('lmenu') == _lmenu && _obj.data('auth') == _auth)_obj.prop("checked",_checked);
			});	
		}else if(_smenu == ''){//2뎁스클릭시
			if(_checked){
				_lmenuObj.prop("checked", _checked);
			}
			$('input[type="checkbox"]').each(function(){
				var _obj = $(this);
				if(_obj.data('lmenu') == _lmenu && _obj.data('mmenu') == _mmenu && _obj.data('auth') == _auth)_obj.prop("checked",_checked);
			});	
		}else if(_tmenu == ''){//3뎁스클릭시
			if(_checked){
				_lmenuObj.prop("checked", _checked);
				_mmenuObj.prop("checked", _checked);
			}
			$('input[type="checkbox"]').each(function(){
				var _obj = $(this);
				if(_obj.data('lmenu') == _lmenu && _obj.data('mmenu') == _mmenu && _obj.data('smenu') == _smenu && _obj.data('auth') == _auth)_obj.prop("checked",_checked);
			});	
		}else{//4뎁스
			if(_checked){	
				_lmenuObj.prop("checked", _checked);
				_mmenuObj.prop("checked", _checked);
				_smenuObj.prop("checked", _checked);
			}
		}
		
	});
	
	$('#saveBtn').click(function(){
		if(confirm(Msg.com.confirmSave)){
			$('#writeForm').submit();
			return false;
		}
	});//저장버트
	
	showhideResult();
});


function showhideResult(){
	if($('#authGrpId').val() != ''){
		$('#SearchResultArea').show();
		$('#saveBtn').show();
	}else{
		$('#SearchResultArea').hide();
		$('#saveBtn').hide();
	}
	
}

//상세데이터 가져오기
function getData(authGrpId){
	$('input[name$="redngAuthYn"]').prop("checked",false);
	$('input[name$="streAuthYn"]').prop("checked",false);
	$('input[name$="delAuthYn"]').prop("checked",false);
	$('input[name$="prntgAuthYn"]').prop("checked",false);
	
	$('#writeForm #authGrpId').val(authGrpId);
	$.ajax({
		type:"post",
		url:actionUrl,
		dataType:'json',
		data:{cmgmaMode:"viewJson",authGrpId:authGrpId}
	}).done(function(data){
		showhideResult();
		$(data).each(function(){
			var _pre = '#' + this.menuSn + '_';
			if(this.redngAuthYn == 'Y')$(_pre+'redngAuthYn').prop("checked",true);
			if(this.streAuthYn == 'Y')$(_pre+'streAuthYn').prop("checked",true);
			if(this.delAuthYn == 'Y')$(_pre+'delAuthYn').prop("checked",true);
			if(this.prntgAuthYn == 'Y')$(_pre+'prntgAuthYn').prop("checked",true);			
		});
	});
}