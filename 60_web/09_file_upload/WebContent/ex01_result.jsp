<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 파일 업로드하고 결과 표시 --%>
<%
	//한글처리를 위한 UTF-8 설정
	request.setCharacterEncoding("UTF-8");

	//form 태그에 enctype="multipart/form-data" 사용시
	//request 객체를 통한 파라미터 값 조회 안됨
	//MultipartRequest 타입의 객체를 통해서만 파라미터 값 추출 가능
	String name = request.getParameter("name");
	System.out.println("name : " + name);
	
	//파일을 저장할 위치 지정(upload)
	ServletContext sc = this.getServletContext();
	String path = sc.getRealPath("/upload");
	System.out.println("path : " + path);
	
	//MultipartRequest 사용해서 처리
	//MultipartRequest 기본 생성자 없음
	MultipartRequest mr = new MultipartRequest(
			request, //요청객체추
			path, //실제 파일을 저장하기 위한 경로사
			10 * 1024 * 1024, //업로드 파일의 최대용량(byte 단위)랑
			"UTF-8", //인코딩타입
			new DefaultFileRenamePolicy() //파일이름중복시 새로운이름 사용
			);
	
	/* MultipartRequest 주요메소드
	getParameter(문자열) : 파라미터값 추출
	getOriginalFileName(문자열) : 업로드시 사용한 원본파일명
	getFilesystemName(문자열) : 업로드시 저장된 파일명(실제 물리적으로)
	getContentType(문자열) : 업로드된 파일의 타입(형식)
	*/
%>
	<h1>파일업로드 결과 보기</h1>
	<h1>올린사람(name) : <%=mr.getParameter("name") %></h1>
	<h1>파일제목(title) : <%=mr.getParameter("title") %></h1>
	<h1>파일(f_name) : <%=mr.getParameter("f_name") %></h1>
	<h1>원본파일명 : <%=mr.getOriginalFileName("f_name") %></h1>
	<h1>저장파일명 : <%=mr.getFilesystemName("f_name") %></h1>
	<h1>파일타입 : <%=mr.getContentType("f_name") %></h1>
<%
	File file = mr.getFile("f_name");
	out.println("<h2>파일이름 : " + file.getName() + "</h2>");
	out.println("<h2>파일크기 : " + file.length() + "</h2>");
%>
	<%-- 다운로드 링크 연결 --%>
	<a href='download.jsp?path=upload&name=<%=mr.getFilesystemName("f_name") %>'>
	다운로드할 파일(<%=mr.getOriginalFileName("f_name") %>)
	</a>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	