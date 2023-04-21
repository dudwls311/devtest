package exts.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.ComSmplService;
import exts.com.vo.ComSmplVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : ComSmplServiceImpl.java
 * @Description : 샘플 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comSmplService")
public class ComSmplServiceImpl extends ExtsAbstractServiceImpl implements ComSmplService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "comSmplDao")
	private ComSmplDao comSmplDao;
	
	/** ID Generation */
    @Resource(name="extsComSmplIdGnrService")
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
	public List<ComSmplVO> selectComSmplList(ComSmplVO searchVO) {
		List<ComSmplVO> list = (List<ComSmplVO>)comSmplDao.selectComSmplList(searchVO);
//		if(list != null){
//			for(ComSmplVO result:list){
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
	public Integer selectComSmplTot(ComSmplVO searchVO) {
		return comSmplDao.selectComSmplTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComSmplVO selectComSmpl(ComSmplVO searchVO) {
		ComSmplVO result = comSmplDao.selectComSmpl(searchVO);
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
	public void writeComSmpl(ComSmplVO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		
		String id = NullUtil.nullString(searchVO.getSmplId());
		if("".equals(id)){
			id = egovIdGnrService.getNextStringId();
			searchVO.setSmplId(id);
			comSmplDao.insertComSmpl(searchVO);
		}else{
			ComSmplVO result = selectComSmpl(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.getSmplId();
			
			comSmplDao.updateComSmpl(searchVO);
		}

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComSmpl(ComSmplVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		ComSmplVO result = selectComSmpl(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		comSmplDao.deleteComSmpl(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComSmplVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComSmplVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ComSmplVO searchVO){
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
	private void validate(ComSmplVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
