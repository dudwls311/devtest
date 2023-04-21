package exts.com.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name : ComExcelValidationResultVO.java
 * @Description : 엑셀 Validation 결과 VO
 * @Modification Information
 * 
 * @author
 * @since 2020. 09.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */

public class ComExcelValidationResultVO {
	@Getter
	private int row;
	@Getter
	@Setter
	private boolean success;
	@Getter
	private Object vo;
	@Getter
	private List<ErrorVO> errorList = new ArrayList<ErrorVO>();
	
	public ComExcelValidationResultVO(int row, Object vo, BindingResult errors) {
		this.row = row;
		this.vo = vo;
		checkError(errors);
	}
	
	public void checkError(BindingResult errors) {
		if(errors.hasErrors()) {
			this.success = false;
			for (final FieldError error : errors.getFieldErrors()) {
				addError(error.getField(), error.getCode(), error.getDefaultMessage());
			}
			//for reject value
			for (final ObjectError error:errors.getAllErrors()) {
			   boolean isExistError = false;
		      for(ErrorVO ae:errorList){
			      if(ae.getErrorCode().equals(error.getCode())){
			         isExistError = true;
			         break;
			      }
			   }
			   if(!isExistError){
			  	 ErrorVO m = new ErrorVO();
			      m.setErrorField(error.getCode());
			      m.setErrorCode(error.getCode());
			      m.setErrorMessage(error.getDefaultMessage());
			      errorList.add(m);
			   }
			}
		}else {
			this.success = true;
		}
	}
	
	/**
	 * error추가.
	 * @param errorField
	 * @param errorCode
	 * @param errorMessage
	 */
	public void addError(String errorField, String errorCode, String errorMessage) {
		if(this.errorList == null) {
			errorList = new ArrayList<ErrorVO>();
		}
		ErrorVO e = new ErrorVO();
		e.setErrorField(errorField);
		e.setErrorCode(errorCode);
		e.setErrorMessage(errorMessage);
		errorList.add(e);
	}
}