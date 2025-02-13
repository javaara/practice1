package com.mystudy.test;
import java.util.Scanner;

public class Reservation {
	/*
	1. 회원제 예약 시스템 - 로그인 필요
	2. 메뉴 : 예매 / 취소 / 조회 / 종료
		"[1]예매                [2]취소                [3]조회                [4]종료                "
	3. 좌석 : vip, sr, r, s... 좌석 개수는 일단 알아서
			빈자리 : □ , 예매된자리 : ■
	4. 순서 : 로그인 - 메뉴선택 - 콘서트 선택 - 콘서트장 선택 - 좌석 등급 선택 - 자리 번호 선택 - 결제 - 로그아웃
	5. 오류메시지 : 메뉴에 없는 것 선택했을 때
	6. 취소할 때는 아이디, 비번 받아서 해당 자리 취소
	*/
	
	//좌석 번호에 좌석 번호와 등급이 같이 나타나게 해서 pk 위반 안되게!!!!!
	//VO랑 DAO도 바꾸기~~~
	//haveseat 입력되게 만들기!!!(빈자리, 선택된자리)
	//seatprice 설정(vip sr r s)
	//hallnum 다른 테이블에서 가져오기
	//취소 어떻게 할지 정하기
	 
	int seat_price;
	String have_seat;
	int concert_num = 1; //임시로 설정
	
	Scanner scan = new Scanner(System.in);
	
	int seatNum[] = new int[25];
	String seatVip[] = new String[25];
	String seatSr[] = new String[25];
	String seatR[] = new String[25];
	String seatS[] = new String[25];

	Reservation() {
		for (int i = 0; i < seatVip.length; i++) {
			seatNum[i] = i + 1;
			seatVip[i] = "□\t";
			seatSr[i] = "□\t";
			seatR[i] = "□\t";
			seatS[i] = "□\t";
		}
	}
	
