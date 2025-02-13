package com.mystudy.jdbc_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO, Dao : Data Access Object / Database Access Object
//데이터(데이터베이스-DB)와 연동해서 CRUD를 구현한 클래스
public class MemberDAO {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "mystudy";
	private static final String PASSWORD = "mystudypw";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//드라이버 로딩 처리
	//static 초기화 구문
	static {
		try {
			Class.forName(DRIVER);
			System.out.println(">> JDBC 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생] 드라이버 로딩 실패!!!");
		}
	}
	
	//SELECT : 테이블 전체 데이터 조회 - selectAll : List<MemberVO>
	//SELECT : 하나의 데이터 조회(ID) - selectOne : MemberVO
	//SELECT : 하나의 데이터 조회(VO) - selectOne : MemberVO
	//INSERT : VO 객체를 받아서 입력 - insertOne : int
	//UPDATE : VO 객체를 받아서 수정 - updateOne : int
	//DELETE : VO 객체를 받아서 삭제 - deleteOne : int
	//DELETE : 키값(id) 받아서 삭제 - deleteOne : int
	
	//로그인 처리 : id, password 값을 받아서 동일한 데이터가 있으면 true
	//			없으면 false 리턴
	// boolean checkIdPassword(id, password)
	//-----------------------------------------------------
	
	//SELECT : 테이블 전체 데이터 조회 - selectAll : List<MemberVO>
	public List<MemberVO> selectAll() {
		List<MemberVO> list = new ArrayList<>();
		
		//DB연결 - Connection 객체 생성(DB와 연결된)
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//PreparedStatement 객체 생성하고 SQL문 실행
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID, NAME, PASSWORD, PHONE, ADDRESS ");
			sql.append("  FROM MEMBER ");
			sql.append(" ORDER BY ID ");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			//SQL문 실행 결과에 대한 처리
			while (rs.next()) {
				/*
				MemberVO mvo = new MemberVO(
						rs.getString("ID"), 
						rs.getString("NAME"),
						rs.getString("PASSWORD"), 
						rs.getString("PHONE"),
						rs.getString("ADDRESS"));
				list.add(mvo);
				*/
				
				list.add(new MemberVO(rs.getString("ID"), 
						rs.getString("NAME"),
						rs.getString("PASSWORD"), 
						rs.getString("PHONE"),
						rs.getString("ADDRESS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//사용객체 close
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//INSERT : VO 객체를 받아서 입력 - insertOne : int
	public int insertOne(MemberVO member) {
		int result = 0;
		try {
			//DB연결 - Connection 객체 생성(DB와 연결된)
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//SQL문장을 작성해서 Statement에 전달하고 SQL문 실행 요청
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MEMBER ");
			sql.append("	   (ID, NAME, PASSWORD, PHONE, ADDRESS) ");
			sql.append("VALUES (?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			//?(바인딩변수)에 값 매칭 시키기
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());
			
			//SQL 실행 요청 - INSERT, UPDATE, DELETE : executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		
		return result;
	}

	//INSERT : List<MemeberVO>를 받아서 입력 - insertOne : int
	public int insertList(List<MemberVO> list) {
		int result = 0;
		try {
			//DB연결 - Connection 객체 생성(DB와 연결된)
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//SQL문장을 작성해서 Statement에 전달하고 SQL문 실행 요청
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MEMBER ");
			sql.append("	   (ID, NAME, PASSWORD, PHONE, ADDRESS) ");
			sql.append("VALUES (?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			//autocommit 상태 확인
			System.out.println("Autocommit 상태 : " + conn.getAutoCommit());
			
			//트랜잭션 처리를 위해 autocommit 해제
			conn.setAutoCommit(false);
			
			for (MemberVO member : list) {
				//?(바인딩변수)에 값 매칭 시키기
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getName());
				pstmt.setString(3, member.getPassword());
				pstmt.setString(4, member.getPhone());
				pstmt.setString(5, member.getAddress());
				
				//SQL 실행 요청 - INSERT, UPDATE, DELETE : executeUpdate()
				result += pstmt.executeUpdate();
				/*
				try {
					System.out.println("> 입력성공 : " + member.getId());
				} catch (SQLException e) {
					System.out.println("> 입력실패 : " + member.getId());
				}
				*/
			} //end for
			conn.commit(); //입력된 데이터 commit 처리
			conn.setAutoCommit(true); //autocommit 상태로 전환
		} catch (SQLException e) {
			//e.printStackTrace();
			try {
				result = 0;
				conn.rollback(); //전체데이터 롤백 처리
				conn.setAutoCommit(true); //autocommit 상태로 전환
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		
		return result;
	}
	//로그인처리를 위한 id, password 체크
	//boolean checkIdPassword(id, password)
	
	public boolean checkIdPassword(String id, String password) {
		boolean result = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			//PreparedStatement 객체 생성하고 SQL문 실행
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ID, NAME, PASSWORD, PHONE, ADDRESS ");
			sql.append("  FROM MEMBER ");
			sql.append(" WHERE ID = ? AND PASSWORD = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			//SQL문 실행 결과에 대한 처리
			if (rs.next()) {//데이터가 있으면
				System.out.println(">> 데이터 있음");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//사용객체 close
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}
		return result;
	}
	
	//(실습)일괄 삭제 : 삭제목록(list)을 받아서 일괄 삭제 처리
	public int deleteList(List<MemberVO> list) {
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM MEMBER ");
			sql.append(" WHERE ID IN (?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			for (MemberVO member : list) {
				pstmt.setString(1, member.getId());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return result;
	}
}
