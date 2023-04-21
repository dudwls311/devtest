/**
 * @version 3.2.0.1
 */
package jnit.board.info.history;

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
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jnit.cms.AdminUtil;
import jnit.cms.CmsHelper;
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
public class JnitboardinfoHistoryController {

	public static Log log = LogFactory.getLog(JnitboardinfoHistoryController.class);
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "jnitboardinfoHistoryService")
    private JnitboardinfoHistoryService jnitboardinfoHistoryService;


    /**
	 * jnitboardinfo 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 JnitboardinfoHistoryVO
	 * @return "/jnit/tpl-temp/jnitboardinfo/JnitboardinfoList"
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/board/info/history/list.do")
    public String selectJnitboardinfoHistoryList(@ModelAttribute("searchVO") JnitboardinfoHistoryVO searchVO,
    		ModelMap model,
    	    HttpServletRequest request)
            throws Exception {

    	AdminUtil.setMenuId("m03040000");
    	if("".equals(CmsHelper.getSessionSiteId(request))){
    		return "/jnit/cms/sub4_msg-nosite";
    	}

    	String siteId = CmsHelper.getSessionSiteId(request);

    	searchVO.setBoardGroup(siteId);
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		searchVO.setSearchBoardGroup(siteId);
        List<?> jnitboardinfoList = jnitboardinfoHistoryService.selectJnitboardinfoHistoryList(searchVO);
        model.addAttribute("resultList", jnitboardinfoList);

        int totCnt = jnitboardinfoHistoryService.selectJnitboardinfoHistoryListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

        return "/jnit/cms/board/history/list";
    }
}
