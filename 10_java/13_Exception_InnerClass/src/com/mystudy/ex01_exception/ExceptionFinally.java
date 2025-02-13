package com.mystudy.ex01_exception;

public class ExceptionFinally {
	
	public static void main(String[] args) {
		// try ~ catch ~ finally
		System.out.println("--- main() 시작 ---");
		int num = 20;
		int result = 30;
		try {
			System.out.println(">> try문 시작");
			num = 0;
			result = 100 / num;
			
			System.out.println(">> 연산처리 정상 종료");
			System.out.println(">> try문 종료");
			//return;
		} catch(ArithmeticException e) {
			System.out.println(">>>> catch문 실행");
		} finally { // return; 문장이 있어도 무조건 실행
			System.out.println("***> finally : 항상실행(무조건)");
		}
		System.out.println("--------------------");
		
		System.out.println("test() 실행결과 : " + test());
		System.out.println("--- main() 종료 ---");
	}
	
	static String test() {
		String result = "";
		int[] num = new int[3]; //인덱스 0, 1, 2
		try {
			num[3] = 100;
			result = "<정상실행>";
		} catch (Exception e) { //test() 여기에서 예외처리
			result = "<예외발생>" + e.getMessage();
			e.printStackTrace();
		} finally {
			System.out.println("===> finally 무조건 실행");
		}
		
		return result;
	}
	
}
