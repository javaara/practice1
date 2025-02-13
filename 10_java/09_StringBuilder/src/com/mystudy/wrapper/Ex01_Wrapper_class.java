package com.mystudy.wrapper;

public class Ex01_Wrapper_class {

	public static void main(String[] args) {
		// Wrapper class : 기본 데이터 타입의 클래스타입들의 총칭
		// 기본데이터 타입 : 전체 소문자, char, int...
		// boolean, char, byte, short, int, long, float, double
		// Wrapper class : 기본데이터 타입의 첫글자를 대문자로 작성
		//	예외 : char -> Character, int -> Integer
		// Boolean, Character, Byte, Short, Integer, Long, Float, Double
		int num = 100; 
		//num = "100"; // Type mismatch: cannot convert from String to int
		System.out.println("num : " + num);
		
		//Integer intNum = 100; //사용가능
		Integer intNum = new Integer(100);
		System.out.println("intNum : " + intNum);
		//intNum = "100" //Type mismatch: cannot convert from String to Integer
		intNum = new Integer("100"); //("")안에 숫자만 가능
		System.out.println("intNum : " + intNum);
		
		intNum = 900; //Integer <- int : 자동형변환
		num = intNum; //int <- Integer : 자동형변환
		
		System.out.println("정수 int 최대값 : " + Integer.MAX_VALUE);
		System.out.println("정수 int 최대값 : " + Integer.MIN_VALUE);
		
		num = Integer.parseInt("999"); //int <- String
		intNum = Integer.valueOf("999"); //Integer <- String
		System.out.println("num + 1 : " + (num + 1));
		System.out.println("intNum + 1 : " + (intNum + 1));		
		
		System.out.println("---- Boolean ----");
		Boolean bool = true;
		bool = new Boolean(true);
		bool = new Boolean("true"); //true <- "true"
		System.out.println("bool : " + bool);
		if (bool) {
			System.out.println("bool 값은 boolean true");
		}
		bool = new Boolean("true1");
		System.out.println("bool : " + bool);
		System.out.println(Boolean.TRUE);
		System.out.println(Boolean.FALSE);
		
		System.out.println("----- String -> Boolean -----");
		bool = Boolean.valueOf("true");
		System.out.println("Boolean.valueOf(\"true\") : " + bool);
		
		bool = Boolean.valueOf("TRUE");
		System.out.println("Boolean.valueOf(\"TRUE\") : " + bool);
		
		bool = Boolean.valueOf("TRue");
		System.out.println("Boolean.valueOf(\"TRue\") : " + bool);
		
		bool = Boolean.valueOf("true1"); //대소문자 구분없이 true가 아니면 false
		System.out.println("Boolean.valueOf(\"true1\") : " + bool);

		System.out.println("----- Float, Double 동일 -----");
		Float f = 10.5f; //Float <- float
		f = new Float(12.5f);
		f = new Float("12.5f");
		System.out.println("new Float(\"12.5f\") : " + f);
		String str = String.valueOf(10.5f); //String <- float
		str = String.valueOf(f); //String <- Float
		System.out.println("str : " + str);
		
		f = Float.valueOf(str); //Float <- String
		System.out.println("f : " + f);
	}

}







