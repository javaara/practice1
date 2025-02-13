package com.mystudy.ojdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager_Select {

	public static void main(String[] args) {
		//JDBC를 이용한 DB 프로그래밍 절차 -------------------
		//0. JDBC 라이브러리 개발환경 설정(빌드경로에 등록)
		//1. JDBC 드라이버 로딩
		//2. DB연결  - Connection 객체 생성 <-DriverManager
		//3. Statement문 실행(SQL문 실행)
		//4. SQL 실행 결과에 대한 처리
		//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
		//   -INSERT, UPDATE, DELETE : int값(건수) 처리
		//5. 클로징 처리에 의한 자원 반납
		//============================================
		
		//0. JDBC 라이브러리 개발환경 설정(빌드경로에 등록)
		//1. JDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println(">> 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] 드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
		
		//2. DB연결  - Connection 객체 생성 <-DriverManager
		//DriverManager.getConnection(url(위치정보), user(사용자정보), password)
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			System.out.println(">> DB연결 성공");
		} catch (SQLException e) {
			System.out.println("[예외] DB연결 실패!!!");
			e.printStackTrace();
		}
		
		//3. Statement문 실행(SQL문 실행)
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//Connection 객체로부터 Statement 객체 생성
			stmt = conn.createStatement();
			
			//SQL문장 준비하고 실행
			String sql = "";
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "  FROM STUDENT ";
			sql += " ORDER BY NAME ";
			
			rs = stmt.executeQuery(sql); //SELECT : excuteQuery
			
			//4. SQL 실행 결과에 대한 처리
			//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
			System.out.println(">> SELECT 결과 처리------------------");
			while (rs.next()) {
				String str = "";
				str += rs.getString("ID") + "\t";
				str += rs.getString("NAME") + "\t";
				str += rs.getInt("KOR") + "\t";
				str += rs.getInt("ENG") + "\t";
				str += rs.getInt("MATH") + "\t";
				str += rs.getInt("TOT") + "\t";
				str += rs.getDouble("AVG");
				
				System.out.println(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//5. 클로징 처리에 의한 자원 반납(객체 생성 순서와 역순으로 진행)
		try {
			if (rs != null) rs.close();
			System.out.println(">> ResultSet close 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) stmt.close();
			System.out.println(">> Statement close 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) conn.close();
			System.out.println(">> Connection close 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//end main

}//end class
