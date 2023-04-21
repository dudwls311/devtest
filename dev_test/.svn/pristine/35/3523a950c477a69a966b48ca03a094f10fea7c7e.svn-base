var ComFns = ComFns || {};

$(document).ready(function(){
	ComFns.hideLoading();
	ComFns.initDatePicker('.date_style');//날짜입력박스
	$('.date_style').mask('0000-00-00');
	
	ComFns.initDateTimePicker('.datetime_style');//날짜입력박스
	$('.datetime_style').mask('0000-00-00 00:00:00');
	
	$('.number_style').number(true);//숫자처리
	ComFns.numberOnly('.number_nocomma_style');//숫자처리(콤마제거)

	$('.ym_style').mask('0000-00');
	$('.mbphno').mask('000-0000-0000');
});

/**
 * contextUrl 가져오기 header에서 선언되어있어야함.
 */
ComFns.getContextUrl = function(){
	var _url = (ComFns.contextUrl != undefined && ComFns.contextUrl != '')?ComFns.contextUrl:'/';
	if(_url.substr(_url.length - 1) != '/')_url += '/';
	return _url;
}

/**
 * 전체 선택,선택해제
 * @param chkAllId 전체선택 체크박스 Id
 * @param chkName 선택될 체크박스 Name
 */
ComFns.checkAll = function(chkAllId, chkName){
	$('#' + chkAllId).click(function(){
		if(this.checked){
			$('input[name='+chkName+']').prop('checked',true);
		}else{
			$('input[name='+chkName+']').prop('checked',false);
		}
		
	});
}



/**
 * datepicker 만들기
 * @param element jqueyr요소
 * @param options datepicker옵션
 */
ComFns.initDatePicker = function (element, options){
	var currentYear = new Date().getFullYear()+2;
	var pastYear = (currentYear - 100);
	var calendarDefault = {};
	var monthNames = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
	var monthNamesShort = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
	var dayNamesMin = ['일','월','화','수','목','금','토'];
	var dateFormat = 'yy-mm-dd';
//	var minDate = '';
//	var maxDate = '';
	var buttonImage = ComFns.getContextUrl() + 'resources/common/images/common/ico-cal.png';
	var yearRange = pastYear+':'+currentYear;
	calendarDefault = {
			monthNames: monthNames,
			monthNamesShort: monthNamesShort,
			dayNamesMin: dayNamesMin,
			weekHeader: 'Wk',
			dateFormat: dateFormat,
		    yearSuffix: '년',
			autoSize: false,
			changeMonth: true,
			changeYear: true,
			showMonthAfterYear: true,
//			buttonImageOnly: true,
//			buttonText: '달력선택',
//			buttonImage: buttonImage,
//			showOn: "both",
//			showOn: "button",
//		    minDate: minDate,
//		    maxDate: maxDate,
			yearRange: yearRange
		};
	for(var k in options){
		calendarDefault[k] = options[k];
	}
	if(options != undefined && options != null && options.reaonly != undefined && options.reaonly != '')$(element).prop("readonly",options.reaonly);
	$(element).datepicker(calendarDefault);
	$(element).prop('maxlength','10');
}

/**
 * datepicker 만들기
 * @param element jqueyr요소
 * @param options datepicker옵션
 */
ComFns.initDateTimePicker = function (element, options){
	var currentYear = new Date().getFullYear()+2;
	var pastYear = (currentYear - 100);
	var calendarDefault = {};
	var monthNames = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
	var monthNamesShort = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
	var dayNamesMin = ['일','월','화','수','목','금','토'];
	var dateFormat = 'yy-mm-dd';
//	var minDate = '';
//	var maxDate = '';
	var buttonImage = ComFns.getContextUrl() + 'resources/common/images/common/ico-cal.png';
	var yearRange = pastYear+':'+currentYear;
	calendarDefault = {
			monthNames: monthNames,
			monthNamesShort: monthNamesShort,
			dayNamesMin: dayNamesMin,
			weekHeader: 'Wk',
			dateFormat: dateFormat,
		    yearSuffix: '년',
			autoSize: false,
			changeMonth: true,
			changeYear: true,
			showMonthAfterYear: true,
//			buttonImageOnly: true,
//			buttonText: '달력선택',
//			buttonImage: buttonImage,
//			showOn: "both",
//			showOn: "button",
//		    minDate: minDate,
//		    maxDate: maxDate,
			yearRange: yearRange
		};
	for(var k in options){
		calendarDefault[k] = options[k];
	}
	if(options != undefined && options != null && options.reaonly != undefined && options.reaonly != '')$(element).prop("readonly",options.reaonly);
	$(element).datetimepicker(calendarDefault);
	$(element).prop('maxlength','10');
}

