package exts.cpf.dpt.vo;

import lombok.Getter;
import lombok.Setter;
import exts.com.vo.ExtsAbstractVO;
/**
 * @Class Name : CpfDptVO.java
 * @Description : 부서관리 VO
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Getter
@Setter
public class CpfDptVO extends ExtsAbstractVO {

	private String deptId;		//부서아이디(PK값)
	private String deptNm;		//부서명
	

	private String cdMode;		//화면 모드
}
