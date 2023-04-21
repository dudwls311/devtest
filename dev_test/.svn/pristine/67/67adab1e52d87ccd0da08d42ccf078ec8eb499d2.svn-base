/**
 * @version 3.2.0.1
 */
package jnit.board.info.statistic;

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

@Service("jnitboardinfoStatisticService")
public class JnitboardinfoStatisticServiceImpl extends EgovAbstractServiceImpl implements
        JnitboardinfoStatisticService {

    @Resource(name="jnitboardinfoStatisticDAO")
    private JnitboardinfoStatisticDAO jnitboardinfoStatisticDAO;


    /**
	 * jnitboardinfoStatistic 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return jnitboardinfo 목록
	 * @exception Exception
	 */
    public List<?> selectJnitboardinfoStatisticList(JnitboardinfoStatisticVO searchVO) throws Exception {
        return jnitboardinfoStatisticDAO.selectJnitboardinfoStatisticList(searchVO);
    }
    
}
