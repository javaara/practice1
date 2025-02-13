package com.mystudy.ex02_fileinputstream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class FileInputStream_Ex3_2 {

	public static void main(String[] args) {
		File file = new File("file/test_01.txt");
		FileInputStream fis = null;
		
		try {
			//1. 객체 생성(파일 읽기) <- File 객체 입력받음
			fis = new FileInputStream(file);

			//2. 객체 사용 작업 처리
			byte[] bytes = new byte[10];
			System.out.println("bytes : " + Arrays.toString(bytes));
			
			//fis.read(byte[] b); : byte[] 배열의 크기만큼 읽어 b변수에 저장
			//리턴값 : 읽어온 개수
			int byteCnt = 0;
			
			//파일을 읽고 읽은 개수가 하나라도 있으면 반복문 실행
			//리턴값이 -1인 경우는 파일의 끝(EOF)을 만난 경우
			while ((byteCnt = fis.read(bytes)) != -1) {
				//byteCnt = fis.read(bytes);
				//if (byteCnt == -1) break; //리턴값 -1 파일의 끝(EOF)
				
				System.out.println("읽어온 개수 : " + byteCnt);
				System.out.println("읽어온 값 : " + Arrays.toString(bytes));
				for (int i = 0; i < byteCnt; i++) {
					System.out.println("숫자 : " + bytes[i] +", char : " + (char)bytes[i]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					//3. 사용객체 close
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
