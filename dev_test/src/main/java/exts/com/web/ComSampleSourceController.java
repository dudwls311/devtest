package exts.com.web;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import exts.com.enums.EnumMenuCd;
import exts.com.service.ComSampleSourceService;
import exts.com.util.SampleSourceUtil;
import exts.com.validator.ComSmplValidator;
import exts.com.vo.ComSampleSourceVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import jnit.cms.CmsHelper;

/**
 *  공통 컨트롤러 클래스
 * @author 
 * @since 2017.11.24
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.07.28            최초 생성
 *
 * </pre>
 */
@Controller
public class ComSampleSourceController extends ExtsAbstractController{

	@Resource(name = "comSampleSourceService")
	private ComSampleSourceService comSampleSourceService;

	@Resource(name = "comSmplValidator")
	private ComSmplValidator comSmplValidator;
	
	protected String getPkg(){return "exts/com/sampleSource";}

	/**
	 * 분기문
	 */
	@RequestMapping(value="/exts/com/sampleSource/index.do")
	public String index(
			@ModelAttribute	ComSampleSourceVO searchVO,
			HttpServletRequest request,
			ModelMap model) throws Exception{

		//공통 처리부		
		if("".equals(NullUtil.nullString(searchVO.getCssMode())))searchVO.setCssMode("write");		//기본 list로 포워딩		
		setIndexProcess(EnumMenuCd.SAMPLE_SOURCE, searchVO.getCssMode());				//분기공통처리
		request.setAttribute(REQUEST_ACTION_URL, "/exts/com/sampleSource/index.do");
		
		StringBuilder sb = new StringBuilder("forward:");
		sb.append("/exts/com/sampleSource/" + searchVO.getCssMode() + ".do");
		
		return sb.toString();
	}
	
	/**
	 * 추가/수정
	 */
	@RequestMapping(value="/exts/com/sampleSource/write.do")
	public String write(
			HttpServletRequest request,
			ModelMap model) throws Exception{
		throwDirect(request);
		
		model.addAttribute("defaultDbmsType", SampleSourceUtil.SAMPLE_SOURCE_DBMS_TYPE);
		model.addAttribute("defaultColumnType", SampleSourceUtil.SAMPLE_SOURCE_COLUMN_TYPE);
		model.addAttribute("defaultColumnSize", SampleSourceUtil.SAMPLE_SOURCE_COLUMN_SIZE);
		
		return "exts/com/sampleSource/write";
	}


	/**
	 * 추가 / 수정 처리
	 */
	@RequestMapping(value="/exts/com/sampleSource/writeActionJson.do")
	public String writeActionJson(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		throwDirect(request);

		boolean isSuccess = false;
		String msg = "";
		try{
			String[] dbColumnArr = request.getParameterValues("dbColumn");
			String[] dbCommentArr = request.getParameterValues("dbComment");
			String[] dbColumnTypeArr = request.getParameterValues("dbColumnType");
			String[] dbColumnSizeArr = request.getParameterValues("dbColumnSize");
			String[] dbColumnIsNotNullArr = request.getParameterValues("dbColumnIsNotNull");
			
			String path = Globals.CONTEXT_PATH+File.separator+"WEB-INF"+File.separator+"jsp"+File.separator+"exts"+File.separator+"sourcetemplate";
			String dbmsType = NullUtil.nullString(request.getParameter("dbmsType"));
			String inPkg = NullUtil.nullString(request.getParameter("inPkg"));
			String inFileName = NullUtil.nullString(request.getParameter("inFileName"));
			String inDescription = NullUtil.nullString(request.getParameter("inDescription"));
			String tableName = NullUtil.nullString(request.getParameter("tableName"));
			
			String zipPath = comSampleSourceService.makeTmpFile(path, dbmsType, inPkg, inFileName, inDescription, tableName, dbColumnArr, dbColumnTypeArr, dbColumnSizeArr, dbColumnIsNotNullArr, dbCommentArr);
			String fileName = zipPath.substring(zipPath.lastIndexOf(File.separator)+1);
			CmsHelper.downloadFileExec(request, response, zipPath, "", "", fileName);
			isSuccess = true;
		}catch(NullPointerException e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}catch(Exception e){
			log.error(e.getMessage());
			msg = "알 수 없는 에러";
		}
		
		return getResponseJsonView(model, isSuccess, msg);
	}
}
