package exts.com.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.Globals;
import exts.com.enums.EnumGrpCd;
import exts.com.service.ComCacheService;
import exts.com.util.FileMngUtil;
import exts.com.vo.ComCodeVO;
import exts.com.vo.ComExcelVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
public class ComController extends ExtsAbstractController{

	protected String getPkg(){return "exts/com";}

	@Resource(name = "comCacheService")
	private ComCacheService comCacheService;
	

	/**
	 * 엑셀 파싱 후 json형태로 리턴.
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/exts/com/parseExcelJson.do")
	public String parseExcelJson(
			final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		ComExcelVO resultList = new ComExcelVO();
		try {
			resultList = comService.parseExcel(multiRequest);
		}catch(IOException e) {
			log.error(e.getMessage());
			JSONArray ja = new JSONArray();
			JSONObject data = new JSONObject();
			data.put("0", "Excel Parse Error");
			ja.add(data);
			resultList.setMaxCell(1);
			resultList.setMaxRow(1);
			resultList.setData(ja);
		}catch(Exception e) {
			log.error(e.getMessage());
			JSONArray ja = new JSONArray();
			JSONObject data = new JSONObject();
			data.put("0", "Excel Parse Error");
			ja.add(data);
			resultList.setMaxCell(1);
			resultList.setMaxRow(1);
			resultList.setData(ja);
		}
		return getResponseJsonView(model, resultList);
	}
	
	/**
	 * 그룹코드로 공통코드 가져오기
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/exts/com/getCodeListByGrpCdJson.do")
	public String getCodeListGrpCdJson(
			@RequestParam(required = true)String grpCd,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		List<ComCodeVO> resultList = null;
		try {
			EnumGrpCd cd = null;
			for(EnumGrpCd enumGrpCd: EnumGrpCd.values()) {
				if(enumGrpCd.getCode().equals(grpCd))cd = enumGrpCd;
			}
			resultList = getCodeListByGrpCd(cd);
		}catch(IllegalArgumentException e) {
			log.debug(e.getMessage());
		}
		return getResponseJsonView(model, resultList);
	}

	/**
	 * 그룹코드 및 상위코드로 공통코드 가져오기
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/exts/com/getCodeListByGrpUprCdJson.do")
	public String getCodeListByGrpUprJson(
			@RequestParam(required = true)String grpCd,
			@RequestParam(required = true)String uprCd,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		List<ComCodeVO> resultList = null;
		try {
			EnumGrpCd cd = null;
			for(EnumGrpCd enumGrpCd: EnumGrpCd.values()) {
				if(enumGrpCd.getCode().equals(grpCd))cd = enumGrpCd;
			}
			resultList = getCodeListByGrpCd(cd,uprCd);
		}catch(IllegalArgumentException e) {
			log.debug(e.getMessage());
		}
		return getResponseJsonView(model, resultList);
	}

	/**
	 * 상위코드로 공통코드 가져오기
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/exts/com/getCodeListByUprCdJson.do")
	public String getCodeListByUprJson(
			@RequestParam(required = true)String uprCd,
			HttpServletRequest request,
			ModelMap model) throws Exception{
		List<ComCodeVO> resultList = null;
		try {/*
			EnumUprIndvlzCd cd = null;
			for(EnumUprIndvlzCd enumUprCd: EnumUprIndvlzCd.values()) {
				if(enumUprCd.getCode().equals(uprCd))cd = enumUprCd;
			}
			*/
			//코드가 많아서 String으로 변경.
			resultList = getCodeListByUprCd(uprCd);
		}catch(IllegalArgumentException e) {
			log.debug(e.getMessage());
		}
		return getResponseJsonView(model, resultList);
	}

	/**
	 * 샘플 파일 다운로드
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("/exts/com/sampleDownload.do")
	public void sampleDownload(
			@RequestParam(required = true)String fileNm,
			@RequestParam(required = true)String saveNm,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception{
		//샘플은 많지 않으므로 폴더 고정(파일명을 업무명으로 정의)
		String root = Globals.CONTEXT_PATH;
		
		String filePath = root + 
				File.separator + "WEB-INF"+ 
				File.separator + "jsp"+ 
				File.separator + "exts"+
				File.separator + "exceltemplate"+
				File.separator + "sample";
		
		FileMngUtil.downFile(request, response, filePath, fileNm, saveNm);
		
	}
}
