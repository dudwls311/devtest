package exts.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.enums.EnumGrpCd;
import exts.com.service.ComCacheService;
import exts.com.service.ComCodeService;
import exts.com.vo.ComCodeVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Class Name : ComCodeServiceImpl.java
 * @Description : 그룹 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comCodeService")
public class ComCodeServiceImpl extends ExtsAbstractServiceImpl implements ComCodeService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "comCodeDao")
	private ComCodeDao comCodeDao;
	
	@Resource(name = "comCacheService")
	private ComCacheService comCacheService;
	
	/** ID Generation */
    @Resource(name="extsComCodeIdGnrService")
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
	public List<ComCodeVO> selectComCodeList(ComCodeVO searchVO) {
		List<ComCodeVO> list = (List<ComCodeVO>)comCodeDao.selectComCodeList(searchVO);
//		if(list != null){
//			for(ComCodeVO result:list){
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
	public Integer selectComCodeTot(ComCodeVO searchVO) {
		return comCodeDao.selectComCodeTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComCodeVO selectComCode(ComCodeVO searchVO) {
		ComCodeVO result = comCodeDao.selectComCode(searchVO);
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
	public void writeComCode(ComCodeVO searchVO) throws EgovBizException, FdlException {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		
		String id = NullUtil.nullString(searchVO.getIndivCd());
		if("".equals(id)){
			id = egovIdGnrService.getNextStringId();
			searchVO.setIndivCd(id);
			comCodeDao.insertComCode(searchVO);
		}else{
			ComCodeVO result = selectComCode(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			id = result.getIndivCd();
			
			comCodeDao.updateComCode(searchVO);
		}

		//캐시 리로드
		comCacheService.reloadCodeList();
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComCode(ComCodeVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		ComCodeVO result = selectComCode(searchVO);
		if(!isModifiable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		comCodeDao.deleteComCode(searchVO);
		//캐시 리로드
		comCacheService.reloadCodeList();
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComCodeVO searchVO){
		return super.isViewable(searchVO);
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComCodeVO searchVO){
		return super.isModifiable(searchVO);
	}


	/**
	 * 전체코드 리스트 가져오기
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeVO> getAllCode(){ 
		return comCacheService.getAllCodeList();		
	}


	/**
	 * 그룹코드로 개별코드 리스트 가져오기
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeVO> getCodeByGrpCd(EnumGrpCd grpCd){
//		return comCodeDao.selectComCodeListSimpleByGroupCd(grpCd.getCode());
		//캐시에서 가져오도록 처리
		List<ComCodeVO> resultList = new ArrayList<ComCodeVO>();
		List<ComCodeVO> dataList = comCacheService.getAllCodeList();
		if(dataList != null) {
			for(ComCodeVO data:dataList) {
				if(grpCd.getCode().equals(data.getGroupCd()))resultList.add(data);
			}
		}
		return resultList;
		
	}


	/**
	 * 그룹코드및 상위코드로 개별코드 리스트 가져오기
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeVO> getCodeByGrpCd(EnumGrpCd grpCd, String uprCd){
//		return comCodeDao.selectComCodeListSimpleByGroupCd(grpCd.getCode());
		//캐시에서 가져오도록 처리
		List<ComCodeVO> resultList = new ArrayList<ComCodeVO>();
		List<ComCodeVO> dataList = comCacheService.getAllCodeList();
		if(dataList != null) {
			for(ComCodeVO data:dataList) {
				if(grpCd.getCode().equals(data.getGroupCd()) && uprCd.equals(data.getUpIndivCd()))resultList.add(data);
			}
		}
		return resultList;
		
	}

	/**
	 * 상위코드로 개별코드 리스트 가져오기
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeVO> getCodeByUprCd(String uprCd){
//		return comCodeDao.selectComCodeListSimpleByUprCd(uprCd);
		//캐시에서 가져오도록 처리
		List<ComCodeVO> resultList = new ArrayList<ComCodeVO>();
		List<ComCodeVO> dataList = comCacheService.getAllCodeList();
		if(dataList != null) {
			for(ComCodeVO data:dataList) {
				if(uprCd.equals(data.getUpIndivCd()))resultList.add(data);
			}
		}
		return resultList;
	}

	/**
	 * 해당 코드가 그룹에 해당하는지 체크
	 * 
	 * @param grpCd
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public boolean isCdInGrp(EnumGrpCd grpCd, String cd) {
		return !"".equals(getCdNmInGrp(grpCd, cd));
	}

	/**
	 * 코드값으로 이름 가져오기
	 * 
	 * @param grpCd
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public String getCdNmInGrp(EnumGrpCd grpCd, String cd) {
		List<ComCodeVO> dataList = getCodeByGrpCd(grpCd);
		if(dataList != null) {
			for(ComCodeVO data:dataList) {
				if(data.getIndivCd().equals(cd))return data.getIndivCdNm();
			}
		}
		return "";
	}

	/**
	 * 이름으로 코드값 가져오기
	 * 
	 * @param grpCd
	 * @param jccNm
	 * @return
	 * @throws Exception
	 */
	public String getCdInGrp(EnumGrpCd grpCd, String jccNm) {
		List<ComCodeVO> dataList = getCodeByGrpCd(grpCd);
		if(dataList != null) {
			for(ComCodeVO data:dataList) {
				if(data.getIndivCdNm().equals(jccNm))return data.getIndivCd();
			}
		}
		return "";
	}


	/**
	 * 코드명으로 코드값 가져오기
	 * @param list
	 * @param cdNm
	 * @return
	 */
	public String getCd(List<ComCodeVO> list, String cdNm) {
		for(ComCodeVO data:list) {
			if(data.getIndivCdNm().equals(cdNm))return data.getIndivCd();
		}
		return "";
	}

	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	// ///////////////////////////////////////////////////////////////////
	/**
	 * DB에 입력하기 위한 데이터 처리.
	 * @param searchVO
	 * @throws EgovBizException
	 */
	private void validate(ComCodeVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
