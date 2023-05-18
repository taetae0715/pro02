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
<title>FAQ 목록</title>
<style>
.container-fluid { width:1280px; }
.fanswer { display:none; }
.fquestion { display:none; }
.fquestion:checked + .fanswer { display:block; }
.lst { clear:both; border-top:2px solid #e1e1e1; }
.lst li { padding-top:12px; padding-bottom:12px; border-top:1px solid #f0f0f0; }
.lst li label { cursor:pointer; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px; ">
	<div class="container-fluid">
		<h2>자주하는 질문</h2>
		<article class="container-fluid">
			<ul class="lst">
				<c:forEach var="faq" items="${faqList }" varStatus="status">
				<li>
					<span class="num">${status.count }</span>
					<label for="fquestion${status.count }">
						<span>${faq.fquestion }</span>
					</label>
					<input type="checkbox" id="fquestion${status.count }" class="fquestion">
					<c:if test="${sid.equals('admin') }">
					<a href="${path1 }/UpdateFaq.do?fno=${faq.fno }" class="fanswer" title="관리자는 이 글을 클릭시 수정할 수 있습니다.">${faq.fanswer }</a>
					</c:if>
					<c:if test="${!sid.equals('admin') }">
					<span class="fanswer">${faq.fanswer }</span>
					</c:if>
				</li>
				</c:forEach>
				<c:if test="${empty faqList }">
				<li>
					<p>FAQ가 존재하지 않습니다.</p>
				</li>
				</c:if>	
			</ul>
		</article>
		<c:if test="${sid.equals('admin') }">
		<div class="btn-group">
			<a href="${path1 }/AddFaq.do" class="btn btn-primary">FAQ 등록</a>
		</div>
		</c:if>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>