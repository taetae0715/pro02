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
<title>장바구니 정보</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>장바구니 정보</h2>
		<table class="table">
			<thead>
				<tr><th>순번</th><th>회원명</th><th>상품명</th><th>가격</th><th>수량</th><th>&nbsp;</th></tr>
			</thead>
			<tbody>
				<c:forEach var="cart" items="${cartList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${cart.name }(${cart.id })</td>
					<td>
						<span title="${cart.pcode }">${cart.pname }</span>
					</td>
					<td>${cart.pprice }</td>
					<td>${cart.amount }</td>
					<td>
						<a href="${path1 }/AdminDeleteCart.do?cno=${cart.cno }" class="btn btn-danger">삭제</a>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty basList }">
				<tr>
					<td colspan="4">장바구니에 담긴 상품이 없습니다.</td>
				</tr>
				<a href="javascript:history.go(-1)" class="btn btn-default">뒤로 가기</a>
				</c:if>	
			</tbody>
		</table>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>