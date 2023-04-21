/**
 * @version 3.2.0.1
 */
package exts.com.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;
import egovframework.com.utl.fcc.service.NullUtil;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import jnit.filego.enums.JnitFilegoFileType;
/**
 * @Class Name  : EgovFileUploadUtil.java
 * @Description : Spring 기반 File Upload 유틸리티
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.08.26       한성곤                  최초 생성
 *
 * @author 공통컴포넌트 개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 * @see
 */

public class ComFileUploadUtil extends EgovFormBasedFileUtil {
	
	public static Log log = LogFactory.getLog(ComFileUploadUtil.class);
	
	private static String type;
	private static String orginFileNm;
	private static final String [] WHITE_FILE_EXT = {Globals.ACCESS_FILE_EXT};
	public final static String BASE_FOLDER = "EXTS";		//upload 폴더의 하위의 폴더명

	
	/**
     * 파일을 Upload 처리 후 Form filename Map을 생성하여 리턴한다.
     * @param request
     * @param fileType
     * @param fileAccessNm
     * @param uploadSubPath1
     * @return
     * @throws Exception
     */
    public static List<EgovFormBasedFileVo> uploadFormFiles(HttpServletRequest request, String fileAccessNm, String uploadSubPath1) throws Exception {
    	return uploadFormFiles(request, fileAccessNm, uploadSubPath1, null, null);
    }
    /**
     * 파일을 Upload 처리 후 Form filename Map을 생성하여 리턴한다.
     * @param request
     * @param fileType
     * @param fileAccessNm
     * @param uploadSubPath1
     * @param uploadSubPath2
     * @return
     * @throws Exception
     */
    public static List<EgovFormBasedFileVo> uploadFormFiles(HttpServletRequest request, String fileAccessNm, String uploadSubPath1, String uploadSubPath2, String[] accessExt) throws Exception {
		List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();
		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
	
		//upload 하위 폴더 경로 설정
		StringBuffer serverSubPathSb = new StringBuffer();
		serverSubPathSb.append(BASE_FOLDER);
		if(!"".equals(NullUtil.nullString(uploadSubPath1))) serverSubPathSb.append(SEPERATOR).append(uploadSubPath1);
		if(!"".equals(NullUtil.nullString(uploadSubPath2))) serverSubPathSb.append(SEPERATOR).append(uploadSubPath2);
		serverSubPathSb.append(SEPERATOR).append(getTodayString());
		
		final Map<String, MultipartFile> files = mptRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			MultipartFile mFile = entry.getValue();
			if(mFile.getName().indexOf(fileAccessNm) == -1) continue;
			
	
			EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
	
			String tmp = mFile.getOriginalFilename();
			if (tmp.lastIndexOf("\\") >= 0) {
				tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
			}
			
			vo.setFileName(tmp);
			vo.setContentType(mFile.getContentType());
			vo.setServerSubPath(serverSubPathSb.toString());
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			vo.setPhysicalName(dayTime.format(cal.getTime())+(int)(Math.random()*100));
			vo.setPhysicalName(("".equals(NullUtil.nullString(orginFileNm))) ? vo.getPhysicalName() : orginFileNm);
			vo.setSize(mFile.getSize());
			
			boolean fileExtension = false;
			String [] splitFileName = tmp.split("\\.");
			String fileExp = splitFileName[splitFileName.length-1];
			vo.setExtnName(fileExp);
			
			if(accessExt == null) accessExt = WHITE_FILE_EXT;
			
			for(int i = 0; i<accessExt.length; i++){
				if(accessExt[i].contains(fileExp.toLowerCase())){
					fileExtension = true;/*허용리스트에 포함되어 있다면 true*/
					break;
				}
			}
			
			if(tmp.lastIndexOf(".") >= 0) {
				vo.setPhysicalName(vo.getPhysicalName() + tmp.substring(tmp.lastIndexOf(".")));
			}
			if(fileExtension){
				if (mFile.getSize() > 0) {
					String uploadFileFullPath = EgovWebUtil.filePathBlackList(Globals.UPLOAD_PATH+SEPERATOR+("noSubPath".equals(type) ? "" : vo.getServerSubPath()+SEPERATOR )+vo.getPhysicalName());
					saveFile(mFile.getInputStream(), new File(uploadFileFullPath), JnitFilegoFileType.UPLOAD);
					list.add(vo);
				}
			}else{
				//허용되지 않은 파일을 업로드 했을때 오류 유도(사용자 페이지에서 확장자 체크하는 스크립트를 변조해서 들어왔을때 발생)
				list = null;
				throw new EgovBizException("업로드 불가능한 파일이 포함되어 있습니다. [ "+tmp+" ]\r\n"+fileExp+" 확장자는 첨부가 불가능합니다.");
			}
		}
	return list;
    }
    
    /**
     * 첨부파일 Null값 체크
     * @param request
     * @param fileAccessNm
     * @return
     */
    public static boolean uploadFormFilesValidate(HttpServletRequest request, String fileAccessNm) {
    	return uploadFormFilesValidate(request, fileAccessNm, WHITE_FILE_EXT);
    }
    
    /**
     * 첨부파일 Null값 체크
     * @param request
     * @param fileAccessNm
     * @param accessExt
     * @return
     */
    public static boolean uploadFormFilesValidate(HttpServletRequest request, String fileAccessNm, String[] accessExt) {
		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
		final Map<String, MultipartFile> files = mptRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			MultipartFile mFile = entry.getValue();
			if(mFile.getName().equals(fileAccessNm)) return true;
		}
		return false;
    }
}
