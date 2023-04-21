/**
 * @version 3.2.0.1
 */
package jnit.cms.sync;

import java.util.Map;

import org.springframework.stereotype.Service;

import jnit.cms.LoginManager;

/**
 * @Class Name : JnitSyncResponseService.java
 * @Description : JnitSyncResponseService class
 * @Modification Information
 *
 * @author JnitSyncResponseService
 * @since 2022.09.16
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("jnitSyncLoginService")
public class JnitSyncLoginServiceImpl implements JnitSyncResponseService {

    @Override
    public RESPONSE sync(Map<String, String> commandMap) {
    	if(commandMap == null) return RESPONSE.FAIL;
    	if("login".equals(commandMap.get("loginType"))) {
    		//로그인 세션
    		LoginManager.setSession(commandMap.get("mbrId"), commandMap.get("mbrCreationTime"));
    	}else if("logout".equals(commandMap.get("loginType"))) {
    		//로그아웃 세션
        	LoginManager.logout(commandMap.get("mbrId"));
    	}
    	return RESPONSE.SUCCESS;
    }
}
