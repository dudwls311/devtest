/**
 * @version 3.2.0.1
 */
package exts.com.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.icu.text.SimpleDateFormat;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.com.utl.fcc.service.SessionUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import exts.com.enums.EnumGrpCd;
import exts.com.enums.EnumMenuCd;
import exts.com.enums.EnumModeType;
import exts.com.exception.MenuAuthRequiredException;
import exts.com.exception.MenuAuthRequiredJsonException;
import exts.com.exception.SessionRequiredException;
import exts.com.exception.SessionRequiredJsonException;
import exts.com.exception.ValidationException.ERROR_TYPE;
import exts.com.service.ComCodeService;
import exts.com.service.ComMbrLogService;
import exts.com.service.ComMbrService;
import exts.com.service.ComService;
import exts.com.util.JsonUtil;
import exts.com.view.ExcelView;
import exts.com.vo.ComCodeVO;
import exts.com.vo.ComLoginMenuVO;
import exts.com.vo.ComMbrLogVO;
import exts.com.vo.ErrorVO;
import exts.com.vo.ExtsAbstractVO;
import jnit.cms.mbr.JnitcmsmbrVO;
import jnit.com.util.SpringHelperUtil;
import net.sf.json.JSONObject;

/**
 *  기본 Abstract 컨트롤러 클래스
 * @author 
 * @since 2020.07.21
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.07.21            최초 생성
 *
 * </pre>
 */

public abstract class ExtsAbstractController {
	
	protected final Log log = LogFactory.getLog(getClass());

	protected abstract String getPkg();
	
	@Resource(name="comService")
	protected ComService comService;
	
	@Resource(name = "comCodeService")
	private ComCodeService comCodeService;
	
	@Resource(name = "comMbrLogService")
	private ComMbrLogService comMbrLogService;
	
	public static final String REQUEST_IS_ADMIN = "isAdmin";//관리자여부 reqeust셋팅변수명
	
	public static final String REQUEST_IS_STRE_AUTH = "isStreAuth";//저장권한
	public static final String REQUEST_IS_REDNG_AUTH = "isRedngAuth";//읽기권한
	public static final String REQUEST_IS_DEL_AUTH = "isDelAuth";//삭제권한
	
	public static final String REQUEST_ACTION_URL = "actionUrl";//분기URL request셋팅변수명
	
	public static final String REQUEST_EXCEL_YN = "excelYn";//엑셀 처리용 변수값

	/**
	 * 로그인 정보 리턴.
	 * @return JnitcmsmbrVO
	 */
	protected JnitcmsmbrVO getLoginVO(){
		return comService.getLoginVO();
	}
	
	/**
	 * 로그인한 아이디가져오기
	 * @return 아이디
	 */
	protected String getLoginId(){
		return comService.getLoginID();
	}
	/**
	 * 로그인되었는지 체크
	 * @return true|false
	 */
	protected boolean isLoginned(){
		return comService.isLogin();
	}
	
	/**
	 * 관리자인지 체크
	 * @return
	 */
	protected boolean isAdmin(){
		return comService.isAdmin();
	}
	
	/**
	 * 직원레벨여부
	 * @return
	 */
	protected boolean isMbrLevelStaff(){
		return comService.isMbrLevelStaff();
	}
	
	/**
	 * 회원유형(최고관리자)
	 * @return
	 */
	protected boolean isMbrTypeSuper(){
		return comService.isMbrTypeSuper();
	}
	
	/**
	 * 재단직원여부
	 * @return
	 */
	protected boolean isFoundStaff(){
		return comService.isFoundStaff();
	}
	
	/**
	 * 센터직원여부
	 * @return
	 */
	protected boolean isCenterStaff(){
		return comService.isCenterStaff();
	}
	
	/**
	 * 북한이탈주민여부
	 * @return
	 */
	protected boolean isNtkrdf() {
		return comService.isNtkrdf();
	}
	
	/**
	 * 일반회원여부
	 * @return
	 */
	protected boolean isNormal() {
		return comService.isNormal();
	}

	/**
	 * 페이지 이동 View
	 * @param model
	 * @param msg
	 * @param href
	 * @return
	 */
	protected String getResponseHrefView(ModelMap model, String msg, String href){
		model.addAttribute("alert", msg);
		model.addAttribute("path", href);
		
		return "jnit/util/alertMove";
	}

