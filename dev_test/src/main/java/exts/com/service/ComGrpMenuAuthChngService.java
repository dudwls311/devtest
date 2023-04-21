package exts.com.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.vo.ComGrpMenuAuthChngVO;

/**
 * @Class Name : ComGrpMenuAuthChngService.java
 * @Description : 그룹메뉴권한변경이력 Service
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComGrpMenuAuthChngService extends ExtsAbstractService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<ComGrpMenuAuthChngVO> selectComGrpMenuAuthChngList(ComGrpMenuAuthChngVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectComGrpMenuAuthChngTot(ComGrpMenuAuthChngVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	ComGrpMenuAuthChngVO selectComGrpMenuAuthChng(ComGrpMenuAuthChngVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeComGrpMenuAuthChng(ComGrpMenuAuthChngVO searchVO) throws EgovBizException;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteComGrpMenuAuthChng(ComGrpMenuAuthChngVO searchVO) throws EgovBizException;
}
