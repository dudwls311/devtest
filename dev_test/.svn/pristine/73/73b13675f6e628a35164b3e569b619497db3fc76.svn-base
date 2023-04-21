/**
 * @version 3.2.0.1
 */
package jnit.com.tag;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.com.utl.fcc.service.StringUtil;
import egovframework.com.utl.fcc.service.UrlUtil;
import egovframework.com.utl.fcc.service.WebUtil;
import exts.com.util.JsonUtil;
import exts.com.vo.ComCodeVO;
import jnit.com.util.SpringHelperUtil;
import jnit.crypto.JnitCryptoService;

public class JnitTag{

	public JnitTag()
    {
    }
    
    /**
     * xss 방지 처리
     * @param html
     * @return
     */
    public static String unscript(String html){
    	return WebUtil.unscript(html);
    }
    
    /**
     * 태그 없애기
     * @param html
     * @return
     */
    public static String removeHtmlTag(String html){
    	return WebUtil.removeTag(html);
    }

    /**
     * 태그를 HTML형태로 그대로 출력할때 변환
     * @param txtValue
     * @return
     */
    public static String dbToHtml(String txtValue){
    	return WebUtil.DbToHtml(txtValue, "");
    }
    /**
     * 태그를 HTML형태로 그대로 출력할때 엔터값을 제외하고 변환
     * @param txtValue
     * @return
     */
    public static String dbToHtmlExceptEnter(String txtValue){
    	return WebUtil.DbToHtmlExceptEnter(txtValue, "");
    }
    
    /**
     * 오늘날짜와 해당날짜의 차이 일수.
     * @param d 해당일
     * @return 차이 날짜수.
     */
    public static long getDayFromToday(Date d){
    	Date today = new Date();
	    long diff = today.getTime() - d.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
    	return diffDays;
    }
    
    /**
     * 글자수로 문자열을 체크하여 글자 자르기.
     * @param str
     * @param cutCount
     * @param addString
     * @return
     */
    public static String cutString(String str, int cutCount,String addString){
    	return StringUtil.cutString(str, cutCount, addString);
    }
    /**
     * Byte크기로 문자열을 체크하여 글자 자르기.
     * @param str
     * @param byteCount
     * @param addString
     * @return
     */
    public static String cutStringByByte(String str, int byteCount,String addString){
    	return StringUtil.cutStringByByte(str, byteCount, addString);
    }
    /**
     * url인코딩
     * @param url
     * @return
     */
    public static String encodeUrl(String url){
    	return UrlUtil.encodeUrl(url);
    }
    /**
     * 현재url인코딩
     * @param request
     * @return
     */
    public static String encodeCurrentUrl(HttpServletRequest request){
    	return UrlUtil.encodeCurrentUrl(request);
    }
    /**
     * 전체 URL가져오기.
     * @param request
     * @return
     */
    public static String currentAllUrl(HttpServletRequest request){
    	return UrlUtil.currentAllUrl(request);
    }
    /**
     * 8자리 형태 날짜를 구분자 추가하여 표현
     * @param str
     * @param strSplit
     * @return
     */
    public static String convertDateSplitString(String str, String strSplit) {
    	if(str == null)return str;
    	
    	if(str.length() == 4)return str;
    	if(str.length() == 6)return str.substring(0,4) + strSplit + str.substring(4,6);
    	if(str.length() != 8)return str;
    	return str.substring(0,4) + strSplit + str.substring(4,6) + strSplit + str.substring(6,8);
    }
    /**
     * 10자리 형태 사업자등록번호를 구분자 추가하여 표현
     * @param str
     * @param strSplit
     * @return
     */
    public static String convertBiznumberSplitString(String str, String strSplit) {
    	if(str == null)return str;
    	if(str.length() != 10)return str;
    	return str.substring(0,3) + strSplit + str.substring(3,5) + strSplit + str.substring(5,10);
    }

    /**
     * 퍼센트 값 구하기
     * @param total
     * @param val
     * @param format
     * @return
     */
    public static String percent(String total, String val, String format) {
    	String ret = "0";
    	try {
    		double t = Double.parseDouble(total);
    		double v = Double.parseDouble(val);
    		if(t == 0 || v == 0)return "0";
    		if("".equals(format))format = "##.##";
    		DecimalFormat df = new DecimalFormat(format);
    		double percent = (v / t) * 100.0;    		
    		ret = df.format(percent);
    	}catch(NumberFormatException e) {
    		ret = "0";
    	}
    	return ret;
    }

    /**
     * 평균 값 구하기
     * @param total
     * @param cnt
     * @param format
     * @return
     */
    public static String average(String total, String cnt, String format) {
    	String ret = "0";
    	try {
    		double t = Double.parseDouble(total);
    		double c = Double.parseDouble(cnt);
    		if(t == 0 || c == 0)return "0";
    		if("".equals(format))format = "##.##";
    		DecimalFormat df = new DecimalFormat(format);
    		double average = t / c;    		
    		ret = df.format(average);
    	}catch(NumberFormatException e) {
    		ret = "0";
    	}
    	return ret;
    }
    
    /**
     * 이미지로 보여주기 위한 코드
     * @param filePath
     * @param fileName
     * @return
     */
    public static String fileViewEncode(String filePath, String fileName, String atchFileSn) {
    	if("".equals(NullUtil.nullString(filePath)) || "".equals(NullUtil.nullString(fileName))) return null;
		StringBuffer sb = new StringBuffer();
		sb.append(filePath).append("||").append(fileName).append("||").append(atchFileSn);
		JnitCryptoService jnitCryptoService = (JnitCryptoService) SpringHelperUtil.getBean("action", "jnitCryptoService");
		return jnitCryptoService.encrypt(sb.toString());
    }
    
    /**
     * 지원관리 이미지로 보여주기 위한 코드
     * @param filePath
     * @param fileName
     * @return
     */
    public static String sprtFileViewEncode(String atchFileSn, String sprtSn) {
    	if("".equals(NullUtil.nullString(atchFileSn))) return null;
		JnitCryptoService jnitCryptoService = (JnitCryptoService) SpringHelperUtil.getBean("action", "jnitCryptoService");
		if(sprtSn == null || "".equals(sprtSn)) {
			return jnitCryptoService.encrypt(atchFileSn);
		}else {
			StringBuffer sb = new StringBuffer();
			sb.append(atchFileSn).append("||").append(sprtSn);
			return jnitCryptoService.encrypt(sb.toString());
		}
    }
    
    /**
     * VO to JSON
     * @param obj
     * @return
     * @throws JsonProcessingException 
     */
    public static String convertObjectToJson(Object obj) throws JsonProcessingException {
    	return JsonUtil.convertObjectToJson(obj);
    }
/////////////////////////////  특정 사이트용 ///////////////////////////
    
    /**
     * 코드값으로 코드명 가져오기
     * @param list
     * @param cd
     * @return
     */
    public static String getCdNm(List<ComCodeVO> list, String cd) {
		if(list != null) {
			for(ComCodeVO data:list) {
				if(data.getIndivCd().equals(cd))return data.getIndivCdNm();
			}
		}
		return "";
    }
}
