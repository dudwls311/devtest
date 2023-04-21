package exts.com.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import exts.com.util.JsonUtil;
import lombok.Data;

/**
 *  기본 모델 클래스
 * 
 * @author 
 * @since 2020.07.21
 * @version 1.0
 * @see
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.07.21            최초 생성
 *
 * </pre>
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value={"searchCondition","searchKeyword","pageIndex","pageUnit","pageSize","firstIndex","lastIndex"
		,"recordCountPerPage","orderSql","redDt","mdfcnDt","retUrl"})
@Data
public abstract class ExtsAbstractVO {

	/** 검색조건 */
	private String searchCondition;
	
	/** 검색Keyword */
	private String searchKeyword;
	
	/** 상세검색여부 */
	private String searchDetailOpen;
	
	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 */
	private int pageUnit = 0;

	/** 페이지사이즈 */
	private int pageSize = 0;

	/** firstIndex */
	private int firstIndex = 0;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	private String orderSql;

	private Date regDt;		//등록일
	private String rgtrId;		//등록자
	private Date mdfcnDt;		//수정일
	private String mdfrId;		//수정자
	private String delYn;		//삭제여부

	private String retUrl;


	@Override
	public String toString() {
		String ret = "";
		try {
			ret = JsonUtil.convertObjectToJson(this);			
		}catch(JsonProcessingException ex) {
			ret = ex.getMessage();
		}
		return ret;
	}
}
