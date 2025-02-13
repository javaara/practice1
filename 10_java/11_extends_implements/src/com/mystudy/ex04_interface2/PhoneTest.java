package com.mystudy.ex04_interface2;

public class PhoneTest {

	public static void main(String[] args) {
		// Phone, Mp3Phone 객체 생성 후 기능 테스트
		System.out.println("=== Phone ===");
		Phone ph1 = new Phone("010-1111-1111");
		ph1.view();
		ph1.call();
		ph1.receiveCall();
		ph1.sendMsg();
		ph1.receiveMsg();
		
		System.out.println("=== Mp3Phone ===");
		Mp3Phone mp3ph = new Mp3Phone("010-2222-2222");
		mp3ph.view();
		mp3ph.call();
		mp3ph.receiveCall();
		mp3ph.sendMsg();
		mp3ph.receiveMsg();
		mp3ph.playMusic();
	}

}
