var ComOz = ComOz || {};

$(document).ready(function(){
});

ComOz.report = {
		/**
		 * initialize
		 * @param 오즈 뷰어 div ID
		 * @param 보고서 파일명 (/ 포함)
		 * @param options
		 */
		init : function(viewerId, ozrNm, options){
			this.ozrNm = ozrNm;
			this.servletUrl = '/oz80/server';
			this.viewerId = viewerId; //오즈 뷰어 div Id
			this.formParam = new Array(); //OZFormParam으로 셋팅될 데이터 
			this.sendToActionScripts = [];//sendToActionScript로 호출될 값들.
			this.startOpt = [];//start실행시 전달될 opt

			//기본 툴바옵션중 저장비활성
			this.addScriptParam('toolbar.addmemo','false');
			this.addScriptParam('toolbar.save','false');
			this.addScriptParam('toolbar.savedm','false');
			

			for(var k in options){
				this[k] = options[k];
			}
			
			if(this.formParamPrefixId != undefined){
				this.hiddenToFormParam(this.formParamPrefixId);
			}
			
		},
		/**
		 * prefixId와 일치하는 모든 hidden값을 formparam으로 전달.
		 * @param prefiexId 모든 Hidden값의 prefix
		 */
		hiddenToFormParam : function(prefixId){
			$('[id^='+prefixId).each(function(){
				var _this = $(this);
				var _param = _this.attr('id').replace(prefixId,'');
				var _val = _this.val();
				ComOz.report.formParam.push({param:_param,val:_val});
			});
		},
		/**
		 * 오즈뷰어의 sendToActionScript 에 호출될 옵션
		 * @param paramName
		 * @param paramValue 
		 */
		addScriptParam : function (paramName, paramValue){
			if(this.sendToActionScripts != undefined){
				this.sendToActionScripts.push({param:paramName, val: paramValue});
			} 
		},
		/**
		 * 마크애니 위변조 처리
		 */
		setMarkany:function(propertiesFilePath){
			this.addScriptParam('print.externalmodule','oz.viewer.export.OZAppletPrintBarcode_MarkAny');
			this.addScriptParam('connection.extraparam','PropertiesFilePath=' + propertiesFilePath + ',UserPassword=a,PdfCreator=MarkAny');
			
			this.startOpt = [];
			this.startOpt["print_exportfrom"] = "server";
		},
		addFont:function(fontNm, fontFileNm, propertiesFilePath){
			this.addScriptParam('font.' + fontNm + '.url',propertiesFilePath + '/' + fontFileNm);
		},
		/**
		 * 오즈 뷰어 실행
		 */
		start : function(){
			start_ozjs(this.viewerId, "/oz80/ozhviewer/", this.startOpt);	
		}
}

function SetOZParamters_OZViewer() {
	var oz;	
	oz = document.getElementById(ComOz.report.viewerId);
	oz.sendToActionScript("connection.servlet", ComOz.report.servletUrl);
	oz.sendToActionScript("connection.reportname", ComOz.report.ozrNm);
	//FormParam셋팅
	if(ComOz.report.formParam != undefined ){
		var _formParamLength = ComOz.report.formParam.length;
		oz.sendToActionScript("connection.pcount",_formParamLength);
		for(var i = 1; i <= _formParamLength; i++){
			oz.sendToActionScript("connection.args" + i,ComOz.report.formParam[i - 1].param + '=' + ComOz.report.formParam[i - 1].val);
		}
	}
	//추가 전달 옵션 처리
	if(ComOz.report.sendToActionScripts != undefined ){
		for(var i = 0; i < ComOz.report.sendToActionScripts.length; i++){
			var _data = ComOz.report.sendToActionScripts[i];
			oz.sendToActionScript(_data.param, _data.val);
		}
	}
	
	return true;
}