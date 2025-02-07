package com.mystudy.ex01_arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListExam {

	public static void main(String[] args) {
		//List 계열 : ArrayList - 배열의 속성과 List 속성을 가짐
		// - List 인터페이스를 구현(implements)한 클래스
		// - List 인터페이스를 상속 확장(extends)한 인터페이스
		
		ArrayList list1 = new ArrayList();
		System.out.println("list1.size() : " + list1.size());
		
		list1.add(new Integer(5));
		list1.add(1); //int -> Integer 자동형변환 되어 입력됨
		list1.add(new Integer(5)); //값에 대한 중복은 허용
		list1.add(new Integer(6)); 
		list1.add(new Integer(7)); 
		
		System.out.println(list1.toString());
		System.out.println("list1.size() : " + list1.size());
		
		list1.add(0, 100); //인덱스 위치에 데이터 추가(삽입)
		System.out.println("0인덱스 100 추가후 : " + list1.toString());
		
		list1.set(0, 999); //인덱스 위치의 데이터 수정(변경)
		System.out.println("0인덱스 999 set후 : " + list1);
		
		list1.remove(0); //인덱스 위치의 데이터 삭제
		System.out.println("0인덱스 remove후 : " + list1);
		
		//list1.remove(5); //숫자 5를 삭제하겠다가 아닌 5번 인덱스 삭제로 동작
		list1.remove(new Integer(5)); 
		System.out.println("숫자Integer 5 삭제후 : " + list1);
		
		Object obj = list1.get(0);
		System.out.println("0번 인덱스 값 : " + obj);
		
		Collections.reverse(list1);
		System.out.println("reverse 적용후 : " + list1.toString());
		
		System.out.println("------------------------------------");
		ArrayList list2 = new ArrayList(list1.subList(1, 4));
		System.out.println("list2 : " + list2);
		
		System.out.println("---- Collections.sort() ---");
		System.out.println("list1 : " + list1);
		
		//list1.add("홍길동");
		Collections.sort(list1); //오름차순 정렬
		System.out.println("sort() 후 list1 : " + list1);
		
		//list2.add("홍길동");
		Collections.sort(list2);
		System.out.println("sort() 후 list2 : " + list2);
		
		System.out.println("---- Collections.reverse() ----");
		//내림차순은 따로 없음, 오름차순 처리 후 reverse()하면 내림차순 효과
		//reverse() : 처음과 끝 위치를 서로 바꾸는 형태로 동작(정렬과는 무관)
		Collections.reverse(list1);
		System.out.println("reverse() 후 list1 : " + list1);
		
		System.out.println("================================");
		System.out.println("list1 : " + list1);
		System.out.println("list2 : " + list2);
		
		list2.add("B");
		list2.add("TEST");
		System.out.println("list2 : " + list2);
		System.out.println("첫번째 데이터 조회 : " + list2.get(0));
		System.out.println("마지막 데이터 조회 : " + list2.get(list2.size() - 1));
		
		System.out.println("--------------------");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " - " + list2.get(i));
		}
		System.out.println("--------------------");
		printData(list1);
		printData("list1", list1);
		printData("list2", list2);
		printData(list1, list2);
		System.out.println("========================");
		//실습 : 반복문(for)을 사용해서 list2에 있는 전체 데이터 삭제
				
		//(문제있음) 데이터가 모두 삭제되지 않고 남는 문제
//		for (int i = 0; i < list2.size(); i++) {
//			printData(">> list2", list2);
//			list2.remove(i);
//		}
//		printData("list2", list2);
		
/*		
		System.out.println("----");
		//(문제있음) IndexOutOfBoundsException 발생
		int list2Cnt = list2.size();
		for (int i = 0; i < list2Cnt; i++) {
			printData(">> list2", list2);
			list2.remove(i);
		}
		printData("list2", list2);
*/
/*		
		//해결1 : 개수만큼 0번 인덱스 값 삭제 처리(크기값 고정)
		int list2Cnt = list2.size();
		for (int i = 0; i < list2Cnt; i++) {
			printData("삭제직전>> list2", list2);
			list2.remove(0);
		}
		printData("최종 결과 list2", list2);		
*/		
//		//해결2 : 개수만큼 뒤에서부터 삭제 처리
//		//맨마지막 데이터 삭제, 뒤에서2번째, 뒤에서3번째....0번까지 삭제
//		int lastIndex = list2.size() - 1;
//		for (int i = lastIndex; i >= 0; i--) {
//			printData("삭제직전>> list2", list2);
//			list2.remove(i);
//		}
//		printData("최종 결과 list2", list2);	
		
		printData("삭제전 list2", list2);	
		list2.clear();
		printData("clear() 삭제후 list2", list2);	
	}
	
	static void printData(ArrayList list1, ArrayList list2) {
		printData("list1", list1);
		printData("list2", list2);
		System.out.println();
	}
	
	static void printData(List list) {
		//null 여부 확인
		if (list == null) return;
		if (list.size() > 0) { //최소 하나 이상의 데이터가 있냐?
			System.out.print(list.get(0));
		}
		for (int i = 1; i < list.size(); i++) {
			System.out.print(", " + list.get(i));
		}
		System.out.println();
	}
	
	static void printData(String title, List list) {
		//null 여부 확인
		if (list == null) {
			System.out.println(title + " : null");
			return;
		}
		if (list.size() == 0) { //null은 아니지만 데이터가 없음
			System.out.println(title + " : 데이터 건수 0");
			return;
		}
		System.out.print(title + " : " + list.get(0));
		for (int i = 1; i < list.size(); i++) {
			System.out.print(", " + list.get(i));
		}
		
		System.out.println();
	}
}



















