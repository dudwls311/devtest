package exts.cpf.filemapping.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.impl.ExtsAbstractServiceImpl;
import exts.cpf.emp.service.impl.CpfBoardEmpDao;
import exts.cpf.filemapping.service.CpfFileMappingService;
import exts.cpf.filemapping.vo.CpfFileMappingVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE

/**
 * @Class Name : CpfFileMappingServiceImpl.java
 * @Description : 첨부파일매핑 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2023.02.24
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("cpfFileMappingService")
public class CpfFileMappingServiceImpl extends ExtsAbstractServiceImpl implements CpfFileMappingService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "cpfFileMappingDao")
	private CpfFileMappingDao cpfFileMappingDao;
	
	@Resource(name = "cpfBoardEmpDao")
	private CpfBoardEmpDao cpfBoardEmpDao;
	
	/** ID Generation */
    //@Resource(name="extsCpfFilemappingCpfFileMappingIdGnrService")		//IDGEN USE
    //private EgovIdGnrService egovIdGnrService;	//IDGEN USE
    
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectCpfFileMappingTot(CpfFileMappingVO searchVO) {
		return cpfFileMappingDao.selectCpfFileMappingTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public CpfFileMappingVO selectCpfFileMapping(CpfFileMappingVO searchVO) {
		CpfFileMappingVO result = cpfFileMappingDao.selectCpfFileMapping(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void writeCpfFileMapping(CpfFileMappingVO searchVO) throws Exception {
		cpfFileMappingDao.insertCpfFileMapping(searchVO);
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteCpfFileMapping(CpfFileMappingVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		CpfFileMappingVO result = selectCpfFileMapping(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		cpfFileMappingDao.deleteCpfFileMapping(searchVO);
	}

	

	

	

	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	// ///////////////////////////////////////////////////////////////////
	/**
	 * DB에 입력하기 위한 데이터 처리.
	 * @param searchVO
	 * @throws EgovBizException
	 */
	private void validate(CpfFileMappingVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
