package com.mystudy.teamproject.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mystudy.teamproject.admin1.concertView;

public class Admin {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.0.78:1521:xe";
	private static final String USER = "MDGUEST";
	private static final String PASSWORD = "mdguest";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	Scanner scan = new Scanner(System.in);

	Admin ad = null;
	
//	---------------------------------------------------------------------------------------드라이버 로딩
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
		}
	}
//	---------------------------------------------------------------------------------------DB에 입력처리
//콘서트 정보
	public List<ConcertInfoVO> selectAll() {
		 		
		   List<ConcertInfoVO> list = new ArrayList<>();

		 try {
			 conn = DriverManager.getConnection(URL, USER, PASSWORD);

			 StringBuilder sql = new StringBuilder();
			 sql.append("SELECT CONCERT_NUM ,CONCERT_NAME , HALL_NUM , CONCERT_DATE");
			 sql.append(" FROM SCHEDULE_INFO ");

			 pstmt = conn.prepareStatement(sql.toString());

			 rs = pstmt.executeQuery();
			 String str = "";
			 while(rs.next()) {
				 str += rs.getInt(1)+"\t\t";
				 str += rs.getString(2)+"\t";
				 str += rs.getInt(3)+"\t";
				 str += rs.getString(4);

				 list.add(new ConcertInfoVO(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			 }
		 }catch (SQLException e) {
					e.printStackTrace();
		 }finally {
					JDBC_Close.closeConnStmtRs(conn, pstmt, rs);
		 }
		 return list;	
		 }	
//------------------------------------------------------------------------------------------	
//콘서트 위치정보
	public List<LocationVO> location(int concert_num) {
		 
		 List<LocationVO> list = new ArrayList<>();
		 
		//DB연결 - Connection 객체 생성(DB와 연결된)
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CONCERT_NUM,HALL_LOCATION,SEAT_SUM");
			sql.append(" FROM HALL_INFO ");
			sql.append("WHERE CONCERT_NUM = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, concert_num);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()) {
				str += rs.getInt(1)+"\t\t";
				str += rs.getString(2)+"\t";
				str += rs.getInt(3);

				list.add(new LocationVO(rs.getInt(3),rs.getString(2),rs.getInt(1)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	 
		}
//---------------------------------------------------------------------------	
	//회원정보조회
	public  ArrayList<CustomerVO> MemList1() {
		ArrayList <CustomerVO> list = new ArrayList();
			
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUSTOMER_ID, CUSTOMER_PW,CUSTOMER_NAME,CUSTOMER_TEL");
			sql.append(" FROM CUSTOMER");

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			CustomerVO str;
			while(rs.next()) { str = new CustomerVO(
				 rs.getString(1)+"\t",	
				 rs.getString(2)+"\t",	
				 rs.getString(3)+"\t",	
				 rs.getString(4));	

			list.add(str);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		}	
//------------------------------------------------------------------------------	
//예매조회

	public List<BookListVO> BookList() {
		
		List<BookListVO> list = new ArrayList();
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT T.RESERVATION_NUM ,T.CONCERT_NAME,T.SEAT_NUM,T.CUSTOMER_ID,P.PAYMENT_DATE");
			sql.append(" FROM TICKET T, PAYMENT P ");
			sql.append(" WHERE T.PAYMENT_UID = P.PAYMENT_UID");
			

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				list.add(new BookListVO(rs.getInt("RESERVATION_NUM"),
						rs.getString("CONCERT_NAME"),rs.getString("SEAT_NUM"),
						rs.getString("CUSTOMER_ID"), rs.getString("PAYMENT_DATE")));
				
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//-----------------------------------------------------------------------	

	//콘서트통계
	public List<concertTotalVO> concertTotal() {
			
		List <concertTotalVO>list = new ArrayList();
			
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT T1.CONCERT_NUM, T1.CONCERT_NAME, T1.CNT, T1.SUM_PRICE, H.SEAT_SUM, (T1.CNT/H.SEAT_SUM)*100 AS PER ");
			sql.append(" FROM HALL_INFO H, ( SELECT CONCERT_NUM , CONCERT_NAME, COUNT(*) AS CNT, SUM(SEAT_PRICE) AS SUM_PRICE FROM TICKET GROUP BY CONCERT_NUM, CONCERT_NAME ) T1 ");
			sql.append("WHERE H.CONCERT_NUM = T1.CONCERT_NUM ");

			pstmt = conn.prepareStatement(sql.toString());
				
			rs = pstmt.executeQuery();
			
				while(rs.next()) 
				{	
					list.add(new concertTotalVO(
						rs.getString("CONCERT_NAME"),
						rs.getInt("CONCERT_NUM"),
						rs.getInt("CNT"),
						rs.getInt("SUM_PRICE"),
						rs.getInt("SEAT_SUM"),
						rs.getInt("PER")
						));
				}
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}	
	
//--------------------------------------------------------------------------------	
	//메인메소드
    public void AdminMenu() {
            boolean run = false;
            concertView view = new concertView();
            
    while(run == false) {        
            
            System.out.println("1.공연정보 2.회원목록 3.예매목록 4.통계  5.종료");
            System.out.print("메뉴를 선택하세요 : ");

            try {

                    String selectMenu = scan.nextLine();
                    
                    if("1".equals(selectMenu)) {

                            List<ConcertInfoVO> v2 = concertList();

                            int i = 0;

                            for(ConcertInfoVO viewV2 : v2) {

                                    System.out.println(v2.get(i).getConcert_num()+"\t"+v2.get(i).getConcert_name());
                                    i++;
                            }
                            System.out.println("정보를 보고싶은 영화를 선택하세요");
                            String selectConcert = scan.nextLine();

                            view.selectConcertView(v2,Integer.parseInt(selectConcert)-1);

                    }else if("2".equals(selectMenu)) {
                            MemList();
                            run = false;
                            
                    }else if("3".equals(selectMenu)) {
                            ConcertBookLists();
                            run = false;
                    
                    }else if("4".equals(selectMenu)) {
                            System.out.println("회원통계페이지");
                            ConcertTotal();
                            run = false;
                    
                    }else if("5".equals(selectMenu)){
                            System.out.println("종료하시겠습니까 y/n");
                            String exit = scan.nextLine();
                            if(exit.equals("y")) {
                                    System.out.println("종료되었습니다");
                                    break;
                                    
                            }
                    }else {
                            System.out.println("주의 1-5까지만 입력가능합니다");
                    }
            }catch(Exception e) {
                    e.printStackTrace();
                    System.out.println("[주의 ] 1-5까지만 입력해주세요");
            }
    }
    
    }
		
	
	
//--------------------------------------------------------------------------------------------
//콘서트리스트 메소드
	public List<ConcertInfoVO> concertList() {
		 
		 System.out.println("공연정보");
		 
		 System.out.println("번호\t공연명\t  ");
		 System.out.println("---------------------------------------------------------");
		 
		 List<ConcertInfoVO> vo1 = selectAll();
		 
		 return vo1;
	 }
//-------------------------------------------------------------------------------------------
//콘서트정보 메소드
	public void concertInfo(List<ConcertInfoVO> con) {
		 
		 System.out.println("콘서트 정보");
		 
		 System.out.println("공연장소 정보");
		 
		 System.out.println(con.get(0).getConcert_date());
		 
		 
		 List<LocationVO> info = location(con.get(0).getConcert_num());
		 
		 for (LocationVO mvo : info) {
				System.out.println( "콘서트번호" +mvo.concert_num +
									"콘서트위치" +mvo.hall_location+
									"총좌석수"+mvo.seat_sum);
			}
	 }
//------------------------------------------------------------------
//멤버정보
	public ArrayList<CustomerVO> MemList() {
		
		System.out.println("회원조회페이지");
		System.out.println("아이디\t\t비밀번호\t\t이름\t\t전화번호");
		
		ArrayList<CustomerVO> vo = MemList1();
		
		for(CustomerVO vo2 : vo) {

			System.out.print(vo2.getCustomer_id());
			System.out.print("\t");
			System.out.print(vo2.getPassword());
			System.out.print("\t");
			System.out.print(vo2.getName());
			System.out.print("\t");
			System.out.print(vo2.getTel());
			System.out.print("\n");
		}
		return vo;
	}
//-----------------------------------------------------------------
//예매목록
	
	public List<BookListVO> ConcertBookLists() {
		
		System.out.println("예매목록 현황");
		System.out.println("예매번호         콘서트이름                 좌석번호                     아이디              예매날짜");
		List<BookListVO> vo=BookList();
		
		int i =0;
		
		for (BookListVO vo1 : vo) {
			
			System.out.println(vo.get(i).getReservation_num()+"\t"+
					"    "+vo.get(i).getConcert_name()+"\t"+
					" "+vo.get(i).getSeat_num()+"\t"+
					" "+vo.get(i).getCustomer_id()+"\t"+
					"    "+vo.get(i).getPayment_date()+"\t");
			
			i++;
		}
		return vo;
	}
//------------------------------------------------------------------
//통계 콘서트당 통계 콘서트번호, 콘서트이름, 콘서트 예매수, 가격, 총좌석수 , 예매율
	
	
	public void ConcertTotal() {
		
		System.out.println("\t\t     콘서트 통계페이지");
		System.out.println("---------------------------------------------------------");
		System.out.println("콘서트번호        콘서트이름      콘서트예매수        총가격       총좌석수     예매율 ");
		System.out.println("---------------------------------------------------------");
		
		
		List <concertTotalVO> list = new ArrayList();
		
		
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  T1.CONCERT_NUM, T1.CONCERT_NAME, T1.CNT, T1.SUM_PRICE,  H.SEAT_SUM, (T1.CNT/H.SEAT_SUM)*100 AS PER  ");
			sql.append(" FROM HALL_INFO H,  (SELECT CONCERT_NUM , CONCERT_NAME, COUNT(*) AS CNT, SUM(SEAT_PRICE) AS SUM_PRICE FROM TICKET GROUP BY CONCERT_NUM, CONCERT_NAME ) T1  ");
			sql.append("WHERE H.CONCERT_NUM = T1.CONCERT_NUM");
			sql.append(" ORDER BY CONCERT_NUM ");

			pstmt = conn.prepareStatement(sql.toString());
				
			rs = pstmt.executeQuery();
			
				while(rs.next()) 
				{	
					list.add(new concertTotalVO(
						rs.getString("CONCERT_NAME"),
						rs.getInt("CONCERT_NUM"),
						rs.getInt("CNT"),
						rs.getInt("SUM_PRICE"),
						rs.getInt("SEAT_SUM"),
						rs.getInt("PER")
						));
				}
				for (concertTotalVO vo : list) {
					System.out.print(vo.concert_num);
					System.out.print(  "\t     ");
					System.out.print(vo.concert_name);
					System.out.print("\t");
					System.out.print(vo.total_cnt);
					System.out.print("\t");
					System.out.print(vo.total_price);
					System.out.print("\t");
					System.out.print(vo.seat_sum);
					System.out.print("\t");
					System.out.print(vo.seat_per);
					System.out.println("\n");
							
							
				}
		
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------------------------------------------
	
}
