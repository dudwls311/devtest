package exts.com.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import egovframework.com.utl.fcc.service.NullUtil;
import exts.com.util.PatternUtil;
import exts.com.vo.ComMbrVO;

/**
 * @Class Name : ComMbrPwChangeValidator.java
 * @Description : 비밀번호 변경 Validator
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by NCMS All right reserved.
 */
@Component("comMbrPwChangeValidator")
public class ComMbrPwChangeValidator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	private static final String passwd = "passwd";
	

	private static final String FIELDNAME_PRFIX = "com.item.login";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return ComMbrVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final ComMbrVO command = (ComMbrVO) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,passwd, REQUIRED_FIELD, makeMessage(passwd, REQUIRED_FIELD));
		
		//패스워드 패턴 체크
		if(!NullUtil.nullString(command.getPasswd()).equals("")){
			if(!PatternUtil.pwdRegularExpressionChk(command.getPasswd()))errors.rejectValue(passwd, "com.error.login.pwpattern", getMessage("com.error.login.pwpattern"));
			if(PatternUtil.sameId(command.getPasswd(), command.getMbrLogin()))errors.rejectValue(passwd, "com.error.login.pwsameid", getMessage("com.error.login.pwsameid"));
		} 
		
	}

}
