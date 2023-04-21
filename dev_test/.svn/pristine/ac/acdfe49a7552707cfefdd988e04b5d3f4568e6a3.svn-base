package exts.com.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import egovframework.com.utl.fcc.service.NullUtil;
import exts.com.enums.EnumMbrTypeCd;
import exts.com.util.PatternUtil;
import exts.com.vo.ComMbrVO;

/**
 * @Class Name : ComMbrValidator.java
 * @Description : 회원 Validator
 * @Modification Information
 * 
 * @author
 * @since 2022. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Component("comMbrValidator")
public class ComMbrValidator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	private static final String mbrLogin = "mbrLogin";
	private static final String passwd = "passwd";
	private static final String mbrNm = "mbrNm";
	private static final String typeId = "typeId";
	private static final String orgId = "orgId";

	private static final String genderCd = "genderCd";		//성별코드
	private static final String brdtYmd = "brdtYmd";		//생년월일
	private static final String rrno = "rrno";		//주민등록번호
	private static final String ntkrdfUnqNo = "ntkrdfUnqNo";		//북한이탈주민고유번호
	private static final String hanawonTh = "hanawonTh";		//하나원기수
	private static final String entcnyYmd = "entcnyYmd";		//입국연월일
	private static final String prtdcsYmd = "prtdcsYmd";		//보호결정연월일
	private static final String hanawonFnshYmd = "hanawonFnshYmd";		//하나원수료연월일
	private static final String mbphno = "mbphno";		//휴대폰번호
	private static final String zip = "zip";		//우편번호
	private static final String addr = "addr";		//주소
	private static final String daddr = "daddr";		//상세주소

	private static final String FIELDNAME_PRFIX = "com.item.mbr";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return ComMbrVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final ComMbrVO command = (ComMbrVO) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,typeId, REQUIRED_FIELD, makeMessage(typeId, REQUIRED_FIELD));
		//재단직원 및 센터직원체크
		if(EnumMbrTypeCd.FOU.getCode().equals(command.getTypeId())||EnumMbrTypeCd.CEN.getCode().equals(command.getTypeId())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,orgId, REQUIRED_FIELD, makeMessage(orgId, REQUIRED_FIELD));
		}
		
		//추가시
		if(NullUtil.nullString(command.getMbrId()).equals("")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,mbrLogin, REQUIRED_FIELD, makeMessage(mbrLogin, REQUIRED_FIELD));
			//ID패턴체크
			if(!NullUtil.nullString(command.getMbrLogin()).equals("") && !PatternUtil.idRegularExpressionChk(command.getMbrLogin()))errors.rejectValue(mbrLogin, "com.error.login.idpattern", getMessage("com.error.login.idpattern"));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,passwd, REQUIRED_FIELD, makeMessage(passwd, REQUIRED_FIELD));
		}
		//패스워드 패턴 체크
		if(!NullUtil.nullString(command.getPasswd()).equals("") && !PatternUtil.pwdRegularExpressionChk(command.getPasswd()))errors.rejectValue(passwd, "com.error.login.pwpattern", getMessage("com.error.login.pwpattern"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,mbrNm, REQUIRED_FIELD, makeMessage(mbrNm, REQUIRED_FIELD));
		
		//일반회원 및 북한이탈주민체크
		if(EnumMbrTypeCd.NTK.getCode().equals(command.getTypeId())||EnumMbrTypeCd.NOR.getCode().equals(command.getTypeId())) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,genderCd, REQUIRED_FIELD, makeMessage(genderCd, REQUIRED_FIELD));
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,brdtYmd, REQUIRED_FIELD, makeMessage(brdtYmd, REQUIRED_FIELD));
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,rrno, REQUIRED_FIELD, makeMessage(rrno, REQUIRED_FIELD));			
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,ntkrdfUnqNo, REQUIRED_FIELD, makeMessage(ntkrdfUnqNo, REQUIRED_FIELD));
			if(EnumMbrTypeCd.NTK.getCode().equals(command.getTypeId()))
				ValidationUtils.rejectIfEmptyOrWhitespace(errors,hanawonTh, REQUIRED_FIELD, makeMessage(hanawonTh, REQUIRED_FIELD));
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,entcnyYmd, REQUIRED_FIELD, makeMessage(entcnyYmd, REQUIRED_FIELD));
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,prtdcsYmd, REQUIRED_FIELD, makeMessage(prtdcsYmd, REQUIRED_FIELD));
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors,hanawonFnshYmd, REQUIRED_FIELD, makeMessage(hanawonFnshYmd, REQUIRED_FIELD));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,mbphno, REQUIRED_FIELD, makeMessage(mbphno, REQUIRED_FIELD));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,zip, REQUIRED_FIELD, makeMessage(zip, REQUIRED_FIELD));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,addr, REQUIRED_FIELD, makeMessage(addr, REQUIRED_FIELD));
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,daddr, REQUIRED_FIELD, makeMessage(daddr, REQUIRED_FIELD));	
		}
		
	}

}
