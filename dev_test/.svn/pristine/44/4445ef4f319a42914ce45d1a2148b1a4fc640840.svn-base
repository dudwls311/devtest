package exts.com.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import exts.com.enums.EnumMenuCd;
import exts.com.service.ComCacheService;

/**
 *  메인페이지 클래스
 * @author 
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.10.07            최초 생성
 *
 * </pre>
 */
@Controller
public class ComMainController extends ExtsAbstractController{

	protected String getPkg(){return "exts/com/main";}
	
	@Resource(name = "comCacheService")
	private ComCacheService comCacheService;
	

	/**
	 * 메인페이지
	 */
	@RequestMapping(value="/index.do")
	public String index(
			HttpServletRequest request,
			ModelMap model) throws Exception{
		setIndexProcess(EnumMenuCd.MAIN, "list");
		
		boolean isAdmin = isAdmin();
		model.addAttribute("isAdmin",isAdmin);
		
		DateTime now = new DateTime();
		model.addAttribute("now", now);
		return "exts/com/main/index";
	}

}
