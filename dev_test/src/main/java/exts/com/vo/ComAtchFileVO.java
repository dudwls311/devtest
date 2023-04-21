package exts.com.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * @Class Name : ComAtchFileVO.java
 * @Description : 첨부파일 VO
 * @Modification Information
 * 
 * @author
 * @since 2022.08.16
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Getter
@Setter
public class ComAtchFileVO extends ExtsAbstractVO {

	private String atchFileSn;		//첨부파일일련번호
	private String orgnlAtchFileNm;	//원본첨부파일명
	private String atchFileNm;		//첨부파일명
	private long   atchFileSz;		//첨부파일크기
	private String atchFileExtnNm;	//첨부파일확장자명
	private String atchFilePathNm;	//첨부파일경로명
	private String delYn; //삭제여부 
	
	private List<String> atchFileSnList;		//첨부파일일련번호 List
}
