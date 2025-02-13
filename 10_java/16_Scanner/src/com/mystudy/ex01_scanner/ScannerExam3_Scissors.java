package com.mystudy.ex01_scanner;

import java.util.Scanner;

public class ScannerExam3_Scissors {

	public static void main(String[] args) {
		/* Scanner를 이용한 가위,바위,보 게임
		컴퓨터와 함께하는 가위,바위,보 게임
		0. 컴퓨터가 가위, 바위, 보를 선택(Math.random())
		1. 1.가위, 2.바위, 3.보, 0.종료 선택 메뉴 출력
		2. 사람이 선택 값 입력
		3. 결과 확인 후 승자, 패자 출력
		(반복) 게임 반복 진행 - 0 선택시 종료
		*/
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.print("1. 가위 2. 바위 3. 보 0. 종료 / 선택 : ");
			int user = scan.nextInt();
			int com = (int) (Math.random() * 3 + 1);
			
			if (user == 1) {
				String users = "가위";
				if (com == 1) {
					String coms = "가위";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 비겼습니다!");
				}
				else if	(com == 2) {
					String coms = "바위";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 컴퓨터 승리!");
				}
				else if	(com == 3) {
					String coms = "보";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 사용자 승리!");
				}
			}
			else if (user == 2) {
				String users = "바위";
				if (com == 1) {
					String coms = "가위";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 사용자 승리!");
				}
				else if	(com == 2) {
					String coms = "바위";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 비겼습니다!");
				}
				else if	(com == 3) {
					String coms = "보";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 컴퓨터 승리!");
				}
			}
			else if (user == 3) {
				String users = "보";
				if (com == 1) {
					String coms = "가위";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 컴퓨터 승리!");
				}
				else if	(com == 2) {
					String coms = "바위";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 사용자 승리!");
				}
				else if	(com == 3) {
					String coms = "보";
					System.out.println("사용자 : " + users + ", 컴퓨터 : " + coms + ", 결과 : 비겼습니다!");
				}
			}
			else if (user == 0) {
				System.out.println(">>> 종료되었습니다");
				break;
			}
			System.out.println("---------------------------------");
			
			/*
			Scanner scan = new Scanner(System.in);
		
			boolean oneMoreGame = true;
			
			while (oneMoreGame) {
				//0. 컴퓨터가 가위, 바위, 보를 선택(Math.random())
				int comSelect = (int) (Math.random() * 3 + 1);//1,2,3
				//comSelect = 1;
				//System.out.println("컴퓨터가 선택한 숫자 : " + comSelect);
				
				//1. 1.가위, 2.바위, 3.보  선택 메뉴 출력
				//2. 사람이 선택 값 입력
				System.out.println("1.가위, 2.바위, 3.보");
				System.out.print(">> 당신의 선택은(1~3)? ");
				int select = Integer.parseInt(scan.nextLine());
				
				String strComputer = "";
				if (comSelect == 1) {
					strComputer = "가위";
				} else if (comSelect == 2) {
					strComputer = "바위";
				} else if (comSelect == 3) {
					strComputer = "보";
				} else {
					System.out.println("1~3중에 하나를 선택해야 합니다.");
				}
				
				String strPerson = "";
				switch (select) {
				case 1: 
					strPerson = "가위"; break;
				case 2:
					strPerson = "바위"; break;
				case 3:
					strPerson = "보"; break;
				default:
					System.out.println("1~3중에 하나를 선택해야 합니다.");
				}
				
				//선택값 출력
				System.out.println("-----------------");
				System.out.println(">> 나의 선택 : " + strPerson);
				System.out.println(">> 컴퓨터의 선택 : " + strComputer);
				
				//3. 결과 확인 후 승자, 패자 출력
				//사람이 "가위"인 경우
				String result = "";
				if (strPerson.equals("가위")) {
					//컴퓨터 : 가위, 바위, 보
					if (strComputer.equals("가위")) {
						result = "무승부";
					}
					if (strComputer.equals("바위")) {
						result = "컴퓨터 승";
					}
					if (strComputer.equals("보")) {
						result = "내가 이겼다!!!";
					}
				}
				//사람이 "바위"인 경우
				if (strPerson.equals("바위")) {
					//컴퓨터 : 가위, 바위, 보
					if (strComputer.equals("가위")) {
						result = "내가 이겼다!!!";
					}
					if (strComputer.equals("바위")) {
						result = "무승부";
					}
					if (strComputer.equals("보")) {
						result = "컴퓨터 승";
					}
				}
				//사람이 "보"인 경우
				if (strPerson.equals("보")) {
					//컴퓨터 : 가위, 바위, 보
					if (strComputer.equals("가위")) {
						result = "컴퓨터 승";
					}
					if (strComputer.equals("바위")) {
						result = "내가 이겼다!!!";
					}
					if (strComputer.equals("보")) {
						result = "무승부";
					}
				}
				//결과 출력
				System.out.println("---- 결과 ---");
				System.out.println(">>> " + result);
				
				System.out.println();
				System.out.print(">> 한 번 더 할래(y:계속 /n:중단)? ");
				String oneMore = scan.nextLine();
				if (oneMore.equalsIgnoreCase("n")) {
					System.out.println(">> 게임을 중단합니다.");
					oneMoreGame = false;
				}
			}
			*/
		}
	}
}
