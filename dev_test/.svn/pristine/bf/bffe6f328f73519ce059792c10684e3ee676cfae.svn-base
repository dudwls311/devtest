package exts.cpf.dpt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



import exts.com.validator.ExtsAbstractValidator;
import exts.cpf.dpt.vo.CpfDptVO;

/**
 * @Class Name : CpfDptValidator.java
 * @Description : 부서관리 Validator
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@SuppressWarnings("unused")
@Component("cpfDptValidator")
public class CpfDptValidator extends ExtsAbstractValidator implements Validator {
	 	
	//validate체크할  field들
	private static final String deptId = "deptId";		//부서아이디(PK값)
	private static final String deptNm = "deptNm";		//부서명
	

	private static final String FIELDNAME_PRFIX = "exts.item.cpf.dpt";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return CpfDptVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final CpfDptVO command = (CpfDptVO) obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors,deptId, REQUIRED_FIELD, makeMessage(deptId, REQUIRED_FIELD));	//부서아이디(PK값)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,deptNm, REQUIRED_FIELD, makeMessage(deptNm, REQUIRED_FIELD));		//부서명
		
	}

}
