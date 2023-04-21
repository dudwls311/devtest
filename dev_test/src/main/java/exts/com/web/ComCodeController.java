package exts.com.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import exts.com.enums.EnumMenuCd;
import exts.com.exception.ValidatorException;
import exts.com.service.ComCodeGrpService;
import exts.com.service.ComCodeService;
import exts.com.validator.ComCodeValidator;
import exts.com.vo.ComCodeGrpVO;
import exts.com.vo.ComCodeVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @Class Name : ComCodeController.java
 * @Description : 개별코드 Controller
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class ComCodeController extends ExtsAbstractController{
	
	protected String getPkg(){return "exts/com/code";}
	
	@Resource(name = "comCodeGrpService")
	private ComCodeGrpService comCodeGrpService;
	
	@Resource(name = "comCodeService")
	private ComCodeService comCodeService;

	@Resource(name = "comCodeValidator")
	private ComCodeValidator comCodeValidator;
	
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/com/code/index.do")
	public String index(
			@ModelAttribute	ComCodeVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCcMode())))searchVO.setCcMode("manage");		//기본 manage로 포워딩		
		setIndexProcess(EnumMenuCd.COM_CODE, searchVO.getCcMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/com/code/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/com/code/" + searchVO.getCcMode() + ".do");
		log.debug(sb);
		return sb.toString();
	}



	/**
	 * 관리화면
	 */
	@RequestMapping(value="/exts/com/code/manage.do")
	public String manage(
			@ModelAttribute	ComCodeVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		//그룹코드 가져오기
		ComCodeGrpVO grpVO = new ComCodeGrpVO();
		grpVO.setGroupCdChgYn("Y");
		grpVO.setRecordCountPerPage(0);
		model.addAttribute("resultList",comCodeGrpService.selectComCodeGrpList(grpVO));

		return "exts/com/code/manage";
	}
	

	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/com/code/list.do")
	public String list(
			ComCodeGrpVO grpVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		ComCodeGrpVO grpResult = comCodeGrpService.selectComCodeGrp(grpVO);
		if(grpResult == null || grpResult.getGroupCd() == null)throwBizException("none");
		model.addAttribute("grpResult", grpResult);
		
		//상위그룹코드가 있는 그룹코드의 상위그룹코드 리스트 가져오기
		if(!grpResult.getGroupCd().equals(grpResult.getUpGroupCd())) {
			ComCodeVO uprVO = new ComCodeVO();
			uprVO.setGroupCd(grpResult.getUpGroupCd());
			model.addAttribute("uprCodeList", comCodeService.selectComCodeList(uprVO));
		}

		ComCodeVO searchVO = new ComCodeVO();
		searchVO.setGroupCd(grpVO.getGroupCd());
		
		//기본값으로 스프링빈에 설정된 값 로드
		if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
		if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
	
		//총갯수
		int totalRecordCount = comCodeService.selectComCodeTot(searchVO);

    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
    	paginationInfo.setTotalRecordCount(totalRecordCount);
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//전체가져올때
//		if(searchVO.getPageUnit() == -1)searchVO.setRecordCountPerPage(0);
		searchVO.setRecordCountPerPage(0);
		
		model.addAttribute("paginationInfo",paginationInfo);
		model.addAttribute("resultCnt",totalRecordCount);
		model.addAttribute("resultList",comCodeService.selectComCodeList(searchVO));
		
		
		return "exts/com/code/list";
	}

	/**
	 * 보기
	 */
	@RequestMapping(value="/exts/com/code/viewJson.do")
	public String viewJson(
			@ModelAttribute("searchVO")	ComCodeVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		ComCodeVO result = comCodeService.selectComCode(searchVO);
		//읽기 권한 체크
		if(!comCodeService.isViewable(result))throwBizException("com.error.noauth.view");
		
		model.addAttribute("result",result);
		return getResponseJsonView(model, result);
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/com/code/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	ComCodeVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			comCodeService.deleteComCode(searchVO);
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
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/com/code/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	ComCodeVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			comCodeValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			comCodeService.writeComCode(searchVO);
			isSuccess = true;
		}catch(ValidatorException e){
			return getResponseValidatorErrorJsonView(model, errors);
		}catch(EgovBizException e){
			msg = e.getMessage();
		}catch(Exception e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}
		
		return getResponseJsonView(model, isSuccess, msg);
	}
	
	
}
