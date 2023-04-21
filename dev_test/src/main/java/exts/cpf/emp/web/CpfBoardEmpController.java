package exts.cpf.emp.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import exts.com.enums.EnumMenuCd;
import exts.com.exception.ValidatorException;
import exts.com.service.ComAtchFileService;
import exts.com.util.FileMngUtil;
import exts.com.vo.ComAtchFileVO;
import exts.com.web.ExtsAbstractController;
import exts.cpf.dpt.service.CpfDptService;
import exts.cpf.dpt.vo.CpfDptVO;
import exts.cpf.emp.service.CpfBoardEmpService;
import exts.cpf.emp.validator.CpfBoardEmpValidator;
import exts.cpf.emp.vo.CpfBoardEmpVO;
import exts.cpf.filemapping.service.CpfFileMappingService;
import exts.cpf.filemapping.vo.CpfFileMappingVO;
import exts.cpf.position.service.CpfPositionService;
import exts.cpf.position.vo.CpfPositionVO;


/**
 * @Class Name : CpfBoardEmpController.java
 * @Description : 사내공고 게시판 Controller
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class CpfBoardEmpController extends ExtsAbstractController {
	
	protected String getPkg(){return "exts/cpf/emp";}
	
	@Resource(name = "cpfBoardEmpService")
	private CpfBoardEmpService cpfBoardEmpService;
	
	@Resource(name = "cpfDptService")
	private CpfDptService cpfDptService;

	@Resource(name = "cpfBoardEmpValidator")
	private CpfBoardEmpValidator cpfBoardEmpValidator;
	
	@Resource(name = "comAtchFileService")
	private ComAtchFileService comAtchFileService;
	
	@Resource(name = "cpfFileMappingService")
	private CpfFileMappingService cpfFileMappingService;
	
	@Resource(name = "cpfPositionService")
	private CpfPositionService cpfPositionService;
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/cpf/emp/index.do")
	public String index(
			@ModelAttribute	CpfBoardEmpVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCbeMode())))searchVO.setCbeMode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.SAMPLE, searchVO.getCbeMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/cpf/emp/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/cpf/emp/" + searchVO.getCbeMode() + ".do");
		
		return sb.toString();
	}


	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/cpf/emp/list.do")
	public String list(
			@ModelAttribute("searchVO")	CpfBoardEmpVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if("Y".equals(request.getParameter(REQUEST_EXCEL_YN))){
			
			searchVO.setRecordCountPerPage(0);
			//총갯수
			PaginationInfo paginationInfo = new PaginationInfo();
			int totalRecordCount = cpfBoardEmpService.selectCpfBoardEmpTot(searchVO);
			paginationInfo.setTotalRecordCount(totalRecordCount);
			model.addAttribute("resultCnt",totalRecordCount);
			model.addAttribute("resultList",cpfBoardEmpService.selectCpfBoardEmpList(searchVO));
			return getResponseExcelView(model, "boardErp", "사내공고 게시판");
		}else {

			//기본값으로 스프링빈에 설정된 값 로드
			if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
			if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
		
			//총갯수
			int totalRecordCount = cpfBoardEmpService.selectCpfBoardEmpTot(searchVO);

	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());
	    	paginationInfo.setTotalRecordCount(totalRecordCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			//전체가져올때
			if(searchVO.getPageUnit() == -1)searchVO.setRecordCountPerPage(0);
			
			/* 직급, 부서 가져오기 */
			CpfDptVO cpfDptVO = new CpfDptVO();
			CpfPositionVO cpfPositionVO = new CpfPositionVO();
			List<CpfDptVO> dpt = cpfDptService.selectCpfDptList(cpfDptVO);
			List<CpfPositionVO> pos = cpfPositionService.selectCpfPositionList(cpfPositionVO);
			
			model.addAttribute("paginationInfo",paginationInfo);
			model.addAttribute("resultCnt",totalRecordCount);
			model.addAttribute("resultList",cpfBoardEmpService.selectCpfBoardEmpList(searchVO));
			model.addAttribute("filelist",cpfBoardEmpService.mappingList(searchVO));
			model.addAttribute("dpt",dpt);
			model.addAttribute("pos",pos);
		}
		
		
		return "exts/cpf/emp/boardEmpList";
	}

	/**
	 * 보기
	 */
	@RequestMapping(value="/exts/cpf/emp/view.do")
	public String view(
			@ModelAttribute("searchVO")	CpfBoardEmpVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
//		int a = Integer.valueOf(NullUtil.nullInt(request.getParameter("fileId")));
		CpfBoardEmpVO result = cpfBoardEmpService.selectCpfBoardEmp(searchVO);
		//읽기 권한 체크
//		if(!cpfBoardEmpService.isViewable(result))throwBizException("com.error.noauth.view");
		
		model.addAttribute("result",result);
		model.addAttribute("isModifiable",cpfBoardEmpService.isModifiable(result));
		model.addAttribute("fresult",cpfBoardEmpService.selectFileIdList(searchVO))  ;
		return "exts/cpf/emp/boardEmpView";
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/cpf/emp/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	CpfBoardEmpVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			cpfBoardEmpService.deleteCpfBoardEmp(searchVO);
			isSuccess = true;
		}catch(EgovBizException e){
			msg = e.getMessage();
		}catch(Exception e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}
		
		return getResponseJsonView(model, isSuccess, msg);
	}


	/**
	 * 추가/수정
	 */
	@RequestMapping(value="/exts/cpf/emp/write.do")
	public String write(
			@ModelAttribute("searchVO")	CpfBoardEmpVO searchVO,  
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		CpfDptVO cpfDptVO = new CpfDptVO();
		int firstIndex = NullUtil.nullInt(request.getParameter("firstIndex"));
//		int recordCountPerPage = NullUtil.nullInt(request.getParameter("recordCountPerPage"));
		cpfDptVO.setRecordCountPerPage(0);
		cpfDptVO.setFirstIndex(firstIndex);
		CpfPositionVO cpfPositionVO = new CpfPositionVO(); 
		List<CpfDptVO> dpt = cpfDptService.selectCpfDptList(cpfDptVO);
		List<CpfPositionVO> pos = cpfPositionService.selectCpfPositionList(cpfPositionVO);
        log.debug("dpt" + dpt );
        
       
		
		if(!"".equals(NullUtil.nullString(searchVO.getId()))) {
			CpfBoardEmpVO result = cpfBoardEmpService.selectCpfBoardEmp(searchVO);
			//읽기 권한 체크
			if(!cpfBoardEmpService.isViewable(result))throwBizException("com.error.noauth.view");
			model.addAttribute("result",result);
			model.addAttribute("isModifiable",cpfBoardEmpService.isModifiable(result));
			model.addAttribute("fresult",cpfBoardEmpService.selectFileIdList(searchVO))  ;
		}		
		model.addAttribute("dpt",dpt);
		model.addAttribute("pos",pos);
		
		return "exts/cpf/emp/boardEmpWrite";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/cpf/emp/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	CpfBoardEmpVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
//			MultipartFile[] uploadfiles,
//			MultipartHttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);

		boolean isSuccess = false;
		String msg = "";
		try{
			
			cpfBoardEmpValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			String fileNm = "abc";
			String uri = "testfile";
			String uri2 = "uploadfile";
//			ArrayList<String> ext = new ArrayList<>();  
			
			cpfBoardEmpService.writeCpfBoardEmp(searchVO);
			 //파일첨부처리 ````````````````
	        List<String> lfileId = comAtchFileService.writeComAtchUploadFile(request,fileNm,uri,uri2);
	        if(lfileId != null && lfileId.size() > 0) {
	        	for(String s : lfileId) {
		        	log.debug("==== fileId  : " + s);
//		         searchVO.setFileId(s);
		        	String id = searchVO.getId();
		         
		         	CpfFileMappingVO fileMappingVO = new CpfFileMappingVO();
			        fileMappingVO.setId(id);
			        fileMappingVO.setFileId(s);
			        
			        //mapping table insert
			        cpfFileMappingService.writeCpfFileMapping(fileMappingVO);
		        }
	        }
			
			isSuccess = true;
		}catch(ValidatorException e){
			e.printStackTrace();
			return getResponseValidatorErrorJsonView(model, errors);
		}catch(EgovBizException e){
			e.printStackTrace();
			msg = e.getMessage();
		}catch(Exception e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}
		
		return getResponseJsonView(model, isSuccess, msg);
	}
	
	/**
	 * 첨부파일 다운로드
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/exts/cpf/emp/fileDownload.do")
	public void fileDownload(@ModelAttribute("searchVO") CpfBoardEmpVO searchVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		throwDirect(request);
		
		//권한 체크 후에 첨부파일 다운로드 로직 도입
		CpfBoardEmpVO result = cpfBoardEmpService.selectCpfBoardEmp(searchVO);
		if(!cpfBoardEmpService.isViewable(result))throwBizException("com.error.noauth.view");
		
		//파라미터로 넘긴 id, fileId로 매핑 테이블 조회(searchVO에 담겨서 전달됨)
		CpfFileMappingVO fileMappingVO = cpfFileMappingService.selectCpfFileMapping(searchVO);
		
		//조회된 매핑테이블 데이터가 있으면
		if(fileMappingVO != null) {
			ComAtchFileVO fileSearchVO = new ComAtchFileVO();
			
			//매핑테이블의 fileId로 파일 테이블 조회
			fileSearchVO.setAtchFileSn(fileMappingVO.getFileId());
			ComAtchFileVO fileVO = comAtchFileService.selectComAtchFile(fileSearchVO);
			
			//조회된 파일테이블의 값으로 첨부파일 다운로드
			FileMngUtil.downFile(request, response, fileVO.getAtchFilePathNm(), fileVO.getAtchFileNm(), fileVO.getOrgnlAtchFileNm(), Globals.UPLOAD_PATH);
		}
		
	}
	
	@RequestMapping(value="/exts/cpf/emp/deleteJson.do")
	public String deleteJson(
			@ModelAttribute("searchVO")	CpfBoardEmpVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		log.debug("딜리트");
		boolean isSuccess = false;
		String msg = "";
		try{
			cpfBoardEmpService.updateDelYn(searchVO);
			isSuccess = true;
		}catch(EgovBizException e){
			msg = e.getMessage();
		}catch(Exception e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}
		
		return getResponseJsonView(model, isSuccess, msg);
	}

}
