<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragma","no-cache");
//response.setHeader("X-Frame-Options","GOFORIT");
response.setDateHeader("Expires",0); 
if (request.getProtocol().equals("HTTP/1.1")) 
response.setHeader("Cache-Control", "no-cache"); 
%><%@ page contentType="application/xml; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:out value="${xml}" escapeXml="false" />