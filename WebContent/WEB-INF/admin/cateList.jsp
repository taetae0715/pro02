<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>카테고리 목록</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>카테고리</h2>
		<hr>
		<div class="btn-group">
			<a href="${path1 }/GetCategory.do?categroup=수제간식" class="btn btn-primary">수제간식</a>
			<a href="${path1 }/GetCategory.do?categroup=강아지용품" class="btn btn-primary">강아지용품</a>
			<a href="${path1 }/GetCategory.do?categroup=고양이용품" class="btn btn-primary">고양이용품</a>
			<a href="${path1 }/GetCategory.do?categroup=관상어용품" class="btn btn-primary">관상어용품</a>
			<a href="${path1 }/GetCategory.do?categroup=기타동물용품" class="btn btn-primary">기타동물용품</a>
		</div>
		<hr>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>카테고리번호</th><th>카테고리 그룹명</th><th>카테고리 이름</th></tr>
			</thead>
			<tbody>
				<c:forEach var="cate" items="${cateList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<a href="#">${cate.cate }</a>						
					</td>
					<td>${cate.categroup }</td>
					<td>${cate.catename }</td>
				</tr>
				</c:forEach>
				<c:if test="${empty cateList }">
				<tr>
					<td colspan="4">카테고리 목록이 존재하지 않습니다.</td>
				</tr>
				</c:if>	
			</tbody>
		</table>
		<c:if test="${!empty sid }">
		<div class="btn-group">
			<a href="${path1 }/InsertCategory.do" class="btn btn-primary">카테고리 등록</a>
		</div>
		</c:if>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>