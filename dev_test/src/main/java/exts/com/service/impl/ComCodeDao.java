package exts.com.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import exts.com.vo.ComCodeVO;

/**
 * @Class Name : ComCodeDAO.java
 * @Description : 개별코드 dao
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Repository("comCodeDao")
public class ComCodeDao extends ExtsAbstractDao {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	private final String namespace = "exts.mapper.com.ComCode.";
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
		return selectList(namespace + "selectList", searchVO);
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectComCodeTot(ComCodeVO searchVO) {
		return (Integer) select(namespace + "selectTot", searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComCodeVO selectComCode(ComCodeVO searchVO) {
		return (ComCodeVO) select(namespace + "select", searchVO);
	}

	/**
	 * 추가
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertComCode(ComCodeVO searchVO) {
		update(namespace + "insert", searchVO);
	}

	/**
	 * 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateComCode(ComCodeVO searchVO) {
		update(namespace + "update", searchVO);
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComCode(ComCodeVO searchVO) {
		update(namespace + "delete", searchVO);
	}
	
	/**
	 * 리스트(코드값, 코드명) 그룹코드로 가져오기
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeVO> selectComCodeListSimpleByGrpCd(String code) {
		return selectList(namespace + "selectListSimpleByGrpCd", code);
	}
	
	/**
	 * 리스트(코드값, 코드명) 상위코드로 가져오기
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<ComCodeVO> selectComCodeListSimpleByUprCd(String code) {
		return selectList(namespace + "selectListSimpleByUprCd", code);
	}
	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역 /////////////////////////////////////////////
	// /////////////////////private,protected 메소드 선언 영역 끝 //////////////////////////////////////////
}
