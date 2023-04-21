package exts.cpf.position.service;

import java.util.List;

import exts.cpf.position.vo.CpfPositionVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Class Name : CpfPositionService.java
 * @Description : 직급관리 Service
 * @Modification Information
 * 
 * @author
 * @since 2023.03.03
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface CpfPositionService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<CpfPositionVO> selectCpfPositionList(CpfPositionVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectCpfPositionTot(CpfPositionVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	CpfPositionVO selectCpfPosition(CpfPositionVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeCpfPosition(CpfPositionVO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteCpfPosition(CpfPositionVO searchVO) throws EgovBizException;


	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isViewable(CpfPositionVO searchVO);


	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isModifiable(CpfPositionVO searchVO);


	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isDeletable(CpfPositionVO searchVO);
}
