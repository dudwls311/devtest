package exts.com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.vo.ComAtchFileVO;

/**
 * @Class Name : ComAtchFileService.java
 * @Description : 첨부파일 Service
 * @Modification Information
 * 
 * @author
 * @since 2022.08.16
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComAtchFileService extends ExtsAbstractService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<ComAtchFileVO> selectComAtchFileList(ComAtchFileVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectComAtchFileTot(ComAtchFileVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	ComAtchFileVO selectComAtchFile(ComAtchFileVO searchVO);
	
	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	String writeComAtchFile(ComAtchFileVO searchVO) throws Exception;

	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @return
	 * @throws Exception
	 */
	List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm) throws Exception;
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @param uploadSubPath1
	 * @return
	 * @throws Exception
	 */
	List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm, String uploadSubPath1) throws Exception;
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @return
	 * @throws Exception
	 */
	List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm, String uploadSubPath1, String uploadSubPath) throws Exception;
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @param uploadSubPath1
	 * @param uploadSubPath2
	 * @return
	 * @throws Exception
	 */
	List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm, String uploadSubPath1, String uploadSubPath2, String[] accessExt) throws Exception;
	
	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteComAtchFile(ComAtchFileVO searchVO) throws EgovBizException;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteComAtchFileList(ComAtchFileVO searchVO) throws EgovBizException;
	
	/**
	 * 이미지 뷰
	 * @param request
	 * @param response
	 * @param atchFileSn
	 * @throws Exception
	 */
	void imageView (HttpServletRequest request, HttpServletResponse response, String atchFileSn) throws Exception;
}
