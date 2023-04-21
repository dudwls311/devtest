package [PACKAGE].web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import exts.com.web.ExtsAbstractController;
import exts.com.exception.ValidatorException;
import [PACKAGE].service.[File_Name]Service;
import [PACKAGE].validator.[File_Name]Validator;
import [PACKAGE].vo.[File_Name]VO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @Class Name : [File_Name]Controller.java
 * @Description : [Description] Controller
 * @Modification Information
 * 
 * @author
 * @since [TODAY]
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class [File_Name]Controller extends ExtsAbstractController {
	
	protected String getPkg(){return "[file_path]";}
	
	@Resource(name = "[file_Name]Service")
	private [File_Name]Service [file_Name]Service;

	@Resource(name = "[file_Name]Validator")
	private [File_Name]Validator [file_Name]Validator;
	/**
	 * 분기문
	 */
	@RequestMapping(value="/[file_path]/index.do")
	public String index(
			@ModelAttribute	[File_Name]VO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.get[Mode_name]Mode())))searchVO.set[Mode_name]Mode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.[ENUM], searchVO.get[Mode_name]Mode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/[file_path]/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/[file_path]/" + searchVO.get[Mode_name]Mode() + ".do");
		
		return sb.toString();
	}


	/**
	 * 리스트
	 */
	@RequestMapping(value="/[file_path]/list.do")
	public String list(
			@ModelAttribute("searchVO")	[File_Name]VO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if("Y".equals(request.getParameter(REQUEST_EXCEL_YN))){
			
			searchVO.setRecordCountPerPage(0);
			model.addAttribute("resultList",[file_Name]Service.select[File_Name]List(searchVO));
			return getResponseExcelView(model, "[file_Sub_Name]", "[Description]");
		}else {

			//기본값으로 스프링빈에 설정된 값 로드
			if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
			if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
		
			//총갯수
			int totalRecordCount = [file_Name]Service.select[File_Name]Tot(searchVO);

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
			model.addAttribute("resultList",[file_Name]Service.select[File_Name]List(searchVO));
		}
		
		
		return "[file_path]/[sample_source]List";
	}

	/**
	 * 보기
	 */
	@RequestMapping(value="/[file_path]/view.do")
	public String view(
			@ModelAttribute("searchVO")	[File_Name]VO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		[File_Name]VO result = [file_Name]Service.select[File_Name](searchVO);
		//읽기 권한 체크
		if(![file_Name]Service.isViewable(result))throwBizException("com.error.noauth.view");
		
		model.addAttribute("result",result);
		model.addAttribute("isModifiable",[file_Name]Service.isModifiable(result));
		return "[file_path]/[sample_source]View";
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/[file_path]/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	[File_Name]VO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			[file_Name]Service.delete[File_Name](searchVO);
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
	@RequestMapping(value="/[file_path]/write.do")
	public String write(
			@ModelAttribute("searchVO")	[File_Name]VO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if(!"".equals(NullUtil.nullString(searchVO.get[FirstVo]()))) {
			[File_Name]VO result = [file_Name]Service.select[File_Name](searchVO);
			//읽기 권한 체크
			if(![file_Name]Service.isViewable(result))throwBizException("com.error.noauth.view");
			model.addAttribute("result",result);
			model.addAttribute("isModifiable",[file_Name]Service.isModifiable(result));
		}		
		
		return "[file_path]/[sample_source]Write";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/[file_path]/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	[File_Name]VO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			[file_Name]Validator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			[file_Name]Service.write[File_Name](searchVO);
			
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