	/**
	 * 이전페이지 이동 View
	 * @param model
	 * @param msg
	 * @param href
	 * @return
	 */
	protected String getResponseBackView(ModelMap model, String msg){
		model.addAttribute("alert", msg);
		
		return "jnit/util/alertBack";
	}

	
	/**
	 * json형태 View(성공,실패 응답)
	 * @param model
	 * @param isSuccess (true|false - 성공|실패)
	 * @param msg 메세지
	 * @return
	 */
	protected String getResponseJsonView(ModelMap model,boolean isSuccess,String msg){
		JSONObject json = new JSONObject();
		json.put("isSuccess", isSuccess);
		json.put("msg", msg);
		
		model.addAttribute("json",json.toString());
		
		return "exts/com/json";
	}

	/**
	 * json형태 View(성공,실패 응답)
	 * @param model
	 * @param isSuccess (true|false - 성공|실패)
	 * @param msg 메세지
	 * @param data 응답 object
	 * @return
	 */
	protected String getResponseJsonView(ModelMap model,boolean isSuccess,String msg, Object data){
		JSONObject json = new JSONObject();
		json.put("isSuccess", isSuccess);
		json.put("msg", msg);
		json.put("data", data);
		
		model.addAttribute("json",json.toString());
		
		return "exts/com/json";
	}

	/**
	 * json형태 View(성공,실패 응답)
	 * @param model
	 * @param isSuccess (true|false - 성공|실패)
	 * @param msg 메세지
	 * @param validation error가 발생한 속성
	 * @param validation error type
	 * @return
	 */
	protected String getResponseJsonView(ModelMap model,boolean isSuccess,String msg, String errorKey, ERROR_TYPE errorType){
		JSONObject json = new JSONObject();
		
		json.put("isSuccess", isSuccess);
		json.put("msg", msg);
		json.put("errorKey", errorKey);
		json.put("errorType", errorType);
		
		model.addAttribute("json",json.toString());
		
		return "exts/com/json";
	}

	protected String getResponseValidatorErrorJsonView(ModelMap model, BindingResult errors)throws JsonProcessingException{
		
		JSONObject json = new JSONObject();	
		json.put("isSuccess", false);
		
		List<ErrorVO> errorList = new ArrayList<ErrorVO>();
		for (final FieldError error : errors.getFieldErrors()) {
			ErrorVO m = new ErrorVO();
			m.setErrorField(error.getField());
			m.setErrorCode(error.getCode());
			m.setErrorMessage(error.getDefaultMessage());
			errorList.add(m);
		}
		//for reject value
		for (final ObjectError error:errors.getAllErrors()) {
		   boolean isExistError = false;
		   for(ErrorVO ae:errorList){
		      if(ae.getErrorCode().equals(error.getCode())){
		         isExistError = true;
		         break;
		      }
		   }
		   if(!isExistError){
		  	 ErrorVO m = new ErrorVO();
		      m.setErrorField(error.getCode());
		      m.setErrorCode(error.getCode());
		      m.setErrorMessage(error.getDefaultMessage());
		      errorList.add(m);
		   }
		}
		json.put("errorList", errorList);
		model.addAttribute("json",json.toString());
		
		return "exts/com/json";
	}
	/**
	 * json형태 View(return object 출력)
	 * @param model
	 * @return
	 */
	protected String getResponseJsonView(ModelMap model, Object returnObj)throws JsonProcessingException{
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		model.addAttribute("json",objectMapper.writeValueAsString(returnObj).toString());
		model.addAttribute("json", JsonUtil.convertObjectToJson(returnObj));
		return "exts/com/json";
	}

	/**
	 * excel View
	 * @param model
	 * @param excelTemplateName /WEB-INF/jsp/exts/exceltemplate/에 있는 엑셀 파일명.(xlsx 제외)
	 * @param excelStoreName 저장될 엑셀 명
	 * @return
	 */
	protected String getResponseExcelView(ModelMap model, String excelTemplateName, String excelStoreName){

		model.addAttribute("sdfParse", new SimpleDateFormat("yyyyMMdd"));	//String to Date
		model.addAttribute("sdf", new SimpleDateFormat("yyyy.MM.dd"));//날짜 변환용
		model.addAttribute("sdfAll", new SimpleDateFormat("yyyy.MM.dd E HH:mm:ss"));//날짜 변환용
		model.addAttribute("excelTemplateName", excelTemplateName);
		model.addAttribute("excelStoreName", excelStoreName);
		
		return "excelView";
	}
	protected String getResponseExcelView(ModelMap model, String excelTemplateName, String excelStoreName, boolean isXls){
		model.addAttribute("isXls", isXls);
		return getResponseExcelView(model, excelTemplateName, excelStoreName);
	}

