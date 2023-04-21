package exts.com.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import exts.com.enums.EnumMenuCd;
import exts.com.service.ComMbrLogService;
import exts.com.validator.ComMbrLogValidator;
import exts.com.vo.ComMbrLogVO;


/**
 * @Class Name : ComMbrLogController.java
 * @Description : 회원로그내역 Controller
 * @Modification Information
 * 
 * @author
 * @since 2022.11.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class ComMbrLogController extends ExtsAbstractController{
	
	protected String getPkg(){return "exts/com/mbrLog";}
	
	@Resource(name = "comMbrLogService")
	private ComMbrLogService comMbrLogService;

	@Resource(name = "comMbrLogValidator")
	private ComMbrLogValidator comMbrLogValidator;
	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/com/mbrLog/index.do")
	public String index(
			@ModelAttribute	ComMbrLogVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCmlMode())))searchVO.setCmlMode("list");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.COM_USER_MENU_AUTH, searchVO.getCmlMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/com/mbrLog/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/com/mbrLog/" + searchVO.getCmlMode() + ".do");
		
		return sb.toString();
	}


	/**
	 * 리스트
	 */
	@RequestMapping(value="/exts/com/mbrLog/list.do")
	public String list(
			@ModelAttribute("searchVO")	ComMbrLogVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		if("Y".equals(request.getParameter(REQUEST_EXCEL_YN))){
			
			searchVO.setRecordCountPerPage(0);
			model.addAttribute("resultList",comMbrLogService.selectComMbrLogList(searchVO));
			return getResponseExcelView(model, "mbrLog", "회원로그내역");
		}else {

			//기본값으로 스프링빈에 설정된 값 로드
			if(searchVO.getPageUnit() == 0)searchVO.setPageUnit(getDefaultPageUnit());
			if(searchVO.getPageSize() == 0)searchVO.setPageSize(getDefaultPageSize());
		
			//총갯수
			int totalRecordCount = comMbrLogService.selectComMbrLogTot(searchVO);

	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
			paginationInfo.setPageSize(searchVO.getPageSize());
	    	paginationInfo.setTotalRecordCount(totalRecordCount);
			
			searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			//전체가져올때
			if(searchVO.getPageUnit() == -1)searchVO.setRecordCountPerPage(0);
			
			model.addAttribute("paginationInfo",paginationInfo);
			model.addAttribute("resultCnt",totalRecordCount);
			model.addAttribute("resultList",comMbrLogService.selectComMbrLogList(searchVO));
		}
		
		
		return "exts/com/mbrLog/mbrLogList";
	}
}
