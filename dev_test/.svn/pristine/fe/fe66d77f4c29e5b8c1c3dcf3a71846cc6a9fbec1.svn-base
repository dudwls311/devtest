package exts.com.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.vo.ComCodeVO;
import exts.com.vo.ComExcelVO;
import exts.com.vo.ComMbrVO;
import jnit.cms.mbr.JnitcmsmbrVO;



/**
 *  공통 Service 클래스
 * @author 
 * @since 2020.07.21
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *	수정일		수정자		수정내용
 *	-------		--------	---------------------------
 *   2020.07.21            최초 생성
 *
 * </pre>
 */
public interface ComService extends ExtsAbstractService {
	public static final String REQUEST_LOG_CNT_NO = "LOG_CNT_NO";
	
	//로그인 여부
	boolean isLogin();
	//로그인 아이디 가져오기
	String getLoginID();
	//로그인 그룹아이디 가져오기
//	String getLoginGroupID();
	//로그인 정보가져오기
	JnitcmsmbrVO getLoginVO() ;
	//추가로그인 정보가져오기
	ComMbrVO getLoginAdtVO() ;
	
	//관리자 여부
	boolean isAdmin();
	
	//직원 레벨 여부
	boolean isMbrLevelStaff();
	
	//유형관리(최고관리자) 여부
	boolean isMbrTypeSuper();
	//재단직원여부
	boolean isFoundStaff();
	//센터직원여부
	boolean isCenterStaff();
	//북한이탈주민 여부
	boolean isNtkrdf();
	//일반사용자 여부
	boolean isNormal();
		
	
	//엑셀 파싱(xls)
	ComExcelVO parseExcel(MultipartHttpServletRequest multiRequest) throws IOException;
	
	//멀티형 데이터 HashMap에 맞춰담기
	ComExcelVO setExcelData(String maxRow, String maxCell, String dataMap);
	
	
	//메세지 properties에 있는 메세지 불러오기
	String getMessage(String code);
	String getMessage(String code, String[] args);
	
	//메시지 properties값으로 EgovBizException 발생
	EgovBizException processException(final String msgKey);
	EgovBizException processException(final String msgKey,  final String[] msgArgs);
	

	/**
	 * 코드명으로 코드값 가져오기
	 * @param list
	 * @param cdNm
	 * @return
	 */
	String getCd(List<ComCodeVO> list, String cdNm); 
}