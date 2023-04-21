package exts.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.service.ComMbrLogService;
import exts.com.vo.ComMbrLogVO;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE

/**
 * @Class Name : ComMbrLogServiceImpl.java
 * @Description : 회원로그내역 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2022.11.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comMbrLogService")
public class ComMbrLogServiceImpl extends ExtsAbstractServiceImpl implements ComMbrLogService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "comMbrLogDao")
	private ComMbrLogDao comMbrLogDao;
    
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComMbrLogVO> selectComMbrLogList(ComMbrLogVO searchVO) {
		List<ComMbrLogVO> list = (List<ComMbrLogVO>)comMbrLogDao.selectComMbrLogList(searchVO);
//		if(list != null){
//			for(ComMbrLogVO result:list){
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
	public Integer selectComMbrLogTot(ComMbrLogVO searchVO) {
		return comMbrLogDao.selectComMbrLogTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComMbrLogVO selectComMbrLog(ComMbrLogVO searchVO) {
		ComMbrLogVO result = comMbrLogDao.selectComMbrLog(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void writeComMbrLog(ComMbrLogVO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		String id = NullUtil.nullString(searchVO.getMbrLogSn());
		
//		if("".equals(id)){
			//id = egovIdGnrService.getNextStringId();	//IDGEN USE
			//searchVO.setMbrLogSn(id);				//IDGEN USE
//			if(!isStreAuth())throwBizException("com.error.noauth.write");
			
			comMbrLogDao.insertComMbrLog(searchVO);
			id = searchVO.getMbrLogSn();				//SEQUENCE USE
//		}else{
//			ComMbrLogVO result = selectComMbrLog(searchVO);
//			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
//			id = result.getMbrLogSn();
//			
//			comMbrLogDao.updateComMbrLog(searchVO);
//		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComMbrLog(ComMbrLogVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		ComMbrLogVO result = selectComMbrLog(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		comMbrLogDao.deleteComMbrLog(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComMbrLogVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComMbrLogVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ComMbrLogVO searchVO){
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
	private void validate(ComMbrLogVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
