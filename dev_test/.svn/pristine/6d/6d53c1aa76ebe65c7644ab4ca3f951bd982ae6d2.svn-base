/**
 * @version 3.2.0.1
 */
package jnit.board.info.statistic;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * @Class Name : jnitboardinfoStatisticDAO.java
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

@Repository("jnitboardinfoStatisticDAO")
public class JnitboardinfoStatisticDAO extends EgovComAbstractDAO {
    /**
	 * jnitboardinfo 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return jnitboardinfo 목록
	 * @exception Exception
	 */
    public List<?> selectJnitboardinfoStatisticList(JnitboardinfoStatisticVO searchVO) throws Exception {
        return list("jnitboardinfoStatisticDAO.selectJnitboardinfoStatisticList_D", searchVO);
    }
}
