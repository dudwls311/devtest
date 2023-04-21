package exts.com.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import exts.com.vo.ComCodeGrpVO;

/**
 * @Class Name : ComCodeGrpService.java
 * @Description : 그룹코드 Service
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComCodeGrpService extends ExtsAbstractService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<ComCodeGrpVO> selectComCodeGrpList(ComCodeGrpVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectComCodeGrpTot(ComCodeGrpVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	ComCodeGrpVO selectComCodeGrp(ComCodeGrpVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeComCodeGrp(ComCodeGrpVO searchVO) throws EgovBizException, FdlException;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteComCodeGrp(ComCodeGrpVO searchVO) throws EgovBizException;
}
