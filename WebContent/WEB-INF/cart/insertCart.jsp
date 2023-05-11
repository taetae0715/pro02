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
<title>장바구니 담기</title>
<style>
.container-fluid { width:1280px; }
.img { width: 350px; height: 300px; }
th { width: 150px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>장바구니 담기</h2>
		<p>${msg }</p>
		<form action="${path1 }/InsertCartPro.do" method="POST">
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">
							<img class="img" src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }"/>
						</td>
					</tr>
					<tr>
						<th><label for="pname">상품명</label></th>
						<td>
							<input type="hidden" name="id" id="id" value="${sid }">
							<input type="hidden" name="pcode" id="pcode" value="${pro.pcode }">
							<input type="text" name="pname" id="pname" value="${pro.pname }" class="input" disabled>
						</td>
					</tr>
					<tr>
						<th><label for="pprice">가격</label></th>
						<td>
							<input type="number" name="pprice" id="pprice" value="${pro.pprice }" min="0" max="5000000" step="100" title="0~5000000" class="input" disabled>
						</td>
					</tr>
					<tr>
						<th><label for="amount">수량(재고: ${pro.amount })</label></th>
						<td>
							<input type="number" name="amount" id="amount" value="1" min="0" max="${pro.amount }" title="최대 ${pro.amount }개까지 구매 가능" class="input">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="장바구니 담기" class="btn btn-warning">
							<a href="${path1 }/MyCart.do?id=${sid }" class="btn btn-success">장바구니 가기</a>				
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>