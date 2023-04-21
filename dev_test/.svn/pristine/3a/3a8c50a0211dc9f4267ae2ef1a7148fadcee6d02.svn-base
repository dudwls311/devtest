package exts.com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.service.ComIndvlzMenuAuthChngService;
import exts.com.vo.ComIndvlzMenuAuthChngVO;

/**
 * @Class Name : ComIndvlzMenuAuthChngServiceImpl.java
 * @Description : 그룹메뉴권한변경이력 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comIndvlzMenuAuthChngService")
public class ComIndvlzMenuAuthChngServiceImpl extends ExtsAbstractServiceImpl implements ComIndvlzMenuAuthChngService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "comIndvlzMenuAuthChngDao")
	private ComIndvlzMenuAuthChngDao comIndvlzMenuAuthChngDao;
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComIndvlzMenuAuthChngVO> selectComIndvlzMenuAuthChngList(ComIndvlzMenuAuthChngVO searchVO) {
		List<ComIndvlzMenuAuthChngVO> list = (List<ComIndvlzMenuAuthChngVO>)comIndvlzMenuAuthChngDao.selectComIndvlzMenuAuthChngList(searchVO);
//		if(list != null){
//			for(ComIndvlzMenuAuthChngVO result:list){
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
	public Integer selectComIndvlzMenuAuthChngTot(ComIndvlzMenuAuthChngVO searchVO) {
		return comIndvlzMenuAuthChngDao.selectComIndvlzMenuAuthChngTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComIndvlzMenuAuthChngVO selectComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) {
		ComIndvlzMenuAuthChngVO result = comIndvlzMenuAuthChngDao.selectComIndvlzMenuAuthChng(searchVO);
		return result;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @param detailList
	 * @throws Exception
	 */
	public void writeComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) throws EgovBizException {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
					
		comIndvlzMenuAuthChngDao.insertComIndvlzMenuAuthChng(searchVO);

	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		ComIndvlzMenuAuthChngVO result = selectComIndvlzMenuAuthChng(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		comIndvlzMenuAuthChngDao.deleteComIndvlzMenuAuthChng(searchVO);
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComIndvlzMenuAuthChngVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComIndvlzMenuAuthChngVO searchVO){
		return super.isModifiable(searchVO);
	}

	/**
	 * 해당 데이터를 삭제할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isDeletable(ComIndvlzMenuAuthChngVO searchVO){
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
	private void validate(ComIndvlzMenuAuthChngVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
