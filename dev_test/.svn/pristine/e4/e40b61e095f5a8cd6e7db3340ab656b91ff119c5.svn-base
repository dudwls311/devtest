/**
 * @version 3.2.0.1
 */
package jnit.cms.sitecopy;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import jnit.cms.site.JnitcmssiteVO;

@Controller
public class JnitCmsSitecopyController {

	@Resource(name="jnitCmsSitecopyService")
    private JnitCmsSitecopyService jnitCmsSitecopyService;
	
	@RequestMapping("/cms/siteCopy.do")
    public String siteCopy(
	    HttpServletRequest request,
	    @ModelAttribute("jnitcmssiteVO") JnitcmssiteVO newCmsSiteVO,
        ModelMap model) throws Exception {
		
    	String originSiteId = NullUtil.nullString(request.getParameter("originSiteId"));			//복사할 사이트 ID
    	if("".equals(originSiteId)){
    		model.addAttribute("alert", "복사할 사이트를 선택해주세요.");
    		return "/jnit/util/alertBack";
    	}
    	
    	if("".equals(NullUtil.nullString(newCmsSiteVO.getSiteNm())) || "".equals(NullUtil.nullString(newCmsSiteVO.getSitePath()))){
    		model.addAttribute("alert", "사이트 설정값을 입력해주세요.");
    		return "/jnit/util/alertBack";
    	}
    	
    	String realPath = Globals.CONTEXT_PATH;
    	String tplDir = new StringBuffer().append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).toString();
        File f = new File(tplDir);
        if(f.exists()) {
        	model.addAttribute("alert","해당 파일의 경로가 존재합니다.");
        	return "/jnit/util/alertBack";
        }
        
        try{
        	jnitCmsSitecopyService.siteCopy(originSiteId, newCmsSiteVO, request);
        }catch(NullPointerException e){
        	model.addAttribute("alert","사이트 복사중 오류가 발생했습니다.");
        	return "/jnit/util/alertBack";
        }catch(Exception e){
        	model.addAttribute("alert","사이트 복사중 오류가 발생했습니다.");
        	return "/jnit/util/alertBack";
        }
        
    	return "forward:/cms/sub3/0101.do";
	}
}