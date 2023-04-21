package jnit.cms.sync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import egovframework.com.cmm.service.Globals;

/**
 * @Class Name : JnitSyncServiceImpl.java
 * @Description : 데이터 동기화ServiceImpl
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Service("jnitSyncService")
public class JnitSyncServiceImpl implements JnitSyncService {
	
	// ////////////////////// Resource 선언 영역 ///////////////////////////////////////////////////////////////////
	
	// ////////////////////// Resource 선언 영역 끝 /////////////////////////////////////////////////////////////////

	// //////////////////////서비스 메소드 선언 영역 ///////////////////////////////////////////////////////////////////
	
	/**
	 * 다중화 WAS에 데이터 동기화 요청
	 * @param syncType
	 * @param param
	 */
	public void syncRequest(String serviceName, Map<String, String> commandMap) {
		
		String servers = Globals.SYNC_PROTOCOL_SERVER_PORT;
		if("".equals(servers) || servers == null || "".equals(serviceName) || serviceName == null || commandMap == null) return;
		
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		paramMap.add(JnitSyncService.SERVICE_NAME, serviceName);
		Iterator<String> itr = commandMap.keySet().iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			if(JnitSyncService.SERVICE_NAME.equals(key)) continue;
			
			String value = commandMap.get(key);
			paramMap.add(key, value);
		}
		
		RestTemplate restTemplate = initRestTemplate();
		String[] urlArr = servers.split(",");
		for(String url : urlArr) {
			restTemplate.postForObject(url+Globals.SYNC_URI, paramMap, String.class);
		}
		
	}

	// //////////////////////서비스 메소드 선언 영역 끝 ///////////////////////////////////////////////////////////////////

	// /////////////////////private,protected 메소드 선언 영역
	/**
	 * RestTemplate 초기화
	 * @return
	 */
	private RestTemplate initRestTemplate(){

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new GsonHttpMessageConverter());
		
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(Integer.parseInt(Globals.SYNC_READ_TIME));
		factory.setConnectTimeout(Integer.parseInt(Globals.SYNC_CONN_TIME));
		CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(Integer.parseInt(Globals.SYNC_MAX_CONN_TOTAL)).setMaxConnPerRoute(Integer.parseInt(Globals.SYNC_MAX_CONN_PER_ROUTE)).build();
		factory.setHttpClient(httpClient);
		
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate; 
	}
	// /////////////////////private,protected 메소드 선언 영역 끝
}
