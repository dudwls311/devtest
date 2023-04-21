package exts.com.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import exts.com.vo.ComMbrLogVO;

/**
 * @Class Name : ComMbrLogService.java
 * @Description : 회원로그내역 Service
 * @Modification Information
 * 
 * @author
 * @since 2022.11.23
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComMbrLogService extends ExtsAbstractService {
    
	/**
	 * 리스트
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<ComMbrLogVO> selectComMbrLogList(ComMbrLogVO searchVO);

	/**
	 * 총갯수
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	Integer selectComMbrLogTot(ComMbrLogVO searchVO);

	/**
	 * Pk데이터
	 * 
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	ComMbrLogVO selectComMbrLog(ComMbrLogVO searchVO);

	/**
	 * 추가/수정
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void writeComMbrLog(ComMbrLogVO searchVO) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param searchVO
	 * @throws Exception
	 */
	void deleteComMbrLog(ComMbrLogVO searchVO) throws EgovBizException;
}
