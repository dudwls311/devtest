package exts.com.service.impl;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.com.utl.fcc.service.SessionUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.enums.EnumMbrLevelCd;
import exts.com.enums.EnumMbrTypeCd;
import exts.com.exception.ValidationException;
import exts.com.exception.ValidationException.ERROR_TYPE;
import exts.com.service.ComMbrService;
import exts.com.vo.ComCodeVO;
import exts.com.vo.ComMbrVO;
import exts.com.vo.ExtsAbstractVO;
import exts.com.web.ExtsAbstractController;
import jnit.cms.CmsHelper;
import jnit.cms.mbr.JnitcmsmbrVO;
import jnit.com.util.SpringHelperUtil;

/**
 *  기본 ServiceImpl 클래스
 * 
 * @author 
 * @since 2020.07.21
 * @version 1.0
 * @see
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *	수정일		수정자		수정내용
 *	-------		--------	---------------------------
 *  2020.07.21		 최초 생성
 *
 * </pre>
 */
public abstract class ExtsAbstractServiceImpl{
	
	protected final Log log = LogFactory.getLog(getClass());


	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	/**
	 * 로그인 여부
	 * 
	 * @return
	 */
	public boolean isLogin() {
		return !getLoginID().equals("");
	}

	/**
	 * 로그인 아이디가져오기
	 * 
	 * @return
	 */
	public String getLoginID() {
		String ret = "";
		JnitcmsmbrVO m = getLoginVO();
		if (m != null && !"".equals(NullUtil.nullString(m.getMbrId()))) ret = m.getMbrId();
		return ret;
	}

	/**
	 * 로그인 그룹ID가져오기
	 * 
	 * @return
	 */
//	public String getLoginGroupID() {
//		String ret = "";		
//		ComMbrVO m = getLoginVO();
//		if (m != null && !"".equals(NullUtil.nullString(m.getAuthGrpId())))
//			ret = m.getAuthGrpId();
//		return ret;
//	}
	
	/**
	 * 로그인 정보가져오기
	 * 
	 * @return
	 */
//	public ComMbrVO getLoginVO() {
//		ComMbrVO ret = null;
//		try{
//			ret = (ComMbrVO)SessionUtil.getAttribute(ComMbrService.SESSION_VO);
//		}catch(NullPointerException e){
//			log.error(e.getMessage());
//		}catch(Exception e){
//			log.error(e.getMessage());
//		}
//		return ret;
//	}
	public JnitcmsmbrVO getLoginVO() {
		JnitcmsmbrVO ret = null;
		try{
			ret = (JnitcmsmbrVO)CmsHelper.getAuthMbr(SpringHelperUtil.getRequest());
		}catch(NullPointerException e){
			log.error(e.getMessage());
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return ret;
	}

	/**
	 * 추가 회원정보 가져오기
	 * @return
	 */
	public ComMbrVO getLoginAdtVO() {
		return (ComMbrVO)SessionUtil.getAttribute(ComMbrService.SESSION_ADT_VO);
	}

	public boolean isAdmin() {
		if(isMbrTypeSuper())return true;
		return false;
	}
	
	/**
	 * 직원 레벨 여부
	 * 
	 * @return
	 */
	public boolean isMbrLevelStaff() {

		JnitcmsmbrVO m = getLoginVO();
		if(m == null)return false;
		
		String lv  = m.getTypeVO().getTypeLv();
		if(EnumMbrLevelCd.LV_A.getCode().equals(lv)
			||EnumMbrLevelCd.LV_B.getCode().equals(lv)
			||EnumMbrLevelCd.LV_C.getCode().equals(lv)
			||EnumMbrLevelCd.LV_D.getCode().equals(lv))return true;
		return false;
	}
	
	/**
	 * 유형관리(최고관리자)
	 * 
	 * @return
	 */
	public boolean isMbrTypeSuper() {

		JnitcmsmbrVO m = getLoginVO();
		if(m == null)return false;
		
		if(EnumMbrTypeCd.SUP.getCode().equals(m.getTypeVO().getTypeId()))return true;
		return false;
	}
	
	/**
	 * 재단직원여부
	 * 
	 * @return
	 */
	public boolean isFoundStaff() {

		JnitcmsmbrVO m = getLoginVO();
		if(m == null)return false;
		
		if(EnumMbrTypeCd.FOU.getCode().equals(m.getTypeVO().getTypeId()))return true;
		return false;
	}
	/**
	 * 센터직원여부
	 * 
	 * @return
	 */
	public boolean isCenterStaff() {

		JnitcmsmbrVO m = getLoginVO();
		if(m == null)return false;
		
		if(EnumMbrTypeCd.CEN.getCode().equals(m.getTypeVO().getTypeId()))return true;
		return false;
	}
	
	/**
	 * 북한이탈주민여부
	 * 
	 * @return
	 */
	public boolean isNtkrdf() {

		JnitcmsmbrVO m = getLoginVO();
		if(m == null)return false;
		
		if(EnumMbrTypeCd.NTK.getCode().equals(m.getTypeVO().getTypeId()))return true;
		return false;
	}

	/**
	 * 일반회원여부
	 * 
	 * @return
	 */
	public boolean isNormal() {

		JnitcmsmbrVO m = getLoginVO();
		if(m == null)return false;
		
		if(EnumMbrTypeCd.NOR.getCode().equals(m.getTypeVO().getTypeId()))return true;
		return false;
	}

	/**
	 * 저장권한
	 * @return
	 */
	public boolean isStreAuth(){
		return (Boolean)SpringHelperUtil.getRequest().getAttribute(ExtsAbstractController.REQUEST_IS_STRE_AUTH);
	}
	/**
	 * 읽기권한
	 * @return
	 */
	public boolean isRedngAuth(){
		return (Boolean)SpringHelperUtil.getRequest().getAttribute(ExtsAbstractController.REQUEST_IS_REDNG_AUTH);
	}
	/**
	 * 삭제권한
	 * @return
	 */
	public boolean isDelAuth(){
		return (Boolean)SpringHelperUtil.getRequest().getAttribute(ExtsAbstractController.REQUEST_IS_DEL_AUTH);
	}


	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ExtsAbstractVO searchVO){
		if(!isRedngAuth())return false;
		
		if(searchVO == null)return false;
		if(isAdmin())return true;
//		return NullUtil.nullString(searchVO.getRgtrId()).equals(getLoginID());
		return true;
	}
	
