package [PACKAGE].service;

import java.util.List;

import [PACKAGE].vo.[File_Name]VO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Class Name : [File_Name]Service.java
 * @Description : [Description] Service
 * @Modification Information
 * 
 * @author
 * @since [TODAY]
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface [File_Name]Service {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<[File_Name]VO> select[File_Name]List([File_Name]VO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer select[File_Name]Tot([File_Name]VO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	[File_Name]VO select[File_Name]([File_Name]VO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void write[File_Name]([File_Name]VO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void delete[File_Name]([File_Name]VO searchVO) throws EgovBizException;


	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isViewable([File_Name]VO searchVO);


	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isModifiable([File_Name]VO searchVO);


	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isDeletable([File_Name]VO searchVO);
}
