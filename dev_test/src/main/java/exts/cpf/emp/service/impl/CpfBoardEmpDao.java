package exts.cpf.emp.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import exts.com.service.impl.ExtsAbstractDao;

import exts.cpf.emp.vo.CpfBoardEmpVO;
import exts.cpf.filemapping.vo.CpfFileMappingVO;

/**
 * @Class Name : CpfBoardEmpDAO.java
 * @Description : 사내공고 게시판 dao
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Repository("cpfBoardEmpDao")
public class CpfBoardEmpDao extends ExtsAbstractDao {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	private final String namespace = "exts.mapper.cpf.emp.CpfBoardEmp.";
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<CpfBoardEmpVO> selectCpfBoardEmpList(CpfBoardEmpVO searchVO) {
		return selectList(namespace + "selectList", searchVO);
	}
	/**
	 * 매핑을 위한 리스트 
	 * 
	 * @param searchVO
	 * @return Exception
	 */
	public List<CpfBoardEmpVO> mappingList(CpfBoardEmpVO searchVO) {
		return selectList(namespace + "selectMappingList", searchVO);
	}
	/**
	 * 파일 데이터 
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<CpfBoardEmpVO> selectFileIdList(CpfBoardEmpVO searchVO){
		return selectList(namespace + "fileIdList", searchVO);
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public int selectCpfBoardEmpTot(CpfBoardEmpVO searchVO) {
		return (Integer) select(namespace + "selectTot", searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public CpfBoardEmpVO selectCpfBoardEmp(CpfBoardEmpVO searchVO) {
		return (CpfBoardEmpVO) select(namespace + "select", searchVO);
	}

	/**
	 * 추가
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void insertCpfBoardEmp(CpfBoardEmpVO searchVO) {
		update(namespace + "insert", searchVO);
	}

	/**
	 * 수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void updateCpfBoardEmp(CpfBoardEmpVO searchVO) {
		update(namespace + "update", searchVO);
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteCpfBoardEmp(CpfBoardEmpVO searchVO) {
		update(namespace + "delete", searchVO);
	}
	
	/**
	 * @param searchVO
	 * @return max Id 
	 */
	public String selectNextId(CpfBoardEmpVO searchVO) {
		return (String) select(namespace + "selectId", searchVO);
	}
	
	/**
	 * 첨부파일 삭제
	 * @param searchVO
	 */
	public void updateDelYn(CpfBoardEmpVO searchVO) {
		update(namespace + "updateDelYn", searchVO);
	}
	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역 /////////////////////////////////////////////
	// /////////////////////private,protected 메소드 선언 영역 끝 //////////////////////////////////////////
	
}