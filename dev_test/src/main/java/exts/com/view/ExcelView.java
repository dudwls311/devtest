package exts.com.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import exts.com.util.EncodingUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.string.EgovDateUtil;
import net.sf.jxls.transformer.XLSTransformer;

public class ExcelView extends AbstractXlsxView {	

	//attribute
	public static final String ATTR_IS_MULTIPLE_SHEET = "isMultipleSheet";//multi sheet 여부 
	public static final String ATTR_SHEET_MAPS = "sheetMaps";//sheet 별 데이터 List
	public static final String ATTR_NEW_SHEET_NAMES = "newSheetNames";//sheet 명 List
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Boolean isXls = (Boolean)model.get("isXls");
		if(isXls == null)isXls = false;
		String ext = isXls?"xls":"xlsx";
		
		XLSTransformer transformer = new XLSTransformer();
		
		String root = Globals.CONTEXT_PATH;
		String templateFileName = root + 
				File.separator + "WEB-INF"+ 
				File.separator + "jsp"+ 
				File.separator + "exts"+
				File.separator + "exceltemplate"+ 
				File.separator + model.get("excelTemplateName")+"."+ext;
		
		String browser = EncodingUtil.getBrowser(request);
    	String encodeFileName = EncodingUtil.getDisposition(NullUtil.nullString((String)model.get("excelStoreName")), browser);
		
		String destFileName = encodeFileName + "_"+EgovDateUtil.getCurrentDateTimeAsString()+"."+ext;

		StringBuffer contentDisposition = new StringBuffer();
		contentDisposition.append("attachment;fileName=\"");
		contentDisposition.append(destFileName);
		contentDisposition.append("\";");
		response.setHeader("Content-Disposition", contentDisposition.toString());
		response.setContentType("application/x-msexcel");
		
		Boolean isMultipleSheet = (Boolean)model.get(ATTR_IS_MULTIPLE_SHEET);
		if(isMultipleSheet == null || !isMultipleSheet) {
			Workbook resultWorkbook = transformer.transformXLS(new FileInputStream(templateFileName), model);
			resultWorkbook.write(response.getOutputStream());
		}else {
			List<HashMap<String, Object>> sheetMaps = (List<HashMap<String, Object>>)model.get(ATTR_SHEET_MAPS);
			List<String> newSheetNames = (List<String>)model.get(ATTR_NEW_SHEET_NAMES);
			Workbook resultWorkbook = transformer.transformMultipleSheetsList(new FileInputStream(templateFileName), sheetMaps, newSheetNames, "result", new HashMap(), 0);
			resultWorkbook.write(response.getOutputStream());
		}
		
	}
}