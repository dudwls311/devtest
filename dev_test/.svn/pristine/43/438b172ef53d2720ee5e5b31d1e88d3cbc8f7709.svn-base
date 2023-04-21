package exts.com.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.enums.EnumMenuCd;
import exts.com.exception.ValidatorException;
import exts.com.service.ComAuthGrpService;
import exts.com.service.ComIndvlzMenuAuthService;
import exts.com.service.ComMenuService;
import exts.com.validator.ComIndvlzMenuAuthValidator;
import exts.com.vo.ComAuthGrpVO;
import exts.com.vo.ComIndvlzMenuAuthVO;
import exts.com.vo.ComMenuVO;
import jnit.cms.mbr.JnitcmsmbrDefaultVO;
import jnit.cms.mbr.JnitcmsmbrService;
import jnit.cms.mbrtype.JnitcmsmbrtypeDefaultVO;
import jnit.cms.mbrtype.JnitcmsmbrtypeService;


/**
 * @Class Name : ComIndvlzMenuAuthController.java
 * @Description : 사용자별메뉴관리 Controller
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by KMK All right reserved.
 */
@Controller
public class ComIndvlzMenuAuthController extends ExtsAbstractController{

	protected String getPkg(){return "exts/com/indvlzMenuAuth";}
	
	@Resource(name = "comMenuService")
	private ComMenuService comMenuService;
	
	@Resource(name = "comIndvlzMenuAuthService")
	private ComIndvlzMenuAuthService comIndvlzMenuAuthService;
	
	@Resource(name = "comIndvlzMenuAuthValidator")
	private ComIndvlzMenuAuthValidator comIndvlzMenuAuthValidator;

	@Resource(name = "jnitcmsmbrService")
	private JnitcmsmbrService jnitcmsmbrService;

