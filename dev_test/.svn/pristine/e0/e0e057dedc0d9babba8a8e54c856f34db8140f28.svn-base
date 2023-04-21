package exts.com.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import exts.com.util.PatternUtil;
import exts.com.vo.ComCodeVO;

/**
 * @Class Name : ComCodeValidator.java
 * @Description : 개별코드 Validator
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@SuppressWarnings("unused")
@Component("comCodeValidator")
public class ComCodeValidator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	private static final String indivCd = "indivCd";                 //개별코드  
	private static final String groupCd = "groupCd";                 //그룹코드  
	private static final String upIndivCd = "upIndivCd";             //상위개별코드
	private static final String indivCdNm = "indivCdNm";             //코드명
	private static final String indivCdSortSn = "indivCdSortSn";     //코드정렬순서   
	private static final String indivCdChgYn = "indivCdChgYn";       //변경가능여부
	private static final String indivCdVlFrst = "indivCdVlFrst";     //추가코드1값
	private static final String indivCdVlScndry = "indivCdVlScndry"; //추가코드2값
	private static final String indivCdVlThird = "indivCdVlThird";   //추가코드3값
	private static final String indivCdExpln = "indivCdExpln";       //코드설명
	

	private static final String FIELDNAME_PRFIX = "com.item.code";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return ComCodeVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final ComCodeVO command = (ComCodeVO) obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCd, REQUIRED_FIELD, makeMessage(indivCd, REQUIRED_FIELD));
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,groupCd, REQUIRED_FIELD, makeMessage(groupCd, REQUIRED_FIELD));
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,upIndivCd, REQUIRED_FIELD, makeMessage(upIndivCd, REQUIRED_FIELD));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdNm, REQUIRED_FIELD, makeMessage(indivCdNm, REQUIRED_FIELD));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdSortSn, REQUIRED_FIELD, makeMessage(indivCdSortSn, REQUIRED_FIELD));
		if(!PatternUtil.isNumber(command.getIndivCdSortSn()))errors.rejectValue(indivCdSortSn, INVALID_NUMBER_FIELD, makeMessage(indivCdSortSn, INVALID_NUMBER_FIELD));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdChgYn, REQUIRED_FIELD, makeMessage(indivCdChgYn, REQUIRED_FIELD));
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdVlFrst, REQUIRED_FIELD, makeMessage(indivCdVlFrst, REQUIRED_FIELD));
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdVlScndry, REQUIRED_FIELD, makeMessage(indivCdVlScndry, REQUIRED_FIELD));
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdVlThird, REQUIRED_FIELD, makeMessage(indivCdVlThird, REQUIRED_FIELD));
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors,indivCdExpln, REQUIRED_FIELD, makeMessage(indivCdExpln, REQUIRED_FIELD));
		
	}

}
