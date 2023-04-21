<%
/**
 *@version 3.2.0.1
 **/
%>
<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0); 
if (request.getProtocol().equals("HTTP/1.1")) 
response.setHeader("Cache-Control", "no-cache"); 
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="jtag" uri="/WEB-INF/tlds/jnittag.tld"%>
<% pageContext.setAttribute("lf", "\n"); %>
<%@ page import = "org.springframework.web.context.*, jnit.cms.handler.*, org.springframework.web.context.support.*, jnit.cms.*, jnit.cms.mbr.*, jnit.visit.log.JnitvisitlogImportController, egovframework.com.utl.fcc.service.NullUtil, egovframework.rte.fdl.property.EgovPropertyService"%>
<%
	//final String deDug = EgovProperties.getProperty("Globals.Debug");
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletConfig().getServletContext());
	CmsProperty cmsProperty = (CmsProperty)ctx.getBean("cmsProperty");
	cmsProperty.setClientProperty(new CmsClientProperty());
	CmsClientProperty cmsClientProperty = cmsProperty.getClientProperty();
	cmsClientProperty.init(request);
	
	JnitcmsmbrVO loginVO = (JnitcmsmbrVO)CmsHelper.getAuthMbr(request);
%>

<%
if( loginVO.getMbrId() != null ) {
	if( loginVO.getTypeVO().getTypeLv().equals("A") ) {
%>		<c:set var="loginType" value="<%=loginVO.getTypeVO().getTypeLv()%>"/>
<%
	}
}
%>
<c:set var="login" value="<%=loginVO%>"/>