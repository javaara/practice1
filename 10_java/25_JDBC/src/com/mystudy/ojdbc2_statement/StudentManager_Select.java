package com.mystudy.ojdbc2_statement;

//import java.sql.*;
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
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//1. JDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. DB연결  - Connection 객체 생성 <-DriverManager
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"mystudy", "mystudypw");
			
			//3. Statement문 실행(SQL문 실행)
			//3-1. Connection 객체로부터 Statement 객체 생성
			stmt = conn.createStatement();
			
			//3-2. Statement 객체를 이용해서 SQL문 실행
			String sql = "";
			String id = "2019001"; //문자열
			sql += "SELECT ID, NAME, KOR, ENG, MATH, TOT, AVG ";
			sql += "   FROM STUDENT ";
			sql += "  WHERE ID = '" + id + "' ";
			System.out.println("sql : " + sql);
			
			rs = stmt.executeQuery(sql);
			//4. SQL 실행 결과에 대한 처리
			//   -SELECT : 조회(검색) 데이타 결과 값에 대한 처리
			while (rs.next()) {
				String str = "";
				str += rs.getString(1) + "\t";
				str += rs.getString(2) + "\t";
				str += rs.getInt(3) + "\t";
				str += rs.getInt(4) + "\t";
				str += rs.getInt(5) + "\t";
				str += rs.getInt(6) + "\t";
				str += rs.getDouble(7);
				
				System.out.println(str);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		}
		
		
	}

}
