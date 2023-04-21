package exts.cpf.dpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.impl.ExtsAbstractServiceImpl;
import exts.cpf.dpt.service.CpfDptService;
import exts.cpf.dpt.vo.CpfDptVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE

/**
 * @Class Name : CpfDptServiceImpl.java
 * @Description : 부서관리 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("cpfDptService")
public class CpfDptServiceImpl extends ExtsAbstractServiceImpl implements CpfDptService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "cpfDptDao")
	private CpfDptDao cpfDptDao;
	
	/** ID Generation */
    //@Resource(name="extsCpfDptCpfDptIdGnrService")		//IDGEN USE
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
	public List<CpfDptVO> selectCpfDptList(CpfDptVO searchVO) {
		List<CpfDptVO> list = (List<CpfDptVO>)cpfDptDao.selectCpfDptList(searchVO);
//		if(list != null){
//			for(CpfDptVO result:list){
//				
//			}
//		}
		return list;
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectCpfDptTot(CpfDptVO searchVO) {
		return cpfDptDao.selectCpfDptTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public CpfDptVO selectCpfDpt(CpfDptVO searchVO) {
		CpfDptVO result = cpfDptDao.selectCpfDpt(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void writeCpfDpt(CpfDptVO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		String id = NullUtil.nullString(searchVO.getDeptId());
		
		if("".equals(id)){
			//id = egovIdGnrService.getNextStringId();	//IDGEN USE
			//searchVO.setDeptId(id);				//IDGEN USE
			if(!isStreAuth())throwBizException("com.error.noauth.write");
			
			cpfDptDao.insertCpfDpt(searchVO);
			id = searchVO.getDeptId();				//SEQUENCE USE
		}else{
			CpfDptVO result = selectCpfDpt(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.getDeptId();
			
			cpfDptDao.updateCpfDpt(searchVO);
		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteCpfDpt(CpfDptVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		CpfDptVO result = selectCpfDpt(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		cpfDptDao.deleteCpfDpt(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(CpfDptVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(CpfDptVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(CpfDptVO searchVO){
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
	private void validate(CpfDptVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
