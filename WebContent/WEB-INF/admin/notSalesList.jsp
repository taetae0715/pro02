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
<title>판매 안된 상품 목록</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>판매 안된 상품 목록</h2>
		<div class="btn-group">
			<a href="#" class="btn btn-warning">판매 안된 상품</a>
			<a href="${path1 }/AdminSalesList.do" class="btn btn-primary">전체 판매 목록</a>
		</div>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>상품명</th><th>남은 수량</th><th>입고 금액</th><th>개별 작업</th></tr>
			</thead>
			<tbody>
				<c:forEach var="pro" items="${nList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<a href="${path1 }/ProductDetail.do?pcode=${pro.pcode }" title="${pro.pcode }">${pro.pname }</a>
					</td>
					<td>
						<span><fmt:formatNumber value="${pro.amount }" pattern="#,##0 개" /></span>
					</td>
					<td>
						구매 금액 : <span><fmt:formatNumber value="${pro.pprice }" pattern="#,##0 원" /></span><br>
						판매할 금액 : <span><fmt:formatNumber value="${pro.pprice*1.4 }" pattern="#,##0 원" /></span>
					</td>
					<td>
						<span>작업 기능 추후 추가</span>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty nList }">
				<tr>
					<td colspan="5">상품 내역이 존재하지 않습니다.</td>
				</tr>
				</c:if>	
			</tbody>
		</table>
		<div class="btn-group">
			<a href="javascript:history.go(-1)" class="btn btn-primary">뒤로 가기</a>
		</div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>