/**
 * 팝업창
 */
ComFns.popup = {
		init: function(optionObj){
			this.title = "팝업";
			this.msg = "";
			this.autoOpen = false;
			this.resizable = false;
			this.height = 600;
			this.width = 850;
			this.modal = true;
			this.callbackMethod = undefined;
			this.confirmBtn = false; //확인버튼 보이기
			this.confirmBtnClickMethod = undefined; //확인버튼클릭메소드
			
			for(var k in optionObj){
				this[k] = optionObj[k];
			}
			
			this.hideConfirmBtn();
			if(this.confirmBtn)this.showConfirmBtn();
			this.removeConfirmBtnClickMethod();
			if(this.confirmBtnClickMethod != undefined)this.setConfirmBtnClickMethod(this.confirmBtnClickMethod);
		}
		,title:""
		,content:""
		,show: function(){
			this.changeTitle(this.title);
			$("#modalDiv").show();
		}
		,hide: function(){
			$("#modalDiv").hide();
//			$('body').removeClass('noscroll');
		}
		,setCenterPosition: function(){
			ComFns.setCenterPosition('#modalDiv.pop_wrap');
			//ComFns.setCenterPosition('#modalBox.popup_box');
		}
		,loadContent: function(url, data, callback){
			//ComFns.showLoading();
			this.removeWidthStyle();
			if(callback == undefined){
				callback = function(){
					ComFns.hideLoading();
					ComFns.popup.setCenterPosition();
				};
			}
//			$('body').addClass('noscroll');
			$("#modalDiv_content").empty();
	        this.removeWidthStyle();
			$("#modalDiv_content").load(url,data,callback);
		}
		,changeContentHtml: function(html){
			$("#modalDiv_content").html(html);
			ComFns.popup.setCenterPosition();
		}
		,changeTitle: function(title){
			this.title = title;
			$('#modalDiv_title').text(title );
		}
		,setConfirmBtnClickMethod: function(fn){
			$('#modalDiv_confirmBtn').unbind('click');
			$('#modalDiv_confirmBtn').on('click',fn);
		}
		,removeConfirmBtnClickMethod: function(fn){
			$('#modalDiv_confirmBtn').unbind('click');
		}
		,showConfirmBtn: function(){
			$('#modalDiv_confirmBtn').show();
		}
		,hideConfirmBtn: function(){
			$('#modalDiv_confirmBtn').hide();
		}
		,loadContentInnerPopup: function(url, data, callback){
			$("#modalDiv_innerPopup").empty();
			$("#modalDiv_innerPopup").load(url,data,callback);
		}
		,showInnerPopup: function(){
			$('#modalDiv_innerPopup').hide();
		}
		,hideInnerPopup: function(){
			$('#modalDiv_innerPopup').hide();
		}
		,setWidthStyle: function(st){
			var _w = '';
			if(st == '300')_w = 'size300';
			else if(st == '400')_w = 'size400';
			else if(st == '500')_w = 'size500';
			else if(st == '600')_w = 'size600';
			else if(st == '700')_w = 'size700';
			else if(st == '800')_w = 'size800';
			else if(st == '900')_w = 'size900';
			else if(st == '1000')_w = 'size1000';
			else if(st == '1400')_w = 'size1400';
	        this.removeWidthStyle();
	        $('#modalDiv div.pop_layer').addClass(_w);
		}
		,removeWidthStyle: function(){
		    var remove_size = [];
		    $('#modalDiv div.pop_layer').removeClass(function () { 
		        var class_name = this.className.match(/size\d+/);
		        if (class_name) {
		            remove_size.push(class_name[0])
		            return class_name[0];
		        }
		    });
		}
}



ComFns.setCenterPosition = function(d){

	var intLeftMargin = (ComFns.getClientWidth() / 2) - ($(d).width() / 2);
	var intTopMargin = (ComFns.getClientHeight() / 2) - ($(d).height() / 2);
	
	if (intLeftMargin < 0)  intLeftMargin = 0;
	if (intTopMargin < 0)  intTopMargin = 0;
	
	var nowScroll = ComFns.getNowScroll();
	if(nowScroll.Y != undefined)intTopMargin = intTopMargin + nowScroll.Y;	// 스크롤 만큼 더해준다.

	$(d).css("left", intLeftMargin + "px");
	$(d).css("top" ,intTopMargin + "px");
}