	/**
	 * excel View(multiple sheet)
	 * @param model
	 * @param excelTemplateName /WEB-INF/jsp/exts/exceltemplate/에 있는 엑셀 파일명.(xlsx 제외)
	 * @param excelStoreName 저장될 엑셀 명
	 * @param sheetNames 시트명
	 * @param sheetMaps 각 sheet별 데이터가 담긴 maps
	 * @return
	 */
	protected String getResponseExcelView(ModelMap model, String excelTemplateName, String excelStoreName, List<String> sheetNames, List<HashMap<String, Object>> sheetMaps){

		model.addAttribute(ExcelView.ATTR_IS_MULTIPLE_SHEET, true);
		model.addAttribute(ExcelView.ATTR_NEW_SHEET_NAMES, sheetNames);
		model.addAttribute(ExcelView.ATTR_SHEET_MAPS, sheetMaps);
		
		return getResponseExcelView(model, excelTemplateName, excelStoreName);
	}

	/**
	 * 분기에서각 메소드 직접호출을 막기위한 셋팅.
	 * @param request
	 */
//	protected void setDirectPkg(HttpServletRequest request){
//		request.setAttribute(getPkg(), getPkg());
//	}
	/**
	 * 각 메소드 직접접근 에러.
	 */
	protected void throwDirect(HttpServletRequest request)throws EgovBizException{
		if(request.getAttribute(getPkg()) == null)throwBizException("com.error.nodirect");
	}

