package exts.com.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import exts.com.enums.EnumGrpCd;
import exts.com.enums.EnumMbrTypeCd;
import exts.com.enums.EnumMenuCd;
import exts.com.exception.ValidatorException;
import exts.com.service.ComMbrService;
import exts.com.validator.ComLoginProcessValidator;
import exts.com.validator.ComMbrPwChangeValidator;
import exts.com.validator.ComMbrValidator;
import exts.com.vo.ComMbrVO;
import jnit.cms.mbrtype.JnitcmsmbrtypeService;
import jnit.cms.org.JnitcmsorgDefaultVO;
import jnit.cms.org.JnitcmsorgService;


/**
 * @Class Name : ComMbrController.java
 * @Description : 관리회원 Controller
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class ComMbrController extends ExtsAbstractController{
	
	protected String getPkg(){return "exts/com/mbr";}
	
	@Resource(name = "comMbrService")
	private ComMbrService comMbrService;

	@Resource(name = "comLoginProcessValidator")
	private ComLoginProcessValidator comLoginProcessValidator;

	@Resource(name = "comMbrValidator")
	private ComMbrValidator comMbrValidator;
    
    @Resource(name = "jnitcmsmbrtypeService")
    private JnitcmsmbrtypeService jnitcmsmbrtypeService;
    
	@Resource(name = "jnitcmsorgService")
	private JnitcmsorgService jnitcmsorgService;

	@Resource(name = "comMbrPwChangeValidator")
	private ComMbrPwChangeValidator comMbrPwChangeValidator;


	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 로그인폼
	 */
	@RequestMapping(value="/exts/com/mbr/login.do")
	public String login(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
//		if(isLoginned())return "redirect:" + Globals.MAIN_PAGE;
		
		return "exts/com/mbr/login";
	}

	/**
	 * 로그인 처리 - CmsController사용
	 */
	@RequestMapping(value="/exts/com/mbr/loginAuthJson.do")
	public String loginAuthJson(
			ComMbrVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		boolean isSuccess = false;
		String msg = "";
		try{
			comLoginProcessValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
			msg = comMbrService.login(searchVO, new String[]{EnumMbrTypeCd.SUP.getCode(),EnumMbrTypeCd.FOU.getCode(),EnumMbrTypeCd.CEN.getCode()});
			if(NullUtil.nullString(msg).indexOf("wrong") >= 0) {
				String pwMiscnt = msg.replaceAll("wrong:", "");
				throwBizException("com.error.login.wrongpw", new String[] {String.valueOf(pwMiscnt),Globals.LOGIN_PW_MISS_MAX_COUNT});
			}
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

	/**
	 * 로그인 처리 - CmsController사용
	 */
	@RequestMapping(value="/exts/com/mbr/loginActionJson.do")
	public String loginActionJson(
			ComMbrVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		boolean isSuccess = false;
		String msg = "";
		try{
			comLoginProcessValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
			msg = comMbrService.login(searchVO, new String[]{EnumMbrTypeCd.SUP.getCode(),EnumMbrTypeCd.FOU.getCode(),EnumMbrTypeCd.CEN.getCode()});
			if(NullUtil.nullString(msg).indexOf("wrong") >= 0) {
				String pwMiscnt = msg.replaceAll("wrong:", "");
				throwBizException("com.error.login.wrongpw", new String[] {String.valueOf(pwMiscnt),Globals.LOGIN_PW_MISS_MAX_COUNT});
			}
			isSuccess = true;
		}catch(ValidatorException e){
			return getResponseValidatorErrorJsonView(model, errors);
		}catch(EgovBizException e){
			/*
			if("pass3month".equals(e.getMessage())) {//3개월이상 비밀번호 변경필요
				SessionUtil.setAttribute(SESSION_LOGINFORM_ID, searchVO.getMbrId());
			}else if("gpki_on".equals(e.getMessage())) {//인증서로그인 필요.
				SessionUtil.setAttribute(SESSION_LOGINFORM_ID, searchVO.getMbrId());
			}
			*/
			msg = e.getMessage();
		}catch(Exception e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}
		
		return getResponseJsonView(model, isSuccess, msg);
	}
	
	/**
	 * 로그아웃 처리 - CmsController사용
	@RequestMapping(value="/exts/com/mbr/logout.do")
	public String logout(
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//cms기본 로그아웃 처리시작.
		
		//cms기본 로그아웃 처리 종료.
		
		
		//cms기본 로그아웃처리 외에 exts에서 사용될 추가처리.
		comMbrService.logout();			
		
		return "exts/com/mbr/logout";
	}
	 */

	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/com/mbr/index.do")
	public String index(
			@ModelAttribute	ComMbrVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCommMode())))searchVO.setCommMode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.CFG_MBR, searchVO.getCommMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/com/mbr/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/com/mbr/" + searchVO.getCommMode() + ".do");
		
		return sb.toString();
	}


	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/com/mbr/list.do")
	public String list(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		//패스워드 틀린횟수 맥스값셋팅
		searchVO.setEx01(Globals.LOGIN_PW_MISS_MAX_COUNT);
		
		//기관리스트
		model.addAttribute("orgList",getOrgList());		
		
		//최고관리자가 아닌 경우 자기 부서만.
		if(!isAdmin()) {
			String orgId = getLoginVO().getOrgId();
			if("".equals(NullUtil.nullString(orgId)))orgId = "=======";
			searchVO.setOrgId(orgId);
		}
		
		
		if("Y".equals(request.getParameter(REQUEST_EXCEL_YN))){
			
			searchVO.setRecordCountPerPage(0);
			model.addAttribute("resultList",comMbrService.selectComMbrList(searchVO));
			return getResponseExcelView(model, "mbr", "회원");
		}else {

			//기본값으로 스프링빈에 설정된 값 로드
			if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
			if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
		
			//총갯수
			int totalRecordCount = comMbrService.selectComMbrTot(searchVO);

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
			model.addAttribute("resultList",comMbrService.selectComMbrList(searchVO));
		}
		
		
		return "exts/com/mbr/mbrList";
	}

	/**
	 * 보기
	 */
	@RequestMapping(value="/exts/com/mbr/view.do")
	public String view(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		ComMbrVO result = comMbrService.selectComMbr(searchVO);
		//읽기 권한 체크
		if(!comMbrService.isViewable(result))throwBizException("com.error.noauth.view");

		//기관리스트
		model.addAttribute("orgList",getOrgList());				
		
		model.addAttribute("result",result);
		model.addAttribute("isModifiable",comMbrService.isModifiable(result));

		model.addAttribute("genderCdList",getCodeListByGrpCd(EnumGrpCd.GENDER_CD));
		
		return "exts/com/mbr/mbrView";
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/com/mbr/deleteActionJson.do")
	public String deleteActionJson(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			comMbrService.deleteComMbr(searchVO);
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
	@RequestMapping(value="/exts/com/mbr/write.do")
	public String write(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if(!"".equals(NullUtil.nullString(searchVO.getMbrId()))) {
			ComMbrVO result = comMbrService.selectComMbr(searchVO);
			//읽기 권한 체크
			if(!comMbrService.isViewable(result))throwBizException("com.error.noauth.view");
			model.addAttribute("result",result);
			model.addAttribute("isModifiable",comMbrService.isModifiable(result));
		}		
		
		//기관리스트
		model.addAttribute("orgList",getOrgList());				

		model.addAttribute("genderCdList",getCodeListByGrpCd(EnumGrpCd.GENDER_CD));
		
		return "exts/com/mbr/mbrWrite";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/com/mbr/writeActionJson.do")
	public String writeActionJson(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			comMbrValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						
			comMbrService.writeComMbr(searchVO);
			
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
	


	/**
	 * 로그인 차단 해제 처리
	 */
	@RequestMapping(value="/exts/com/mbr/unlockActionJson.do")
	public String unlockActionJson(
			@ModelAttribute("searchVO")	ComMbrVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		

		boolean isSuccess = false;
		String msg = "";
		try{
			comMbrService.initWrongPwCnt(searchVO);			
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
	 * 비밀번호 변경 폼
	 */
	@RequestMapping(value="/exts/com/mbr/changePassword.do")
	public String changePassword(
			HttpServletRequest request,
			ModelMap model) throws Exception{
		
		return "exts/com/mbr/mbrChangePassword";
	}


	/**
	 * 마이페이지 - 비밀번호 변경처리
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/exts/com/mbr/changePasswordActionJson.do")
	public String mypagePasswordActionJson(
			ComMbrVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		boolean isSuccess = false;
		String msg = "";
		try{
			searchVO.setMbrId(getLoginId());
			
			comMbrPwChangeValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");

			comMbrService.changePw(searchVO, request.getParameter("orPasswd"));
			
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
	@SuppressWarnings("rawtypes")
	private List getOrgList() throws Exception {
		JnitcmsorgDefaultVO orgVO = new JnitcmsorgDefaultVO();
		orgVO.setFirstIndex(0);
		orgVO.setLastIndex(500);
		orgVO.setRecordCountPerPage(500);
		return jnitcmsorgService.selectJnitcmsorgList(orgVO);
	}
}
