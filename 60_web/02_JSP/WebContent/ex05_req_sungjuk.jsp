<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성척처리</title>
</head>
<body>
	<h2>학생성적</h2>
	<form action="ex05_resp.jsp">
		<p>이름: <input type="text" name="name" value="홍길동"></p>
		<p>국어: <input type="text" name="kor" value="100"></p>
		<p>영어: <input type="text" name="eng" value="90"></p>
		<p>수학: <input type="text" name="math" value="80"></p>
		
		<input type="submit" value="결과보기">
		<input type="reset" value="초기화">
	</form>
</body>
</html>