	/**
	 * 분기에서 공통으로 처리할 내용
	 * @param menuCd
	 * @param request
	 * @param mode
	 */
	@SuppressWarnings("unchecked")
	protected void setIndexProcess(EnumMenuCd menuCd, String mode)
			throws SessionRequiredException, SessionRequiredJsonException, MenuAuthRequiredJsonException,MenuAuthRequiredException{
		
		HttpServletRequest request = SpringHelperUtil.getRequest();
		//메뉴Id 셋팅
		request.setAttribute("curMenuCode", menuCd.toString());
		request.setAttribute("curMenuSn", menuCd.getMenuSn());
		//SSO사용여부
		//request.setAttribute("useSSO",Globals.SSO_USE);
		
		JnitcmsmbrVO loginVO = getLoginVO();
		request.setAttribute("loginVO", loginVO);
		
		//로그인 체크
		String loginId = getLoginId();
		request.setAttribute("loginId", loginId);
		if("".equals(loginId)){
			if(NullUtil.nullString(mode).indexOf("Json") >= 0){
				throw new SessionRequiredJsonException("");
			}else{
				SessionUtil.setAttribute("urlAfterLogin", request.getRequestURL().toString());
				throw new SessionRequiredException("");
			}
		}
		//메소드 직접접근 방지
		request.setAttribute(getPkg(), getPkg());

		boolean isAdmin = isAdmin();
		boolean isFoundStaff = isFoundStaff();
		boolean isCenterStaff = isCenterStaff();	
		
		if(EnumMenuCd.NONE != menuCd){

			//해당 메소드만 로그 남기기
			EnumModeType modeType = null;
			try{
				modeType = EnumModeType.valueOf(mode);
			}catch(NullPointerException e) {
				log.debug("NONE MODE TYPE : " + mode);
			}catch(Exception e){
				log.debug("NONE MODE TYPE : " + mode);
			}
			
			String curLmenuSn = "";
			String curLmenuNm = "";
			String curLmenuUrl = "";
			String curLuprMenuSn = "";
			String curMmenuSn = "";
			String curMmenuNm = "";
			String curMmenuUrl = "";
			String curMuprMenuSn = "";
			String curSmenuSn = "";
			String curSmenuNm = "";
			String curSmenuUrl = "";
			String curSuprMenuSn = "";
			String curTmenuSn = "";
			String curTmenuNm = "";
			String curTmenuUrl = "";
			String curTuprMenuSn = "";
			
			boolean isStreAuth = false;
			boolean isRedngAuth = false;
			boolean isDelAuth = false;
			boolean isPrntgAuth = false;
			
			List<ComLoginMenuVO> lmenuList = (List<ComLoginMenuVO>)SessionUtil.getAttribute(ComMbrService.SESSION_LMENU_LIST);
			List<ComLoginMenuVO> mmenuList = (List<ComLoginMenuVO>)SessionUtil.getAttribute(ComMbrService.SESSION_MMENU_LIST);
			List<ComLoginMenuVO> smenuList = (List<ComLoginMenuVO>)SessionUtil.getAttribute(ComMbrService.SESSION_SMENU_LIST);
			List<ComLoginMenuVO> tmenuList = (List<ComLoginMenuVO>)SessionUtil.getAttribute(ComMbrService.SESSION_TMENU_LIST);
			if(tmenuList != null) {
				for(ComLoginMenuVO tmenu:tmenuList){
					if(tmenu.getMenuSn().equals(menuCd.getMenuSn())){
						curTmenuSn = tmenu.getMenuSn();
						curTmenuNm = tmenu.getMenuNm();
						curTmenuUrl = tmenu.getMenuUrl();
						curTuprMenuSn = tmenu.getUprMenuSn();
						
						isStreAuth = tmenu.isStreAuth();
						isRedngAuth = tmenu.isRedngAuth();
						isDelAuth = tmenu.isDelAuth();
						isPrntgAuth = tmenu.isPrntgAuth();
						break;
					}
				}
			}
			if(smenuList != null) {
				for(ComLoginMenuVO smenu:smenuList){
					if(smenu.getMenuSn().equals(menuCd.getMenuSn()) || smenu.getMenuSn().equals(curTuprMenuSn)){
						curSmenuSn = smenu.getMenuSn();
						curSmenuNm = smenu.getMenuNm();
						curSmenuUrl = smenu.getMenuUrl();
						curSuprMenuSn = smenu.getUprMenuSn();

						if(smenu.getMenuSn().equals(menuCd.getMenuSn())){
							isStreAuth = smenu.isStreAuth();
							isRedngAuth = smenu.isRedngAuth();
							isDelAuth = smenu.isDelAuth();
							isPrntgAuth = smenu.isPrntgAuth();
						}
						break;
					}
				}
			}
			if(mmenuList != null) {
				for(ComLoginMenuVO mmenu:mmenuList){
					if(mmenu.getMenuSn().equals(menuCd.getMenuSn()) || mmenu.getMenuSn().equals(curSuprMenuSn)){
						curMmenuSn = mmenu.getMenuSn();
						curMmenuNm = mmenu.getMenuNm();
						curMmenuUrl = mmenu.getMenuUrl();
						curMuprMenuSn = mmenu.getUprMenuSn();
						if(mmenu.getMenuSn().equals(menuCd.getMenuSn())){
							isStreAuth = mmenu.isStreAuth();
							isRedngAuth = mmenu.isRedngAuth();
							isDelAuth = mmenu.isDelAuth();
							isPrntgAuth = mmenu.isPrntgAuth();
						}
						break;
					}
				}
			}
			if(lmenuList != null) {
				for(ComLoginMenuVO lmenu:lmenuList){
					if(lmenu.getMenuSn().equals(menuCd.getMenuSn()) || lmenu.getMenuSn().equals(curMuprMenuSn)){
						curLmenuSn = lmenu.getMenuSn();
						curLmenuNm = lmenu.getMenuNm();
						curLmenuUrl = lmenu.getMenuUrl();
						curLuprMenuSn = lmenu.getUprMenuSn();
						if(lmenu.getMenuSn().equals(menuCd.getMenuSn())){
							isStreAuth = lmenu.isStreAuth();
							isRedngAuth = lmenu.isRedngAuth();
							isDelAuth = lmenu.isDelAuth();
							isPrntgAuth = lmenu.isPrntgAuth();
						}
						break;
					}
				}
			}

			
			//TODO:샘플용 추 후 삭제
			if(menuCd == EnumMenuCd.SAMPLE) {
				isStreAuth = true;
				isRedngAuth = true;
				isDelAuth = true;
				isPrntgAuth = true;
			}
			//TODO:샘플용 추 후 삭제 끝
			
			
			if(menuCd == EnumMenuCd.MAIN || menuCd == EnumMenuCd.SAMPLE_SOURCE || isAdmin) {
				isStreAuth = true;
				isRedngAuth = true;
				isDelAuth = true;
				isPrntgAuth = true;
			}
			
			//권한에 체크에 따른 exception
			if(modeType != null){
				boolean isAuth = false;
				
				if(
					(
						EnumModeType.list == modeType || EnumModeType.view == modeType 	
					) 
					&& isRedngAuth)isAuth = true;
				if(
					(
						EnumModeType.write == modeType || EnumModeType.writeActionJson == modeType
					)
					&& isStreAuth)isAuth = true;
				if(
					(
						EnumModeType.deleteActionJson == modeType
					)
					&& isDelAuth)isAuth = true;
				if(
						(
							EnumModeType.print == modeType
						)
						&& isPrntgAuth)isAuth = true;
				
				if(!isAuth){
					if(NullUtil.nullString(mode).indexOf("Json") >= 0){
						throw new MenuAuthRequiredJsonException("No Auth");
					}else{
						throw new MenuAuthRequiredException("No Auth");
					}
				}
			}

			request.setAttribute("curLmenuSn",curLmenuSn);
			request.setAttribute("curLmenuNm",curLmenuNm);
			request.setAttribute("curLmenuUrl",curLmenuUrl);
			request.setAttribute("curLuprMenuSn",curLuprMenuSn);
			request.setAttribute("curMmenuSn",curMmenuSn);
			request.setAttribute("curMmenuNm",curMmenuNm);
			request.setAttribute("curMmenuUrl",curMmenuUrl);
			request.setAttribute("curMuprMenuSn",curMuprMenuSn);
			request.setAttribute("curSmenuSn",curSmenuSn);
			request.setAttribute("curSmenuNm",curSmenuNm);
			request.setAttribute("curSmenuUrl",curSmenuUrl);
			request.setAttribute("curSuprMenuSn",curSuprMenuSn);
			request.setAttribute("curTmenuSn",curTmenuSn);
			request.setAttribute("curTmenuNm",curTmenuNm);
			request.setAttribute("curTmenuUrl",curTmenuUrl);
			request.setAttribute("curTuprMenuSn",curTuprMenuSn);
			
			request.setAttribute(REQUEST_IS_STRE_AUTH,isStreAuth);
			request.setAttribute(REQUEST_IS_REDNG_AUTH,isRedngAuth);
			request.setAttribute(REQUEST_IS_DEL_AUTH,isDelAuth);
//					request.setAttribute(REQUEST_IS_PRNTG_AUTH,isPrntgAuth);
			
			//로그 남기기
			try{
				if(modeType != null){
					insertComMbrLog(request, menuCd, modeType.getName());
				}
			}catch(NullPointerException e){
				log.error(e.getMessage());
			}catch(Exception e){
				log.error(e.getMessage());
			}
			
		}

		//header에서 사용하므로 모든 페이지 처리.
		request.setAttribute("isAdmin",isAdmin);
		request.setAttribute("isFoundStaff",isFoundStaff);
		request.setAttribute("isCenterStaff",isCenterStaff);
	}
	
