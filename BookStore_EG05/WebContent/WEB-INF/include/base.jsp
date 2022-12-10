<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--     http://localhost:8080/BookStore_EG03/  <br/>
    
<base href="http://localhost:8080/BookStore_EG03/"/> -->
<%-- <base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/> --%>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort }${pageContext.request.contextPath}/"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>