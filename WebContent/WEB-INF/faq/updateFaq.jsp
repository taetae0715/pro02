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
<title>FAQ 수정하기</title>
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
		<h2>FAQ 수정</h2>
		<p>${msg }</p>
		<form action="${path1 }/UpdateFaqPro.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="fquestion">질문</label></th>
						<td>
							<input type="hidden" name="fno" id="fno" value="${faq.fno }">
							<input type="text" name="fquestion" id="fquestion" value="${faq.fquestion }" maxlength="390" title="390자 내로 작성" placeholder="100자 내로 작성" class="form-control" required autofocus>
						</td>
					</tr>
					<tr>
						<th><label for="fanswer">답변</label></th>
						<td>
							<input type="text" name="fanswer" id="fanswer" value="${faq.fanswer }" maxlength="390" title="390자 내로 작성" placeholder="100자 내로 작성" class="form-control" required>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="FAQ 수정하기" class="btn btn-primary">
							<a href="${path1 }/DelFaq.do?fno=${faq.fno }" class="btn btn-primary">FAQ 삭제</a>
							<a href="${path1 }/FaqList.do" class="btn btn-primary">FAQ 목록</a>				
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