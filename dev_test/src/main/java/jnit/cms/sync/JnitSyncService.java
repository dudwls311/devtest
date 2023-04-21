package jnit.cms.sync;

import java.util.Map;

/**
 * @Class Name : JnitSyncService.java
 * @Description : 데이터 동기화 Service
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface JnitSyncService {
    
	public static final String SERVICE_NAME = "serviceName";
	
	/**
	 * 동기화 처리
	 * @param serviceName
	 * @param m
	 */
	void syncRequest(String serviceName, Map<String, String> commandMap);
}
