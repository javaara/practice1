<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 설명</title>
</head>
<body>
	<!-- HTML 주석 : 소스보기를 통해서 볼 수 있음 -->
	<%-- JSP주석 : 소스보기에서 볼 수 없음 --%>
	
	<%-- JSP 구성요소 : 디렉티브(지시어), 선언부, 스크립트릿, 표현식
	1. 디렉티브(<%@ %>) : JSP 페이지에 대한 설정정보 지정
		- page : JSP 페이지에 대한 정보 지정
		- taglib : 사용할 태그 라이브러리 지정(JSTL 사용)
		- include : 특정 영역에 다른 문서를 포함할 때 사용
	2. 선언부(<%! %>) : 전역변수 또는 메소드 선언할 때 사용
	3. 스크립트릿(<% %>) : 자바코드 작성할 때 사용
		- service() 메소드 영역에 추가되는 코드
	4. 표현식(<%= %>) : 값을 출력(변수값 또는 메소드 리턴값 표시할 때) - out.write()에 해당
		- out 객체 사용 출력 
	 --%>
</body>
</html>