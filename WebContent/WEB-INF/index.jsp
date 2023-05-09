<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common.jsp" %>
<title>메인 페이지</title>
<style>
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<div  class="content">
	<h2>메인 페이지</h2>
	<h2>제작자 : ${author }</h2>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>