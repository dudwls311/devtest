package exts.com.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.utl.sim.service.EgovFileScrty;
import exts.com.vo.ComMbrVO;

/**
 * @Class Name : ComMbrDao.java
 * @Description : 회원 dao(cms연계)
 * @Modification Information
 * 
 * @author
 * @since 2022. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Repository("comMbrDao")
public class ComMbrDao extends ExtsAbstractDao {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	private final String namespace = "exts.mapper.com.ComMbr.";
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComMbrVO> selectComMbrList(ComMbrVO searchVO) {
		return selectList(namespace + "selectList", searchVO);
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectComMbrTot(ComMbrVO searchVO) {
		return (Integer) select(namespace + "selectTot", searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComMbrVO selectComMbr(ComMbrVO searchVO) {
		return (ComMbrVO) select(namespace + "select", searchVO);
	}

	/**
	 * 추가
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertComMbr(ComMbrVO searchVO) {
		update(namespace + "insert", searchVO);
	}

	/**
	 * 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateComMbr(ComMbrVO searchVO) {
		update(namespace + "update", searchVO);
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComMbr(ComMbrVO searchVO) {
		update(namespace + "delete", searchVO);
	}

	/**
	 * 비밀번호 틀린 횟수 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateComMbrWrongPwCnt(ComMbrVO searchVO) {
		update(namespace + "updateWrongPwCnt", searchVO);
	}
	
	/**
	 * 비밀번호 변경일 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateComMbrPwModified(ComMbrVO searchVO) {
		update(namespace + "updatePwModified", searchVO);
	}

	/**
	 * 비밀번호 암호화
	 * 
	 * @param String
	 * @return
	 * @throws Exception
	 */
	public String selectComMbrEncodePw(String pw) throws Exception {
//		return (String) select(namespace + "selectEncodePw", pw);
		//cms 연계
		return EgovFileScrty.encryptPassword(pw);
	}
	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역 /////////////////////////////////////////////
	// /////////////////////private,protected 메소드 선언 영역 끝 //////////////////////////////////////////
}
