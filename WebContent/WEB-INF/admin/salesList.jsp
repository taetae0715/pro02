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
<title>판매 목록</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>판매 목록</h2>
		<div class="btn-group">
			<a href="#" class="btn btn-warning">전체 판매 목록</a>
			<a href="${path1 }/NotSalesList.do" class="btn btn-primary">판매 안된 상품</a>
		</div>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>판매상품</th><th>구매자</th><th>수량</th><th>금액</th><th>개별 작업</th></tr>
			</thead>
			<tbody>
				<c:set var="total1" value = "0" />
				<c:set var="total2" value = "0" />
				<c:forEach var="sale" items="${sList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<a href="${path1 }/ProductDetail.do?pcode=${sale.pcode }" title="${sale.pcode }">${sale.pname }</a>
					</td>
					<td><span title="${sale.id }">${sale.username }</span></td>
					<td>
						<span><fmt:formatNumber value="${sale.amount }" pattern="#,##0 개" /></span>
					</td>
					<td>
						구매금액 : <span><fmt:formatNumber value="${sale.price/1.4 }" pattern="#,##0 원" /></span><br>
						판매금액 : <span><fmt:formatNumber value="${sale.price }" pattern="#,##0 원" /></span>
						<c:set var="total1" value="${total1 + (sale.price/1.4)}"/>
						<c:set var="total2" value="${total2 + sale.price }"/>
					</td>
					<td>
						<span>작업 기능 추후 추가</span>
					</td>
				</tr>
				</c:forEach>
				<tr>
					<th colspan="4">누 계</th>
					<td colspan="2">
						<span>구매금액 합계 : ${total1 }</span><br>
						<span>판매금액 합계 : ${total2 }</span><br>
						<span>이익금액 합계 : ${total2-total1 }</span>
					</td>
				</tr>
				<c:if test="${empty sList }">
				<tr>
					<td colspan="6">판매 내역이 존재하지 않습니다.</td>
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