	/**
	 * 기본 수정권한
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ExtsAbstractVO searchVO){
		if(!isStreAuth())return false;
		
		if(searchVO == null)return false;
		if(isAdmin())return true;

//		return NullUtil.nullString(searchVO.getRgtrId()).equals(getLoginID());
		return true;
	}
	
	/**
	 * 기본 삭제권한
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ExtsAbstractVO searchVO){
		if(!isDelAuth())return false;
		
		if(searchVO == null)return false;
		if(isAdmin())return true;

//		return NullUtil.nullString(searchVO.getRgtrId()).equals(getLoginID());
		return true;
	}
	
	/**
	 * msgKey를 통한 메세지 가져오기
	 * 
	 * @param msgKey
	 * @return
	 */
	protected String getMessage(final String msgKey) {
		return getMessage(msgKey, null);
	}

	/**
	 * msgKey를 통한 메세지 가져오기
	 * 
	 * @param msgKey
	 * @param msgArgs
	 * @return
	 */
	protected String getMessage(final String msgKey, String[] msgArgs) {
		return getMessage(msgKey, msgArgs, Locale.getDefault());
	}

	/**
	 * msgKey를 통한 메세지 가져오기
	 * 
	 * @param msgKey
	 * @param msgArgs
	 * @param locale
	 * @return
	 */
	protected String getMessage(final String msgKey, String[] msgArgs, Locale locale) {
		return messageSource.getMessage(msgKey, msgArgs, locale);
	}

	/**
	 * msgKey를 통한 메세지 가져오기
	 * 
	 * @param msgKey
	 * @param msgArgs
	 * @param defaultMessage
	 * @param locale
	 * @return
	 */
	protected String getMessage(final String msgKey, String[] msgArgs, String defaultMessage, Locale locale) {
		return messageSource.getMessage(msgKey, msgArgs, defaultMessage, locale);
	}

	/**
	 * EgovBizException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @return Exception EgovBizException 객체
	 */
	protected EgovBizException processException(final String msgKey) {
		return processException(msgKey, new String[] {});
	}

	/**
	 * EgovBizException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @return Exception EgovBizException 객체
	 */
	protected EgovBizException processException(final String msgKey, Exception e) {
		return processException(msgKey, new String[] {}, e);
	}

	/**
	 * EgovBizException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @return Exception EgovBizException 객체
	 */
	protected EgovBizException processException(final String msgKey, final String[] msgArgs) {
		return processException(msgKey, msgArgs, null);
	}

	/**
	 * EgovBizException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @return Exception EgovBizException 객체
	 */
	protected EgovBizException processException(final String msgKey, final String[] msgArgs, final Exception e) {
		return processException(msgKey, msgArgs, e, LocaleContextHolder.getLocale());
	}

