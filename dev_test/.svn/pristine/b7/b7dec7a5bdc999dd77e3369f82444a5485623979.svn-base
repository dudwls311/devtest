package exts.com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.com.utl.fcc.service.SessionUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.service.ComGrpMenuAuthService;
import exts.com.service.ComIndvlzMenuAuthService;
import exts.com.service.ComMbrService;
import exts.com.service.ComMenuService;
import exts.com.util.PatternUtil;
import exts.com.vo.ComGrpMenuAuthVO;
import exts.com.vo.ComIndvlzMenuAuthVO;
import exts.com.vo.ComLoginMenuVO;
import exts.com.vo.ComMbrVO;
import exts.com.vo.ComMenuVO;
import jnit.cms.AdminUtil;
import jnit.cms.LoginManager;
import jnit.cms.config.CmsConfigController;
import jnit.cms.config.ConfigUtil;
import jnit.cms.mbr.JnitcmsmbrService;
import jnit.cms.mbr.JnitcmsmbrVO;
import jnit.cms.mbr.MbrUtil;
import jnit.cms.mbrsess.JnitcmsmbrsessService;
import jnit.cms.mbrtype.JnitcmsmbrtypeService;
import jnit.cms.mbrtype.JnitcmsmbrtypeVO;
import jnit.com.util.SpringHelperUtil;
import jnit.login.log.loginlogUtil;

