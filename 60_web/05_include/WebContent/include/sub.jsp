<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서브페이지</title>
</head>
<body>
	<h1>서브페이지(sub)</h1>
	<a href="main.jsp">메인 페이지로 이동</a>
	<h2>서브 페이지 내용</h2>
	<p>sub 내용......</p>	
	<p>sub 내용......</p>	
	<p>sub 내용......</p>	
	<p>sub 내용......</p>	
	<p>sub 내용......</p>	
	
	<%-- 디렉티브(directive) include : copy & paste 적용됨 --%>
	<%@ include file="footer.jsp" %>
	
	<hr><hr>
	<footer>
	<hr>
	<p>비트캠프 서울시 서초구 강남대로 459 (서초동, 백암빌딩)｜ 사업자등록번호 : 214-85-24928
	(주)비트컴퓨터 서초본원 대표이사 : 조현정 / 문의 : 02-1111-2222 / 팩스 : 02-6007-1245
	통신판매업 신고번호 : 제 서초-00098호 / 개인정보보호관리책임자 : 최종진
	Copyright (c) 비트캠프 All rights reserved.</p>	
	</footer>
</body>
</html>