	/**
	 * 분기에서 공통으로 처리할 내용
	 * @param menuCd
	 * @param request
	 * @param mode
	 */
	protected void setIndexProcess(EnumMenuCd menuCd, String orMode, String mode )
			throws SessionRequiredException, SessionRequiredJsonException, MenuAuthRequiredJsonException,MenuAuthRequiredException{
		setIndexProcess(menuCd, mode);
	}
	/**
	 * setIndexProcess에서 처리된 권한중 읽기권한이 있는지 체크
	 * @return
	protected boolean isRedngAuth(HttpServletRequest request) {		
		return (Boolean)request.getAttribute(REQUEST_IS_REDNG_AUTH);
	}

	/**
	 * setIndexProcess에서 처리된 권한중 저장권한이 있는지 체크
	 * @return
	protected boolean isStreAuth(HttpServletRequest request) {		
		return (Boolean)request.getAttribute(REQUEST_IS_STRE_AUTH);
	}

	/**
	 * setIndexProcess에서 처리된 권한중 삭제권한이 있는지 체크
	 * @return
	protected boolean isDelAuth(HttpServletRequest request) {		
		return (Boolean)request.getAttribute(REQUEST_IS_DEL_AUTH);
	}

	/**
	 * setIndexProcess에서 처리된 권한중 출력권한이 있는지 체크
	 * @return
	protected boolean isPrntgAuth(HttpServletRequest request) {		
		return (Boolean)request.getAttribute(REQUEST_IS_PRNTG_AUTH);
	}
	 */
	/**
	 * 각 리스트용 기본 권한 셋팅
	 * @param searchVO
	 */
	protected void setListDefaultCondition(ExtsAbstractVO searchVO){
		if(isAdmin())return;
	}
	
