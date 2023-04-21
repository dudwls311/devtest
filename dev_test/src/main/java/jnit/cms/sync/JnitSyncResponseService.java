package jnit.cms.sync;

import java.util.Map;

/**
 * @Class Name : JnitSyncResponseService.java
 * @Description : 서버간 동기화 응답  interface
 * @Modification Information
 * 
 * @author
 * @since 2022. 09.16
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface JnitSyncResponseService {
    public enum RESPONSE{SUCCESS, FAIL};
    
    /**
     * 동기화 처리
     * @param m 서버 동기화를 위해 넘어온 파라미터 key, value 셋
     * @return
     */
    RESPONSE sync(Map<String, String> commandMap);
}