//화면 가로 길이 구하기
ComFns.getClientWidth = function (){    
	var intClientWidth;

	if (self.innerWidth){    // IE 외 모든 브라우저			
		intClientWidth = self.innerWidth;
	}else if (document.documentElement && document.documentElement.clientWidth){    // IE 6 Strict
		intClientWidth = document.documentElement.clientWidth;
	}else if (document.body){	    // IE
		intClientWidth = document.body.clientWidth;
	}
	return intClientWidth;
}

//화면 세로 길이 구하기
ComFns.getClientHeight = function (){   
	var intClientHeight = 0;

	if (self.innerHeight){
		intClientHeight = self.innerHeight;
	}else if (document.documentElement && document.documentElement.clientHeight){
		intClientHeight = document.documentElement.clientHeight;
	}else if (document.body){
		intClientHeight = document.body.clientHeight;
		intClientHeight;
	}
	return intClientHeight;
}

ComFns.getNowScroll = function(){ 
	var de = document.documentElement; 
	var b = document.body;
	var now = {}; 
	now.X = document.all ? (!de.scrollLeft ? b.scrollLeft : de.scrollLeft) : (window.pageXOffset ? window.pageXOffset : window.scrollX); 
	now.Y = document.all ? (!de.scrollTop ? b.scrollTop : de.scrollTop) : (window.pageYOffset ? window.pageYOffset : window.scrollY); 
	return now; 
};

ComFns.betweenDatepickerOption = function(startDateEle, endDateEle){
	$(startDateEle).datepicker('option','onSelect',function(selected){
		$(endDateEle).datepicker("option","minDate", selected);
	});
	$(endDateEle).datepicker('option','onSelect',function(selected){
		$(startDateEle).datepicker("option","maxDate", selected);
	});
}

/**
 * html포함텍스트를 일반텍스트로 변경 
 */
ComFns.convertText = function(txt){
	if(txt == undefined || txt == null)return '';
	return $("<p/>").text(txt).html();
}

/**
 * ajax 로딩 보이기
 */
ComFns.showLoading = function(opt){
	//stoppable: true, 
	if(opt == undefined)opt = {stoppable: false, zIndex:99999, message:'처리중입니다...'};
	$('body').loading(opt);
}

/**
 * ajax 로딩 정지
 */
ComFns.hideLoading = function(){
	$('body').loading('stop');
}



ComFns.tabOrder = function(thisTabId,nextTabId, thisTabSize) {  
    if ($('#' + thisTabId).val().length == thisTabSize) {
    	$('#' + nextTabId).focus() ;
        return;
     }
}

/**
 * 첨부파일의 용량이 초과되었는지 여부
 * @param fileFormId 첨부파일 object의 id
 * @param maxBytes 최대 byte
 */
ComFns.isFileExeed = function(fileFormId, maxBytes){
	if($('#'+fileFormId)[0].files[0] == undefined)return false;
	return $('#'+fileFormId)[0].files[0].size >= maxBytes;
}

/**
 * 첨부파일 확장자 가능 여부
 * @param fileFormId 첨부파일 object의 id
 * @param exts 콤마로 연결된 확장자
 */
ComFns.isPossibleFileExt = function(fileFormId, exts){
	if($('#'+fileFormId).val() == '')return true;
	var _fileExt = $('#'+fileFormId).val().split('.').pop().toLowerCase();
	var _arrExt = exts.split(',');
	return $.inArray(_fileExt, _arrExt) > -1;
}


/**
 * byte를 표현문자(KB, MB 등)로 변환
 */
ComFns.byteCalculation = function(bytes) {
    var bytes = parseInt(bytes);
    var s = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
    var e = Math.floor(Math.log(bytes)/Math.log(1024));
   
    if(e == "-Infinity") return "0 "+s[0]; 
    else 
    return (bytes/Math.pow(1024, Math.floor(e))).toFixed(2)+" "+s[e];
}


/**
 * 카카오 우편번호찾기
 */
