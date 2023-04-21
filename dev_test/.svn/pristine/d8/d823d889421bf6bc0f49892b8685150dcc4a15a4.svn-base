/**
 * @version 3.2.0.1
 */
package jnit.cms.sitecopy;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import jnit.cms.CmsManageSub11Controller;
import jnit.cms.cnt.JnitcmscntDefaultVO;
import jnit.cms.cnt.JnitcmscntService;
import jnit.cms.cnt.JnitcmscntVO;
import jnit.cms.cntvt.JnitcmscntvtService;
import jnit.cms.cntvt.JnitcmscntvtVO;
import jnit.cms.disseminate.JnitcmsdisseminateDefaultVO;
import jnit.cms.disseminate.JnitcmsdisseminateService;
import jnit.cms.disseminate.JnitcmsdisseminateVO;
import jnit.cms.disseminatehisotry.JnitcmsdisseminatehistoryService;
import jnit.cms.handler.CmsHandler;
import jnit.cms.hist.JnitcmshistService;
import jnit.cms.mbr.JnitcmsmbrService;
import jnit.cms.mbrtype.JnitcmsmbrtypeService;
import jnit.cms.menu.JnitcmsmenuDefaultVO;
import jnit.cms.menu.JnitcmsmenuService;
import jnit.cms.menu.JnitcmsmenuVO;
import jnit.cms.menu.menuUtil;
import jnit.cms.mgmt.JnitcmsmgmtDefaultVO;
import jnit.cms.mgmt.JnitcmsmgmtService;
import jnit.cms.mgmt.JnitcmsmgmtVO;
import jnit.cms.mgmt.mgmtUtil;
import jnit.cms.org.JnitcmsorgService;
import jnit.cms.pos.JnitcmsposService;
import jnit.cms.rank.JnitcmsrankService;
import jnit.cms.site.JnitcmssiteService;
import jnit.cms.site.JnitcmssiteVO;
import jnit.cms.tpl.JnitcmstplDefaultVO;
import jnit.cms.tpl.JnitcmstplService;
import jnit.cms.tpl.JnitcmstplVO;
import jnit.cms.tpl.tplUtil;
import jnit.com.util.FileCopy;

