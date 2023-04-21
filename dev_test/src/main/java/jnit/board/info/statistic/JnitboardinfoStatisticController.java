/**
 * @version 3.2.0.1
 */
package jnit.board.info.statistic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import jnit.board.info.JnitboardinfoDefaultVO;
import jnit.board.info.JnitboardinfoService;
import jnit.cms.AdminUtil;
import jnit.cms.CmsHelper;
import net.sf.json.JSONArray;
/**
 * @Class Name : JnitboardinfoController.java
 * @Description : Jnitboardinfo Controller class
 * @Modification Information
 *
 * @author Dael @ JNIT
 * @since 2013.01.21
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */

@Controller
public class JnitboardinfoStatisticController {

	public static Log log = LogFactory.getLog(JnitboardinfoStatisticController.class);
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "jnitboardinfoStatisticService")
    private JnitboardinfoStatisticService jnitboardinfoStatisticService;

    @Resource(name = "jnitboardinfoService")
    private JnitboardinfoService jnitboardinfoService;

    /**
	 * 게시판 통계 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 JnitboardinfoStatisticVO
	 * @return "/jnit/tpl-temp/jnitboardinfo/JnitboardinfoList"
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/cms/board/info/statistic/list.do")
    public String selectJnitboardinfoStatisticList(ModelMap model, HttpServletRequest request) throws Exception {

    	AdminUtil.setMenuId("m03050000");
    	if("".equals(CmsHelper.getSessionSiteId(request))){
    		return "/jnit/cms/sub4_msg-nosite";
    	}
    	String siteId = CmsHelper.getSessionSiteId(request);
    	
    	List<String> boardIds = new ArrayList<String>();
    	
    	JnitboardinfoDefaultVO infoSearchVO = new JnitboardinfoDefaultVO();
    	infoSearchVO.setSearchBoardGroup(siteId);
    	infoSearchVO.setPageYn("N");
    	List<EgovMap> infoResultList = jnitboardinfoService.selectJnitboardinfoList(infoSearchVO);
    	for(EgovMap infoMap : infoResultList) {
    		boardIds.add(infoMap.get("boardId").toString());
    	}
    	
    	JnitboardinfoStatisticVO statisticSearchVO = new JnitboardinfoStatisticVO();
//    	statisticSearchVO.setBoardGroup(siteId);
    	statisticSearchVO.setBoardIds(boardIds);
    	model.addAttribute("resultList", jnitboardinfoStatisticService.selectJnitboardinfoStatisticList(statisticSearchVO));

        return "/jnit/cms/board/statistic/list";
    }
    
    /**
	 * 게시판 통계 Ajax
	 * @param searchVO - 조회할 정보가 담긴 JnitboardinfoStatisticVO
	 * @return "/jnit/tpl-temp/jnitboardinfo/JnitboardinfoList"
	 * @exception Exception
	 */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/cms/board/info/statistic/ajax.do")
    public String selectJnitboardinfoStatisticAjax(ModelMap model, HttpServletRequest request) throws Exception {

    	if("".equals(CmsHelper.getSessionSiteId(request))){
    		model.addAttribute("json", new JSONArray());
    		return "/jnit/util/json";
    	}
    	String siteId = CmsHelper.getSessionSiteId(request);
    	
    	List<String> boardIds = new ArrayList<String>();
    	
    	JnitboardinfoDefaultVO infoSearchVO = new JnitboardinfoDefaultVO();
    	infoSearchVO.setSearchBoardGroup(siteId);
    	infoSearchVO.setPageYn("N");
    	List<EgovMap> infoResultList = jnitboardinfoService.selectJnitboardinfoList(infoSearchVO);
    	for(EgovMap infoMap : infoResultList) {
    		boardIds.add(infoMap.get("boardId").toString());
    	}
    	
    	JnitboardinfoStatisticVO statisticSearchVO = new JnitboardinfoStatisticVO();
//    	statisticSearchVO.setBoardGroup(siteId);
    	statisticSearchVO.setBoardIds(boardIds);
    	model.addAttribute("json", JSONArray.fromObject(jnitboardinfoStatisticService.selectJnitboardinfoStatisticList(statisticSearchVO)));

        return "/jnit/util/json";
    }
}