	/**
	 * 비지니스 로직에러 발생(공통)
	 * @param msg
	 * @throws BizException
	 */
	protected void throwBizException(final String msgKey)throws EgovBizException{
		throwBizException(msgKey,null);
	}
	protected EgovBizException throwBizException(final String msgKey,String[] args)throws EgovBizException{
		throw comService.processException(msgKey,args);
	}

	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService; 
	
	//공통 페이지 크기
	protected int getDefaultPageUnit(){
		return propertiesService.getInt("pageUnit");
	}
	protected int getDefaultPageSize(){
		return propertiesService.getInt("pageSize");
	}
	//공통 팝업 페이지 크기
	protected int getDefaultPopupPageUnit(){
		return 6;
	}
	
	//최대년도 부터 최소년도까지 list형태로 리턴
	protected List<Integer> getYearList(){
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = getMaxYear(); i >= getMinYear(); i--){
			ret.add(i);
		}
		return ret;
	}
	//년도 출력시 최소 년도값
	protected int getMinYear(){
		return 2013;
	}
	//년도 출력시 최대 년도값
	protected int getMaxYear(){
		return getCurrentYear();
	}
	//현재년도
	protected int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	
	/**
	 * 연락처 앞자리 배열가져오기
	 * @return
	 */
	protected String[] getFrontOfTel(){
		return "02,031,032,033,041,042,043,044,051,052,053,054,055,061,062,063,064,010,011,016,107,018,019,0130,080,070,0303,0502,0503,0504,0505,0506,0507".split(",");
	}

	/**
	 * 핸드폰 앞자리 배열가져오기
	 * @return
	 */
	protected String[] getFrontOfPhone(){
		return "010,011,016,017,018,019".split(",");
	}
	
	/**
	 * 그룹코드로 공통코드 리스트 가져오기
	 * @param grpCd
	 * @return
	 */
	protected List<ComCodeVO> getCodeListByGrpCd(EnumGrpCd grpCd) throws Exception{
		return comCodeService.getCodeByGrpCd(grpCd);
	}
	
	/**
	 * 그룹코드 및 상위코드로 공통코드 리스트 가져오기
	 * @param grpCd
	 * @return
	 */
	protected List<ComCodeVO> getCodeListByGrpCd(EnumGrpCd grpCd, String uprCd) throws Exception{
		return comCodeService.getCodeByGrpCd(grpCd, uprCd);
	}
	/**
	 * 상위코드로 공통코드 리스트 가져오기
	 * @param grpCd
	 * @return
	 */
	protected List<ComCodeVO> getCodeListByUprCd(String uprCd) throws Exception{
		return comCodeService.getCodeByUprCd(uprCd);
	}
	

	/**
	 * 메뉴로그 남기기
	 * @param request
	 * @param act
	 */
	protected void insertComMbrLog(HttpServletRequest request, EnumMenuCd menuCode, String act){
		
		try {
			ComMbrLogVO logVO = new ComMbrLogVO();
			logVO.setMbrId(getLoginId());
			logVO.setLogCnts(act);
			logVO.setMenuSn(menuCode.getMenuSn());
			comMbrLogService.writeComMbrLog(logVO);
			request.setAttribute(ComService.REQUEST_LOG_CNT_NO, logVO.getMbrLogSn());
		}catch(NullPointerException e){
			log.error(e.getMessage());
		}catch(Exception e){
			log.error(e.getMessage());
		}
		
	}

	/**
	 * 기타로그 남기기
	 * @param request
	 * @param act
	 */
	protected void insertComMbrLogEtc(HttpServletRequest request, String act){
//		comMbrLogService.insertComMbrLogEtc(request, act);
	}


	/**
	 * 코드명으로 코드값 가져오기
	 * @param list
	 * @param cdNm
	 * @return
	 */
	protected String getCd(List<ComCodeVO> list, String cdNm) throws Exception{
		return comCodeService.getCd(list, cdNm);
	}
	
}
