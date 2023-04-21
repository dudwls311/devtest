package exts.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.ComCodeGrpService;
import exts.com.vo.ComCodeGrpVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : ComCodeGrpServiceImpl.java
 * @Description : 그룹코드 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comCodeGrpService")
public class ComCodeGrpServiceImpl extends ExtsAbstractServiceImpl implements ComCodeGrpService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "comCodeGrpDao")
	private ComCodeGrpDao comCodeGrpDao;
	
	/** ID Generation */
    @Resource(name="extsComCodeGrpIdGnrService")
    private EgovIdGnrService egovIdGnrService;
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeGrpVO> selectComCodeGrpList(ComCodeGrpVO searchVO) {
		List<ComCodeGrpVO> list = (List<ComCodeGrpVO>)comCodeGrpDao.selectComCodeGrpList(searchVO);
//		if(list != null){
//			for(ComCodeGrpVO result:list){
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
	public Integer selectComCodeGrpTot(ComCodeGrpVO searchVO) {
		return comCodeGrpDao.selectComCodeGrpTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComCodeGrpVO selectComCodeGrp(ComCodeGrpVO searchVO) {
		ComCodeGrpVO result = comCodeGrpDao.selectComCodeGrp(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @param detailList
	 * @throws FdlException 
	 * @throws Exception
	 */
	public void writeComCodeGrp(ComCodeGrpVO searchVO) throws EgovBizException, FdlException {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		
		String id = NullUtil.nullString(searchVO.getGroupCd());
		if("".equals(id)){
			id = egovIdGnrService.getNextStringId();
			searchVO.setGroupCd(id);
			comCodeGrpDao.insertComCodeGrp(searchVO);
		}else{
			ComCodeGrpVO result = selectComCodeGrp(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.getGroupCd();
			
			comCodeGrpDao.updateComCodeGrp(searchVO);
		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComCodeGrp(ComCodeGrpVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		ComCodeGrpVO result = selectComCodeGrp(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		comCodeGrpDao.deleteComCodeGrp(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComCodeGrpVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComCodeGrpVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ComCodeGrpVO searchVO){
		log.debug(searchVO);
		log.debug(isAdmin());
		log.debug(isDelAuth());
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
	private void validate(ComCodeGrpVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
