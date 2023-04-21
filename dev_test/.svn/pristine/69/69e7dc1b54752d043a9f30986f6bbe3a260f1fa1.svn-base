package jnit.filego.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import jnit.filego.enums.JnitFilegoFileType;
import jnit.filego.vo.JnitFilegoResponseVO;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.sun.star.io.ConnectException;

import egovframework.com.cmm.service.Globals;


public class JnitFilegoSendUtil {

	private static Log log = LogFactory.getLog(JnitFilegoSendUtil.class);
	
	private final static String MEDIA_TYPE = "application";
	private final static String MEDIA_SUB_TYPE = "json";
	private final static String MEDIA_CHARSET = "UTF-8";
	
	/**
	 * 파일 배포 요청
	 * @throws Exception 
	 * @type
	 * @fileName
	 */
	public static void sendFile(JnitFilegoFileType type, String fileName){
		String active = Globals.FILEGO_ACTIVE;
		if(!active.toLowerCase().equals("on")) return;			//배포모듈 사용여부중 on이 아니면 전송하지 않고 return
		if(!sendYn(type)) return;								//배포 허용유형이 아니면 전송하지 않고 return
		
		try{
			RestTemplate restTemplate = initRestTemplate(MEDIA_TYPE, MEDIA_SUB_TYPE, MEDIA_CHARSET);
			String url = Globals.FILEGO_SEND_FILE_URL;
			
			JSONObject reqData = new JSONObject();			//요청 데이터
			reqData.put("type", type.getFileType());
			reqData.put("fileName", fileName);
			
			restTemplate.postForObject(url, reqData, JnitFilegoResponseVO.class);
		}catch(ConnectException e){
			log.error(e.getMessage());
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 파일 삭제 요청
	 * @throws Exception 
	 * @type
	 * @fileName
	 */
	public static void deleteFile(JnitFilegoFileType type, String fileName){
		String active = Globals.FILEGO_ACTIVE;
		if(!active.toLowerCase().equals("on")) return;			//배포모듈 사용여부중 on이 아니면 전송하지 않고 return
		if(!sendYn(type)) return;								//배포 허용유형이 아니면 전송하지 않고 return
		
		try{
			RestTemplate restTemplate = initRestTemplate(MEDIA_TYPE, MEDIA_SUB_TYPE, MEDIA_CHARSET);
			String url = Globals.FILEGO_SEND_DELETE_FILE_URL;
			
			JSONObject reqData = new JSONObject();			//요청 데이터
			reqData.put("type", type.getFileType());
			reqData.put("fileName", fileName);
			
			restTemplate.postForObject(url, reqData, JnitFilegoResponseVO.class);
		}catch(ConnectException e){
			log.error(e.getMessage());
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}
	
	/**
	 * 파일 리네임 요청
	 * @throws Exception 
	 * @type
	 * @fileName
	 */
	public static void renameFile(JnitFilegoFileType type, String srcFileName, String dstFileName){
		String active = Globals.FILEGO_ACTIVE;
		if(!active.toLowerCase().equals("on")) return;			//배포모듈 사용여부중 on이 아니면 전송하지 않고 return
		if(!sendYn(type)) return;								//배포 허용유형이 아니면 전송하지 않고 return
		
		try{
			RestTemplate restTemplate = initRestTemplate(MEDIA_TYPE, MEDIA_SUB_TYPE, MEDIA_CHARSET);
			String url = Globals.FILEGO_SEND_RENAME_FILE_URL;
			
			JSONObject reqData = new JSONObject();			//요청 데이터
			reqData.put("type", type.getFileType());
			reqData.put("srcFileName", srcFileName);
			reqData.put("dstFileName", dstFileName);
			restTemplate.postForObject(url, reqData, JnitFilegoResponseVO.class);
		}catch(ConnectException e){
			log.error(e.getMessage());
		}catch(Exception e){
			log.error(e.getMessage());
		}
	}
	
	/**
	 * resttemplate 설정
	 * @param restTemplate
	 * @return
	 * @throws Exception
	 */
	private static RestTemplate initRestTemplate(String mediaType, String mediaSubType, String mediaCharset) throws Exception{
		/* 실행시 오류 발생으로 인한 주석처리
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(1000);		//연결시간초과 1초
		factory.setReadTimeout(1000);			//읽기시간초과 1초
		*/
		RestTemplate restTemplate = new RestTemplate(/*factory*/);
    	List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
    	supportedMediaTypes.add(new MediaType(mediaType, mediaSubType, Charset.forName(mediaCharset)));
    	
    	List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
    	list.add(new MappingJackson2HttpMessageConverter());		//json-type
    	restTemplate.setMessageConverters(list);
    	return restTemplate;
	}
	
	/**
	 * 파일 유형에 따른 파일전송 여부
	 * */
	public static boolean sendYn(JnitFilegoFileType type) {
		String sendYn = "";
		if(JnitFilegoFileType.CONTENT.getFileType() == type.getFileType()){
			sendYn = Globals.FILEGO_CONTENT_SENDYN;
		}else if(JnitFilegoFileType.UPLOAD.getFileType() == type.getFileType()){
			sendYn = Globals.FILEGO_UPLOAD_SENDYN;
		}else if(JnitFilegoFileType.CONFIG.getFileType() == type.getFileType()){
			sendYn = Globals.FILEGO_CONFIG_SENDYN;
		}else if(JnitFilegoFileType.BOARD.getFileType() == type.getFileType()){
			sendYn = Globals.FILEGO_BOARD_SENDYN;
		}else if(JnitFilegoFileType.CRON.getFileType() == type.getFileType()){
			sendYn = Globals.FILEGO_CRON_SENDYN;
		}else{
			sendYn = "N";
		}
		return ("Y".equals(sendYn.toUpperCase()));
	}
	
	/**
	 * 프록시 설정
	 * @rt
	 */
	/*
	@SuppressWarnings("unused")
	private static void setProxy(RestTemplate rt){
         SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
          Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("localhost", NullUtil.nullInt("8888")));
          requestFactory.setProxy(proxy);
          rt.setRequestFactory(requestFactory);
   }
   */
}
