/**
 * @version 3.2.0.1
 */
package jnit.cms.sitecopy;

import javax.servlet.http.HttpServletRequest;

import jnit.cms.site.JnitcmssiteVO;

/**
 * @Class Name : JnitcmssiteService.java
 * @Description : Jnitcmssite Business class
 * @Modification Information
 *
 * @author JNIT
 * @since 2012.06.01
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface JnitCmsSitecopyService {
	
    /**
	 * JNITCMSSITE을 수정한다.
	 * @param vo - 수정할 정보가 담긴 JnitcmssiteVO
	 * @return void형
	 * @exception Exception
	 */
    void siteCopy(String originSiteId, JnitcmssiteVO newCmsSiteVO, HttpServletRequest request) throws Exception;
}
