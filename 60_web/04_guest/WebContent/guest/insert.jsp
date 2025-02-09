<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달받은 데이터를 사용해서 DB에 입력처리
	정상입력 : list.jsp 페이지로 이동
	예외발생 : 현재 페이지 보여주기
--%>
<%
	//JDBC 프로그램을 위한 변수 선언
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USER = "mystudy";
	final String PASSWORD = "mystudypw";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
		
	
	//전달받은 파라미터 값 추출
	int sabun = Integer.parseInt(request.getParameter("sabun"));
	String name = request.getParameter("name");
	int pay = Integer.parseInt(request.getParameter("pay"));
	
	int result = 0;
	try {
		//1. 드라이버 로딩
		Class.forName(DRIVER);
		//2. DB연결
		conn = DriverManager.getConnection(URL, USER, PASSWORD);

		//사용할 쿼리 작성
		String sql = "";
		sql = "INSERT INTO GUEST VALUES(?, ?, SYSDATE, ?)";
		
		//3-1. Statement 객체 생성(Connection 객체로부터)
		pstmt = conn.prepareStatement(sql);
		
		//3-2. 바인드 변수에 데이터 셋팅하기
		pstmt.setInt(1, sabun);
		pstmt.setString(2, name);
		pstmt.setInt(3, pay);
		
		//4. 쿼리실행
		result = pstmt.executeUpdate();			
		
		System.out.println("정상입력");			

	} catch (Exception e){
		e.printStackTrace();
		
	} finally {
		try {
			if (pstmt != null)  pstmt.close();
			if (conn != null)  conn.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//작업결과 확인 후 처리
	if (result > 0) { //데이터 입력된 경우
		response.sendRedirect("list.jsp");			
	}
	
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사원 등록 실패</h1>
	<p>입력작업 처리를 하지 못했습니다<br>
	[입력페이지로 이동] 클릭해서 다시 작업하세요.</p>
	<a href="addForm.jsp">입력페이지 이동</a>
	<a href="list.jsp">전체목록 이동</a>
</body>
</html>