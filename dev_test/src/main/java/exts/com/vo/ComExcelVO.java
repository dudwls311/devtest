package exts.com.vo;

import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONArray;

/**
 * @Class Name : ComExcelVO.java
 * @Description : 엑셀 파싱VO VO
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.17
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Getter
@Setter
public class ComExcelVO {

	private String excelNm;
	private int maxRow;
	private int maxCell;
	private JSONArray data;
}