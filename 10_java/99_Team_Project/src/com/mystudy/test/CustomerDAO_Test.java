package com.mystudy.test;

import java.util.Scanner;

public class CustomerDAO_Test {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		CustomerVO vo = null;
		CustomerDAO dao = new CustomerDAO();
		int result = 0;
		int select = 0;

		String id = null;
		String password;
		String name;
		String tel;
		
		//아이디, 비밀번호, 이름 조건
		String idpwForm = "^[a-zA-Z0-9]{3,8}$"; //아아디 비밀번호 조건 : 3~8자리, 영문소문자, 대문자, 숫자 가능
		String nameForm = "^[가-힣]{1,12}$"; //이름 조건 : 한글만 가능, 1~12자리
		String telForm = "^010-\\d{4}-\\d{4}$";//전화번호 입력 조건

			while (true) {
				System.out.println("===========반갑습니다 회원가입 페이지 입니다.=======");
				System.out.println("[1.회원가입    2.회원 정보 수정    3.회원 탈퇴    4.가입종료]");
				System.out.println("========================================");
				System.out.println("[메뉴 선택]");
				select = Integer.parseInt(scan.nextLine());
				
				while (true) {
					switch (select) {
					case 1:
						while (true) {
							System.out.print("아이디 : ");
							id = scan.nextLine();
							if (id.matches(idpwForm)) {
								if (dao.checkId(id)) {
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
						vo = new CustomerVO(id, password, name, tel);
						dao.joinStart(vo);
						break;

					case 2:
						while (true) {
							System.out.print("아이디 : ");
							id = scan.nextLine();
							System.out.print("비밀번호 :");
							password = scan.nextLine();
							if (!dao.checkIdPassword(id, password)) {
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
										dao.updatePassword(password, id);
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
										dao.updateName(name, id);
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
										dao.updatetel(tel, id);
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
							id = scan.nextLine();
							System.out.print("비밀번호 :");
							password = scan.nextLine();
							if (!dao.checkIdPassword(id, password)) {
								System.out.println("없는 회원입니다");
							} else {
								vo = new CustomerVO(id, password);
								dao.delete(vo);
								System.out.println("탈퇴 되었습니다.");
								break;
							}
						}
						break;
					case 4:
							System.out.print("가입종료");
							id = scan.nextLine();
						    break;
						
					}
					break;
				}
			}
		}
}