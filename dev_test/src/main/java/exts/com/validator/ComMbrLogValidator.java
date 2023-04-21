package exts.com.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import exts.com.vo.ComMbrLogVO;

/**
 * @Class Name : ComMbrLogValidator.java
 * @Description : 회원로그내역 Validator
 * @Modification Information
 * 
 * @author
 * @since 2022.11.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@SuppressWarnings("unused")
@Component("comMbrLogValidator")
public class ComMbrLogValidator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	private static final String mbrLogSn = "mbrLogSn";		//회원로그내역일련번호
	private static final String mbrId = "mbrId";		//회원ID
	private static final String logDate = "logDate";		//로그일자
	private static final String menuSn = "menuSn";		//메뉴순번
	private static final String logRegDt = "logRegDt";		//로그등록일시
	private static final String logCnts = "logCnts";		//로그내용
	private static final String delYn = "delYn";		//사용여부
	

	private static final String FIELDNAME_PRFIX = "exts.item.com";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return ComMbrLogVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final ComMbrLogVO command = (ComMbrLogVO) obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors,mbrLogSn, REQUIRED_FIELD, makeMessage(mbrLogSn, REQUIRED_FIELD));	//회원로그내역일련번호
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,mbrId, REQUIRED_FIELD, makeMessage(mbrId, REQUIRED_FIELD));		//회원ID
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,logDate, REQUIRED_FIELD, makeMessage(logDate, REQUIRED_FIELD));		//로그일자
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,menuSn, REQUIRED_FIELD, makeMessage(menuSn, REQUIRED_FIELD));		//메뉴순번
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,logRegDt, REQUIRED_FIELD, makeMessage(logRegDt, REQUIRED_FIELD));		//로그등록일시
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,logCnts, REQUIRED_FIELD, makeMessage(logCnts, REQUIRED_FIELD));		//로그내용
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,delYn, REQUIRED_FIELD, makeMessage(delYn, REQUIRED_FIELD));		//사용여부
		
	}

}
