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
<title>상품 상세 보기</title>
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
		<table class="table">
			<tbody>
				<tr>
					<td colspan="2">
						<img class="img" src='${path1 }/product/${pro.pic1 }' alt="${pro.pname }"/>
					</td>
				</tr>
				<tr>
					<th>상품명(상품코드)</th>
					<td>${pro.pname }(${pro.pcode })</td>
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
					<th>가격</th>
					<td><fmt:formatNumber value="${pro.pprice }" type="currency" /></td>
				</tr>
				<tr>
					<th>남은 수량</th>
					<td>${pro.amount }</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn-group">
							<c:if test="${pro.amount>0 && !sid.equals('admin')}">
								<a href="${path1 }/InsertCart.do?pcode=${pro.pcode}" class="btn btn-warning" role="button">장바구니</a>
								<a href="${path1 }/AddSales.do?pcode=${pro.pcode}&id=${sid }" class="btn btn-danger" role="button">구매하기</a>
								<a href="${path1 }/ProductList.do?cate=${pro.cate}" class="btn btn-default" role="button">목록</a>
							</c:if>
							<c:if test="${sid.equals('admin') }">
								<a href="${path1 }/ReceiptProduct.do?pcode=${pro.pcode }" class="btn btn-warning" role="button">상품 입고</a>
								<a href="${path1 }/UpdateProduct.do?pcode=${pro.pcode }" class="btn btn-success" role="button">상품 수정</a>
								<a href="${path1 }/DelProduct.do?pcode=${pro.pcode }" class="btn btn-danger" role="button">상품 삭제</a>
							</c:if>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<table class="table">
			<tbody>
				<c:forEach var="rev" items="${rList }">
				<tr>
					<td>작성자 : ${rev.id }</td>
					<td>내용 : ${rev.rcontent }</td>
					<td>만족도 : ${rev.rpoint }</td>
					<td>
						<c:if test="${rev.id==sid }">
							<a href="${path1 }/UpdateReview.do?rcode=${rev.rcode }" class="btn btn-primary">이용후기 수정</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${sid.equals('admin') }">
		<div class="btn-group">
			<a href="${path1 }/InsertProduct.do" class="btn btn-primary">상품 등록</a>
			<a href="${path1 }/ProductList.do" class="btn btn-default" role="button">상품 목록</a>
		</div>
		</c:if>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>