ComFns.daumPostPopup = function(callback){
	new daum.Postcode({
		oncomplete: function(data) {
			var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 도로명 조합형 주소 변수

			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				extraRoadAddr += data.bname;
			}

			if(data.buildingName !== '' && data.apartment === 'Y'){
			   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}

			if(extraRoadAddr !== ''){
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}

			if(fullRoadAddr !== ''){
				fullRoadAddr += extraRoadAddr;
			}

			callback(data.zonecode, fullRoadAddr);			
		}
	}).open();
}

ComFns.numberOnly = function(ele){
	$(ele).on('keyup',function(){
		$(this).val( $(this).val().replace(/[^0-9]/gi,"") );
	});
}

ComFns.convertDateSplitString = function(dt, sp){
	if(dt == undefined || dt == null)return '';
	var _dt = dt.replace(/\./gi,'').replace(/\-/gi,'');
	if(_dt.length != 8)return '';
	return _dt.substring(0,4) + sp + _dt.substring(4,6) + sp + _dt.substring(6,8);
}
//////////////////////////////// 업무 관련 //////////////////////////////////////////
/**
 * 기본 Ajax처리용
 * @url url
 * @data data
 * @successAfterFunction 성공후 실행될 function
 * @options override용 옵션
 */
ComFns.ajax = function(url, data, successAfterFunction, options, isReturnValue){
	if(isReturnValue == undefined || isReturnValue == null || isReturnValue == '') isReturnValue = false;
	
	var _ajaxParam = {};
	_ajaxParam.method = 'post';
	_ajaxParam.url = url;
	_ajaxParam.data = data;
	_ajaxParam.success = function(resultData){
		if(resultData.isSuccess){
			if(isReturnValue == true) successAfterFunction(resultData);
			else if(isReturnValue == false) successAfterFunction();
			else successAfterFunction();
		}else{
			ComFns.hideLoading();
			alert(resultData.msg);
		}
	}
	
	for(var k in options){
		_ajaxParam[k] = options[k];
	}
	ComFns.showLoading();
	$.ajax(_ajaxParam);
}

/**
 * 그룹코드로 공통코드 리스트 가져오기
 * @grpCd 그룹코드
 * @reutrn 리스트 json({indivCd, indivCdNm})
 */
ComFns.getCodeListByGrpCd = function(grpCd){
	var _ret = null;
	$.ajax({
		url:ComFns.getContextUrl() + 'exts/com/getCodeListByGrpCdJson.do',
		data:{grpCd:grpCd},
		async:false,
		success:function(resultData){
			_ret = resultData;
		}
	});
	return _ret;
}


/**
 * 그룹코드로 공통코드 리스트 가져와서 select태그에 셋팅
 * @
 * @grpCd 그룹코드
 */
ComFns.makeCodeSelectTagByGrpCd = function(grpCd, element, selectedValue, firstValue){
	var _html = '';
	if(firstValue != undefined && firstValue != null){
		_html += '<option value="">' + firstValue + '</option>';
	}
	if(grpCd != ''){
		var _list = ComFns.getCodeListByGrpCd(grpCd);
		if(_list != null){
			for(var k in _list){
				var _selected = (_list[k].indivCd == selectedValue)?' selected="selected"':'';
				_html += '<option value="' + _list[k].indivCd + '"' + _selected + '>' + _list[k].indivCdNm + '</option>';
			}
		}
	}
	$(element).html(_html);
}

/**
 * 상위코드로 공통코드 리스트 가져오기
 * @uprCd 상위코드
 * @grpCd 상위코드
 * @reutrn 리스트 json({indivCd, indivCdNm})
 */
ComFns.getCodeListByUprCd = function(uprCd, grpCd){
	var _ret = null;
	var _url = ComFns.getContextUrl() + 'exts/com/getCodeListByUprCdJson.do';
	if(grpCd != undefined)_url = ComFns.getContextUrl() + 'exts/com/getCodeListByGrpUprCdJson.do';
	$.ajax({
		url:_url,
		data:{uprCd:uprCd, grpCd:grpCd},
		async:false,
		success:function(resultData){
			_ret = resultData;
		}
	});
	return _ret;
}


/**
 * 상위코드로 공통코드 리스트 가져와서 select태그에 셋팅
 * @
 * @uprCd 상위코드
 */
