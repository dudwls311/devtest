package [PACKAGE].validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import exts.com.validator.ExtsAbstractValidator;
import [PACKAGE].vo.[File_Name]VO;

/**
 * @Class Name : [File_Name]Validator.java
 * @Description : [Description] Validator
 * @Modification Information
 * 
 * @author
 * @since [TODAY]
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@SuppressWarnings("unused")
@Component("[file_Name]Validator")
public class [File_Name]Validator extends ExtsAbstractValidator implements Validator {
	//validate체크할  field들
	[VALIDATOR_COLUMN]

	private static final String FIELDNAME_PRFIX = "[first_package].item.[tail_package]";
	@Override
	protected String getTablePrefix() {return FIELDNAME_PRFIX;}
	
	@Override
	public boolean supports(final Class<?> clazz) {

		return [File_Name]VO.class.equals(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {

		final [File_Name]VO command = ([File_Name]VO) obj;
		[VALIDATOR_REJECT]
	}

}
