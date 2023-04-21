package exts.com.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.service.ComService;
import exts.com.util.ExcelUtil;
import exts.com.vo.ComCodeVO;
import exts.com.vo.ComExcelVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *  공통기능 ServiceImpl 클래스
 * @author JNIT
 * @since 2015.02.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *	수정일		수정자		수정내용
 *	-------		--------	---------------------------
 *  2015.02.18	meddogi1	 최초 생성
 *
 * </pre>
 */
@Service("comService")
public class ComServiceImpl extends ExtsAbstractServiceImpl implements ComService {
	
	/**
	 * 엑셀 파싱(xls)
	 */
	public ComExcelVO parseExcel(MultipartHttpServletRequest multiRequest) throws IOException {
		ComExcelVO excelVO = new ComExcelVO();
		JSONArray resultList = new JSONArray();

		int maxRow = 0;
    	int maxCell = 0;
    	
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			//TODO: FASOO DRM 해제 로직 추가필요
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			excelVO.setExcelNm(orginFileName);
			
			if (!"".equals(orginFileName) && (orginFileName.toLowerCase().endsWith(".xls")||orginFileName.toLowerCase().endsWith(".xlsx"))) {	

				ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
				Workbook wbT = null;
				
				if(orginFileName.toLowerCase().endsWith(".xls")) {
					wbT = new HSSFWorkbook(bis);
				}else if(orginFileName.toLowerCase().endsWith(".xlsx")) {
					wbT = new XSSFWorkbook(bis);
				}
            	Sheet sheet = wbT.getSheet(wbT.getSheetName(0));
            	Row row;
				for (int rowVal = 0; rowVal < sheet.getPhysicalNumberOfRows(); rowVal++) {
					maxRow++;
					
					row = sheet.getRow(rowVal); // row 가져오기
					JSONObject data = new JSONObject();
					if(row == null) {
						resultList.add(data);
						continue;
					}
					for(int cellVal = 0; cellVal < row.getLastCellNum(); cellVal++) {
						if(maxCell < cellVal) maxCell = cellVal;
						data.put(cellVal, ExcelUtil.getCellValue(row.getCell(cellVal)));	//각 셀번호로 변수 셋팅
					}
					resultList.add(data);
				}
			}
		}			
		excelVO.setMaxRow(maxRow);
		excelVO.setMaxCell((maxCell+1));
		excelVO.setData(resultList);
		return excelVO;
	}
	
	/**
	 * 멀티형 데이터 HashMap에 맞춰담기
	 * @param request
	 * @param reqParams
	 * @return
	 */
	public ComExcelVO setExcelData(String maxRow, String maxCell, String dataMap) {
		ComExcelVO comExcelVO = new ComExcelVO();
		comExcelVO.setMaxRow(NullUtil.nullInt(maxRow));
		comExcelVO.setMaxCell(NullUtil.nullInt(maxRow));
		
		JSONArray resultList = new JSONArray();
		try {
			resultList = JSONArray.fromObject(dataMap);
		}catch(JSONException e){
			log.error(e.getMessage());
			resultList = null;
		}catch(Exception e) {
			log.error(e.getMessage());
			resultList = null;
		}
		comExcelVO.setData(resultList);
		return comExcelVO;
	}

	public String getMessage(String code) {return super.getMessage(code, null);}
	
	public String getMessage(String code, String[] args) {return super.getMessage(code, args);}
	
	public EgovBizException processException(final String msgKey){return super.processException(msgKey);}
	
	public EgovBizException processException(final String msgKey,  final String[] msgArgs){return super.processException(msgKey, msgArgs);}

	public String getCd(List<ComCodeVO> list, String cdNm) {return super.getCd(list, cdNm);};
	
}
