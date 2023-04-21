package exts.cpf.filemapping.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import exts.com.validator.ExtsAbstractValidator;
import exts.cpf.filemapping.vo.CpfFileMappingVO;

/**
 * @Class Name : CpfFileMappingValidator.java
 * @Description : 첨부파일매핑 Validator
 * @Modification Information
 * 
 * @author
 * @since 2023.02.24
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@SuppressWarnings("unused")
@Component("cpfFileMappingValidator")
public class CpfFileMappingValidator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	private static final String id = "id";		//고유ID
	private static final String fileId = "fileId";		//파일고유아이디
	

	private static final String FIELDNAME_PRFIX = "exts.item.cpf.filemapping";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return CpfFileMappingVO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final CpfFileMappingVO command = (CpfFileMappingVO) obj;
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors,id, REQUIRED_FIELD, makeMessage(id, REQUIRED_FIELD));	//고유ID
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,fileId, REQUIRED_FIELD, makeMessage(fileId, REQUIRED_FIELD));		//파일고유아이디
		
	}

}
