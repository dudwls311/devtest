/**
 * @version 3.2.0.1
 */
package jnit.board.info.history;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * @Class Name : jnitboardinfoHistoryDAO.java
 * @Description : Jnitboardinfo DAO Class
 * @Modification Information
 *
 * @author Dael @ JNIT
 * @since 2013.01.21
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("jnitboardinfoHistoryDAO")
public class JnitboardinfoHistoryDAO extends EgovComAbstractDAO {

	/**
	 * jnitboardinfo을 등록한다.
	 * @param vo - 등록할 정보가 담긴 JnitboardinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertJnitboardinfoHistory(JnitboardinfoHistoryVO vo) throws Exception {
        return (String)insert("jnitboardinfoHistoryDAO.insertJnitboardinfoHistory_S", vo);
    }
    
    /**
	 * jnitboardinfo 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return jnitboardinfo 목록
	 * @exception Exception
	 */
    public List<?> selectJnitboardinfoHistoryList(JnitboardinfoHistoryVO searchVO) throws Exception {
        return list("jnitboardinfoHistoryDAO.selectJnitboardinfoHistoryList_D", searchVO);
    }

    /**
	 * jnitboardinfo 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return jnitboardinfo 총 갯수
	 * @exception
	 */
    public int selectJnitboardinfoHistoryListTotCnt(JnitboardinfoHistoryVO searchVO) {
        return (Integer)select("jnitboardinfoHistoryDAO.selectJnitboardinfoHistoryListTotCnt_S", searchVO);
    }
}
