/**
 * jquery.form 관련
 * @param writeFormId ajaxform을 사용할 form의 id값
 * @param options {successMessage:'',failMessage:'',successFunction:function,errorFunction:function,async:boolean}
 */
function ComAjaxForm(writeFormId, listFormId, options){
	
	this.data = {
			successMessage:'처리되었습니다',
			async:true
		};
	this.data.writeFormId = writeFormId;
	this.data.listFormId = listFormId;
	this.data.beforeSerializeFunction = function(form, options) {
	};
	this.data.beforeSubmitFunction = function(formData, jqForm, options) {
		try{ComFns.showLoading();}catch(ex){}
	};
	
	this.data.successFunction = function(resultData, statusText, xhr, $form){
		if(resultData.isSuccess == true){
			if($form.prop('data-obj').data.successMessage != '')alert($form.prop('data-obj').data.successMessage);
			if($form.prop('data-obj').data.reload == 'Y'){
				location.reload();
				return false;
			}
			if($form.prop('data-obj').data.callback != undefined && $form.prop('data-obj').data.callback != ''){
				$form.prop('data-obj').data.callback();
				return false;
			}
			$('#' + $form.prop('data-obj').data.listFormId).submit();
		}else{
			try{ComFns.hideLoading();}catch(ex){}	
			if(resultData.msg){
				alert(resultData.msg);	
				return;
			}
			if(resultData.errorList){
				for(var i = 0; i < resultData.errorList.length; i++){
					var e = resultData.errorList[i];
					alert(e.errorMessage);
					$('#'+e.errorField).focus();
					return;
				}	
			}
		}
	};
	this.data.errorFunction = function(xhr, textStatus, errorThrown) {
		alert('ajax form error');
		console.log(xhr);
		console.log(textStatus);
		console.log(errorThrown);
	};
	
	for(var k in options){
		this.data[k] = options[k];
	}
	$('#' + writeFormId).prop('data-obj',this);
}


ComAjaxForm.prototype = {
	constructor : ComAjaxForm,
	init : function(){
		$('#' + this.data.writeFormId).ajaxForm({
			beforeSerialize : this.data.beforeSerializeFunction,
			beforeSubmit : this.data.beforeSubmitFunction,
			success : this.data.successFunction,
			error: this.data.errorFunction,
			async:this.data.async
		});
	},
	setSuccessMessage : function(msg){
		this.data.successMessage = msg;
	},
	setAsync : function(msg){
		this.data.async = msg;
	},
	setBeforeSubmitFunction : function(fn){
		this.data.beforeSubmitFunction = fn;
	},
	setSuccessFunction : function(fn){
		this.data.successFunction = fn;
	}
}