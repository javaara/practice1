package com.mystudy.ex01_instanceof;

import org.omg.CosNaming.IstringHelper;

public class CarTest {

	public static void main(String[] args) {
		System.out.println("=== Car ===");
		Car car = new Car();
		car.drive();
		car.stop();
		
		System.out.println("=== Ambulance ===");
		Ambulance am = new Ambulance();
		am.drive();
		am.stop();
		am.siren();
		
		System.out.println("=== FireEngine ===");
		FireEngine fe = new FireEngine();
		fe.drive();
		fe.stop();
		fe.siren();
		fe.water();
		
		System.out.println("=== Car타입에 자녀타입 저장 사용 ===");
		Car carTemp = new Car();
		carTemp.drive();
		carTemp.stop();
		
		//Car <- Ambulance : Super <- sub(자동형변환)
		carTemp = am;
		carTemp.drive();
		carTemp.stop();
		System.out.println("---- Am <- Car <- Am ----");
		((Ambulance)carTemp).siren();
		
		System.out.println("---- Fe <- Car <- Am ----");
		System.out.println("--- 구급차를 소방차로 바꾸고 사이렌 울리기 시도 ---");
		//실행시 예외 발생 ClassCastException
		// ((FireEngine)carTemp).water();
		// ((FireEngine)carTemp).siren();
		
		System.out.println("----- Car 타입에 저장된 객체의 타입 확인 -----");
//		carTemp = new Car();
//		carTemp = am; //Car <- Ambulance
		carTemp = fe; //Car <- FireEngine
		
		System.out.println("--- Object 타입인  경우 ---");
		if (carTemp instanceof Object) {
			System.out.println("Object 타입입니다.");
		}
		System.out.println("--- Car 타입인 경우 ---");
		if (carTemp instanceof Car) { //Car 타입의 객체(instance)냐?
			carTemp.drive();
			carTemp.stop();
		}
		
		System.out.println("--- Ambulance 인 경우 ---");
		if (carTemp instanceof Ambulance) {
			carTemp.drive();
			carTemp.stop();
			((Ambulance) carTemp).siren();
		}
		
		System.out.println("--- FireEngine 인 경우 ---");
		if (carTemp instanceof FireEngine) {
			carTemp.drive();
			carTemp.stop();
			((FireEngine) carTemp).siren();
			((FireEngine) carTemp).water();
		}
		
		System.out.println("=============================");
		System.out.println("--- Car 타입 전달시(타입체크) ---");
		typeCheck(car);
		
		System.out.println("--- Ambulance 타입 전달시(타입체크) ---");
		typeCheck(am);
		
		System.out.println("--- FireEngine 타입 전달시(타입체크) ---");
		typeCheck(fe);
	}
	
	//메소드 오버로딩(overloading)
/*	static void typeCheck(Car carTemp) {
		System.out.println("::: Car 타입입니다 --------");
		carTemp.drive();
		carTemp.stop();
	}
	
	static void typeCheck(Ambulance am) {
		System.out.println("::: Ambulance 타입입니다 -------");
		am.drive();
		am.stop();
		am.siren();
	}
	static void typeCheck(FireEngine fe) {
		System.out.println("::: FireEngine 타입입니다 -------");
		fe.drive();
		fe.stop();
		fe.siren();
		fe.water();
	}
*/

	static void typeCheck(Car carTemp) {
		System.out.println("::: Car 타입입니다 --------");
		carTemp.drive();
		carTemp.stop();
		
		//타입확인 후 처리 : instanceof
		//비교할객체 instanceof 비교할클래스명(타입)
		if (carTemp instanceof Ambulance) {
			System.out.println("::: Ambulance 타입입니다 -------");
			((Ambulance) carTemp).siren();
		}
		
		if (carTemp instanceof FireEngine) {
			System.out.println("::: FireEngine 타입입니다 -------");
			((FireEngine) carTemp).siren();
			((FireEngine) carTemp).water();
		}
	}
	

}

















