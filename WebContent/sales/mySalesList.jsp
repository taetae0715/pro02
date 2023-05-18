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
<title>사용자 구매 목록</title>
<style>
.container-fluid { width:1280px; }
.thumbnail { height:480px; }
.comment { width:auto; height:60px; overflow: hidden;  text-overflow: ellipsis; 
 display: -webkit-box;  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical; }
.thumb_box { width:145px; margin:24px auto; margin-bottom:10px; height:auto; overflow:hidden;
padding-top:5px; padding-bottom:5px; 
border:1px solid #e0e0f0; text-align:center; }
.thumb_box::after { content:""; display:block; clear:both; }
.thumb_box img { width:auto; height:193px; }  
.pro_title { overflow:hidden; white-space:nowrap; text-overflow:ellipsis; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>${user.name }님의 상품 구매 목록</h2>
		<hr>
		<fmt:setLocale value="ko_kr" />
		<table class="table">
			<thead>
				<tr><th>순번</th><th>상품명</th><th>수량</th><th>구매가격</th><th>구매일</th><th>배송상태</th></tr>
			</thead>
			<tbody>
				<c:forEach var="sale" items="${sList }" varStatus="status">
				<tr>
					<td><span title="주문 번호 : ${sale.ocode }" style="cursor:pointer; display:inline-block;">${status.count }</span></td>
					<td>
						<span title="제품 코드 : ${sale.pcode }" style="cursor:pointer; display:inline-block;">${sale.pname }</span>
					</td>
					<td>${sale.amount }개</td>
					<td>
						<span title="구매 수량 : ${sale.amount }" style="cursor:pointer; display:inline-block;">
							<fmt:formatNumber value="${sale.price }" pattern="#,##0원"/>
						</span>
					</td>
					<td>
						<fmt:parseDate value="${sale.odate }" var="odate" pattern="yyyy-MM-dd HH:mm:ss" />
						<span style="display:inline-block;"><fmt:formatDate value="${odate }" pattern="yyyy년 MM월 dd일" /></span>
					</td>
					<td>
						<span style="display:inline-block; width:100px;" title="구매 완료된 상품은 반품이 불가능합니다.">${sale.ostate }</span> &nbsp; &nbsp; &nbsp;
						<c:if test="${sale.ostate=='상품준비중' }">
							<a href="${path1 }/CanclePay.do?ocode=${sale.ocode }" class="btn btn-danger">주문 취소</a>
						</c:if>
						<c:if test="${sale.ostate=='배송중' || sale.ostate=='배송완료'}">
							<a href="${path1 }/ReturnBuy.do?ocode=${sale.ocode }" class="btn btn-warning">반품 요청</a>
							<a href="${path1 }/OkBuy.do?ocode=${sale.ocode }" class="btn btn-primary">구매 결정</a>
							&nbsp; &nbsp;
							<span style="display:inline-block; width:160px;" title="배송 코드 : ${sale.dcode }">배송사 : ${sale.dname }</span>
							<a href="${path1 }/AddResultUserReview.do?ocode=${sale.ocode }" class="btn btn-primary">구매 후기 작성</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty sList }">
				<tr>
					<td colspan="4">구매 내역이 존재하지 않습니다.</td>
				</tr>
				</c:if>	
			</tbody>
		</table>
		<div class="btn-group">
			<a href="javascript:history.go(-1)" class="btn btn-default">이전</a>
		</div>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>