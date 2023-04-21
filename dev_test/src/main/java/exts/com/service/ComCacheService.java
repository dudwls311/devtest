package exts.com.service;

import java.util.HashMap;
import java.util.List;

import exts.com.vo.ComCodeVO;

/**
 * @Class Name : ComCacheService.java
 * @Description : 캐쉬 Service
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
public interface ComCacheService extends ExtsAbstractService {
	
	public enum ENUM_CACHE_KEY{
		COM_CODE, //공통개별코드
		SPR_CTG_MNG, //지원범주별 관리자
		SPR_MNG, //지원별 관리자
	}
	
	//개별코드 리로드(db에서 데이터 가져와서 캐시에 저장)
	public void reloadCodeList();
	//개별코드 리스트 가져오기(캐시에서)
	public List<ComCodeVO> getAllCodeList();
	//캐시 전체 데이터 리턴
	public HashMap<ENUM_CACHE_KEY, Object> getData();
	//키에 해당하는 캐시 데이터 리턴
	public Object getCache(ENUM_CACHE_KEY key);
	//전체 캐시 삭제
	public void clearAllCache();
}
