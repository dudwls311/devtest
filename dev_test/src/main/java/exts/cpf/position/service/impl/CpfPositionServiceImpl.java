package exts.cpf.position.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.impl.ExtsAbstractServiceImpl;
import exts.cpf.position.service.CpfPositionService;
import exts.cpf.position.vo.CpfPositionVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE

/**
 * @Class Name : CpfPositionServiceImpl.java
 * @Description : 직급관리 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2023.03.03
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("cpfPositionService")
public class CpfPositionServiceImpl extends ExtsAbstractServiceImpl implements CpfPositionService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "cpfPositionDao")
	private CpfPositionDao cpfPositionDao;
	
	/** ID Generation */
    //@Resource(name="extsCpfPositionCpfPositionIdGnrService")		//IDGEN USE
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
	public List<CpfPositionVO> selectCpfPositionList(CpfPositionVO searchVO) {
		List<CpfPositionVO> list = (List<CpfPositionVO>)cpfPositionDao.selectCpfPositionList(searchVO);
//		if(list != null){
//			for(CpfPositionVO result:list){
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
	public Integer selectCpfPositionTot(CpfPositionVO searchVO) {
		return cpfPositionDao.selectCpfPositionTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public CpfPositionVO selectCpfPosition(CpfPositionVO searchVO) {
		CpfPositionVO result = cpfPositionDao.selectCpfPosition(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void writeCpfPosition(CpfPositionVO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		String id = NullUtil.nullString(searchVO.getPositionId());
		
		if("".equals(id)){
			//id = egovIdGnrService.getNextStringId();	//IDGEN USE
			//searchVO.setPositionId(id);				//IDGEN USE
			if(!isStreAuth())throwBizException("com.error.noauth.write");
			
			cpfPositionDao.insertCpfPosition(searchVO);
			id = searchVO.getPositionId();				//SEQUENCE USE
		}else{
			CpfPositionVO result = selectCpfPosition(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.getPositionId();
			
			cpfPositionDao.updateCpfPosition(searchVO);
		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteCpfPosition(CpfPositionVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		CpfPositionVO result = selectCpfPosition(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		cpfPositionDao.deleteCpfPosition(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(CpfPositionVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(CpfPositionVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(CpfPositionVO searchVO){
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
	private void validate(CpfPositionVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
