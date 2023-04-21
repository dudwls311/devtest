package jnit.cron;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import jnit.cms.handler.CmsHandler;
import jnit.cms.menu.JnitcmsmenuDefaultVO;
import jnit.cms.menu.JnitcmsmenuService;
import jnit.cms.menu.JnitcmsmenuVO;
import jnit.cms.site.JnitcmssiteDefaultVO;
import jnit.cms.site.JnitcmssiteService;
import jnit.cms.site.JnitcmssiteVO;

public class JnitCronSiteBuild {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="jnitcmssiteService")
    private JnitcmssiteService jnitcmssiteService;
	
	@Resource(name = "jnitcmsmenuService")
    private JnitcmsmenuService jnitcmsmenuService;
	
	/**
	 * 사이트 빌드
	 */
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "${Globals.CRON.SITE.BUILDER.TIMER}")
	public void excute() {
		try {
		
			String rebuild = "true";
			JnitcmssiteDefaultVO siteSearchVO = new JnitcmssiteDefaultVO();
	    	siteSearchVO.setFirstIndex(0);
	    	siteSearchVO.setRecordCountPerPage(100);
	    	List<EgovMap> siteResultList = jnitcmssiteService.selectJnitcmssiteList(siteSearchVO);
	    	
	    	JnitcmssiteVO siteVO = null;
	    	for(EgovMap egovSiteMap : siteResultList) {
	    		siteVO = new JnitcmssiteVO();
	    		String siteId = egovSiteMap.get("siteId").toString();
	    		
	    		siteVO.setSiteId(siteId);
	    		siteVO = jnitcmssiteService.selectJnitcmssite(siteVO);

	    		JnitcmsmenuDefaultVO searchVO = new JnitcmsmenuDefaultVO();
	    		
	    		searchVO.setSiteId(siteVO.getSiteId());
	    		if("true".equals(rebuild)) searchVO.setSearchCondition("4");
	    	    List<EgovMap> jnitcmsmenuList = jnitcmsmenuService.selectJnitcmsmenuListAll(searchVO);

	    	    HashMap<String, JSONArray> childList = new HashMap<String, JSONArray>();

	    	    String etcDir = Globals.CONTEXT_PATH + "/" + siteVO.getSitePath() + "/_etc";
	    	    File etcDirF = new File(etcDir);
	    	    if(etcDirF.exists() == false) {
	    	    	etcDirF.mkdirs();
	    	    }
	    	    if(jnitcmsmenuList.size() > 0) {
	    		    EgovMap menuVO;

	    		    int maxDepth = 0;

	    		    for(int i=0; i<jnitcmsmenuList.size(); i++) {
	    		    	menuVO = (EgovMap)jnitcmsmenuList.get(i);
	    		    	int cDepth = Integer.parseInt( menuVO.get("depth").toString() );
	    		    	if(maxDepth < cDepth) maxDepth = cDepth;

	    		    	JnitcmsmenuVO getMenuVO = new JnitcmsmenuVO();
	    		    	getMenuVO.setMenuId(menuVO.get("menuId").toString());
	    		    	getMenuVO.setDepth(Integer.valueOf(menuVO.get("depth").toString()));
	    		    	getMenuVO.setPid(NullUtil.nullString(""+menuVO.get("pid"))); //NullPointer 처리
	    		    	getMenuVO.setSiteId(menuVO.get("siteId").toString());
	    		    	getMenuVO.setMenuPath(menuVO.get("menuPath").toString());
	    		    	
	    		    	try {
	    		    		getMenuVO.setAdmMbrId(menuVO.get("admMbrId").toString());
	    		    	} catch (NullPointerException e){
	    					log.error(e.getMessage());
	    				} catch (Exception e) {
	    					log.error(e.getMessage());
	    				}
	    		    	String linkurl = (String) menuVO.get("linkurl");
	    		    	String linktgt = (String) menuVO.get("linktgt");
	    		    	if(NullUtil.nullString(linkurl).equals("null")){
	    			    	linkurl = "";
	    			    }
	    		    	if(NullUtil.nullString(linktgt).equals("null")){
	    		    		linktgt = "";
	    			    }

	    		    	JSONObject obj = new JSONObject();
	    		    		obj.put("title", menuVO.get("menuNm"));
	    				    obj.put("menuid", menuVO.get("menuId"));
	    				    obj.put("menudepth", menuVO.get("depth"));
	    				    obj.put("menumethod", menuVO.get("method"));
	    				    obj.put("menuislink", menuVO.get("islink"));
	    				    obj.put("menulinkurl", linkurl);
	    				    obj.put("menulinktgt", linktgt);
	    				    obj.put("menutplid", menuVO.get("tplId"));
	    				    obj.put("menuadmid", menuVO.get("admMbrId"));
	    				    obj.put("hDel", menuVO.get("hDel"));
	    				    obj.put("lDel", menuVO.get("lDel"));
	    				    obj.put("sDel", menuVO.get("sDel"));
	    				    obj.put("cntId", menuVO.get("cntId"));
	    				    
	    				    if(rebuild != null){
	    				    	String menupath = jnitcmsmenuService.makeMenuDirectoryName(getMenuVO, siteVO.getSitePath());
	    				    	if(NullUtil.nullString(menupath).equals("null")||NullUtil.nullString(menupath).equals("")){
	    				    		menupath = "";
	    				    	}
	    				    	obj.put("menupath", menupath);
	    				    }
	    				    String defMenu = NullUtil.nullString(""+menuVO.get("defMenu"));

	    			    	if(NullUtil.nullString(defMenu).equals("null")){
	    			    		defMenu = "";
	    			    	}
	    				    obj.put("defaultmenu", defMenu);
	    				    obj.put("key", menuVO.get("menuId"));
	    				    obj.put("expand", true);
	    				    int iscnt = Integer.parseInt(menuVO.get("iscnt").toString());

	    				    if(iscnt == 0) {//menuVO.get("iscnt").equals(0)
	    				    	obj.put("isFolder", true);

	    				    	if(rebuild != null) {
	    				    		int isdel = Integer.parseInt(menuVO.get("isdel").toString());
	    					    	if(isdel == 0) {  // menuVO.get("isdel")..equals(0)
	    					    		String fileDir = Globals.CONTEXT_PATH + "/" + obj.get("menupath");

	    					    		File fileDirFS = new File(fileDir);
	    					    		if(fileDirFS.exists() == false) {
	    					    			fileDirFS.mkdirs();
	    					    		}
	    					    		int islink = Integer.parseInt(menuVO.get("islink").toString());

	    					    		if(islink == 1) { // menuVO.get("islink")..equals(1)
	    					    			CmsHandler.writeFile(fileDir + "/index.jsp", "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><% response.sendRedirect(\""+obj.get("menulinkurl")+"\"); %>");
	    					    		}else {
	    					    			String menuPath = "";
	    					    			if("".equals(NullUtil.nullString(String.valueOf(menuVO.get("redirectUrl"))))){
	    					    				JnitcmsmenuVO menuVo2 = new JnitcmsmenuVO();
	    					    				menuVo2.setSiteId(siteVO.getSiteId());
	    					    				menuVo2.setMenuId(menuVO.get("menuId").toString());
	    					    				menuVo2 = jnitcmsmenuService.selectJnitcmsmenu(menuVo2);
	    				    					menuPath = jnitcmsmenuService.makeMenuRedirect(menuVo2);

	    					    				if("noindex.jsp".equals(menuPath)){
	    					    					String message = "해당 메뉴 하부에 등록된 콘텐츠 페이지가 존재하지 않거나,\\n\\n모든 하부콘텐츠가 메뉴 메뉴설정에 비노출설정이 되어\\n\\n있어 페이지를 정상적으로 호출할수 없습니다.\\n\\n관리자모드에 메뉴/콘텐츠관리를 확인해 주시기 바랍니다.";
	    					    					CmsHandler.writeFile(fileDir + "/noindex.jsp", "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><script type=\"text/javascript\">alert(\""+message+"\");</script>");
	    					    				}
	    					    			}else{
	    					    				String contextPath = Globals.CONTEXT_PATH;
	    					    				menuPath = contextPath + menuVO.get("redirectUrl").toString();
	    					    			}
	    					    			obj.put("redirect", menuPath);
	    				    				CmsHandler.writeFile(fileDir + "/index.jsp", "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><% response.sendRedirect(\""+obj.get("redirect")+"\"); %>");
	    					    		}
	    					    	}
	    				    	}
	    				    }else if(iscnt == 1){
	    				    	if(rebuild != null) {
	    				    		JnitcmsmenuVO pageMenuVO = new JnitcmsmenuVO();
	    					    	pageMenuVO.setSiteId(siteVO.getSiteId());
	    					    	pageMenuVO.setMenuId(String.valueOf(menuVO.get("menuId")));
	    					    	pageMenuVO = jnitcmsmenuService.selectJnitcmsmenu(pageMenuVO);
	    					    	String menuLinkNames = (String)jnitcmsmenuService.makeMenuLinkNames(pageMenuVO, "Home", siteVO.getSitePath());
	    					    	String menuNavTitle = (String)jnitcmsmenuService.makeMenuNavTitleNames(pageMenuVO, siteVO.getSiteNm(), siteVO.getSitePath());
	    					    	String menuPid = (String)jnitcmsmenuService.makeMenuPid(pageMenuVO, "return");

	    					    	obj.put("pageLinkNav", menuLinkNames);
	    					    	obj.put("pageNavTitle", menuNavTitle);
	    					    	obj.put("pid", menuPid);
	    				    	}
	    				    }

	    				    String pid = (String)menuVO.get("pid");
	    					pid = (pid == null || pid.equals("") ? "ROOT" : pid);

	    		    		JSONArray cList = (JSONArray)childList.get(pid);
	    		    		if(cList == null) {
	    		    			cList = new JSONArray();
	    		    			childList.put(pid, cList);
	    		    		}
	    		    		cList.add(obj);
	    		    }

	    		    if(!childList.toString().contains("ROOT")){
	    		    	JSONArray rootList = new JSONArray();
	    		    	childList.put("ROOT", rootList);
	    		    }

	    	    	Set<String> keySet = childList.keySet();
	    	    	Object[] keys = keySet.toArray();
	    		    int scanDepth = maxDepth - 1;
	    		    while(scanDepth > -1) {
	    		    	for(int i=0; i<keys.length; i++) {
	    		    		JSONArray cObjs = (JSONArray)childList.get(keys[i]);
	    		    		for(int i2=0; i2<cObjs.size(); i2++) {
	    		    			JSONObject cObj = (JSONObject)cObjs.get(i2);
	    		    			if(Integer.parseInt( cObj.get("menudepth").toString() ) == scanDepth) {
	    			    			if(childList.get(cObj.get("menuid").toString()) != null) {
	    			    				cObj.put("children", childList.get(cObj.get("menuid").toString()).clone());
	    			    				JSONArray cObjs2 = (JSONArray) childList.get(cObj.get("menuid").toString());
	    			    				cObjs2.clear();
	    			    			}
	    		    			}
	    		    		}
	    		    	}
	    		    	scanDepth--;
	    		    }
	    	    	Boolean isOnDrop = true;
	    	    	for(int i=0; i<keys.length; i++) {
	    	    		JSONArray cObjs2 = (JSONArray)childList.get(keys[i]);
	    	    		if(!"ROOT".equals(keys[i])){
	    	    			if(!childList.toString().contains("\"menuid\":\""+keys[i]+"\"")){
	    	    				for(int i3=0; i3<cObjs2.size(); i3++){
	    			    			JSONObject cObj2 = (JSONObject)cObjs2.get(i3);
	    			    			JSONArray ROOT = (JSONArray)childList.get("ROOT");
	    		    				ROOT.add(cObj2);
	    		    				isOnDrop = false;
	    			    		}
	    	    			};
	    	    		}
	    	    	}
	    		    if(rebuild != null) CmsHandler.writeFile(etcDir+"/menu.json", childList.get("ROOT").toJSONString());
	    	    }
	    	}
		}catch(NumberFormatException e) {
			log.error(e.getMessage());
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
}
