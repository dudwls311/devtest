package exts.cpf.dpt.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import exts.com.web.ExtsAbstractController;
import exts.com.enums.EnumMenuCd;
import exts.com.exception.ValidatorException;
import exts.cpf.dpt.service.CpfDptService;
import exts.cpf.dpt.validator.CpfDptValidator;
import exts.cpf.dpt.vo.CpfDptVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @Class Name : CpfDptController.java
 * @Description : 부서관리 Controller
 * @Modification Information
 * 
 * @author
 * @since 2023.02.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class CpfDptController extends ExtsAbstractController {
	
	protected String getPkg(){return "exts/cpf/dpt";}
	
	@Resource(name = "cpfDptService")
	private CpfDptService cpfDptService;

	@Resource(name = "cpfDptValidator")
	private CpfDptValidator cpfDptValidator;
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/cpf/dpt/index.do")
	public String index(
			@ModelAttribute	CpfDptVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCdMode())))searchVO.setCdMode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.SAMPLE, searchVO.getCdMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/cpf/dpt/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/cpf/dpt/" + searchVO.getCdMode() + ".do");
		
		return sb.toString();
	}


	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/cpf/dpt/list.do")
	public String list(
			@ModelAttribute("searchVO")	CpfDptVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if("Y".equals(request.getParameter(REQUEST_EXCEL_YN))){
			
			searchVO.setRecordCountPerPage(0);
			model.addAttribute("resultList",cpfDptService.selectCpfDptList(searchVO));
			return getResponseExcelView(model, "dpt", "부서관리");
		}else {

			//기본값으로 스프링빈에 설정된 값 로드
			if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
			if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
		
			//총갯수
			int totalRecordCount = cpfDptService.selectCpfDptTot(searchVO);

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
			
			model.addAttribute("paginationInfo",paginationInfo);
			model.addAttribute("resultCnt",totalRecordCount);
			model.addAttribute("resultList",cpfDptService.selectCpfDptList(searchVO));
		}
		
		
		return "exts/cpf/dpt/dptList";
	}

	/**
	 * 보기
	 */
	@RequestMapping(value="/exts/cpf/dpt/view.do")
	public String view(
			@ModelAttribute("searchVO")	CpfDptVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		CpfDptVO result = cpfDptService.selectCpfDpt(searchVO);
		//읽기 권한 체크
		if(!cpfDptService.isViewable(result))throwBizException("com.error.noauth.view");
		
		model.addAttribute("result",result);
		model.addAttribute("isModifiable",cpfDptService.isModifiable(result));
		return "exts/cpf/dpt/dptView";
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/cpf/dpt/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	CpfDptVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			cpfDptService.deleteCpfDpt(searchVO);
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
	@RequestMapping(value="/exts/cpf/dpt/write.do")
	public String write(
			@ModelAttribute("searchVO")	CpfDptVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if(!"".equals(NullUtil.nullString(searchVO.getDeptId()))) {
			CpfDptVO result = cpfDptService.selectCpfDpt(searchVO);
			//읽기 권한 체크
			if(!cpfDptService.isViewable(result))throwBizException("com.error.noauth.view");
			model.addAttribute("result",result);
			model.addAttribute("isModifiable",cpfDptService.isModifiable(result));
		}		
		
		return "exts/cpf/dpt/dptWrite";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/cpf/dpt/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	CpfDptVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			cpfDptValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			cpfDptService.writeCpfDpt(searchVO);
			
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
