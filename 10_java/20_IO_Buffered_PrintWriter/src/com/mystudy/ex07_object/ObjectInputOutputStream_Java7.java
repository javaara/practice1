package com.mystudy.ex07_object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectInputOutputStream_Java7 {

	public static void main(String[] args) {
		// ObjectInputStream, ObjectOutputStream
		// Object 타입의 데이터를 사용해서 입출력(I/O)
		//-----------------------------------
		// Object 타입의 데이터를 파일에 저장하고 읽어오기
		// StudentVO 타입의 데이터 사용
		
		File file = new File("file/object_io.txt");
		//파일에 쓰기 위한 용도
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
		
		//파일로부터 읽기 위한 용도
//		FileInputStream fis = null;
//		ObjectInputStream ois = null;
		
		//파일에 저장할 데이터 준비
		System.out.println("--- 저장할 데이터 준비 ---");
		StudentVO stu1 = new StudentVO("19001", "홍길동", 100, 90, 81);
		StudentVO stu2 = new StudentVO("19002", "김유신", 95, 90, 85);
		System.out.println(stu1);
		System.out.println(stu2);
		System.out.println("===============================");
		
		//파일에 쓰기 =========================================
		//Java7 부터 제공되는 try ~ with resources
		//try (사용할 자원선언) {} catch() {}
		//finally 구문에 명시적인 close문을 작성하지 않아도 자동 close 처리됨
		try (FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			
			System.out.println(">>> 파일에 쓰기(ObjectOutputStream) 사용");
			oos.writeObject(stu1);
			oos.writeObject(stu2);
			System.out.println(">> 파일에 쓰기 성공!!!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(">>[IOException] 파일에 쓰기 실패!!!");
		} 
		
		
		//파일로부터 읽기 =========================================
		try (FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);) {
		
			System.out.println("--- 파일에서 읽어서 화면 출력 ---");
//			Object obj = ois.readObject();
//			//StudentVO stu = (StudentVO) obj;
//			StudentVO stu = null;
//			if (obj instanceof StudentVO) {
//				stu = (StudentVO) obj;
//			}
//			System.out.println(">> 읽어온 데이터 사용");
//			System.out.println("이름: " + stu.getName() + ", 평균: " + stu.getAvg());
//			System.out.println(stu);
			
			StudentVO stuIn1 = (StudentVO) ois.readObject();
			StudentVO stuIn2 = (StudentVO) ois.readObject();
			
			System.out.println(">> 읽어온 데이터 화면 출력");
			System.out.println("stuIn1 : " + stuIn1);
			System.out.println("stuIn1 : " + stuIn2);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				if (ois != null) ois.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
		
	}//end main

}//end class


















