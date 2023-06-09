package exts.com.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.vo.ComMbrVO;

/**
 * @Class Name : ComMbrService.java
 * @Description : 관리 회원 Service
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComMbrService extends ExtsAbstractService {
	public final static String SESSION_VO = "loginVO";//로그인세션VO
	public final static String SESSION_ADT_VO = "loginAdtVO";//회원추가정보세션VO
	public final static String SESSION_LMENU_LIST = "lmenuList";//1depth메뉴리스트
	public final static String SESSION_MMENU_LIST = "mmenuList";//2depth메뉴리스트
	public final static String SESSION_SMENU_LIST = "smenuList";//3depth메뉴리스트
	public final static String SESSION_TMENU_LIST = "tmenuList";//4depth메뉴리스트
	public final static String SESSION_MY_MAIN = "myMain";//나의 메뉴권한이 있는 첫번째 메뉴URL

	/**
	 * 로그인처리 - 해당 회원타입만
	 * 
	 * @param searchVO
	 * @param types
	 * @return 세션에 저장되어 있는 메뉴중 최초 대메뉴
	 * @throws Exception
	 */
	String login(ComMbrVO searchVO, String[] types) throws Exception;
	
	/**
	 * 로그인처리
	 * 
	 * @param searchVO
	 * @return 세션에 저장되어 있는 메뉴중 최초 대메뉴
	 * @throws Exception
	 */
	String login(ComMbrVO searchVO) throws Exception;

	/**
	 * 로그아웃처리
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void logout() throws Exception;
	
	

	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<ComMbrVO> selectComMbrList(ComMbrVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectComMbrTot(ComMbrVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	ComMbrVO selectComMbr(ComMbrVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeComMbr(ComMbrVO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteComMbr(ComMbrVO searchVO) throws Exception;

	/**
	 * 해당 데이터를 볼 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	public boolean isViewable(ComMbrVO searchVO);

	/**
	 * 해당 데이터를 수정할 수 있는 권한이 있는지 체크
	 * @param searchVO
	 * @return
	 */
	boolean isModifiable(ComMbrVO searchVO);
	
	/**
	 * 비밀번호 틀린횟수 초기화
	 * @param searchVO
	 * @return
	 */
	public void initWrongPwCnt(ComMbrVO searchVO)throws Exception;

	/**
	 * 인증서 정보 초기화
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	public void initDnComMbr(ComMbrVO searchVO) throws EgovBizException;
	
	/**
	 * 비밀번호 변경
	 * @param searchVO
	 * @param orPw
	 * @return
	 */
	void changePw(ComMbrVO searchVO, String orPw)throws Exception;
	
	/**
	 * 공인인증서 등록
	 * @param searchVO
	 * @param gpki
	 * @return
	 */
	void updateDn(ComMbrVO searchVO)throws Exception;

	/**
	 * id중복확인
	 * 
	 * @param searchVO
	 */
	boolean idCheck(String id) throws Exception ;
	
	/**
	 * 변경이력 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	List<ComMbrInfoChngVO> selectComMbrChngInfoList(ComMbrInfoChngVO searchVO) ;
	 */
}
