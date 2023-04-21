package jnit.login.check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import jnit.cms.AdminUtil;
import jnit.cms.CmsHelper;
import jnit.cms.mbr.JnitcmsmbrVO;
import jnit.com.util.IpFilterUtil;

public class JnitLoginChecker extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ModelMap model = new ModelMap();
		JnitcmsmbrVO mbrVO = (JnitcmsmbrVO)CmsHelper.getAuthMbr(request);
		
		boolean isPass = true;
		String msg = "";
		String url = "";
		
		if("".equals(NullUtil.nullString(mbrVO.getMbrId()))) {
			msg = "로그인이 필요합니다.";
			url = Globals.CMS_LOGIN_URL;
			isPass = false;
		}else if("".equals(NullUtil.nullString(mbrVO.getTypeId()))) {
			msg = "회원 TypeId가 설정되어 있지 않습니다.";
			url = Globals.CMS_LOGIN_URL;
			isPass = false;
		}else if(!IpFilterUtil.isPass()) {
			msg = "접근이 불가능한 IP 입니다.("+request.getRemoteAddr()+")";
			url = "/";
			isPass = false;
		}else {
			/* 관리자 레벨 */
			final String admLevelProp = EgovProperties.getProperty(EgovProperties.getProperty("Webadm.Properties"), "Webadm.Levels");
			final String[] admLevels = admLevelProp.split(",");
			boolean loginAdmin = false;
			for(int i1=0; i1<admLevels.length; i1++) {
				if(loginAdmin == false) {
					if(mbrVO.getTypeVO().getTypeLv().equals(admLevels[i1]) || mbrVO.getTypeVO().getTypeLv().equals("A")) {
						loginAdmin = true;
						break;
					}
				}
			}
			if(!loginAdmin) {
				msg = "CMS 접근레벨이 아닙니다. ( 레벨:"+mbrVO.getTypeVO().getTypeLv()+" )";
				url = Globals.CMS_LOGIN_URL;
				isPass = false;
			}
		}
		
		if(!isPass) {
			ModelAndView modelAndView;
	    	model.addAttribute("alert", msg);
			model.addAttribute("path",request.getContextPath()+url);
			modelAndView = new ModelAndView("/jnit/util/alertMove",model);
			throw new ModelAndViewDefiningException(modelAndView);
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		boolean isPass = true;
		String msg = "";
		String url = "";
		
		if(!AdminUtil.menuCheck()) {
			//Controller에 설정된 menuId값을 이용하다 보니 시작전이 아닌 시작 후에 체크
			msg = "해당 메뉴에 접근권한이 없습니다.";
			url = "/cms/";
			isPass = false;
		}
		
		ModelMap model = new ModelMap();
		if(!isPass) {
	    	model.addAttribute("alert", msg);
			model.addAttribute("path",url);
			modelAndView = new ModelAndView("/jnit/util/alertMove",model);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	} 
}
