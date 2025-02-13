package com.mystudy.ex02_extends;
/* 클래스의 상속(extends) 실습
실습1: Phone 클래스를 상속 확장(extends)
WebPhone 클래스 작성
	- Phone 클래스 상속 확장해서 만들기
	- 생성자 정의
		* 폰번호(phoneNo)만 받아서 객체(인스턴스) 생성
		* 타입 값은 "WebPhone 타입"으로 작성
	- 기능 : 전화걸고, 받고, 정보보기, 웹검색 
	- 웹검색 기능
		webSearch() : ">> WebPhone - 웹 검색" 문구 화면 출력
*/
class WebPhone extends Phone {
	public WebPhone(String phoneNo) {
		super("WebPhone 타입", phoneNo);
	}
	
	public void webSearch() {
		System.out.println(">> WebPhone - 웹검색");
	}
}
