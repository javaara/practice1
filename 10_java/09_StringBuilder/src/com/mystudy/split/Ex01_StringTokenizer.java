package com.mystudy.split;

import java.util.StringTokenizer;

public class Ex01_StringTokenizer {

	public static void main(String[] args) {
		// String.split() vs StringTokenizer
		System.out.println("--- String.split() ---");
		String str = "사과,배,복숭아,,포도";
		System.out.println("str : " + str);
		
		String[] strSplit = str.split(",");
		System.out.println("strSplit.length : " + strSplit.length);
		
		System.out.println("--- str.split(\",\") 실행 결과 ---");
		for (int i = 0; i < strSplit.length; i++) {
			System.out.println(i + " : -" + strSplit[i] + "-");
		}
		System.out.println();
		
		//개선된 for문(다른언어 : forEach문)
		//for (데이터타입 변수명 : 집합객체) {}
		System.out.println("--- 개선된 for문으로 ---");
		for (String str2 : strSplit) {
			System.out.println("-" + str2 + "-");
		}
		System.out.println("------------");
		
		int idx = 0;
		for (String str2 : strSplit) {
			System.out.println(idx + " : " + "-" + str2 + "-");
			idx++;
		}
		System.out.println("==============");
		
		System.out.println("---- StringTokenizer 사용 ----");
		System.out.println("str : " + str);
		StringTokenizer strToken = new StringTokenizer(str, ",");
		System.out.println("strToken.countTokens() : " + strToken.countTokens());
		
		while (strToken.hasMoreTokens()) { //토큰이 더 있냐?
			String token = strToken.nextToken(); //토큰값 사용
			System.out.println("-" + token + "-");
		}
		System.out.println("strToken.countTokens() : " + strToken.countTokens());
		
		System.out.println("===== for문 사용 토큰값 조회 =====");
		strToken = new StringTokenizer(str, ",");
		System.out.println("저장된 토큰 개수 : " + strToken.countTokens());
		
		int tokenCnt = strToken.countTokens();
		for (int i = 0; i < tokenCnt; i++) {
			//System.out.println(i + " - 토큰 개수 : " + strToken.countTokens());	
			String token = strToken.nextToken();
			System.out.println((i + 1) + " : " + token);
		}
		System.out.println("========== While문 사용 ============");
		//주의할 사항 : 반복문 내에서 2개 이상의 토큰을 한번에 사용하지 말 것
		strToken = new StringTokenizer(str, ",");
		while (strToken.hasMoreTokens()) { //토큰이 더 있냐?
			System.out.println(strToken.nextToken());
			System.out.println("-" + strToken.nextToken() + "-");
		}
	}

}



















