package exts.cpf.filemapping.web;

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
import exts.cpf.filemapping.service.CpfFileMappingService;
import exts.cpf.filemapping.validator.CpfFileMappingValidator;
import exts.cpf.filemapping.vo.CpfFileMappingVO;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @Class Name : CpfFileMappingController.java
 * @Description : 첨부파일매핑 Controller
 * @Modification Information
 * 
 * @author
 * @since 2023.02.24
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class CpfFileMappingController extends ExtsAbstractController {
	
	protected String getPkg(){return "exts/cpf/filemapping";}
	
 	@Resource(name = "cpfFileMappingService")
	private CpfFileMappingService cpfFileMappingService;

	@Resource(name = "cpfFileMappingValidator")
	private CpfFileMappingValidator cpfFileMappingValidator;
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/cpf/filemapping/index.do")
	public String index(
			@ModelAttribute	CpfFileMappingVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCfmMode())))searchVO.setCfmMode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.SAMPLE, searchVO.getCfmMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/cpf/filemapping/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/cpf/filemapping/" + searchVO.getCfmMode() + ".do");
		
		return sb.toString();
	}






	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/cpf/filemapping/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	CpfFileMappingVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			cpfFileMappingService.deleteCpfFileMapping(searchVO);
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
	@RequestMapping(value="/exts/cpf/filemapping/write.do")
	public String write(
			@ModelAttribute("searchVO")	CpfFileMappingVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if(!"".equals(NullUtil.nullString(searchVO.getId()))) {
			CpfFileMappingVO result = cpfFileMappingService.selectCpfFileMapping(searchVO);
			
			
			model.addAttribute("result",result);
			
		}		
		
		return "exts/cpf/filemapping/fileMappingWrite";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/cpf/filemapping/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	CpfFileMappingVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			cpfFileMappingValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			cpfFileMappingService.writeCpfFileMapping(searchVO);
			
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
