package exts.cpf.filemapping.service;

import java.util.List;

import exts.cpf.filemapping.vo.CpfFileMappingVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * @Class Name : CpfFileMappingService.java
 * @Description : 첨부파일매핑 Service
 * @Modification Information
 * 
 * @author
 * @since 2023.02.24
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface CpfFileMappingService {
    
	

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectCpfFileMappingTot(CpfFileMappingVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	CpfFileMappingVO selectCpfFileMapping(CpfFileMappingVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeCpfFileMapping(CpfFileMappingVO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteCpfFileMapping(CpfFileMappingVO searchVO) throws EgovBizException;

}