/**
 * @Class Name : ComMbrServiceImpl.java
 * @Description : 관리 회원 ServiceImpl(추가관리자페이지용)
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("comMbrService")
public class ComMbrServiceImpl extends ExtsAbstractServiceImpl implements ComMbrService {
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////

	private static final String SMS_AUTH = "smsAuth";
	
	@Resource(name = "comMenuService")
	private ComMenuService comMenuService;
	@Resource(name = "comGrpMenuAuthService")
	private ComGrpMenuAuthService comGrpMenuAuthService;
	@Resource(name = "comIndvlzMenuAuthService")
	private ComIndvlzMenuAuthService comIndvlzMenuAuthService;
	
	@Resource(name = "comMbrDao")
	protected ComMbrDao comMbrDao;
	
    
	/*
	@Resource(name = "comMbrRegChngReqstDao")
	private ComMbrRegChngReqstDao comMbrRegChngReqstDao;
	@Resource(name = "comMbrInfoChngDao")
	private ComMbrInfoChngDao comMbrInfoChngDao;
	*/

	//cms연계
    @Resource(name = "jnitcmsmbrService")
    protected JnitcmsmbrService jnitcmsmbrService;

    @Resource(name = "jnitcmsmbrtypeService")
    protected JnitcmsmbrtypeService jnitcmsmbrtypeService;

    @Resource(name = "jnitcmsmbrsessService")
    protected JnitcmsmbrsessService jnitcmsmbrsessService;
    
    @Resource(name = "comMbrService")
	private ComMbrService comMbrService;
    
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////

	/**
	 * 로그인처리 - 해당 회원타입만
	 * 
	 * @param searchVO
	 * @param types
	 * @return 세션에 저장되어 있는 메뉴중 최초 대메뉴
	 * @throws Exception
	 */
	public String login(ComMbrVO searchVO, String[] types) throws Exception{

		String ret = "";
		boolean isOnepassLogin = (searchVO.getMbrId() != null && searchVO.getSn() != null)?true:false;//원패스로 로그인 시키기 위해 넘어왔을 때 체크할 내용 제거를 위한 값.
		
		//cms 로그인처리 이관
		HttpServletRequest request = SpringHelperUtil.getRequest();
		JnitcmsmbrVO resVO = null;
		if(!isOnepassLogin) {
	    	resVO = jnitcmsmbrService.exeLogin(request, searchVO);

	    	// 1. 일반 로그인 처리
	    	if(resVO == null ||  resVO.getMbrId() == null || "".equals(resVO.getMbrId())) {
	    		//로그인 막기
	        	JnitcmsmbrVO mbroutVO = new JnitcmsmbrVO();
	        	searchVO.setSelectCondition("1");
	    		if("0".equals(ConfigUtil.getDefaultProp("sso")) && !"".equals(searchVO.getSiteId())){
	    			searchVO.setSiteIdYn("Y");
	    			searchVO.setSiteId(searchVO.getSiteId());
	    		}
	    		mbroutVO = jnitcmsmbrService.selectJnitcmsmbr(searchVO);
	        	
	        	if(mbroutVO != null && mbroutVO.getMbrId() != null){
	        		int pwMiscnt = mbroutVO.getPwMiscnt();
	        		if(pwMiscnt > NullUtil.nullInt(Globals.LOGIN_PW_MISS_MAX_COUNT)){
	        			throwBizException("com.error.login.exeedwrongpw", new String[] {Globals.LOGIN_PW_MISS_MAX_COUNT});
	        		}else{
	        			mbroutVO.setPwMiscnt(pwMiscnt+1);
	        			mbroutVO.setUpdateCondition("1");
	        			jnitcmsmbrService.updateJnitcmsmbr(mbroutVO);
	        			//exception발생시 롤백되므로 controller에서 exception 처리
	//        			throwBizException("com.error.login.wrongpw", new String[] {String.valueOf(pwMiscnt),Globals.LOGIN_PW_MISS_MAX_COUNT});
	        			return "wrong:"+pwMiscnt;
	        			
	        		}
	        	}
	    		throwBizException("com.error.login.nodata");
	    	}
		}else {
			//원패스로그인시 기존 정보를 미리 가져와 있음.
			resVO = new JnitcmsmbrVO();
			BeanUtils.copyProperties(searchVO, resVO);
		}
    	
    	//만료된 회원 체크
    	if(resVO.getExpireDate() != null) {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		DateTime today = new DateTime(sdf.format(new Date()));
    		DateTime expire = new DateTime(resVO.getExpireDate().getTime());
    		if(today.isAfter(expire)) throwBizException("com.error.login.expire");
    	}
    	//탈퇴회원
    	if("30016".equals(resVO.getTypeId()))throwBizException("com.error.login.out");
    	
    	//비밀번호 틀린횟수 체크
    	int pwMiscnt = resVO.getPwMiscnt();
    	if(pwMiscnt > NullUtil.nullInt(Globals.LOGIN_PW_MISS_MAX_COUNT))throwBizException("com.error.login.exeedwrongpw", new String[] {Globals.LOGIN_PW_MISS_MAX_COUNT});
    	
    	
    	//회원타입체크
    	if(types != null) {
    		boolean isType = false;
    		for(String type:types) {
    			if(type.equals(resVO.getTypeId())) {
    				isType = true;
    				break;
    			}
    		}
    		if(!isType)throwBizException("com.error.login.nottype");
    	}
    	
    	// 로그인 회원 등급 로딩
    	JnitcmsmbrtypeVO mbrtypeVO = new JnitcmsmbrtypeVO();
    	mbrtypeVO.setTypeId(resVO.getTypeId());
    	mbrtypeVO = jnitcmsmbrtypeService.selectJnitcmsmbrtype(mbrtypeVO);
    	resVO.setTypeVO(mbrtypeVO);
    	
    	//중복 로그인 프로세스
    	duplicateLoginProcess(request, resVO.getMbrId());
		
    	//회원가입 추가 정보 가져오기
    	ComMbrVO comMbrVO = new ComMbrVO();
    	comMbrVO.setMbrId(resVO.getMbrId());
    	comMbrVO = selectComMbr(comMbrVO);
		
		// 2-1. 로그인 정보를 세션에 저장
		resVO.setCmsMenutree(AdminUtil.CmsMenuTree(resVO.getTypeId())/*getCmsMenuTree(request, model)*/);
		SessionUtil.setAttribute(SESSION_VO, resVO);
		SessionUtil.setAttribute("loginIp", request.getRemoteAddr());		//쿠키변조를 막기위한 세션에 IP저장
		
		//추가개발 전용 처리 시작 -------------------
    	SessionUtil.setAttribute(SESSION_ADT_VO, comMbrVO);
    	
		//관리자회원정보 처리.
		if(isMbrLevelStaff()) {//직원 회원만 처리.
			//세션에 메뉴권한 처리
			ComMbrVO loginVO = new ComMbrVO();
			BeanUtils.copyProperties(resVO, loginVO);
			ret = loginByVO(loginVO);	
		}	
    	//사용자페이지에서 로그인은 /로
    	if(types == null)ret = "/";
		
		//추가개발 처리 종료 -------------------
				
		//비밀번호 틀린 횟수 횟수 초기화
		resVO.setPwMiscnt(1);
		jnitcmsmbrService.updateJnitcmsmbr(resVO);
		
		//로그인 기록
		loginlogUtil.loginLog();
		
		if( SessionUtil.getAttribute("sessReturnUrl") != null ) {
			ret = NullUtil.nullString( SessionUtil.getAttribute("sessReturnUrl").toString() );
			SessionUtil.removeAttribute("sessReturnUrl");
		}
		return ret;
	}
	
	/**
	 * 로그인처리
	 * 
	 * @param searchVO
	 * @return 세션에 저장되어 있는 메뉴중 최초 대메뉴
	 * @throws Exception
	 */
	public String login(ComMbrVO searchVO) throws Exception{
		return login(searchVO, null);
	}
	
	/**
	 * 로그아웃처리
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void logout() throws Exception{
    	LoginManager.logout(getLoginID());
    	
    	SessionUtil.removeAttribute(SESSION_VO);//cms로그아웃처리
    	
    	SessionUtil.removeAttribute(SESSION_ADT_VO);
		if(isMbrLevelStaff()) {//직원 회원만 처리.
			SessionUtil.removeAttribute(SESSION_LMENU_LIST);
			SessionUtil.removeAttribute(SESSION_MMENU_LIST);
			SessionUtil.removeAttribute(SESSION_SMENU_LIST);
			SessionUtil.removeAttribute(SESSION_MY_MAIN);
		}
		SessionUtil.invalidate();
	}
	
	/**
	 * 로그인 권한 처리
	 * @param loginVO
	 * @return 권한이 있는 URL
	 */
	public String loginByVO(ComMbrVO loginVO){
		String ret = "";

		////로그인정보에 따른 메뉴 정보 셋팅
		//각 단계별 세션에 저장될 메뉴값
		List<ComLoginMenuVO> lmenuList = new ArrayList<ComLoginMenuVO>();
		List<ComLoginMenuVO> mmenuList = new ArrayList<ComLoginMenuVO>();
		List<ComLoginMenuVO> smenuList = new ArrayList<ComLoginMenuVO>();
		List<ComLoginMenuVO> tmenuList = new ArrayList<ComLoginMenuVO>();
		
		//권한이 있는 메뉴ID값들(부서권한 + 사용자권한)
		HashSet<String> menuIds = new HashSet<String>();
		//나의 아이디 권한가져오기
		ComIndvlzMenuAuthVO umaVO = new ComIndvlzMenuAuthVO();
		umaVO.setRecordCountPerPage(0);
		umaVO.setMbrId(loginVO.getMbrId());
		List<ComIndvlzMenuAuthVO> umaList = comIndvlzMenuAuthService.selectIndvlzMenuAuthList(umaVO);
		if(umaList != null){
			for(ComIndvlzMenuAuthVO tmpVO:umaList){
				menuIds.add(tmpVO.getMenuSn());
			}
		}
		//나의 부서 권한가져오기
		ComGrpMenuAuthVO dmaVO = new ComGrpMenuAuthVO();
		dmaVO.setRecordCountPerPage(0);
		dmaVO.setAuthGrpId(loginVO.getTypeId());
		List<ComGrpMenuAuthVO> dmaList = comGrpMenuAuthService.selectGrpMenuAuthList(dmaVO);
		if(umaList != null){
			for(ComGrpMenuAuthVO tmpVO:dmaList){
				menuIds.add(tmpVO.getMenuSn());
			}
		}
		
/*
		//나의 직급 메뉴제한 가져오기
		CmGradeMenuExceptVO jgmeVO = new CmGradeMenuExceptVO();
		jgmeVO.setRecordCountPerPage(0);
		jgmeVO.setGrade(loginVO.getColGradeCode());
		if(jgmeVO.getGrade() == null)jgmeVO.setGrade("======");
		List<CmGradeMenuExceptVO> jgmeList = cmGradeMenuExceptService.selectGradeMenuExceptList(jgmeVO);		
*/
		
		//전체 메뉴 가져오기
		ComMenuVO menuVO = new ComMenuVO();
		menuVO.setRecordCountPerPage(0);
		List<ComMenuVO> menuList = comMenuService.selectMenuList(menuVO);
		if(menuList != null){
			for(ComMenuVO tmpVO:menuList){
				if("4".equals(tmpVO.getMenuLvlVl())){
					boolean isExist = menuIds.contains(tmpVO.getMenuSn());
					if(!isExist)continue;
					ComLoginMenuVO mVO = makeMenuVO(tmpVO, umaList, dmaList);
					if(mVO != null && mVO.isRedngAuth())tmenuList.add(mVO);
//					tmenuList.add(makeMenuVO(tmpVO, umaList, dmaList));
				}					
			}
			for(ComMenuVO tmpVO:menuList){
				if("3".equals(tmpVO.getMenuLvlVl())){
					boolean isExist = menuIds.contains(tmpVO.getMenuSn());
					//하위메뉴에 있는지 체크
					boolean isExistTmenu = false;
					for(ComLoginMenuVO tmenu:tmenuList){
						if(tmpVO.getMenuSn().equals(tmenu.getUprMenuSn())){
							isExistTmenu = true;
							tmpVO.setMenuUrl(tmenu.getMenuUrl());
							break;
						}
					}
					if(!isExist && !isExistTmenu)continue;
					ComLoginMenuVO mVO = makeMenuVO(tmpVO, umaList, dmaList);
					if(mVO != null && mVO.isRedngAuth())smenuList.add(mVO);
//					smenuList.add(makeMenuVO(tmpVO, umaList, dmaList));
				}					
			}
			
			for(ComMenuVO tmpVO:menuList){
				if("2".equals(tmpVO.getMenuLvlVl())){
					boolean isExist = menuIds.contains(tmpVO.getMenuSn());
					//하위메뉴에 있는지 체크
					boolean isExistSmenu = false;
					for(ComLoginMenuVO smenu:smenuList){
						if(tmpVO.getMenuSn().equals(smenu.getUprMenuSn())){
							isExistSmenu = true;
							tmpVO.setMenuUrl(smenu.getMenuUrl());
							break;
						}
					}
					if(!isExist && !isExistSmenu)continue;
					ComLoginMenuVO mVO = makeMenuVO(tmpVO, umaList, dmaList);
					if(mVO != null && mVO.isRedngAuth())mmenuList.add(mVO);
//					mmenuList.add(makeMenuVO(tmpVO, umaList, dmaList));
				}					
			}
			
			for(ComMenuVO tmpVO:menuList){
				if("1".equals(tmpVO.getMenuLvlVl())){
					boolean isExist = menuIds.contains(tmpVO.getMenuSn());
					//하위메뉴에 있는지 체크
					boolean isExistMmenu = false;
					for(ComLoginMenuVO mmenu:mmenuList){
						if(tmpVO.getMenuSn().equals(mmenu.getUprMenuSn())){
							isExistMmenu = true;
							tmpVO.setMenuUrl(mmenu.getMenuUrl());
							break;
						}
					}
					if(!isExist && !isExistMmenu)continue;
//					if("".equals(ret))ret = tmpVO.getMenuUrl(); 
					ComLoginMenuVO lVO = makeMenuVO(tmpVO, umaList, dmaList);
					if(lVO != null && lVO.isRedngAuth())lmenuList.add(lVO);
//					lmenuList.add(makeMenuVO(tmpVO, umaList, dmaList));
				}					
			}
		}
		
		
		SessionUtil.setAttribute(SESSION_LMENU_LIST, lmenuList);
		SessionUtil.setAttribute(SESSION_MMENU_LIST, mmenuList);
		SessionUtil.setAttribute(SESSION_SMENU_LIST, smenuList);
		SessionUtil.setAttribute(SESSION_TMENU_LIST, tmenuList);
		
		//SessionUtil.setAttribute(SESSION_MY_MAIN, ret);
		SessionUtil.setAttribute(SESSION_MY_MAIN, "/exts/com/main.do");//메인페이지 처리.		
		if("".equals(ret))ret = "/exts/com/main.do";
		
		//로그인 시 접근페이지 url로 이동.
		String urlAfterLogin = (String)SessionUtil.getAttribute("urlAfterLogin");
		SessionUtil.setAttribute("urlAfterLogin", null);
		if(urlAfterLogin != null)ret = urlAfterLogin;
		
		return ret;
	}

	/**
	 * 중복 로그인 프로세스
	 * @param mbrId
	 */
	public void duplicateLoginProcess(HttpServletRequest request, String mbrId) {
    	Properties properties = CmsConfigController.getCmsDefaultProp(request);
		/* 중복 로그인 프로세스*/
		String overlap = NullUtil.nullString((String) properties.get("overlap"));
		if(overlap.equals("1")){
			HashMap<String, String> m = new HashMap<String, String>();
        	m.put("mbrId", mbrId);
        	m.put("creationTime", request.getSession().getCreationTime()+"");
        	if(LoginManager.duplicationLoginCheck(mbrId, Long.toString(request.getSession().getCreationTime()))){
       			LoginManager.logout(mbrId);
       			
       			m.put("loginType", "logout");
       			//jnitSyncService.syncRequest("jnitSyncResponseService",m);
        	}
        	LoginManager.setSession(mbrId, Long.toString(request.getSession().getCreationTime()));
        	
        	m.put("loginType", "login");
   			//jnitSyncService.syncRequest("jnitSyncResponseService",m);
		}else{
			LoginManager.logout(mbrId);
			
			HashMap<String, String> m = new HashMap<String, String>();
        	m.put("mbrId", mbrId);
        	m.put("creationTime", request.getSession().getCreationTime()+"");
        	m.put("loginType", "logout");
        	//jnitSyncService.syncRequest("jnitSyncResponseService",m);
		}
	}
	

	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public List<ComMbrVO> selectComMbrList(ComMbrVO searchVO) {
		List<ComMbrVO> list = (List<ComMbrVO>)comMbrDao.selectComMbrList(searchVO);
		/*
		if(list != null){
			for(ComMbrVO result:list){
				
			}
		}
		*/
		return list;
//		return null;
	}

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectComMbrTot(ComMbrVO searchVO) {
		return comMbrDao.selectComMbrTot(searchVO);
//		return null;
	}

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public ComMbrVO selectComMbr(ComMbrVO searchVO) {
		ComMbrVO result = comMbrDao.selectComMbr(searchVO);
		return result;
//		return null;
	}

	/**
	 * 추가 / 수정
	 * 
	 * @param searchVO
	 * @param detailList
	 * @throws Exception
	 */
	public void writeComMbr(ComMbrVO searchVO) throws Exception {
		
		
		String id = NullUtil.nullString(searchVO.getMbrId());
		if("".equals(id)){
			if(!isStreAuth())throwBizException("com.error.noauth.write");
			
			if(!idCheck(searchVO.getMbrLogin()))throwBizException("");
			
			searchVO.setPasswd(comMbrDao.selectComMbrEncodePw(searchVO.getPasswd()));
//			comMbrDao.insertComMbr(searchVO);
			JnitcmsmbrVO mbrVO = new JnitcmsmbrVO();
			BeanUtils.copyProperties(searchVO, mbrVO);
			id = jnitcmsmbrService.insertJnitcmsmbr(mbrVO);
			
			MbrUtil.insertMbrlog(SpringHelperUtil.getRequest(), mbrVO, mbrVO,"가입", null, null);
		}else{
			ComMbrVO result = selectComMbr(searchVO);
			if(!isModifiable(result))throwBizException("com.error.noauth.modify");
			
			//비밀번호 변경일 때만 처리.
			if(NullUtil.nullString(searchVO.getPasswd()).equals(""))searchVO.setPasswd(result.getPasswd());
			else searchVO.setPasswd(comMbrDao.selectComMbrEncodePw(searchVO.getPasswd()));
			
			//비밀번호 틀린횟수 초기화
			searchVO.setPwMiscnt(1);

			//기존 데이터중 유지해야할 데이터
			
			id = result.getMbrId();
//			comMbrDao.updateComMbr(searchVO);
			JnitcmsmbrVO mbrVO = new JnitcmsmbrVO();
			BeanUtils.copyProperties(searchVO, mbrVO);
			mbrVO.setUpdateCondition("0");
			jnitcmsmbrService.updateJnitcmsmbr(mbrVO);
			
			MbrUtil.insertMbrlog(SpringHelperUtil.getRequest(), mbrVO, mbrVO,"수정", null, null);
		}
		//추가정보
		searchVO.setMbrId(id);
	}

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void deleteComMbr(ComMbrVO searchVO) throws Exception {
		/*
		String userId = getLoginID();
		//권한 체크
		ComMbrVO result = selectComMbr(searchVO);
		if(!isDeletable(result))throwBizException("com.error.noauth.delete");
				
		searchVO.setRegstrId(userId);
		searchVO.setUpdrId(userId);
		comMbrDao.deleteComMbr(searchVO);
		*/
		
		if(!isDelAuth()) throwBizException("com.error.noauth.delete");
		if(!isAdmin()) throwBizException("com.error.noauth.delete");
		
		ComMbrVO mbrVO = new ComMbrVO();
		mbrVO = selectComMbr(searchVO);
		if(mbrVO == null || mbrVO.getMbrId() == null)throwBizException("com.login.nomatch");
		
		//cms 회원탈퇴
		JnitcmsmbrVO loginVO = getLoginVO();
		mbrVO.setEx01(mbrVO.getMbrLogin());
    	mbrVO.setEx02(mbrVO.getMbrNm());
    	mbrVO.setEx03(mbrVO.getTypeId());
		mbrVO.setMbrLogin(mbrVO.getMbrId());//MbrLogin으로 할 경우 한 사용자가 여러번 탈퇴할경우 제약조건 위배됨.
    	mbrVO.setOrgId(null);
    	mbrVO.setPosId(null);
    	mbrVO.setPasswd("#"+mbrVO.getMbrId());
    	//mbrVO.setMbrNm("#"+mbrVO.getMbrNm());
    	mbrVO.setMbrNm(mbrVO.getMbrId());
    	mbrVO.setTypeId("30040");//삭제회원
    	mbrVO.setTel("");
    	mbrVO.setMobile("");
    	mbrVO.setEmail("");
    	mbrVO.setHomepage("");
    	mbrVO.setPostcode("");
    	mbrVO.setAddress("");
    	mbrVO.setEmailRecv((byte)0);
    	mbrVO.setSmsRecv((byte)0);
    	mbrVO.setSn("");
    	mbrVO.setDp("");
    	
		jnitcmsmbrService.leaveJnitcmsmbr(mbrVO);
    	MbrUtil.insertMbrlog(SpringHelperUtil.getRequest(), mbrVO, loginVO,"탈퇴", null, null);
	}

	/**
	 * 인증서 정보 초기화
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void initDnComMbr(ComMbrVO searchVO) throws EgovBizException {
/*
		String userId = getLoginID();
		
		ComMbrVO result = selectComMbr(searchVO);
		if(!isModifiable(result))throwBizException("com.error.noauth.delete");
		
		result.setUpdrId(userId);
		result.setCrtfctInfoCnts(null);
		comMbrDao.updateComMbr(result);
		*/
	}

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComMbrVO searchVO){
		if(!isRedngAuth())return false;
		
		if(searchVO == null)return false;
		if(isAdmin())return true;
//		return NullUtil.nullString(searchVO.getMbrId()).equals(getLoginID());
		return NullUtil.nullString(searchVO.getOrgId()).equals(getLoginVO().getOrgId());
	}

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isModifiable(ComMbrVO searchVO){
		if(!isStreAuth())return false;
		
		if(searchVO == null)return false;
		if(isAdmin())return true;

//		return NullUtil.nullString(searchVO.getMbrId()).equals(getLoginID());
		return NullUtil.nullString(searchVO.getOrgId()).equals(getLoginVO().getOrgId());
	}
	
	/**
	 * 비밀번호 변경
	 * @param searchVO
	 * @param orPw 현재비밀번호
	 * @return
	 */
	public void changePw(ComMbrVO searchVO, String orPw)throws Exception{
		if(NullUtil.nullString(searchVO.getMbrId()).equals("")){
			searchVO.setMbrId("============");
		}
		
		ComMbrVO loginListVO = new ComMbrVO();
		loginListVO.setMbrId(searchVO.getMbrId());
		loginListVO.setRecordCountPerPage(0);
		List<ComMbrVO> list = (List<ComMbrVO>)comMbrDao.selectComMbrList(loginListVO);
		if(list != null && list.size() > 0){
			ComMbrVO loginVO = list.get(0);
			if(loginVO == null || loginVO.getMbrId() == null)throwBizException("com.login.nomatch");
			
			String encodePw = comMbrDao.selectComMbrEncodePw(orPw);
			
			if(loginVO.getPasswd().equals(encodePw)){
				loginVO.setPasswd(comMbrDao.selectComMbrEncodePw(searchVO.getPasswd()));
//				comMbrDao.updateComMbrPwModified(loginVO);
				//cms 연계
				JnitcmsmbrVO mbrVO = new JnitcmsmbrVO();
				BeanUtils.copyProperties(loginVO, mbrVO);
				mbrVO.setUpdateCondition("0");
				jnitcmsmbrService.updateJnitcmsmbr(mbrVO);
			}else {
				throwBizException("com.error.login.notmatchpw");
			}
	
		}else {
			throwBizException("com.login.nomatch");
		}
		
		
	}
	
	/**
	 * 공인인증서 등록
	 * @param searchVO
	 * @param gpki
	 * @return
	 */
	public void updateDn(ComMbrVO searchVO)throws Exception{
		/*
		ComMbrVO loginListVO = new ComMbrVO();
		loginListVO.setMbrId(searchVO.getMbrId());
		loginListVO.setRecordCountPerPage(0);
		List<ComMbrVO> list = (List<ComMbrVO>)comMbrDao.selectComMbrList(loginListVO);
		if(list != null && list.size() > 0){
			ComMbrVO loginVO = list.get(0);
			if(loginVO == null || loginVO.getMbrId() == null)throwBizException("com.login.nomatch");
			loginVO.setCrtfctInfoCnts(searchVO.getCrtfctInfoCnts());
			comMbrDao.updateComMbrUpdateDn(loginVO);				
			
		}else {
			throwBizException("com.login.nomatch");
		}
		*/
	}
	
	/**
	 * 비밀번호 틀린횟수 초기화
	 * @param searchVO
	 * @return
	 */
	public void initWrongPwCnt(ComMbrVO searchVO) throws Exception{
		
    	JnitcmsmbrVO mbrVO = new JnitcmsmbrVO();
    	mbrVO.setMbrId(searchVO.getMbrId());
    	mbrVO.setUpdateCondition("1");
    	mbrVO.setPwMiscnt(1);
    	jnitcmsmbrService.updateJnitcmsmbr(mbrVO);
//		searchVO.setLoginFailrNmb("0");
//		comMbrDao.updateComMbrWrongPwCnt(searchVO);
		
	}
	

	/**
	 * id중복확인
	 * 
	 * @param searchVO
	 */
	public boolean idCheck(String id) throws Exception{
		if(NullUtil.nullString(id).equals(""))return false;
		if(MbrUtil.limitNm(id.toLowerCase()))return false;
		if(!PatternUtil.idRegularExpressionChk(id))return false;
		JnitcmsmbrVO mbrVO = new JnitcmsmbrVO();
		mbrVO.setMbrLogin(id);
		
		return !jnitcmsmbrService.hasJnitcmsmbr(mbrVO);
	}



	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	// ///////////////////////////////////////////////////////////////////
	/**
	 * 세션에 저장될 메뉴 VO 만들기
	 * @param searchVO
	 * @return
	 */
	private ComLoginMenuVO makeMenuVO(ComMenuVO searchVO, List<ComIndvlzMenuAuthVO> umaList, List<ComGrpMenuAuthVO> dmaList){
		ComLoginMenuVO menuVO = new ComLoginMenuVO(); 

		menuVO.setMenuSn(searchVO.getMenuSn());
		menuVO.setMenuNm(searchVO.getMenuNm());
		menuVO.setMenuUrl(searchVO.getMenuUrl());
		menuVO.setUprMenuSn(searchVO.getUprMenuSn());
		menuVO.setMenuLvlVl(searchVO.getMenuLvlVl());
		menuVO.setMenuSeq(searchVO.getMenuSeq());
		menuVO.setMenuExplnt(searchVO.getMenuExplnt());
		
		//권한저장
		menuVO.setStreAuth(false);
		menuVO.setRedngAuth(false);
		menuVO.setDelAuth(false);
		menuVO.setPrntgAuth(false);
		if(umaList != null){
			for(ComIndvlzMenuAuthVO tmpVO:umaList){
				if(tmpVO.getMenuSn().equals(searchVO.getMenuSn())){
					if("Y".equals(tmpVO.getStreAuthYn()))menuVO.setStreAuth(true);
					if("Y".equals(tmpVO.getRedngAuthYn()))menuVO.setRedngAuth(true);
					if("Y".equals(tmpVO.getDelAuthYn()))menuVO.setDelAuth(true);
					if("Y".equals(tmpVO.getPrntgAuthYn()))menuVO.setPrntgAuth(true);
					break;
				}
			}
		}
		if(dmaList != null){
			for(ComGrpMenuAuthVO tmpVO:dmaList){
				if(tmpVO.getMenuSn().equals(searchVO.getMenuSn())){
					if("Y".equals(tmpVO.getStreAuthYn()))menuVO.setStreAuth(true);
					if("Y".equals(tmpVO.getRedngAuthYn()))menuVO.setRedngAuth(true);
					if("Y".equals(tmpVO.getDelAuthYn()))menuVO.setDelAuth(true);
					if("Y".equals(tmpVO.getPrntgAuthYn()))menuVO.setPrntgAuth(true);
					break;
				}
			}
		}
//		if(menuVO.isRedngAuth() || menuVO.isDelAuth() || menuVO.isPrntgAuth())menuVO.setStreAuth(true);
		
		if(!menuVO.isRedngAuth())menuVO = null;

		return menuVO;
	}
	
	/**
	 * 벨리데이션 체크
	 * @param searchVO
	 * @throws EgovBizException
	private void validate(ComMbrVO searchVO){
		
	}
	 */
	// /////////////////////private,protected 메소드 선언 영역 끝
	
	// ///////////////////////////////////////////////////////////////////
}
