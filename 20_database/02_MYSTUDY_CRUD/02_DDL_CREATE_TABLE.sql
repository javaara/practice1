/* *************************
데이타 정의어
- DDL(Data Definition Language) : 데이타를 정의하는 언어
- CREATE(생성), DROP(삭제), ALTER(수정)
- {}반복가능, []생략가능, | 또는(선택)
CREATE TABLE 테이블명 (
{컬럼명 데이타타입
    [NOT NULL | UNIQUE | DEFAULT 기본값 | CHECK 체크조건]
}
    [PRIMARY KEY 컬럼명]
    {[FOREIGN KEY 컬럼명 REFERENCES 테이블명(컬럼명)]
    [ON DELETE [CASCADE | SET NULL]
    }
);
--------
컬럼의 기본 데이타 타입
VARCHAR2(n) : 문자열 가변길이
CHAR(n) : 문자열 고정길이
NUMBER(p, s) : 숫자타입 p:전체길이, s:소수점이하 자리수
  예) (5,2) : 정수부 3자리, 소수부 2자리 - 전체 5자리
DATE : 날짜형 년,월,일 시간 값 저장

문자열 처리 : UTF-8 형태로 저장
- 숫자, 알파벳 문자, 특수문자 : 1 byte 처리(키보드 자판 글자들)
- 한글 : 3 byte 처리
***************************/
CREATE TABLE MEMBER (
        ID VARCHAR2 (20) PRIMARY KEY, --UNIQUE + NOT NULL
        NAME VARCHAR2 (30) NOT NULL,
        PASSWORD VARCHAR2 (20) NOT NULL,
        PHONE VARCHAR2 (20),
        ADDRESS VARCHAR2 (200)
);
---------------------------------------------------
INSERT INTO MEMBER 
            (ID, NAME, PASSWORD)
VALUES ('hong', '홍길동', '1234');

INSERT INTO MEMBER 
            (ID, NAME, PASSWORD)
VALUES ('hong2', '홍길동', '1234');
-----
--cannot insert NULL into ("MYSTUDY"."MEMBER"."PASSWORD")
INSERT INTO MEMBER (ID, NAME) VALUES ('hong3', '홍길동3');
-----
INSERT INTO MEMBER (ID, NAME, PASSWORD) 
VALUES ('hong4', '홍길동4', '1234');
----------------------------------------------------
SELECT * FROM MEMBER;
--컬럼을 명시적으로 쓰지 않으면 INSERT문 사용시
--테이블에 있는 모든 컬럼에 대하여 순서대로 데이터 입력해야 함
INSERT INTO MEMBER
VALUES ('hong5', '홍길동5', '1234', '010-1111-1111', '서울시');
INSERT INTO MEMBER
VALUES ('hong6', '홍길동5', '1234', '서울시', '010-1111-1111');

INSERT INTO MEMBER (ID, NAME, PASSWORD, ADDRESS, PHONE)
VALUES ('hong7', '홍길동7', '1234', '서울시', '010-1111-1111');
-------------
--수정 : hong6의 전화번호를 010-2222-2222로 변경
--수정 : hong6의 주소를 '부산'으로 변경
UPDATE MEMBER SET PHONE = '010-2222-2222' WHERE ID = 'hong6';
UPDATE MEMBER SET ADDRESS = '부산' WHERE ID = 'hong6';
UPDATE MEMBER 
        SET PHONE = '010-2222-2222', ADDRESS = '부산' 
 WHERE ID = 'hong6';
 
--삭제 : hong7 데이터 삭제
--삭제 : 이름이 홍길동인 사람 삭제
DELETE FROM MEMBER WHERE ID = 'hong7';
DELETE FROM MEMBER WHERE NAME = '홍길동';

--입력 : 아이디-hong8, 이름-홍길동8, 암호-1111, 주소-서울시 서초구
INSERT INTO MEMBER (ID, NAME, PASSWORD, ADDRESS)
VALUES ('hong8', '홍길동8', '1111', '서울시 서초구');

--검색(조회) : 이름이 홍길동8인 사람의 아이디, 이름, 주소 데이터만 조회
SELECT ID, NAME, ADDRESS FROM MEMBER WHERE NAME = '홍길동8';
-----------------------------------------------------
INSERT INTO MEMBER
VALUES ('hong9', '홍길동9', '1234', '서울시', '010-1111-1111');
--===========================================
--컬럼 특성을 확인하기 위한 테이블
CREATE TABLE TEST (
        NUM NUMBER (5, 2), --전체자리수 5, 정수부 3, 소수부 2
        STR1 CHAR (10), --고정길이
        STR2 VARCHAR2 (10), --가변길이
        DATE1 DATE --날짜데이터 : 년월일시분초
);
INSERT INTO TEST VALUES (100.456, 'ABC', 'ABC', SYSDATE);
INSERT INTO TEST VALUES (100.455, 'ABC', 'ABC', SYSDATE);
INSERT INTO TEST VALUES (100.454, 'ABC', 'ABC', SYSDATE);

