package exts.cpf.position.web;

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
import exts.cpf.position.service.CpfPositionService;
import exts.cpf.position.validator.CpfPositionValidator;
import exts.cpf.position.vo.CpfPositionVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @Class Name : CpfPositionController.java
 * @Description : 직급관리 Controller
 * @Modification Information
 * 
 * @author
 * @since 2023.03.03
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class CpfPositionController extends ExtsAbstractController {
	
	protected String getPkg(){return "exts/cpf/position";}
	
	@Resource(name = "cpfPositionService")
	private CpfPositionService cpfPositionService;

	@Resource(name = "cpfPositionValidator")
	private CpfPositionValidator cpfPositionValidator;
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/cpf/position/index.do")
	public String index(
			@ModelAttribute	CpfPositionVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCpMode())))searchVO.setCpMode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.SAMPLE, searchVO.getCbeMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/cpf/position/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/cpf/position/" + searchVO.getCpMode() + ".do");
		
		return sb.toString();
	}


	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/cpf/position/list.do")
	public String list(
			@ModelAttribute("searchVO")	CpfPositionVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if("Y".equals(request.getParameter(REQUEST_EXCEL_YN))){
			
			searchVO.setRecordCountPerPage(0);
			model.addAttribute("resultList",cpfPositionService.selectCpfPositionList(searchVO));
			return getResponseExcelView(model, "position", "직급관리");
		}else {

			//기본값으로 스프링빈에 설정된 값 로드
			if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
			if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
		
			//총갯수
			int totalRecordCount = cpfPositionService.selectCpfPositionTot(searchVO);

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
			model.addAttribute("resultList",cpfPositionService.selectCpfPositionList(searchVO));
		}
		
		
		return "exts/cpf/position/positionList";
	}

	/**
	 * 보기
	 */
	@RequestMapping(value="/exts/cpf/position/view.do")
	public String view(
			@ModelAttribute("searchVO")	CpfPositionVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		CpfPositionVO result = cpfPositionService.selectCpfPosition(searchVO);
		//읽기 권한 체크
		if(!cpfPositionService.isViewable(result))throwBizException("com.error.noauth.view");
		
		model.addAttribute("result",result);
		model.addAttribute("isModifiable",cpfPositionService.isModifiable(result));
		return "exts/cpf/position/positionView";
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/cpf/position/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	CpfPositionVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			cpfPositionService.deleteCpfPosition(searchVO);
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
	@RequestMapping(value="/exts/cpf/position/write.do")
	public String write(
			@ModelAttribute("searchVO")	CpfPositionVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if(!"".equals(NullUtil.nullString(searchVO.getPositionId()))) {
			CpfPositionVO result = cpfPositionService.selectCpfPosition(searchVO);
			//읽기 권한 체크
			if(!cpfPositionService.isViewable(result))throwBizException("com.error.noauth.view");
			model.addAttribute("result",result);
			model.addAttribute("isModifiable",cpfPositionService.isModifiable(result));
		}		
		
		return "exts/cpf/position/positionWrite";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/cpf/position/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	CpfPositionVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			cpfPositionValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			cpfPositionService.writeCpfPosition(searchVO);
			
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
