package exts.com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.FdlException;
//import egovframework.rte.fdl.idgnr.EgovIdGnrService;		//IDGEN USE
import exts.com.service.ComAtchFileService;
import exts.com.util.ComFileUploadUtil;
import exts.com.util.FileMngUtil;
import exts.com.vo.ComAtchFileVO;

/**
 * @Class Name : ComAtchFileServiceImpl.java
 * @Description : 첨부파일 ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2022.08.16
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comAtchFileService")
public class ComAtchFileServiceImpl extends ExtsAbstractServiceImpl implements ComAtchFileService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	@Resource(name = "comAtchFileDao")
	private ComAtchFileDao comAtchFileDao;
	
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComAtchFileVO> selectComAtchFileList(ComAtchFileVO searchVO) {
		searchVO.setRecordCountPerPage(0);
		List<ComAtchFileVO> list = (List<ComAtchFileVO>)comAtchFileDao.selectComAtchFileList(searchVO);
//		if(list != null){
//			for(ComAtchFileVO result:list){
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
	public Integer selectComAtchFileTot(ComAtchFileVO searchVO) {
		return comAtchFileDao.selectComAtchFileTot(searchVO);
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComAtchFileVO selectComAtchFile(ComAtchFileVO searchVO) {
		ComAtchFileVO result = comAtchFileDao.selectComAtchFile(searchVO);
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
	public String writeComAtchFile(ComAtchFileVO searchVO) throws Exception {
		String mbrId = getLoginID();
		searchVO.setRgtrId(mbrId);
		searchVO.setMdfrId(mbrId);

		validate(searchVO);
		//String id = NullUtil.nullString(searchVO.getAtchFileSn());  //IDGEN USE
		String id = NullUtil.nullString(searchVO.getAtchFileSn());	//SEQUENCE USE
		
//		if("".equals(id)){
			//id = egovIdGnrService.getNextStringId();	//IDGEN USE
			//searchVO.setAtchFileSn(id);				//IDGEN USE
			comAtchFileDao.insertComAtchFile(searchVO);
			id = searchVO.getAtchFileSn();				//SEQUENCE USE
//		}else{
//			ComAtchFileVO result = selectComAtchFile(searchVO);
//			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
//			id = result.getAtchFileSn();
//			
//			comAtchFileDao.updateComAtchFile(searchVO);
//		}
		return id;
	}
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @return
	 * @throws Exception
	 */
	public List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm) throws Exception {
		return writeComAtchUploadFile(request, fileAccessNm, null, null, null);
	}
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @return
	 * @throws Exception
	 */
	public List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm, String uploadSubPath1) throws Exception {
		return writeComAtchUploadFile(request, fileAccessNm, uploadSubPath1, null, null);
	}
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @return
	 * @throws Exception
	 */
	public List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm, String uploadSubPath1, String uploadSubPath) throws Exception {
		return writeComAtchUploadFile(request, fileAccessNm, uploadSubPath1, uploadSubPath, null);
	}
	
	/**
	 * 첨부파일 업로드 후 일련번호 List 반환
	 * @param request
	 * @param fileAccessNm
	 * @param uploadSubPath1
	 * @return
	 * @throws Exception
	 */
	public List<String> writeComAtchUploadFile(HttpServletRequest request, String fileAccessNm, String uploadSubPath1, String uploadSubPath2, String[] accessExt) throws Exception {
		List<String> atchFileSnList = new ArrayList<String>();
		for(EgovFormBasedFileVo fvo : ComFileUploadUtil.uploadFormFiles(request, fileAccessNm, uploadSubPath1, uploadSubPath2, accessExt)) {
			ComAtchFileVO atchFileVO = new ComAtchFileVO();
			atchFileVO.setOrgnlAtchFileNm(fvo.getFileName());
			atchFileVO.setAtchFileNm(fvo.getPhysicalName());
			atchFileVO.setAtchFileSz(fvo.getSize());
			atchFileVO.setAtchFileExtnNm(fvo.getExtnName());
			atchFileVO.setAtchFilePathNm(fvo.getServerSubPath());
			writeComAtchFile(atchFileVO);
			
			atchFileSnList.add(atchFileVO.getAtchFileSn());
		}
		return atchFileSnList;
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComAtchFile(ComAtchFileVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
		ComAtchFileVO result = selectComAtchFile(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRgtrId(userId);
		searchVO.setMdfrId(userId);
		comAtchFileDao.deleteComAtchFile(searchVO);
	}
	
	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComAtchFileList(ComAtchFileVO searchVO) throws EgovBizException {
		String userId = getLoginID();
		//권한 체크
//		ComAtchFileVO result = selectComAtchFile(searchVO);
//		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		if(searchVO != null && searchVO.getAtchFileSnList() != null && searchVO.getAtchFileSnList().size() > 0) {
			searchVO.setRgtrId(userId);
			searchVO.setMdfrId(userId);
			comAtchFileDao.deleteComAtchFileList(searchVO);
		}
	}
	
	/**
	 * 이미지 뷰
	 * @param request
	 * @param response
	 * @param atchFileSn
	 * @throws Exception
	 */
	public void imageView (HttpServletRequest request, HttpServletResponse response, String atchFileSn) throws Exception {
		if(!"".equals(NullUtil.nullString(atchFileSn))) {
			ComAtchFileVO atchFileSearchVO = new ComAtchFileVO();
			atchFileSearchVO.setAtchFileSn(atchFileSn);
			ComAtchFileVO atchFileVO = selectComAtchFile(atchFileSearchVO);
			if(atchFileVO != null) {
				FileMngUtil.showFile(request, response, atchFileVO.getAtchFilePathNm(), atchFileVO.getAtchFileNm(), Globals.UPLOAD_PATH);
			}
		}
	}
	
	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	// ///////////////////////////////////////////////////////////////////
	/**
	 * DB에 입력하기 위한 데이터 처리.
	 * @param searchVO
	 * @throws EgovBizException
	 */
	private void validate(ComAtchFileVO searchVO){
		
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
	// ///////////////////////////////////////////////////////////////////
}
