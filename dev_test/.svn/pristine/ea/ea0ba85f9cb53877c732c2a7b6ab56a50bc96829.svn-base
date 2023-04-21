/**
 * @version 3.2.0.1
 */
package exts.com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.com.utl.fcc.service.SessionUtil;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성 
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  </pre>
 */


public class ExtsAuthenticInterceptor extends HandlerInterceptorAdapter {
	
	Log log = LogFactory.getLog(getClass());
	
	/**
	 * 쿠키변조 차단
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {	
		ModelMap model = new ModelMap();
		
		String loginIp = (String) SessionUtil.getAttribute("loginIp");		//로그인IP
		String currentIp = request.getRemoteAddr();
		
		//접속IP
		if(!"".equals(NullUtil.nullString(loginIp)) && !loginIp.equals(currentIp)) {
			log.error("COOKIE FALSIFICATION");
			model.addAttribute("alert","쿠키변조가 의심되어 접속을 차단합니다.");
			model.addAttribute("path","/error");
			ModelAndView modelAndView = new ModelAndView("/jnit/util/alertMove",model);
			throw new ModelAndViewDefiningException(modelAndView);
		}
		return true;
	}
}