ComFns.makeCodeSelectTagByUprCd = function(uprCd, element, selectedValue, firstValue, grpCd){
	var _html = '';
	if(firstValue != undefined && firstValue != null){
		_html += '<option value="">' + firstValue + '</option>';
	}
	if(uprCd != ''){
		var _list = (grpCd != undefined)?ComFns.getCodeListByUprCd(uprCd,grpCd):ComFns.getCodeListByUprCd(uprCd);
		if(_list != null){
			for(var k in _list){
				var _selected = (_list[k].indivCd == selectedValue)?' selected="selected"':'';
				_html += '<option value="' + _list[k].indivCd + '"' + _selected + '>' + _list[k].indivCdNm + '</option>';
			}
		}
	}
	$(element).html(_html);
}

/**
 * 엑셀 다운로드하기
 * 리스트폼에 excel파라메터를 추가하여 처리함.
 */
ComFns.excelDownload = function(element){
	var _fid = (element == undefined)?'#listPageForm':element;
	var _ef = $('#excelDownloadForm');
	if(_ef.length == 0){
		$('body').append('<form id="excelDownloadForm" action="?"></form>');
		_ef = $('#excelDownloadForm');
	}
	_ef.empty();
	_ef.append('<input type="hidden" name="excelYn" value="Y" />');
	_ef.append($(_fid).html());
	_ef.submit();
}

/**
 * 에러메시지 출력
 */
ComFns.printErrorArrayMessage = function(arrError, msgField){
	if(msgField == undefined)msgField = 'errorMessage';
	var _text = '';
	if(arrError != undefined && arrError != null){
		for(var i = 0; i < arrError.length; i++){
			if(_text != '')_text += ',';
			_text += arrError[i][msgField];
		}	
	}
	return ComFns.convertText(_text);
}
/**
 * 샘플 파일 다운로드
 */
ComFns.sampleDownload = function(storeName, orginalName){
	var _ef = $('#sampleDownloadForm');
	if(_ef.length == 0){
		$('body').append('<form id="sampleDownloadForm" action="' + ComFns.getContextUrl() + 'exts/com/sampleDownload.do" target="_blank"></form>');
		_ef = $('#sampleDownloadForm');
	}
	_ef.empty();
	_ef.append('<input type="hidden" name="fileNm" value="' + ComFns.convertText(storeName) + '" />');
	_ef.append('<input type="hidden" name="saveNm" value="' + ComFns.convertText(orginalName) + '" />');
	_ef.submit();
}


/**
 * 엑셀 다운로드하기
 * 리스트폼에 excel파라메터를 추가하여 처리함.
 */
ComFns.excelDownload = function(element){
	var _fid = (element == undefined)?'#listPageForm':element;
	var _ef = $('#excelDownloadForm');
	if(_ef.length == 0){
		$('body').append('<form id="excelDownloadForm" action="?"></form>');
		_ef = $('#excelDownloadForm');
	}
	_ef.empty();
	_ef.append('<input type="hidden" name="excelYn" value="Y" />');
	_ef.append($(_fid).html());
	_ef.submit();
}

/**
 * 엑셀 파일 업로드 및 파싱 후 배열로 리턴.
 * @param fileNameInput 파일 선택시 파일명이 출력될 input 박스
 * @param fileSelectBtn 업로드 실행할 버튼
 * @param uploadBtn 업로드 실행할 버튼
 * @param callback 엑셀 파싱후 실행될 callback함수
 * @param changeFn 파일선택시 실행할 함수
 */
