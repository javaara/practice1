package com.mystudy.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerDAO {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "mdguest";
	private static final String PASSWORD = "mdguest";
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//드라이버 로딩
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
		}
	}
	
	//DB에 입력처리
	public int joinStart(CustomerVO Customer_Join) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO CUSTOMER ");
			sql.append("	(CUSTOMER_ID, CUSTOMER_PW, CUSTOMER_NAME, CUSTOMER_TEL) ");
			sql.append("VALUES (?, ?, ?, ?) ");
			pstmt = conn.prepareStatement(sql.toString());
			
			int idx = 1;
			pstmt.setString(idx++, Customer_Join.getId());
			pstmt.setString(idx++, Customer_Join.getPassword());
			pstmt.setString(idx++, Customer_Join.getName());
			pstmt.setString(idx++, Customer_Join.getTel());
			
			return pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1;
	}
	
	//비밀번호 수정 메소드
	public int updatePassword(String password, String customer_id) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CUSTOMER SET CUSTOMER_PW = ? WHERE CUSTOMER_ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, password);
			pstmt.setString(2, customer_id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1;
	}
	
	//이름 수정 메소드
	public int updateName(String name, String customer_id) {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CUSTOMER SET CUSTOMER_NAME = ? WHERE CUSTOMER_ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, name);
			pstmt.setString(2, customer_id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1;	
	}
	
	//전화번호 수정 메소드
	public int updatetel(String tel, String id) {
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CUSTOMER SET CUSTOMER_TEL = ? WHERE CUSTOMER_ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, tel);
			pstmt.setString(2, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
		}
		return -1;	
	}
	
	public int delete(CustomerVO Customer_Join) {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM CUSTOMER ");
			sql.append("	WHERE CUSTOMER_ID = ? AND CUSTOMER_PW = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, Customer_Join.getId());
			pstmt.setString(2, Customer_Join.getPassword());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmt(conn, pstmt);
	}
		return -1;
	}
	
	public boolean checkId(String customer_id) {
		boolean result = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUSTOMER_ID FROM CUSTOMER WHERE CUSTOMER_ID = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, customer_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}		
		return result;
	}
	
	public boolean checkIdPassword(String customer_id, String password) {
		boolean result = false;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUSTOMER_ID, CUSTOMER_PW FROM CUSTOMER WHERE CUSTOMER_ID = ? AND CUSTOMER_PW = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, customer_id);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}		
		return result;
	}
	
	public boolean checkTel(String tel) {
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUSTOMER_TEL FROM CUSTOMER WHERE CUSTOMER_TEL = ?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, tel);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {//데이터가 있으면
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		}		
		return result;
	}
	
	public void join() {
		Scanner scan = new Scanner(System.in);
		
		CustomerVO vo = null;
		CustomerDAO dao = new CustomerDAO();
		int result = 0;
		int select = 0;

		String customer_id = null;
		String password;
		String name;
		String tel;
		
		//아이디, 비밀번호, 이름 조건
		String idpwForm = "^[a-zA-Z0-9]{3,8}$"; //아아디 비밀번호 조건 : 3~8자리, 영문소문자, 대문자, 숫자 가능
		String nameForm = "^[가-힣]{1,12}$"; //이름 조건 : 한글만 가능, 1~12자리
		String telForm = "^010-\\d{4}-\\d{4}$";//전화번호 입력 조건

			while (true) {
				System.out.println("========== 반갑습니다 회원가입 페이지 입니다. ==========");
				System.out.println(" [1.회원가입    2.회원 정보 수정    3.회원 탈퇴    4.가입종료]");
				System.out.println("==============================================");
				System.out.println("[메뉴 선택]");
				select = Integer.parseInt(scan.nextLine());
				
				switch (select) {
				case 1:
					while (true) {
						System.out.print("아이디 : ");
						customer_id = scan.nextLine();
						if (customer_id.matches(idpwForm)) {
							if (dao.checkId(customer_id)) {
								System.out.println("존재하는 아이디 입니다!");
							} else {
								break;
							}
						} else {
							System.out.println("다른 아이디를 입력하세요.");
						}
					}

					while (true) {
						System.out.print("비밀번호 :");
						password = scan.nextLine();
						if (password.matches(idpwForm)) {
							break;
						} else {
							System.out.println("조건에 맞지 않습니다 다시 입력하세요");
						}
					}

					while (true) {
						System.out.print("이름 : ");
						name = scan.nextLine();
						if (name.matches(nameForm)) {
							break;
						} else {
							System.out.println("이름을 확인하세요");
						}
					}

					while (true) {
						System.out.print("핸드폰 번호 : ");
						tel = scan.nextLine();
						if (tel.matches(telForm)) {
							if (dao.checkTel(tel)) {
								System.out.println("존재하는 핸드폰번호 입니다.");
							} else {
								break;
							}
						} else {
							System.out.println("다시 입력 하세요.");
						}
					}
					vo = new CustomerVO(customer_id, password, name, tel);
					dao.joinStart(vo);
					System.out.println(" 로그인 화면으로 넘어갑니다");
					CustomerLogMethod cl = new CustomerLogMethod();
					cl.inputIdPw();
					break;

				case 2:
					while (true) {
						System.out.print("아이디 : ");
						customer_id = scan.nextLine();
						System.out.print("비밀번호 :");
						password = scan.nextLine();
						if (!dao.checkIdPassword(customer_id, password)) {
							System.out.println("가입내역이 없습니다.");
						} else {
							break;
						}
					}

					while (true) {
						System.out.println("================ 회원 정보 변경 ===================");
						System.out.println("[1.비밀번호 변경    2.이름 변경    3.전화번호 변경    4.메인화면으로]");
						System.out.println("==============================================");
						select = Integer.parseInt(scan.nextLine());

						if (select == 1) {
							while (true) {
								System.out.print("변경할 비밀번호 : ");
								password = scan.nextLine();
								if (password.matches(idpwForm)) {
									dao.updatePassword(password, customer_id);
									System.out.println(password + "로 바뀌었습니다");
									break;
								} else {
									System.out.println("비밀번호 확인");
								}
							}
						}
						if (select == 2) {
							while (true) {
								System.out.print("변경할 이름 : ");
								name = scan.nextLine();
								if (name.matches(nameForm)) {
									dao.updateName(name, customer_id);
									System.out.println(name + "으로 바뀌었습니다.");
									break;
								} else {
									System.out.println("이름확인");
								}
							}
						}
						if(select == 3) {
							while(true) {
								System.out.print("변경할 전화번호 : ");
								tel = scan.nextLine();
								if(tel.matches(telForm)) {
									dao.updatetel(tel, customer_id);
									System.out.println(tel +"으로 변경되었습니다.");
									break;
								} else {
									System.out.println("전화번호확인");
								}
							}
						}
						if(select == 4) {
							System.out.println("처음화면");
							break;
						}
					}
					break;
					
				case 3:
					while (true) {
						System.out.print("아이디 : ");
						customer_id = scan.nextLine();
						System.out.print("비밀번호 :");
						password = scan.nextLine();
						if (!dao.checkIdPassword(customer_id, password)) {
							System.out.println("없는 회원입니다");
						} else {
							vo = new CustomerVO(customer_id, password);
							dao.delete(vo);
							System.out.println("탈퇴 되었습니다.");
							break;
						}
					}
					break;
				case 4:
						System.out.print("가입종료");
						System.out.println(" 로그인 화면으로 넘어갑니다");
						cl = new CustomerLogMethod();
						cl.inputIdPw();
					    break;			
				}
				break;
				
			}
		}

}