/**
 * @Class Name : JnitcmssiteServiceImpl.java
 * @Description : Jnitcmssite Business Implement class
 * @Modification Information
 *
 * @author JNIT
 * @since 2012.06.01
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("jnitCmsSitecopyService")
public class JnitCmsSitecopyImpl extends EgovAbstractServiceImpl implements
        JnitCmsSitecopyService {
    
	@Resource(name = "jnitcmsdisseminateService")
    private JnitcmsdisseminateService jnitcmsdisseminateService;

    @Resource(name = "jnitcmscntService")
    private JnitcmscntService jnitcmscntService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "jnitcmscntvtService")
    private JnitcmscntvtService jnitcmscntvtService;

    @Resource(name="jnitcmssiteService")
    private JnitcmssiteService jnitcmssiteService;

    @Resource(name="jnitcmstplService")
    private JnitcmstplService jnitcmstplService;

    @Resource(name = "jnitcmshistService")
    private JnitcmshistService jnitcmshistService;

    @Resource(name="jnitcmsmbrService")
    private JnitcmsmbrService jnitcmsmbrService;

    @Resource(name="jnitcmsmbrtypeService")
    private JnitcmsmbrtypeService jnitcmsmbrtypeService;

    @Resource(name = "jnitcmsmenuService")
    private JnitcmsmenuService jnitcmsmenuService;

    @Resource(name = "jnitcmsdisseminatehistoryService")
    private JnitcmsdisseminatehistoryService jnitcmsdisseminatehistoryService;

    @Resource(name = "jnitcmsorgService")
    private JnitcmsorgService jnitcmsorgService;

    @Resource(name = "jnitcmsrankService")
    private JnitcmsrankService jnitcmsrankService;

    @Resource(name = "jnitcmsposService")
    private JnitcmsposService jnitcmsposService;
    
    @Resource(name = "jnitcmsmgmtService")
    private JnitcmsmgmtService jnitcmsmgmtService;
	
    /**
	 * JNITCMSSITE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 JnitcmssiteVO
	 * @return void형
	 * @exception Exception
	 */
    
    @SuppressWarnings("unchecked")
	@Transactional
    public void siteCopy(String originSiteId, JnitcmssiteVO newCmsSiteVO, HttpServletRequest request) throws Exception {
    	
    	String realPath = Globals.CONTEXT_PATH;
        String newSiteId = jnitcmssiteService.insertJnitcmssite(newCmsSiteVO);
    	HashMap<String,String> mappingMap = new HashMap<String, String>();
    	
    	Properties props = new Properties();
    	
    	JnitcmssiteVO bfCmsSiteSearchVO = new JnitcmssiteVO();
    	bfCmsSiteSearchVO.setSiteId(originSiteId);
    	
    	bfCmsSiteSearchVO.setSiteId(originSiteId);
    	JnitcmssiteVO originCmsSiteVO = jnitcmssiteService.selectJnitcmssite(bfCmsSiteSearchVO);
    	
    	mappingMap.put(originSiteId, newSiteId);
    	mappingMap.put(originCmsSiteVO.getSiteNm(), newCmsSiteVO.getSiteNm());
    	
    	JnitcmstplDefaultVO jnitcmstplDefaultVO = new JnitcmstplDefaultVO();
    	jnitcmstplDefaultVO.setPageYn("N");
    	jnitcmstplDefaultVO.setSiteId(originSiteId);
		List<EgovMap> jnitcmstplList = jnitcmstplService.selectJnitcmstplList(jnitcmstplDefaultVO);
		JnitcmstplVO jnitcmstplVO;
		List<JnitcmstplVO> tplList = new ArrayList<JnitcmstplVO>();
		for(EgovMap map : jnitcmstplList){
			jnitcmstplVO = new JnitcmstplVO();
			jnitcmstplVO.setSiteId(mappingMap.get(map.get("siteId")));
			jnitcmstplVO.setTplId((String)map.get("tplId"));
			jnitcmstplVO.setTplType((String)map.get("tplType"));
			jnitcmstplVO.setTplNm((String)map.get("tplNm"));
			jnitcmstplVO.setTplDesc((String)map.get("tplDesc"));
			jnitcmstplVO.setTplSrc((String)map.get("tplSrc"));
			jnitcmstplVO.setStaticType((Integer)map.get("staticType"));
			
			String originTplId = jnitcmstplVO.getTplId();
			String newTplId = jnitcmstplService.insertJnitcmstpl(jnitcmstplVO);
			mappingMap.put(originTplId, newTplId);
			tplList.add(jnitcmstplVO);
		}
		
		for(JnitcmstplVO vo : tplList){
			if(!"".equals(NullUtil.nullString(vo.getTplDesc()))){
				vo.setTplDesc(mappingMap.get(vo.getTplDesc()));
				jnitcmstplService.updateJnitcmstpl(vo);
			}
		}
		
		JnitcmsmenuDefaultVO jnitcmsmenuDefaultVO = new JnitcmsmenuDefaultVO();
		jnitcmsmenuDefaultVO.setIsdelYn("Y");
		jnitcmsmenuDefaultVO.setSiteIdYn("Y");
		jnitcmsmenuDefaultVO.setSiteId(originSiteId);
		jnitcmsmenuDefaultVO.setPageYn("N");
		List<EgovMap> jnitcmsmenuList = jnitcmsmenuService.selectJnitcmsmenuList(jnitcmsmenuDefaultVO);
		JnitcmsmenuVO menuVO;
		List<JnitcmsmenuVO> menuList = new ArrayList<JnitcmsmenuVO>();
		for(EgovMap map : jnitcmsmenuList){
			menuVO = new JnitcmsmenuVO();
			menuVO.setMenuId((String)map.get("menuId"));
			menuVO.setSiteId(mappingMap.get(map.get("siteId")));
			menuVO.setMenuNm((String)map.get("menuNm"));
			menuVO.setMenuPath((String)map.get("menuPath"));
			menuVO.setPid((String)map.get("pid"));
			menuVO.setDepth((Integer)map.get("depth"));
			menuVO.setMethod((Integer)map.get("method"));
			menuVO.setIslink((Integer)map.get("islink"));
			menuVO.setIscnt((Integer)map.get("iscnt"));
			menuVO.setLinkurl((String)map.get("linkurl"));
			menuVO.setLinktgt((String)map.get("linktgt"));
			menuVO.setTplId(mappingMap.get(map.get("tplId")));
			menuVO.setCntId((String)map.get("cntId"));
			menuVO.setDefMenu((String)map.get("defMenu"));
			menuVO.setMenuImgFile((String)map.get("menuImgFile"));
			menuVO.setMenuImgPath((String)map.get("menuImgPath"));
			menuVO.setMenuImgOnm((String)map.get("menuImgOnm"));
			menuVO.setMenuImgUrl((String)map.get("menuImgUrl"));
			menuVO.setAdmMbrId((String)map.get("admMbrId"));
			menuVO.setActive((Integer)map.get("active"));
			menuVO.setCcl((String)map.get("ccl"));
			menuVO.setContentSearchChk((Integer)map.get("contentSearchChk"));
			menuVO.setRobotYn((Integer)map.get("robotYn"));
			menuVO.sethDel((String)map.get("hDel"));
			menuVO.setsDel((String)map.get("sDel"));
			menuVO.setlDel((String)map.get("lDel"));
			menuVO.setRedirectUrl((String)map.get("redirectUrl"));
			menuVO.setMetaTitle((String)map.get("metaTitle"));
			menuVO.setMetaSubject((String)map.get("metaSubject"));
			menuVO.setMetaKeyword((String)map.get("metaKeyword"));
			menuVO.setMetaDescription((String)map.get("metaDescription"));
			menuVO.setMetaClassification((String)map.get("metaClassification"));
			menuVO.setMetaRePly((String)map.get("metaRePly"));
			menuVO.setMetaLanguage((String)map.get("metaLanguage"));
			menuVO.setMetaBuild((String)map.get("metaBuild"));
			
			String originMenuId = menuVO.getMenuId();
			String newMenuId = jnitcmsmenuService.insertJnitcmsmenu(menuVO, newCmsSiteVO.getSitePath(), request);
			menuVO.setMenuId(newMenuId);
			mappingMap.put(originMenuId, newMenuId);
			menuList.add(menuVO);
		}
		
		JnitcmscntDefaultVO jnitcmscntDefaultVO = new JnitcmscntDefaultVO();
		jnitcmscntDefaultVO.setSiteId(originSiteId);
		jnitcmscntDefaultVO.setFirstIndex(0);
		jnitcmscntDefaultVO.setLastIndex(9999);
		List<EgovMap> jnitcmscntList = jnitcmscntService.selectJnitcmscntList(jnitcmscntDefaultVO);
		JnitcmscntVO cntVO;
		List<JnitcmscntVO> cntList = new ArrayList<JnitcmscntVO>();
		for(EgovMap map : jnitcmscntList){
			cntVO = new JnitcmscntVO();
			cntVO.setSiteId(mappingMap.get(map.get("siteId")));
			cntVO.setMenuId(mappingMap.get(map.get("menuId")));
			cntVO.setCntId((String)map.get("cntId"));
			cntVO.setCntDesc(mappingMap.get(map.get("cntDesc")));
			cntVO.setCntSrc((String)map.get("cntSrc"));
			cntVO.setTplId(NullUtil.nullString(mappingMap.get(map.get("tplId"))));
			cntVO.setIsmain((Integer)map.get("ismain"));
			cntVO.setCntWebStandard((Integer)map.get("cntWebStandard"));
			cntVO.setCntWebStandardCss((Integer)map.get("cntWebStandardCss"));
			cntVO.setCntWebAccess((Integer)map.get("cntWebAccess"));
			
			String originCntId = cntVO.getCntId();
			String newCntId = jnitcmscntService.insertJnitcmscnt(cntVO);
			mappingMap.put(originCntId, newCntId);
			cntList.add(cntVO);
		}
		
		for(JnitcmsmenuVO menu : menuList){
			menu.setPid(mappingMap.get(menu.getPid()));
			menu.setCntId(mappingMap.get(menu.getCntId()));
			jnitcmsmenuService.updateJnitcmsmenu(menu);
		}
		
		JnitcmsmgmtDefaultVO jnitcmsmgmtDefaultVO = new JnitcmsmgmtDefaultVO();
		jnitcmsmgmtDefaultVO.setSiteIdYn("Y");
		jnitcmsmgmtDefaultVO.setSiteId(originSiteId);
		jnitcmsmgmtDefaultVO.setPageYn("N");
		List<EgovMap> JnitcmsmgmtList = jnitcmsmgmtService.selectJnitcmsmgmtList(jnitcmsmgmtDefaultVO);
		JnitcmsmgmtVO mgmtVO;
		List<JnitcmsmgmtVO> mgmtList = new ArrayList<JnitcmsmgmtVO>();
		for(EgovMap map : JnitcmsmgmtList){
			mgmtVO = new JnitcmsmgmtVO();
			mgmtVO.setSiteId(mappingMap.get(map.get("siteId")));
			mgmtVO.setMgmtId((String)map.get("mgmtId"));
			mgmtVO.setMgmtSort((Integer)map.get("mgmtSort"));
			mgmtVO.setMgmtNm((String)map.get("mgmtNm"));
			mgmtVO.setMgmtType((String)map.get("mgmtType"));
			mgmtVO.setMgmtScriptType((String)map.get("mgmtScriptType"));
			mgmtVO.setMgmtContent((String)map.get("mgmtContent"));
			mgmtVO.setMgmtActId((String)map.get("mgmtActId"));
			mgmtVO.setMgmtHearderYn((Integer)map.get("mgmtHearderYn"));
			mgmtVO.setMgmtFileNm((String)map.get("mgmtFileNm"));
			mgmtVO.setMgmtFileOrigin((String)map.get("mgmtFileOrigin"));
			mgmtVO.setMgmtFilePath((String)map.get("mgmtFilePath"));
			mgmtVO.setMgmtFileUrl((String)map.get("mgmtFileUrl"));
			mgmtVO.setMgmtFilePathUrl((String)map.get("mgmtFilePathUrl"));
			mgmtVO.setMgmtInsertPath((String)map.get("mgmtInsertPath"));
			mgmtVO.setMgmtDownloadCount((Integer)map.get("mgmtDownloadCount"));
			mgmtVO.setBoardId((String)map.get("boardId"));
			mgmtVO.setBoardCount((String)map.get("boardCount"));
			mgmtVO.setBoardSkin((String)map.get("boardSkin"));
			mgmtVO.setThumbWidth((Integer)map.get("thumbWidth"));
			mgmtVO.setThumbHeight((Integer)map.get("thumbHeight"));
			
			String originMgmtId = mgmtVO.getMgmtId();
			String newMgmtId = jnitcmsmgmtService.insertJnitcmsmgmt(mgmtVO);
			mappingMap.put(originMgmtId, newMgmtId);
			mappingMap.put(newMgmtId, originMgmtId);
			mgmtList.add(mgmtVO);
		}
		
		for(JnitcmsmgmtVO vo : mgmtList){
			if(!"".equals(NullUtil.nullString(vo.getMgmtFileUrl()))){
				vo.setMgmtFileUrl(vo.getMgmtFileUrl().replace(originSiteId, newSiteId).replace(mappingMap.get(vo.getMgmtId()), vo.getMgmtId()));
			}
			if(!"".equals(NullUtil.nullString(vo.getMgmtFilePathUrl()))){
				vo.setMgmtFilePathUrl(vo.getMgmtFilePathUrl().replace(new StringBuffer().append("/").append(originCmsSiteVO.getSitePath()).append("/").toString(), new StringBuffer().append("/").append(newCmsSiteVO.getSitePath()).append("/").toString()));
			}
			jnitcmsmgmtService.updateJnitcmsmgmt(vo);
		}
		
		JnitcmsdisseminateDefaultVO jnitcmsdisseminateDefaultVO = new JnitcmsdisseminateDefaultVO();
		jnitcmsdisseminateDefaultVO.setIsdelYn("Y");
		jnitcmsdisseminateDefaultVO.setPageYn("N");
		jnitcmsdisseminateDefaultVO.setSearchCondition(originSiteId);
		List<EgovMap> JnitcmsdisseminateList = jnitcmsdisseminateService.selectdisseminateList(jnitcmsdisseminateDefaultVO);
		JnitcmsdisseminateVO disseminateVO;
		for(EgovMap map : JnitcmsdisseminateList){
			disseminateVO = new JnitcmsdisseminateVO();
			disseminateVO.setDisseminateId((String)map.get("disseminateId"));
			disseminateVO.setSiteId(mappingMap.get(map.get("siteId")));
			disseminateVO.setMenuId(mappingMap.get(map.get("menuId")));
			disseminateVO.setCntId(mappingMap.get(map.get("cntId")));
			disseminateVO.setContentPath((String)map.get("contentPath"));
			disseminateVO.setDisseminateType((String)map.get("disseminateType"));
			disseminateVO.setMbrId((String)map.get("mbrId"));
			disseminateVO.setDisseminateConfirm((Integer)map.get("disseminateConfirm"));
			disseminateVO.setDisseminateMemo((String)map.get("disseminateMemo"));
			disseminateVO.setContentConfirm((Integer)map.get("contentConfirm"));
			disseminateVO.setDisseminateDay((Date)map.get("disseminateDay"));
			disseminateVO.setDisseminateHistoryId((String)map.get("disseminateHistoryId"));
			disseminateVO.setDisseminateName((String)map.get("disseminateName"));
			disseminateVO.setDisseminateNum((String)map.get("disseminateNum"));
			disseminateVO.setDisseminateStand((Integer)map.get("disseminateStand"));
			
			String originDisseminateId = disseminateVO.getDisseminateId();
			String newDisseminateId = jnitcmsdisseminateService.insertJnitcmsdisseminate(disseminateVO);
			mappingMap.put(originDisseminateId, newDisseminateId);
		}
    	
		for(JnitcmsmenuVO mVO : menuList){
			mVO = jnitcmsmenuService.selectJnitcmsmenu(mVO);
			
			JnitcmsmenuVO pmenuVO = new JnitcmsmenuVO();
	        pmenuVO.setSiteId(newSiteId);
	        pmenuVO.setMenuId(mVO.getPid());
	        pmenuVO = jnitcmsmenuService.selectJnitcmsmenu(pmenuVO);
	        
	        if(mVO.getIscnt() == 1){
	        	JnitcmscntvtVO jnitcmscntvtVO = null;
	        	for(JnitcmscntVO cVO : cntList){
		        	if(NullUtil.nullString(mVO.getSiteId()).equals(NullUtil.nullString(cVO.getSiteId())) 
		        		&& NullUtil.nullString(mVO.getMenuId()).equals(NullUtil.nullString(cVO.getMenuId()))){
		        		
		        		jnitcmscntvtVO = new JnitcmscntvtVO();
		        		jnitcmscntvtVO.setSiteId(NullUtil.nullString(cVO.getSiteId()));
		        		jnitcmscntvtVO.setMenuId(NullUtil.nullString(cVO.getMenuId()));
		        		jnitcmscntvtVO.setCntId(NullUtil.nullString(cVO.getCntId()));
		        		jnitcmscntvtVO.setTplId(NullUtil.nullString(cVO.getTplId()));
		        		jnitcmscntvtVO.setCntDesc(NullUtil.nullString(cVO.getCntDesc()));
		        		jnitcmscntvtVO.setCntSrc(NullUtil.nullString(cVO.getCntSrc()));
		        	}
		        }
	        	
	        	//암호화 파일 생성
	    		if(CmsManageSub11Controller.cntWrite(props, jnitcmscntvtVO, newCmsSiteVO, pmenuVO, mVO, request, "Y" , null)){
	
	    			//암호화 파일 load
	    			String dirName = jnitcmsmenuService.makeMenuDirectoryName(mVO, newCmsSiteVO.getSitePath());
	    			String encodeDirName = EgovFileScrty.encode(dirName);
	    			String ctxRoot = Globals.CONTEXT_PATH+
	    			jnitcmsmenuService.makeMenuDirectoryName(mVO, newCmsSiteVO.getSitePath())+"/"+encodeDirName+".jsp";
	
	    			String str = CmsHandler.readFile(ctxRoot);
	
	    			//index File create
	    			String CtxRoot = Globals.CONTEXT_PATH+
	    			jnitcmsmenuService.makeMenuDirectoryName(mVO, newCmsSiteVO.getSitePath())+"/index.jsp";
	    			CmsHandler.writeFile(CtxRoot, str);
	    		}
	        }
		}
		
		mgmtUtil.writeFile("메인템플릿","2", "3", newCmsSiteVO);
        
		// TPL File 저장
        for(JnitcmstplVO vo : tplList){
        	tplUtil.tplWrite(request, vo, newCmsSiteVO);
        }
        
        //mata 핸들링
		menuUtil.makeMeta(request, newCmsSiteVO, "서브템플릿");
		
        //파일 핸들링
        mgmtUtil.writeFile("서브템플릿","2", "3", newCmsSiteVO);
        
        StringBuffer sourceSb = new StringBuffer();
        StringBuffer targetSb = new StringBuffer();
        
        //site/common/config/hading/css
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(realPath).append(File.separator).append(originCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("config").append(File.separator).append("handing").append(File.separator).append("css").toString())
        					 , new File(targetSb.append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("config").append(File.separator).append("handing").append(File.separator).append("css").toString()));
        
        //site/common/config/hading/js
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(realPath).append(File.separator).append(originCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("config").append(File.separator).append("handing").append(File.separator).append("js").toString())
        					 , new File(targetSb.append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("config").append(File.separator).append("handing").append(File.separator).append("js").toString()));
        
        //site/common/css
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(realPath).append(File.separator).append(originCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("css").toString())
        					 , new File(targetSb.append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("css").toString()));
        
        //site/common/js
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(realPath).append(File.separator).append(originCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("js").toString())
        					 , new File(targetSb.append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("js").toString()));
        
        //site/common/font
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(realPath).append(File.separator).append(originCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("fonts").toString())
        					 , new File(targetSb.append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("fonts").toString()));
        
        //site/common/img
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(realPath).append(File.separator).append(originCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("img").toString())
        					 , new File(targetSb.append(realPath).append(File.separator).append(newCmsSiteVO.getSitePath()).append(File.separator).append("common").append(File.separator).append("img").toString()));
        
        //sitemenu
        String sitemenuPath = new StringBuffer().append(realPath).append(File.separator).append("WEB-INF").append(File.separator).append("jsp").append(File.separator).append("jnit").append(File.separator).append("cms").append(File.separator).append("menu").append(File.separator).append("sitemenu").append(File.separator).toString();
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(sitemenuPath).append(originCmsSiteVO.getSitePath()).append("_original_sub_code.jsp").toString())
        					 , new File(targetSb.append(sitemenuPath).append(newCmsSiteVO.getSitePath()).append("_original_sub_code.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(sitemenuPath).append(originCmsSiteVO.getSitePath()).append("_sub.jsp").toString())
        					 , new File(targetSb.append(sitemenuPath).append(newCmsSiteVO.getSitePath()).append("_sub.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(sitemenuPath).append(originCmsSiteVO.getSitePath()).append("_sub_code.jsp").toString())
        					 , new File(targetSb.append(sitemenuPath).append(newCmsSiteVO.getSitePath()).append("_sub_code.jsp").toString()));
        
        //sitemap
        String sitemapPath = new StringBuffer().append(realPath).append(File.separator).append("WEB-INF").append(File.separator).append("jsp").append(File.separator).append("jnit").append(File.separator).append("cms").append(File.separator).append("menu").append(File.separator).append("sitemap").append(File.separator).toString();
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(sitemapPath).append(originCmsSiteVO.getSitePath()).append("_sitemap.jsp").toString())
        					 , new File(targetSb.append(sitemapPath).append(newCmsSiteVO.getSitePath()).append("_sitemap.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(sitemapPath).append(originCmsSiteVO.getSitePath()).append("_sitemap_code.jsp").toString())
        					 , new File(targetSb.append(sitemapPath).append(newCmsSiteVO.getSitePath()).append("_sitemap_code.jsp").toString()));
        
        //rsc
        String rscPath = new StringBuffer().append(realPath).append(File.separator).append("WEB-INF").append(File.separator).append("jsp").append(File.separator).append("jnit").append(File.separator).append("rsc").append(File.separator).toString();
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(rscPath).append(originCmsSiteVO.getSitePath()).append("_Uaddpage.jsp").toString())
        					 , new File(targetSb.append(rscPath).append(newCmsSiteVO.getSitePath()).append("_Uaddpage.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(rscPath).append(originCmsSiteVO.getSitePath()).append("_Uaddpage_code.jsp").toString())
        					 , new File(targetSb.append(rscPath).append(newCmsSiteVO.getSitePath()).append("_Uaddpage_code.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(rscPath).append(originCmsSiteVO.getSitePath()).append("_upPage.jsp").toString())
        					 , new File(targetSb.append(rscPath).append(newCmsSiteVO.getSitePath()).append("_upPage.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(rscPath).append(originCmsSiteVO.getSitePath()).append("_upPage_code.jsp").toString())
        					 , new File(targetSb.append(rscPath).append(newCmsSiteVO.getSitePath()).append("_upPage_code.jsp").toString()));
        
        //import
        String importPath = new StringBuffer().append(realPath).append(File.separator).append("cms").append(File.separator).append("import").append(File.separator).toString();
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(importPath).append(originCmsSiteVO.getSitePath()).append("_content_admin.jsp").toString())
        					 , new File(targetSb.append(importPath).append(newCmsSiteVO.getSitePath()).append("_content_admin.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(importPath).append(originCmsSiteVO.getSitePath()).append("_content_admin_code.jsp").toString())
        					 , new File(targetSb.append(importPath).append(newCmsSiteVO.getSitePath()).append("_content_admin_code.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(importPath).append(originCmsSiteVO.getSitePath()).append("_updateButton.jsp").toString())
        					 , new File(targetSb.append(importPath).append(newCmsSiteVO.getSitePath()).append("_updateButton.jsp").toString()));
        
        sourceSb.setLength(0);
        targetSb.setLength(0);
        FileCopy.copyDirectory(new File(sourceSb.append(importPath).append(originCmsSiteVO.getSitePath()).append("_updateButton_code.jsp").toString())
        					 , new File(targetSb.append(importPath).append(newCmsSiteVO.getSitePath()).append("_updateButton_code.jsp").toString()));
    }
}
