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
<title>리뷰 목록 보기</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h3>리뷰(이용후기) 목록</h3>
		<br><hr><br>
		<fmt:setLocale value="ko_kr" />
		<table class="table">
			<thead>
				<tr>
					<th>작성자</th><th>내용</th><th>만족도</th><th>작업</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rev" items="${rList }">
				<tr>
					<td>작성자 : ${rev.id }</td>
					<td>내용 : ${rev.rcontent }</td>
					<td>만족도 : ${rev.rpoint }</td>
					<td>
						<a href="${path1 }/DeleteReview.do?rcode=${rev.rcode }" class="btn btn-danger">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${sid.equals('admin') }">
		<div class="btn-group">
			<a href="${path1 }/InsertProduct.do" class="btn btn-primary">상품 등록</a>
		</div>
		</c:if>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>