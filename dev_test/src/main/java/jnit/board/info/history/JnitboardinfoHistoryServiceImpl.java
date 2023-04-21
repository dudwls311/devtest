/**
 * @version 3.2.0.1
 */
package jnit.board.info.history;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : JnitboardinfoServiceImpl.java
 * @Description : Jnitboardinfo Business Implement class
 * @Modification Information
 *
 * @author Dael @ JNIT
 * @since 2013.01.21
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("jnitboardinfoHistoryService")
public class JnitboardinfoHistoryServiceImpl extends EgovAbstractServiceImpl implements
        JnitboardinfoHistoryService {

    @Resource(name="jnitboardinfoHistoryDAO")
    private JnitboardinfoHistoryDAO jnitboardinfoHistoryDAO;

	/**
	 * jnitboardinfoHistory을 등록한다.
	 * @param vo - 등록할 정보가 담긴 JnitboardinfoHistoryVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public void insertJnitboardinfoHistory(JnitboardinfoHistoryVO vo) throws Exception {
    	jnitboardinfoHistoryDAO.insertJnitboardinfoHistory(vo);
    }

    /**
	 * jnitboardinfoHistory 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return jnitboardinfo 목록
	 * @exception Exception
	 */
    public List<?> selectJnitboardinfoHistoryList(JnitboardinfoHistoryVO searchVO) throws Exception {
        return jnitboardinfoHistoryDAO.selectJnitboardinfoHistoryList(searchVO);
    }

    /**
	 * jnitboardinfoHistory 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return jnitboardinfo 총 갯수
	 * @exception
	 */
    public int selectJnitboardinfoHistoryListTotCnt(JnitboardinfoHistoryVO searchVO) {
		return jnitboardinfoHistoryDAO.selectJnitboardinfoHistoryListTotCnt(searchVO);
	}
    
}