	public void startReserv() {
		boolean bool = true;
		System.out.println("<<< 콘서트 예매가 시작되었습니다");
		while (bool) {
			Integer choice;
			menu();
			System.out.print("원하는 작업을 선택해주세요(1~4) : ");		
			try {
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
					case 1: //예매작업
						showSeat();
						grade();
						PaymentDAO pdao = new PaymentDAO();
						pdao.startPayment();
						break;
					case 2: //취소작업
						cancel();				
						break;
					case 3: //예매 내역 조회
						showSeat();
						break;
					case 4: //프로그램 종료
						System.out.println("<<< "
								+ "콘서트 예매가 종료되었습니다.");
						bool = false;
						break;
					default:
						System.out.println("[주의] 1~4 사이의 숫자를 선택하세요");
				}
			}
			catch(Exception e) {
				System.out.println("[주의] 숫자만 입력 가능합니다.");
				
			}
		}
		
//		SEAT_INFO_VO svo = new SEAT_INFO_VO(num, grade, concert_num, seat_price, have_seat);
//		SEAT_INFO_DAO sdao = new SEAT_INFO_DAO();
//		sdao.insert(svo);

	}
	
	//취소 메소드
	public void cancel() {
		//아이디, 비번 체크 일치할 경우 해당 좌석 취소
		System.out.println("취소를 위해 아이디와 비밀번호를 다시 입력해주세요");
		inputIdPw();		
	}
	
	//로그인 - 취소 메소드
	private String customer_id;
	private String pw;
	
	protected boolean inputIdPw() {
		boolean inputLog = false;
		
		while(true) {
			System.out.print("아이디 : ");
			customer_id = scan.nextLine(); // 아이디 입력
			
			System.out.print("비밀번호 : ");
			pw = scan.nextLine(); // 비밀번호 입력
			
			CustomerLogVO lvo = new CustomerLogVO(customer_id, pw);
			 
			boolean logTrue = new CustomerLogDAO().checkIdPassword(lvo);
			
			if (logTrue) {
				inputLog = true;
				System.out.println(grade + num + " 좌석이 정상적으로 취소되었습니다.");
				if (grade.equalsIgnoreCase("VIP")) {
					seatVip[num - 1] = "□\t";
					showSeat();
					break;
				}
				if (grade.equalsIgnoreCase("SR")) {
					seatSr[num - 1] = "□\t";			
					showSeat();
					break;
				}
				if (grade.equalsIgnoreCase("R")) {
					seatR[num - 1] = "□\t";
					showSeat();
					break;
				}
				if (grade.equalsIgnoreCase("S")) {
					seatS[num - 1] = "□\t";
					showSeat();
					break;
				}
				break;
			} else {
				System.out.println("다시 입력해주세요.");
			}			
		}
		return inputLog;
	}
	
	
	//예약할 좌석 등급 선택 메소드
	String grade;		
	public void grade() {
		while (true) {
			System.out.print("좌석 등급을 선택해주세요(VIP/SR/R/S) : ");			
			grade = scan.nextLine();
			if (grade.equalsIgnoreCase("VIP")) {
				seatNum(seatVip);
				showSeat();
				break;
			}
			if (grade.equalsIgnoreCase("SR")) {
				seatNum(seatSr);				
				showSeat();
				break;
			}
			if (grade.equalsIgnoreCase("R")) {
				seatNum(seatR);
				showSeat();
				break;
			}
			if (grade.equalsIgnoreCase("S")) {
				seatNum(seatS);
				showSeat();
				break;
			}
			else {
				System.out.println("[주의] 없는 좌석 등급입니다.");
			}
		
		}	
	}

	//예약할 좌석 번호 선택 메소드
	String seat[];
	Integer num;
	public void seatNum(String seat[]) {
		while (true) {
			try {
				System.out.print("좌석 번호를 선택해주세요(1~25) : ");
				num = Integer.parseInt(scan.nextLine());
				
				if (num < 1 || num > 25) {
					System.out.println("없는 좌석 번호입니다.");
			
				} else {
					if (seat == seatVip) {
						if (seatVip[num - 1].toString().equals("■\t")) {
							System.out.println("이미 선택된 자리입니다");
							continue;
						} 
						else {
							seatVip[num - 1] = "■\t";		
						}
					}
					if (seat == seatSr) {
						if (seatSr[num - 1].toString().equals("■\t")) {
							System.out.println("이미 선택된 자리입니다");
							continue;
						} 
						else {
							seatSr[num - 1] = "■\t";	
						}
					}
					if (seat == seatR) {
						if (seatR[num - 1].toString().equals("■\t")) {
							System.out.println("이미 선택된 자리입니다");
							continue;
						} 
						else {
							seatR[num - 1] = "■\t";				
						}
					}
					if (seat == seatS) {
						if (seatS[num - 1].toString().equals("■\t")) {
							System.out.println("이미 선택된 자리입니다");
							continue;
						} 
						else {
							seatS[num - 1] = "■\t";
						}
					}
					break;
				}				
			}
			catch (Exception e) {
				System.out.println("[주의] 숫자만 입력 가능합니다.");
			}	
		}
		if (seat == seatVip) {
			seat_price = 150000;
		}
		if (seat == seatSr) {
			seat_price = 130000;
		}
		if (seat == seatR) {
			seat_price = 100000;
		}
		if (seat == seatS) {
			seat_price = 90000;
		}
		
		if (seat[num-1].toString().equals("□")) {
			have_seat = "배정안됨";
		}
		else {
			have_seat = "배정됨";
		}
	}
	

	//좌석 조회
	public void showSeat() {
		System.out.println();
		System.out.print("\t");
		for (int i = 0; i < seatVip.length; i++) {
			System.out.print(seatNum[i] + "\t");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("VIP\t");
		for (int i = 0; i < seatVip.length; i++) {
			System.out.print(seatVip[i]);
		}
		System.out.println();
		System.out.print("SR\t");
		for (int i = 0; i < seatSr.length; i++) {
			System.out.print(seatSr[i]);
		}
		System.out.println();
		System.out.print("R\t");
		for (int i = 0; i < seatR.length; i++) {
			System.out.print(seatR[i]);
		}
		System.out.println();
		System.out.print("S\t");
		for (int i = 0; i < seatS.length; i++) {
			System.out.print(seatS[i]);
		}
		System.out.println();
	}	
		
	//메뉴 선택
	public void menu() {
		System.out.println("======================= 콘서트 예매 시스템 =======================");
		System.out.println("");
		System.out.println("[1]예매                [2]취소                [3]조회                [4]종료                ");
		System.out.println("");
		System.out.println("============================================================");
	}
	
}
