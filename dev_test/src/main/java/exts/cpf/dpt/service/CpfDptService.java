package exts.cpf.dpt.service;

import java.util.List;

import exts.cpf.dpt.vo.CpfDptVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Class Name : CpfDptService.java
 * @Description : 부서관리 Service
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface CpfDptService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<CpfDptVO> selectCpfDptList(CpfDptVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectCpfDptTot(CpfDptVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	CpfDptVO selectCpfDpt(CpfDptVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeCpfDpt(CpfDptVO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteCpfDpt(CpfDptVO searchVO) throws EgovBizException;


	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isViewable(CpfDptVO searchVO);


	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isModifiable(CpfDptVO searchVO);


	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isDeletable(CpfDptVO searchVO);
}
