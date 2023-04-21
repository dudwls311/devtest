package exts.com.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Class Name : ComSmplVO.java
 * @Description : 샘플 그룹 VO
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.17
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Getter
@Setter
public class ComSampleSourceVO extends ExtsAbstractVO {

	private String pkgName;		//패키지명
	private String fileName;	//JAVA 파일명
	private String description;	//프로그램 설명
	private String tableName;	//테이블 명
	

	private String cssMode;
}