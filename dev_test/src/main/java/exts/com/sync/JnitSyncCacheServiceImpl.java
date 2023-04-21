/**
 * @version 3.2.0.1
 */
package exts.com.sync;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import exts.com.service.ComCacheService;
import jnit.cms.sync.JnitSyncResponseService;

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

@Service("jnitSyncCacheService")
public class JnitSyncCacheServiceImpl implements JnitSyncResponseService {

	@Resource(name = "comCacheService")
	private ComCacheService comCacheService;
	
    @Override
    public RESPONSE sync(Map<String, String> m) {
    	comCacheService.reloadCodeList();
    	return RESPONSE.SUCCESS;
    }
}
