package com.mystudy.ex02_extends;

class PhoneTest {

	public static void main(String[] args) {
		//Phone, Mp3Phone 객체 생성 후 기능 테스트
		System.out.println("=== Phone ===");
		Phone ph1 = new Phone("010-1111-1111");
		ph1.view();
		ph1.call();
		ph1.receiveCall();
		
		Phone ph2 = new Phone("삼성갤럭시", "010-2222-2222");
		ph2.view();
		ph2.call();
		ph2.receiveCall();
		
		System.out.println("=== MP3Phone ===");
		Mp3Phone mp3ph = new Mp3Phone("010-3333-3333");
		mp3ph.view();
		mp3ph.call();
		mp3ph.receiveCall();
		mp3ph.playMusic(); //Mp3Phone 고유기능
		
		System.out.println("=== WebPhone ===");
		WebPhone webph = new WebPhone("010-4444-4444");
		webph.view();
		webph.call();
		webph.receiveCall();
		webph.webSearch(); //webPhone 고유기능
		//webph.playMusic();
				
	}

}
