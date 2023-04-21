package jnit.cms.sync;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.service.Globals;
import jnit.com.util.SpringHelperUtil;
import jnit.cron.JnitCronUtil;


/**
 * @Class Name : ComSmplController.java
 * @Description : 샘플 Controller
 * @Modification Information
 * 
 * @author
 * @since 2020. 07.20
 * @version 1.0
 * @see Copyright (C) by  All right reserved.
 */
@Controller
public class JnitSyncController{
	
	protected String getPkg(){return "exts/com/sync";}
	
	@Resource(name = "jnitSyncService")
	private JnitSyncService jnitSyncService;
	
	/**
	 * 동기화 요청 (사용자가 URL을 호출하여 동기화)
	 * @param response
	 * @param serviceName
	 */
	@RequestMapping("/sync/request.do")
	public void syncRequest(
			HttpServletResponse response,
			Map<String, String> commandMap) {
		
		if(commandMap == null) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}else {
			String serviceName = commandMap.get(JnitSyncService.SERVICE_NAME);
			if(serviceName == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}else {
				jnitSyncService.syncRequest(serviceName, commandMap);
				response.setStatus(HttpStatus.OK.value());
			}
		}
	}
	
	/**
	 * 동기화 응답
	 * @param response
	 * @param syncType
	 */
	@RequestMapping("/sync/response.do")
	public void syncResponse(
			HttpServletResponse response,
			Map<String, String> commandMap) {
		
		//if (!JnitCronUtil.isExcuteServer())return;
		
		if(commandMap == null) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}else {
			String serviceName = commandMap.get(JnitSyncService.SERVICE_NAME);
			if(serviceName == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			}else {
				try {
					((JnitSyncResponseService) SpringHelperUtil.getBean(Globals.SERVLET_NAME, serviceName)).sync(commandMap);
					response.setStatus(HttpStatus.OK.value());
				}catch(NoSuchBeanDefinitionException e) {
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				}catch(Exception e) {
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				}
			}
		}
	}
}