ComFns.excelParseInit = function(fileNameInput, fileSelectBtn, uploadBtn, callback, changeFn){
	$('#excelUploadForm').ajaxForm({
		beforeSubmit:function(){
			ComFns.showLoading();
		},
		success:function(resultData, statusText, xhr, $form){
			ComFns.hideLoading();
			callback(resultData);			
		},
		error:function(xhr, textStatus, errorThrown) {
			ComFns.hideLoading();
			alert('ajax form error');
			console.log(xhr);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
	$('#excelFileForParse').on('change',function(){
		if($(this).val() != ""){
			$(fileNameInput).text($(this).val());
			if(changeFn != undefined)changeFn();
		}
	});
//	$(fileSelectBtn).prop('for', 'excelFileForParse');
	$(fileSelectBtn).on('click', function(){$('#excelFileForParse').trigger('click');return false;});
	$(uploadBtn).on('click', function(){
		if($(fileNameInput).text() == ''){
			alert('파일을 선택해 주세요');
			return false;
		}
		$('#excelUploadForm').submit();
		return false;
	});
}


/**
 * 엑셀 파일 업로드 폼 html 추가
 * @param result ComExcelVO
 */
ComFns.makeExcelData = function(formId, result){
	
	if(result == undefined || result == null || result == '') return;
	if(formId == undefined || formId == null) formId = '';
	
	var formObj = $(formId);
	if(formObj.length > 0){
		var data = JSON.stringify(result.data);
		var maxRow = result.maxRow;
		var maxCell = result.maxCell;
		var excelNm = result.excelNm;
		
		if(maxRow == null || isNaN(Number(maxRow))) maxRow = '';
		if(maxCell == null || isNaN(Number(maxCell))) maxCell = '';
		if(data == null) data = '';
		
		var objNmArr = new Array('maxRow', 'maxCell', 'data','excelNm');
		$(objNmArr).each(function(fnIdx, fnObj){
			var appendObj = formObj.find('[name="'+fnObj+'"]');
			var objHtml = '<input type="hidden" name="'+fnObj+'" />';
			if(appendObj.length == 0) formObj.append(objHtml);
		});
		
		/*input 태그와 동시에 value를 append하게 되면 JSON.stringify 먹히지 않아서 ' or " 문자로 인해 html 깨짐
		  append 동시에 value를 셋팅하려고 하면 append 오브젝트를 찾지 못하기 때문에 each문 추가*/
		$(objNmArr).each(function(fnIdx, fnObj){
			var appendObj = formObj.find('[name="'+fnObj+'"]');
			appendObj.val((fnObj == 'excelNm' ? excelNm : fnObj == 'maxRow' ? maxRow : fnObj == 'maxCell' ? maxCell : fnObj == 'data' ? data : ''));
		});
	}
}

/**
 * 에러메시지 출력
 */
ComFns.printErrorArrayMessage = function(arrError, msgField){
	if(msgField == undefined)msgField = 'errorMessage';
	var _text = '';
	if(arrError != undefined && arrError != null){
		for(var i = 0; i < arrError.length; i++){
			if(_text != '')_text += ',';
			_text += arrError[i][msgField];
		}	
	}
	return ComFns.convertText(_text);
}
/**
 * 샘플 파일 다운로드
 */
ComFns.sampleDownload = function(storeName, orginalName){
	var _ef = $('#sampleDownloadForm');
	if(_ef.length == 0){
		$('body').append('<form id="sampleDownloadForm" action="' + ComFns.getContextUrl() + 'exts/com/sampleDownload.do" target="_blank"></form>');
		_ef = $('#sampleDownloadForm');
	}
	_ef.empty();
	_ef.append('<input type="hidden" name="fileNm" value="' + ComFns.convertText(storeName) + '" />');
	_ef.append('<input type="hidden" name="saveNm" value="' + ComFns.convertText(orginalName) + '" />');
	_ef.submit();
}

/**
 * 실명인증 팝업
 */
ComFns.openRealAuth = function(){
	window.open('/user/exts/realauth/authPopup.do?authType=nice&sAuthType=M', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	//정상처리 후 호출될 callback method명 : fnAuthCallback
}

/**
 * 실명인증 팝업(ipin)
 */
ComFns.openRealAuthIpin = function(){
	window.open('/user/exts/realauth/authPopup.do?authType=niceipin&sAuthType=M', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
	//정상처리 후 호출될 callback method명 : fnAuthCallback
}

/**
 * 핸드폰번호 자동 - 붙이기.
 */
ComFns.mobileMask = function(selector){
	var _v = $(selector);
	if(_v.length > 0){
		_v.keydown(function (event) {
			var key = event.charCode || event.keyCode || 0;
			$text = $(this); 
						
			if (key !== 8 && key !== 9) {
				if ($text.val().length === 3) {
				    $text.val($text.val() + '-');
				}
				if ($text.val().length === 8) {
				    $text.val($text.val() + '-');
				}
			}
						
			return (key == 8 || key == 9 || key == 46 || (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
		});
	
		if (_v.val().length === 10) {
		    _v.val(_v.val().substr(0,3) + '-'  + _v.val().substr(3,3) + '-' + _v.val().substr(7,4));
		}else if (_v.val().length === 11) {
			_v.val(_v.val().substr(0,3) + '-'  + _v.val().substr(3,4) + '-' + _v.val().substr(7,4));
		}
	}
}