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
<title>질문 글 수정하기</title>
<style>
.container-fluid { width:1280px; }
#fileSel1:checked ~ #file1 { display:none; }
#fileSel2:checked ~ #file1 { display:block; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>질문 글 수정</h2>
		<p>${msg }</p>
		<form action="${path1 }/UpdateQnaPro.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="title">제목</label></th>
						<td>
							<input type="hidden" name="author" id="author" value="${sid }">
							<input type="hidden" name="qno" id="qno" value="${qn.qno }">
							<input type="text" name="title" id="title" value="${qn.title }" maxlength="98" title="100자 내로 작성" placeholder="100자 내로 작성" class="form-control" required autofocus>
						</td>
					</tr>
					<tr>
						<th><label for="content">질문 내용</label></th>
						<td>
							<textarea rows="10" cols="100" name="content" id="content" maxlength="990" title="1000자 내로 작성" class="form-control">${qn.content }</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="질문 수정" class="btn btn-primary">
							<a href="${path1 }/QnaList.do" class="btn btn-primary">질문 및 답변 목록</a>				
						</td>
					</tr>
				</tbody>
			</table>
		</form>
				<hr>
		<h3>답글 보기</h3>
		<table class="table">
			<thead>
				<tr><th>연번</th><th>답변 제목</th><th>작성자</th><th>작성일</th></tr>
			</thead>
			<tbody>
				<c:forEach var="qna" items="${qnaList }" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>
						<a href="${path1 }/GetQna.do?qno=${qna.qno }">${qna.title }</a>
					</td>
					<td>${qna.author }</td>
					<td>
						<fmt:parseDate value="${qna.resdate }" var="resdate" pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${resdate }" pattern="yyyy년 MM월 dd일" />
					</td>
				</tr>
				</c:forEach>
				<c:if test="${empty qnaList }">
				<tr>
					<td colspan="4">답변 목록이 존재하지 않습니다.</td>
				</tr>
				</c:if>	
			</tbody>
		</table>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>