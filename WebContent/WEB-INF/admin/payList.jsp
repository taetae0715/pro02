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
<title>결제 목록</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>결제 목록</h2>
		<div class="btn-group">
			<a href="#" class="btn btn-warning">전체 결제 목록</a>
		</div>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>결제상품</th><th>구매자</th><th>수량</th><th>금액</th><th>결제 내용</th></tr>
			</thead>
			<tbody>
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
						<span><fmt:formatNumber value="${sale.price }" pattern="#,##0 원" /></span>
					</td>
					<td>
						결제 번호 : <span>${sale.pno }</span><br>
						결제 종류 : <span>${sale.type }</span><br>
						결제 카드/계좌 번호 : <span>${sale.type_no }</span>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty sList }">
				<tr>
					<td colspan="6">결제 내역이 존재하지 않습니다.</td>
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