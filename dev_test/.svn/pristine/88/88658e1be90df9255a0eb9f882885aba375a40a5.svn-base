package exts.cpf.emp.service;

import java.util.List;

import exts.cpf.emp.vo.CpfBoardEmpVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Class Name : CpfBoardEmpService.java
 * @Description : 사내공고 게시판 Service
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface CpfBoardEmpService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<CpfBoardEmpVO> selectCpfBoardEmpList(CpfBoardEmpVO searchVO);
	
	/**
	 * 매핑을 위한 리스트 
	 * 
	 * @param searchVO
	 * @return 
	 * @throws Exception
	 */
	List<CpfBoardEmpVO> mappingList(CpfBoardEmpVO searchVO);
	
	/**
	 * select idList 
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<CpfBoardEmpVO> selectFileIdList(CpfBoardEmpVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectCpfBoardEmpTot(CpfBoardEmpVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	CpfBoardEmpVO selectCpfBoardEmp(CpfBoardEmpVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeCpfBoardEmp(CpfBoardEmpVO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteCpfBoardEmp(CpfBoardEmpVO searchVO) throws EgovBizException;


	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isViewable(CpfBoardEmpVO searchVO);


	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isModifiable(CpfBoardEmpVO searchVO);


	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isDeletable(CpfBoardEmpVO searchVO);
	
	/**
	 * 첨부파일 삭제 
	 * @param searchVO
	 * @throws Exception
	 */
	void updateDelYn(CpfBoardEmpVO searchVO) throws Exception;
}
