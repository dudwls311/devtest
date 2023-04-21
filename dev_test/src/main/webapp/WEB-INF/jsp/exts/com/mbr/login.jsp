<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<title>EXTS</title>
<%@ include file="/WEB-INF/jsp/exts/com/header_inc.jsp" %>
	</head>
	<body>

		<script type="text/javascript">
		$(document).ready(function(){
//	     	$('#mbrId').focus();
	        
	    	var ajform = new ComAjaxForm('loginForm','listForm', 
	    		{
	    		beforeSerializeFunction : function(){
	    			$('#loginForm input').removeClass('input_rere');
	    			$('#msgDiv').hide();
	    			$('#mbrLoginMsg').hide();
	    			$('#passwdMsg').hide();
	    		},
	    		successFunction : function(resultData, statusText, xhr, $form){
	    			if(resultData.isSuccess == true){
	    				if(resultData.msg == 'smsAuth'){
	    					setInterval(fnSetIntervalSmsInvlid, 1000);
	    					alert('SMS가 전송되었습니다.');
	    					$("#loginSpan").hide();
	    					ComFns.hideLoading();
	    					$("#smsAuthNumberReqYn").val('N');
	    					$("#smsAuthNumberSpan").slideDown();
	    				}else{
	    					location.href = resultData.msg;
	    				}
	    			}else{
	    				ComFns.hideLoading();
	    				if(resultData.msg){
	    					if(resultData.msg.indexOf('로그인이 차단되었습니다') >= 0){

	    						ComFns.popup.init({title:'로그인 차단'});
	    						ComFns.popup.changeContentHtml($('#divBlock').html());
	    						ComFns.popup.show();
	    					}else{
		    					//alert(resultData.msg);
		    					$('#msgDiv > p').text(resultData.msg);
		    					$('#msgDiv').show();	    						
	    					}
	    					
	    					return;
	    				}
	    				if(resultData.errorList){
	    					for(var i = 0; i < resultData.errorList.length; i++){
	    						var e = resultData.errorList[i];
	    						//alert(e.errorMessage);
								$('#'+e.errorField).addClass('input_rere');
								$('#'+e.errorField + 'Msg').text(e.errorMessage);
	    						$('#'+e.errorField + 'Msg').show();
	    						return;
	    					}	
	    				}
	    			}
	    		}
	    	});
	    	ajform.init();
	    	
	    	function fnSetIntervalSmsInvlid(){
	    		var invalidTimeText = ' (유효시간  '+fnSecondToMinute()+')';
	    		$("#invalidSecondSpan").text(invalidTimeText);
			}
			
			var second = $("#smsAuthInvalidTime").val();
			function fnSecondToMinute() {
				second--;
				var min = parseInt((second%3600)/60);
				var sec = second%60;
				//if(min < 10) min = '0'+min;
				if(sec < 10) sec = '0'+sec;
				
				if(sec >= 0){
					return min+":"+sec;
				}else{
					return '만료';
				}
			}
	    });
		</script>
			
			
<div id="wrap">
	<!-- Sample S 이상 -->
	<div class="login_box">
		<h1><strong class="logo"><a href="/index.do"><img src="/resources/img/common/logo_h1.png" alt="EXTS"></a></strong></h1>	
		<h2>EXTS</h2>
		<div class="login_box_02">
			<h3>로그인</h3>
			<form name="loginForm" id="loginForm" class="form-vertical" action ="<c:url value='/exts/com/mbr/loginActionJson.do'/>" method="post">
				<input type="hidden" id="smsAuthNumberReqYn" name="smsAuthNumberReqYn" value="<c:out value="${smsAuthNumberReqYn }" />" />
				<input type="hidden" id="smsAuthInvalidTime" value="<c:out value="${smsAuthInvalidTime }" />" />
				
				<span id="loginSpan">
					<label for="mbrLogin">아이디</label>
					<input type="text" name="mbrLogin" id="mbrLogin" class="" placeholder="아이디">
					<p id="mbrLoginMsg" class="txt_c_re" style="display: none;">아이디를 입력해 주세요.</p>
		
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd" id="passwd" placeholder="비밀번호">
					<p id="passwdMsg" class="txt_c_re" style="display: none;">비밀번호를 입력해 주세요.</p>
					<!-- <a href="#none" class="login_btn" onclick="fn_login();return false;" >로그인</a> -->
				</span>
				
				<span id="smsAuthNumberSpan" style="display: none;">
					<label for="smsAuthNumber">인증번호<span id="invalidSecondSpan"></span></label>
					<input type="text" name="smsAuthNumber" id="smsAuthNumber" placeholder="인증번호">
					<p id="smsAuthNumberMsg" class="txt_c_re" style="display: none;">인증번호를 입력해 주세요.</p>
				</span>
				
				<button type="submit" class="login_btn">로그인</button>
			</form>
			
			<div class="box_w_wht MAB0" id="msgDiv" style="display:none">
				<p class="txt_c_or"></p>
			</div>
			<p class="MAT20 txt_s_fgp">[ 아이디 비밀번호 등 로그인 관련 문의 ]<br>
				<b>1577-6635</b>
			</p>
			
		</div>
		<p class="p_info">Copyright</p>
    </div>
	<!-- Sample E 이하 -->      
</div>
<div id="divBlock" style="display:none">
	<div class="box_w_gray AlignCenter">
		비밀번호를 5회 이상 잘못 입력하여 로그인이 차단되었습니다.<br>
		로그인 차단 해제하시려면 관리자에게 문의해주시기 바랍니다<br><br>
      </div>
</div>
	</body>
</html>