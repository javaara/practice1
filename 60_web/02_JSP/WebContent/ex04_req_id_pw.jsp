<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP id, pw</title>
<script>
	function send_go(frm) {
		alert("전달받은 객체 : " + frm);
		frm.action = "ex04_resq.jsp";
		frm.submit();
	}
</script>
</head>
<body>
	<%-- JSP 요청페이지에서 응답페이지로 이동 --%>
	<h2>(요청)ID, PW 값 서버측에 전달</h2>
	<%-- <form action="ex04_resq.jsp"> --%>
	<form>
		<p>ID: <input type="text" name="id"></p>
		<p>PW: <input type="password" name="pw"></p>
		
		<input type="submit" value="전송(다른페이지로 이동)" onclick="send_go(this.form)">	
	</form>
</body>
</html>