	/**
	 * EgovBizException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @param locale 명시적 국가/언어지정
	 * @return Exception EgovBizException 객체
	 */
	protected EgovBizException processException(final String msgKey, final String[] msgArgs, final Exception e, Locale locale) {
		return processException(msgKey, msgArgs, e, locale, null);
	}

	/**
	 * EgovBizException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @param locale 명시적 국가/언어지정
	 * @param exceptionCreator 외부에서 별도의 Exception 생성기 지정
	 * @return Exception EgovBizException 객체
	 */
	protected EgovBizException processException(final String msgKey, final String[] msgArgs, final Exception e,
			final Locale locale, ExceptionCreator exceptionCreator) {
		ExceptionCreator eC = null;
		if (exceptionCreator == null) {
			eC = new ExceptionCreator() {

				public EgovBizException createBizException(MessageSource messageSource) {
					return new EgovBizException(messageSource, msgKey, msgArgs, locale, e);
				}
			};
		}
		return eC.createBizException(messageSource);
	}

	protected interface ExceptionCreator {

		EgovBizException createBizException(MessageSource messageSource);
	}


	
	/**
	 * 비지니스 로직에러 발생(꽁통)
	 * @param msg
	 * @throws BizException
	 */
	protected void throwBizException(final String msgKey)throws EgovBizException{
		throwBizException(msgKey,null);
	}
	protected void throwBizException(final String msgKey,String[] args)throws EgovBizException{
		throw processException(msgKey,args);
	}
	

	/**
	 * ValidationException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @return Exception EgovBizException 객체
	 */
	protected ValidationException validationException(final String errorKey, final String msgKey) {
		return validationException(errorKey, ERROR_TYPE.NOT_NULL, msgKey);
	}
	protected ValidationException validationException(final String errorKey, final String msgKey, final String[] args) {
		return validationException(errorKey, ERROR_TYPE.NOT_NULL, msgKey, args);
	}
	protected ValidationException validationException(final String errorKey, final ERROR_TYPE errorType, final String msgKey) {
		return validationException(errorKey, errorType, msgKey, new String[] {});
	}
	protected ValidationException validationException(final String errorKey, final ERROR_TYPE errorType, final String msgKey, final String[] msgArgs) {
		return validationException(errorKey, errorType, msgKey, msgArgs, null);
	}

	/**
	 * ValidationException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @return Exception EgovBizException 객체
	 */
	private ValidationException validationException(final String errorKey, final ERROR_TYPE errorType, final String msgKey, final String[] msgArgs, final Exception e) {
		return validationException(errorKey, errorType,msgKey, msgArgs, e, LocaleContextHolder.getLocale());
	}

	/**
	 * ValidationException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @param locale 명시적 국가/언어지정
	 * @return Exception EgovBizException 객체
	 */
	private ValidationException validationException(final String errorKey, final ERROR_TYPE errorType, final String msgKey, final String[] msgArgs, final Exception e, Locale locale) {
		return validationException(errorKey, errorType, msgKey, msgArgs, e, locale, null);
	}

	/**
	 * ValidationException 발생을 위한 메소드
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @param locale 명시적 국가/언어지정
	 * @param exceptionCreator 외부에서 별도의 Exception 생성기 지정
	 * @return Exception EgovBizException 객체
	 */
	private ValidationException validationException(final String errorKey, final ERROR_TYPE errorType, final String msgKey, final String[] msgArgs, final Exception e,
			final Locale locale, ValidationExceptionCreator exceptionCreator) {
		ValidationExceptionCreator eC = null;
		if (exceptionCreator == null) {
			eC = new ValidationExceptionCreator() {

				public ValidationException createValidationException(MessageSource messageSource) {
					return new ValidationException(messageSource, errorKey, errorType, msgKey, msgArgs, locale, e);
				}
			};
		}
		return eC.createValidationException(messageSource);
	}

	private interface ValidationExceptionCreator {

		ValidationException createValidationException(MessageSource messageSource);
	}
	

	
	/**
	 * 밸리데이션 로직에러 발생(꽁통)
	 * @param msg
	 * @throws BizException
	 */
	protected void throwValidationException(final String errorKey, final String msgKey)throws ValidationException{
		throwValidationException(errorKey, msgKey,null);
	}
	protected void throwValidationException(final String errorKey, final String msgKey,String[] args)throws ValidationException{
		throw validationException(errorKey, msgKey,args);
	}

	/**
	 * 코드명으로 코드값 가져오기
	 * @param list
	 * @param cdNm
	 * @return
	 */
	protected String getCd(List<ComCodeVO> list, String cdNm) {
		for(ComCodeVO data:list) {
			if(data.getIndivCdNm().equals(cdNm))return data.getIndivCd();
		}
		return "";
	}
}
