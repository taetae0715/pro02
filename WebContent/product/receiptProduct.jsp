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
<title>상품 입고</title>
<style>
.container-fluid { width:1280px; }
.img { width: 300px; height: 300px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h3>상품 > <a href="${path1 }/ProductList.do?cate=${pro.cate}">${cateMap.catename }</a> > ${pro.pname }</h3>
		<br><hr><br>
		<fmt:setLocale value="ko_kr" />
		<form action="ReceiptProductPro.do" method="post">
			<table class="table">
				<tbody>
					<tr>
						<td colspan="2">
							<img class="img" src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }"/>
						</td>
					</tr>
					<tr>
						<th>상품명(상품코드)</th>
						<td>
							${pro.pname }(${pro.pcode })
							<input type="hidden" name="pcode" id="pcode" value="${pro.pcode }">
						</td>
					</tr>
					<tr>
						<th>규격</th>
						<td>${pro.pstd }</td>
					</tr>
					<tr>
						<th>설명</th>
						<td>${pro.pcom }</td>
					</tr>
					<tr>
						<th>현재 가격</th>
						<td>${pro.pprice }</td>
					</tr>
					<tr>
						<th><label for="pprice">입고 가격</label></th>
						<td><input type="number" name="pprice" id="pprice" value="${pro.pprice }" min="0" max="5000000" step="100" title="0~5000000" class="form-control"></td>
					</tr>
					<tr>
						<th>현재 수량</th>
						<td>${pro.amount }</td>
					</tr>
					<tr>
						<th><label for="amount">입고할 수량</label></th>
						<td><input type="number" name="amount" id="amount" value="1" min="1" max="500" title="1~500" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="btn-group">
								<c:if test="${sid.equals('admin') }">
									<input type="submit" class="btn btn-warning" role="button" value="입고 처리">
									<a href="${path1 }/UpdateProduct.do?pcode=${pro.pcode }" class="btn btn-success" role="button">상품 수정</a>
									<a href="${path1 }/ProductList.do?cate=${pro.cate}" class="btn btn-primary" role="button">카테고리 목록</a>
								</c:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<c:if test="${sid.equals('admin') }">
				<div class="btn-group">
					<a href="${path1 }/InsertProduct.do" class="btn btn-primary">상품 등록</a>
					<a href="${path1 }/DeleteProduct.do?pcode=${pro.pcode }" class="btn btn-danger" role="button">상품 삭제</a>
					<a href="${path1 }/ProductList.do" class="btn btn-default" role="button">상품 전체 목록</a>
				</div>
			</c:if>
		</form>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>