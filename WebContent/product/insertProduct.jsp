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
<title>상품 등록</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>상품 등록</h2>
		<p>${msg }</p>
		<form action="${path1 }/InsertProductPro.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="pro_code">상품코드</label></th>
						<td>
							<input type="hidden" name="pro_code" id="pro_code" value="${sid }">
							<input type="text" name="pro_code" id="title" maxlength="8" class="form-control" required autofocus>
						</td>
					</tr>
					<tr>
						<th><label for="pname">상품명</label></th>
						<td><input type="text" name="pname" id="pname" maxlength="40" class="form-control" required autofocus></td>
					</tr>
					<tr>
						<th><label for="pstd">규격/단위</label></th>
						<td><input type="text" name="pstd" id="pstd" maxlength="40" class="form-control" required autofocus></td>
					</tr>
					<tr>
						<th><label for="pcost">가격</label></th>
						<td><input type="text" name="pcost" id="pcost" maxlength="40" class="form-control" required autofocus></td>
					</tr>
					<tr>
						<th><label for="pcom">상품설명</label></th>
						<td>
							<textarea rows="5" cols="150" name="pcom" id="pcom" maxlength="490" title="500자 내로 작성" class="form-control"></textarea>
						</td>
					</tr>
					<tr>
						<th><label for="amount">수량</label></th>
						<td><input type="text" name="amount" id="amount" maxlength="99999" class="form-control" required autofocus></td>
					</tr>
					<tr>
						<th><label for="cate">분류번호</label></th>
						<td><input type="text" name="cate" id="cate" maxlength="40" class="form-control" required autofocus></td>
					</tr>
					<tr>
						<th><label for="pic1">첨부 파일</label></th>
						<td>
							<input type="file" name="pic1" id="pic1" class="">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="등록하기" class="btn btn-primary">
							<a href="${path1 }/ProductList.do" class="btn btn-default">상품 목록</a>				
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