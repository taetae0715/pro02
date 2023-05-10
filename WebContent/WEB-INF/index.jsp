<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common.jsp" %>
<link rel="shortcut icon" href="${path1 }/img/favicon.ico">
<title>메인 페이지</title>
<style>
.content { text-align: center; }
.container-fluid { width:1280px; }
.main { width: 1280px; height: 1200px; margin: 0 auto; padding-left: 120px; }
/* .fix_area { position:fixed; z-index:9999; bottom:40px; right:40px; }
.fix_area .cir_box { display:block; width: 60px; height: 60px; text-align:center; 
border-radius:35px; box-sizing: border-box; padding: 6px; font-size:12px; padding-top:15px; 
overflow:hidden; box-shadow:1px 1px 6px #333; }
.fix_area .counsel { background-color:rgba(255,255,255,0.75); color:#333; }
.fix_area .totop { background-color:#dc2c34; color:#fff; margin-top: 20px; } */
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="content">
	<img alt="왓섭이" class="main" src="${path1 }/img/whatsup.jpg">
	<h3 class="author">제작자 : ${author }</h3>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>