package exts.cpf.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.impl.ExtsAbstractServiceImpl;
import exts.cpf.emp.service.CpfBoardEmpService;
import exts.cpf.emp.vo.CpfBoardEmpVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE

/**
 * @Class Name : CpfBoardEmpServiceImpl.java
 * @Description : 사내공고 게시판 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("cpfBoardEmpService")
public class CpfBoardEmpServiceImpl extends ExtsAbstractServiceImpl implements CpfBoardEmpService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "cpfBoardEmpDao")
	private CpfBoardEmpDao cpfBoardEmpDao;
	
	/** ID Generation */
    //@Resource(name="extsCpfEmpCpfBoardEmpIdGnrService")		//IDGEN USE
    //private EgovIdGnrService egovIdGnrService;	//IDGEN USE
    
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<CpfBoardEmpVO> selectCpfBoardEmpList(CpfBoardEmpVO searchVO) {
		List<CpfBoardEmpVO> list = (List<CpfBoardEmpVO>)cpfBoardEmpDao.selectCpfBoardEmpList(searchVO);
//		if(list != null){
//			for(CpfBoardEmpVO result:list){
//				
//			}
//		}
		return list;
	}
	
	/**
	 * 매핑을 위한 리스트 
	 * 
	 * @param searchVO
	 * @return 
	 * @throws Exception
	 */
	public List<CpfBoardEmpVO> mappingList(CpfBoardEmpVO searchVO) {
		List<CpfBoardEmpVO> mList = (List<CpfBoardEmpVO>)cpfBoardEmpDao.mappingList(searchVO);
		return mList;
	}
	
	/**
	 * select id list
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<CpfBoardEmpVO> selectFileIdList (CpfBoardEmpVO searchVO) {
		List<CpfBoardEmpVO> Idlist = (List<CpfBoardEmpVO>)cpfBoardEmpDao.selectFileIdList(searchVO);
		return Idlist;
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectCpfBoardEmpTot(CpfBoardEmpVO searchVO) {
		return cpfBoardEmpDao.selectCpfBoardEmpTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public CpfBoardEmpVO selectCpfBoardEmp(CpfBoardEmpVO searchVO) {
		CpfBoardEmpVO result = cpfBoardEmpDao.selectCpfBoardEmp(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void writeCpfBoardEmp(CpfBoardEmpVO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		String id = NullUtil.nullString(searchVO.getId());
		
		if("".equals(id)){
			//id = egovIdGnrService.getNextStringId();	//IDGEN USE
			//searchVO.setId(id);				//IDGEN USE
			if(!isStreAuth())throwBizException("com.error.noauth.write");
			
			cpfBoardEmpDao.insertCpfBoardEmp(searchVO);
			id = searchVO.getId();				//SEQUENCE USE
		}else{
			CpfBoardEmpVO result = selectCpfBoardEmp(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.getId();
			
			cpfBoardEmpDao.updateCpfBoardEmp(searchVO);
		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteCpfBoardEmp(CpfBoardEmpVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		CpfBoardEmpVO result = selectCpfBoardEmp(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		cpfBoardEmpDao.deleteCpfBoardEmp(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(CpfBoardEmpVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(CpfBoardEmpVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(CpfBoardEmpVO searchVO){
		if(searchVO == null)return false;
		if(isAdmin())return true;
		
		return isDelAuth();
	}

	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	// ///////////////////////////////////////////////////////////////////
	/**
	 * DB에 입력하기 위한 데이터 처리.
	 * @param searchVO
	 * @throws EgovBizException
	 */
	private void validate(CpfBoardEmpVO searchVO){
		
	}
	
	public void updateDelYn(CpfBoardEmpVO searchVO) throws Exception {
		cpfBoardEmpDao.updateDelYn(searchVO);
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