SELECT * FROM TEST;
SELECT '-' || STR1 || '-', '-' || STR2 || '-' FROM TEST; -- || : 문자열 붙이기 부호
INSERT INTO TEST VALUES (100.454, 'DEF', 'DEF  ', SYSDATE);
----------------------------------------
SELECT * FROM TEST WHERE STR1 = STR2; --조회된 데이터 없음
SELECT * FROM TEST WHERE STR1 = 'ABC'; --오라클에서는 조회됨
SELECT * FROM TEST WHERE STR1 = 'ABC       '; --모든 DB 적용
SELECT * FROM TEST WHERE STR2 = 'DEF'; --'DEF' vs 'DEF  ' 다른 데이터
SELECT * FROM TEST WHERE STR2 = 'DEF  '; --데이터 조회됨
----------
SELECT * FROM TEST WHERE NUM = 100.45; --NUMBER = NUMBER
SELECT * FROM TEST WHERE NUM = '100.45'; --오라클에서는 조회됨 NUMBER = VARCHAR2 
SELECT * FROM TEST WHERE NUM = '100.45A'; --ORA-01722: invalid number
----------
INSERT INTO TEST (STR1, STR2) VALUES ('1234567890', '1234567890');
SELECT * FROM TEST WHERE STR1 = STR2;
SELECT '-' || STR1 || '-', '-' || STR2 || '-' FROM TEST;
----------------------------------------
-- UTF-8 타입으로 데이터 저장
INSERT INTO TEST (STR1, STR2) VALUES ('ABCDEFGHIJ', 'ABCDEFGHIJ');
INSERT INTO TEST (STR1, STR2) VALUES ('ABCDEFGHIJ', '대한민국'); --한글 4 * 3byte = 12
INSERT INTO TEST (STR1, STR2) VALUES ('ABCDEFGHIJ', '홍길동'); --3 * 3byte = 9


--===========================
-- NULL (널) : 빈문자열('')도 아니고, 특정 값이 아닌 데이터가 없는 상태
-- NULL은 비교처리가 안됨 : =, <>, !=, >, <, >=, <= 비교처리 의미가 없음
-- NULL과의 연산 결과는 항상 NULL(연산의 의미도 없음)
-- NULL 값에 대한 조회는 IS NULL, IS NOT NULL 문장으로만 조회
------------------------
SELECT * FROM TEST WHERE NUM = NULL; --조회안됨(NULL과의 비교연산 의미없음)
SELECT * FROM TEST WHERE NUM IS NULL;
SELECT * FROM TEST WHERE NUM <> NULL; --조회안됨
SELECT * FROM TEST WHERE NUM IS NOT NULL; 

-- NULL 과의 연산 결과
SELECT 100 + 200, 111 + 222 FROM DUAL; --DUAL 테이블 일명 Dummy 테이블
SELECT * FROM DUAL;
SELECT NUM, NUM + 10 FROM TEST; --NULL과의 연산 결과는 항상 NULL
-- 정렬시 NULL
SELECT * FROM TEST ORDER BY STR2; --기본 오름차순 정렬, ASC 키워드 생략
SELECT * FROM TEST ORDER BY STR2 DESC; --DESC : 내림차순 정렬
-- 정렬시 오라클에서는 NULL 값을 가장 큰 값으로 처리(맨 마지막에 출력)
-- NULL값의 순서 변경처리 : NULLS FIRST, NULLS LAST
SELECT * FROM TEST ORDER BY NUM;
SELECT * FROM TEST ORDER BY NUM DESC;
SELECT * FROM TEST ORDER BY NUM NULLS FIRST; --NULL값을 맨 처음 표시
SELECT * FROM TEST ORDER BY NUM DESC NULLS LAST; --NULL값을 마지막에 표시
--------------------------------
SELECT NUM FROM TEST;
-- 내장함수 NVL(컬림, 설정값) : 컬럼이 NULL이면 설정값으로 변환
SELECT NUM, NUM + 10, NVL(NUM, 0), NVL(NUM, 0) + 10 FROM TEST;
----------------------------------
INSERT INTO TEST (NUM, STR1, STR2) VALUES (200, '', NULL); --'' 값 NULL 처리됨
SELECT * FROM TEST WHERE STR1 = ''; --데이터 조회 안됨
SELECT STR1, STR1 || '-테스트', 
                NVL (STR1, '내용없음') || '-테스트'
    FROM TEST;
--========================================