    @Resource(name = "jnitcmsmbrtypeService")
    private JnitcmsmbrtypeService jnitcmsmbrtypeService;	 
	
	
	@Resource(name = "comAuthGrpService")
	private ComAuthGrpService comAuthGrpService;
	
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/com/indvlzMenuAuth/index.do")
	public String indexLarge(
			@ModelAttribute	ComIndvlzMenuAuthVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCmumaMode())))searchVO.setCmumaMode("list");//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.COM_USER_MENU_AUTH, searchVO.getCmumaMode());//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/com/indvlzMenuAuth/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/com/indvlzMenuAuth/" + searchVO.getCmumaMode() + ".do");
		
		return sb.toString();
	}



	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/com/indvlzMenuAuth/list.do")
	public String list(
			@ModelAttribute("searchVO") ComIndvlzMenuAuthVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);

		//그룹리스트
		/*
		ComAuthGrpVO groupVO = new ComAuthGrpVO();
		groupVO.setRecordCountPerPage(0);
		groupVO.setSearchCondition("1");
		model.addAttribute("groupList",comAuthGrpService.selectComAuthGrpList(groupVO));
		*/
		//cms연계: type id 리스트로 그룹처리
		JnitcmsmbrtypeDefaultVO typeVO = new JnitcmsmbrtypeDefaultVO();
		typeVO.setFirstIndex(0);
		typeVO.setLastIndex(10000);
		typeVO.setRecordCountPerPage(1000);    	
    	model.addAttribute("groupList",jnitcmsmbrtypeService.selectJnitcmsmbrtypeList(typeVO));
		
		//사용자리스트
		JnitcmsmbrDefaultVO userVO = new JnitcmsmbrDefaultVO();
		//최고관리자가 아닌 경우 자기 부서만.
		if(!isAdmin()) {
			String orgId = getLoginVO().getOrgId();
			if("".equals(NullUtil.nullString(orgId)))orgId = "=======";
			userVO.setOrgIdYn("Y");
			userVO.setOrgId(orgId);
		}
		//직원만 검색 
		userVO.setTypeIdCondition("0");
		userVO.setSearchCondition("5");
		userVO.setSearchKeyword(request.getParameter("searchKeyword"));
		userVO.setPageYn("N");
		model.addAttribute("userList",jnitcmsmbrService.selectLeftJoinmbrList(userVO));
		
		
		ComMenuVO menuVO = new ComMenuVO();
		menuVO.setRecordCountPerPage(0);
		model.addAttribute("menuList",comMenuService.selectMenuList(menuVO));
		
		
		return "exts/com/indvlzMenuAuth/list";
	}

	/**
	 * 뷰 Json
	 */
	@RequestMapping(value="/exts/com/indvlzMenuAuth/viewJson.do")
	public String viewJson(
			ComIndvlzMenuAuthVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);

		if("".equals(NullUtil.nullString(searchVO.getMbrId()))){
			searchVO.setMbrId("===========");
		}
		searchVO.setRecordCountPerPage(0);
		return getResponseJsonView(model, comIndvlzMenuAuthService.selectIndvlzMenuAuthList(searchVO));
	}


	/**
	 * 삭제
	 */
	@RequestMapping(value="/exts/com/indvlzMenuAuth/deleteActionJson.do")
	public String deleteActionJson(
			ComIndvlzMenuAuthVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		boolean isSuccess = false;
		String msg = "";
		try{
			
			comIndvlzMenuAuthService.deleteIndvlzMenuAuth(searchVO);
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
	 * 추가 / 수정
	 */
	@RequestMapping(value="/exts/com/indvlzMenuAuth/write.do")
	public String write(
			ComIndvlzMenuAuthVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if(!"".equals(NullUtil.nullString(searchVO.getMbrId()))){
			ComIndvlzMenuAuthVO result = comIndvlzMenuAuthService.selectIndvlzMenuAuth(searchVO);
			model.addAttribute("result",result);
		}
		
		return "exts/com/indvlzMenuAuth/write";
	}
	

	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/com/indvlzMenuAuth/writeActionJson.do")
	public String writeActionJson(
			ComIndvlzMenuAuthVO searchVO,
			BindingResult errors,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);		
		

		boolean isSuccess = false;
		String msg = "";
		try{
			comIndvlzMenuAuthValidator.validate(searchVO, errors);
			if(errors.hasErrors())throw new ValidatorException("");
						

			List<ComIndvlzMenuAuthVO> authList = new ArrayList<ComIndvlzMenuAuthVO>();
			
			ComMenuVO menuVO = new ComMenuVO();
			menuVO.setRecordCountPerPage(0);
			List<ComMenuVO> menuList = comMenuService.selectMenuList(menuVO);
			if(menuList != null){
				for(ComMenuVO menu:menuList){
					String pre = menu.getMenuSn() + "_";
					String redngAuthYn = NullUtil.nullString(request.getParameter(pre+"redngAuthYn"));
					String streAuthYn = NullUtil.nullString(request.getParameter(pre+"streAuthYn"));
					String delAuthYn = NullUtil.nullString(request.getParameter(pre+"delAuthYn"));
					String prntgAuthYn = NullUtil.nullString(request.getParameter(pre+"prntgAuthYn"));
					if(!"".equals(redngAuthYn) || !"".equals(streAuthYn) || !"".equals(delAuthYn) || !"".equals(prntgAuthYn)){
						ComIndvlzMenuAuthVO authVO = new ComIndvlzMenuAuthVO();
						authVO.setMenuSn(menu.getMenuSn());
						authVO.setStreAuthYn(streAuthYn);
						authVO.setRedngAuthYn(redngAuthYn);
						authVO.setDelAuthYn(delAuthYn);
						authVO.setPrntgAuthYn(prntgAuthYn);
						authList.add(authVO);
					}
				}
			}
			searchVO.setAuthList(authList);
			
			comIndvlzMenuAuthService.writeIndvlzMenuAuth(searchVO);
			
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
