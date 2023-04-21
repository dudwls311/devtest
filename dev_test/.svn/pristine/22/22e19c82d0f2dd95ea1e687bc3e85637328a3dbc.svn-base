package exts.cpf.position.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import exts.com.validator.ExtsAbstractValidator;
import exts.cpf.position.vo.CpfPositionVO;

/**
 * @Class Name : CpfPositionValidator.java
 * @Description : 직급관리 Validator
 * @Modification Information
 * 
 * @author
 * @since 2023.03.03
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@SuppressWarnings("unused")
@Component("cpfPositionValidator")
public class CpfPositionValidator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	private static final String positionId = "positionId";		//직급PK
	private static final String positionNm = "positionNm";		//직급명
	

	private static final String FIELDNAME_PRFIX = "exts.item.cpf.position";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return CpfPositionVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final CpfPositionVO command = (CpfPositionVO) obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors,positionId, REQUIRED_FIELD, makeMessage(positionId, REQUIRED_FIELD));	//직급PK
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,positionNm, REQUIRED_FIELD, makeMessage(positionNm, REQUIRED_FIELD));		//직급명
		
	}

}
