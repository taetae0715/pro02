<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }" />  
<script src="${path }/jquery-1.10.1.min.js"></script>
<script src="${path }/datatables.min.js"></script>
<link rel="stylesheet" href="${path }/datatables.min.css">  
<%
	String sid = "";
	if(session!=null) sid = (String) session.getAttribute("sid");  
%>
<header id="hd" class="container">
	<div class="container-fluid">
		<nav id="tnb" class="navbar navbar-default">
			<ul class="nav navbar-nav navbar-right" style="padding-right:40px">
				<c:if test="${empty sid }">
					<li><a href="${path }/UserLogin.do">로그인</a></li>
					<li><a href="${path }/UserJoin.do">회원가입</a></li>
				</c:if>
				<c:if test="${!empty sid }">
					<li><a href="${path }/MyPage.do">${sid }님의 마이페이지</a></li>
					<li><a href="${path }/MyCart.do?id=${sid }">장바구니</a></li>
					<li><a href="${path }/MySalesList.do?id=${sid }">구매내역</a></li>
					<li><a href="${path }/UserLogout.do">로그아웃</a></li>
				</c:if>
			<%--<c:if test="${sid=='admin' }">
					<li><a href="${path }/Admin.do">관리자 페이지</a></li>
				</c:if> --%>
			</ul>
		</nav>
	</div>
	<div class="container-fluid" style="padding-right:30px;">	
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="${path }/">HAPPYDOG</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav" data-toggle="tab-hover" role="tablist">
		      	<li class="active"><a href="${path1 }/ProductList.do">소개<span class="sr-only">(current)</span></a></li>
		        <li class="dropdown">
		          <a href="${path }/ProductList.do?cate=01" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">수제간식<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/ProductList.do?cate=0101">껌</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0102">저키/스틱/사시미</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0103">건조간식</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0104">베이커리</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0105">특식</a></li>
		            <li class="divider"></li>
		          </ul>
		        </li>
		        <li class="dropdown">
		          <a href="${path1 }/ProductList.do?cate=02" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">강아지용품<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/ProductList.do?cate=0201">사료</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0202">영양제</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0203">위생/배변용품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0204">미용/목욕용품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0205">하우스/실내용품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0206">장난감/훈련용품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0207">의류/액세서리</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0208">급수/급식기</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0209">이동장/리드줄/야외용품</a></li>
		            <li class="divider"></li>
		          </ul>
		        </li>
		        <li class="dropdown">
		          <a href="${path1 }/ProductList.do?cate=03" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">고양이용품<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/ProductList.do?cate=0301">사료</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0302">캣닢/영양제</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0303">미용/목욕용품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0304">화장실/모래/위생용품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0305">하우스/캣타워</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0306">장난감/스크래처</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0307">의류/액세서리</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0308">급수/급식기</a></li>
		            <li class="divider"></li>
		          </ul>
		        </li>
		        <li class="dropdown">
		          <a href="${path1 }/ProductList.do?cate=04" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">관상어용품<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/ProductList.do?cate=0401">사료</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0402">수조/어항</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0403">부속품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0404">장식품</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0405">청소용품</a></li>
		            <li class="divider"></li>
		          </ul>
		        </li>
		        <li class="dropdown">
		          <a href="${path1 }/ProductList.do?cate=05" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">기타동물용품<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/ProductList.do?cate=0501">토끼/기니피그</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0502">햄스터/다람쥐</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0503">파충류</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0504">조류</a></li>
		            <li><a href="${path1 }/ProductList.do?cate=0505">곤충</a></li>
		            <li class="divider"></li>
		          </ul>
		        </li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">커뮤티니<span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		         	<li><a href="${path }/NoticeList.do">공지사항</a></li>
		            <li class="divider"></li>
		            <li><a href="${path1 }/FaqList.do">FAQ</a></li>
		            <li><a href="${path1 }/QnaList.do">QNA</a></li>
		            <li class="divider"></li>
		            <li><a href="${path1 }/ReviewList.do">고객 후기</a></li>
		            <li><a href="${path1 }/EventList.do">홍보/이벤트</a></li>
		          </ul>
		        </li>
		      </ul>
		     <c:if test="${sid=='admin' }">
		      <ul class="nav navbar-nav navbar-right">
		        <li><a href="${path1 }/MemberList.do">회원 관리</a></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">상품/판매 관리 <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/AdminCategoryList.do">카테고리 관리</a></li>
		            <li><a href="${path1 }/AdminProductList.do">상품 관리</a></li>
		            <li><a href="${path1 }/AdminSalesList.do">판매 관리</a></li>
		            <li><a href="${path1 }/AdminInventoryList.do">재고 관리</a></li>
		            <li class="divider"></li>
		            <li><a href="${path1 }/AdminCartList.do">장바구니 관리</a></li>
		           	<li><a href="${path1 }/Survey.do">배송 관리</a></li>
		            <li><a href="${path1 }/AdminPayList.do">결제 관리</a></li>
		          </ul>
		        </li>
		        <li class="dropdown" style="padding-right:30px; margin-right:30px;">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">게시글 관리 <span class="caret"></span></a>
		          <ul class="dropdown-menu" role="menu">
		            <li><a href="${path1 }/NoticeList.do">공지사항 관리</a></li>
		            <li class="divider"></li>
		            <li><a href="${path1 }/FaqList.do">FAQ 관리</a></li>
		            <li><a href="${path1 }/QnaList.do">QNA 관리</a></li>
		            <li class="divider"></li>
		            <li><a href="${path1 }/AdminReviewList.do">고객 후기 관리</a></li>
		            <li><a href="${path1 }/EventList.do">홍보/이벤트 관리</a></li>
		          </ul>
		        </li>
		      </ul>
		      </c:if>
		    </div>
		  </div>
		</nav>
	</div>
</header>