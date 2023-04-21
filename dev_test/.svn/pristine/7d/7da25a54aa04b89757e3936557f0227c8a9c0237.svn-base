package exts.com.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import exts.com.vo.ComIndvlzMenuAuthChngVO;

/**
 * @Class Name : ComIndvlzMenuAuthChngDAO.java
 * @Description : 그룹메뉴권한변경이력 dao
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Repository("comIndvlzMenuAuthChngDao")
public class ComIndvlzMenuAuthChngDao extends ExtsAbstractDao {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	private final String namespace = "exts.mapper.com.ComIndvlzMenuAuthChng.";
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
		return selectList(namespace + "selectList", searchVO);
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectComIndvlzMenuAuthChngTot(ComIndvlzMenuAuthChngVO searchVO) {
		return (Integer) select(namespace + "selectTot", searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComIndvlzMenuAuthChngVO selectComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) {
		return (ComIndvlzMenuAuthChngVO) select(namespace + "select", searchVO);
	}

	/**
	 * 추가
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) {
		update(namespace + "insert", searchVO);
	}

	/**
	 * 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) {
		update(namespace + "update", searchVO);
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) {
		update(namespace + "delete", searchVO);
	}

	/**
	 * 일괄추가
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertAllComIndvlzMenuAuthChng(ComIndvlzMenuAuthChngVO searchVO) {
		update(namespace + "insertAll", searchVO);
	}
	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역 /////////////////////////////////////////////
	// /////////////////////private,protected 메소드 선언 영역 끝 //////////////////////////////////////////
}
