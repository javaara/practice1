/* ***********************************
SELECT [ * | DISTINCT ] { 컬럼명, 컬럼명, ... }
    FROM 테이블명
[ WHERE 조건절 ]
[ GROUP BY { 컬럼명, .... } ]
    [ HAVING 조건 ] -- GROUP BY 절에 대한 조건
[ ORDER BY { 컬럼명 [ ASC | DESC ], .... } ] --ASC : 오름차순(기본/생략가능)
                                                                        --DESC : 내림차순
[ ] 안의 내용은 생략은 가능하지만 순서는 지켜서 써야 함
HAVING과 WHERE절은 단독으로 쓸 수 없음
**************************************/
SELECT * FROM BOOK;
SELECT * FROM CUSTOMER;
SELECT * FROM ORDERS;
--------------------
SELECT * FROM BOOK ORDER BY BOOKNAME DESC;
-- 출판사 기준 오름차순(또는 내림차순), 책제목 오름차순(또는 내림차순)
SELECT * FROM BOOK ORDER BY PUBLISHER, BOOKNAME;
-- 출판사 기준 오름차순, 가격 내림차순(금액 큰것 먼저)
SELECT * FROM BOOK ORDER BY PUBLISHER, PRICE DESC;
-------------------------------------
-- AND, OR, NOT
-- AND : 출판사 대한미디어, 금액이 3만원 이상인 책 조회
SELECT * 
        FROM BOOK 
    WHERE PUBLISHER = '대한미디어' AND PRICE >= 30000;

-- OR : 출판사 대한미디어 또는 이상미디어 출판한 책 조회
SELECT * FROM BOOK 
    WHERE PUBLISHER = '대한미디어' OR PUBLISHER = '이상미디어';

SELECT * FROM BOOK
    WHERE PUBLISHER = '굿스포츠' OR PUBLISHER = '대한미디어';

-- NOT : 출판사 굿스포츠를 제외하고 나머지 전체 
SELECT * FROM BOOK 
    WHERE NOT (PUBLISHER = '굿스포츠')
;
SELECT * FROM BOOK WHERE PUBLISHER <> '굿스포츠'; -- <> : 같지않다(다르다)
SELECT * FROM BOOK WHERE PUBLISHER != '굿스포츠'; -- != : 같지않다(다르다)

-- 굿스포츠, 대한미디어 출판사가 아닌 책
SELECT * FROM BOOK 
    WHERE PUBLISHER <> '굿스포츠' AND PUBLISHER <> '대한미디어'
;
SELECT * FROM BOOK
    WHERE NOT (PUBLISHER = '굿스포츠' OR PUBLISHER = '대한미디어')
;
--------------------------------------------
-- IN : 안에 있냐?(OR 문을 단순화)
-- NOT IN : 안에 없냐?
-- (실습) 출판사 : 나무수, 대한미디어, 삼성당 에서 출판한 책 목록
SELECT * FROM BOOK 
    WHERE PUBLISHER = '나무수' OR PUBLISHER = '대한미디어' OR PUBLISHER = '삼성당'
;
SELECT * FROM BOOK
    WHERE PUBLISHER IN ('나무수', '대한미디어', '삼성당')
;
-- (실습) 나무수, 대한미디어, 삼성당 제외한 출판사 책 목록
SELECT * FROM BOOK
    WHERE PUBLISHER <> '나무수' AND PUBLISHER <> '대한미디어' AND PUBLISHER <> '삼성당'
;
SELECT * FROM BOOK
    WHERE PUBLISHER NOT IN ('나무수', '대한미디어', '삼성당')
;
--===========================================
-- 같다(=), 크다(>), 작다(<), 크거나같다(>=), 작거나같다(<=)
-- 같지않다/다르다 : <>, !=
-- (실습) 출판된 책중에 8000원 이상이고, 22000원 이하인 책(가격순으로 정렬)
SELECT * FROM BOOK
    WHERE PRICE >= 8000 AND PRICE <= 22000
    ORDER BY PRICE
;
-- 비교대상 BETWEEN 값1(부터) AND 값2(까지) : 값1 부터 값2 까지 형태로 사용
SELECT * FROM BOOK
    WHERE PRICE BETWEEN 8000 AND 22000 -- 경계값 포함 8000 <= PRICE <= 22000
    ORDER BY PRICE
;
-- NOT BETWEEN 값1 AND 값2 : 값1 부터 값2 까지 범위가 아닌 것
SELECT * FROM BOOK
    WHERE PRICE NOT BETWEEN 8000 AND 22000 -- 경계값 제외 PRICE < 8000 OR PRICE > 22000
    ORDER BY PRICE
;
------------------------------------
-- 책 제목이 '야구' ~ '올림픽'
SELECT * FROM BOOK 
    WHERE BOOKNAME BETWEEN '야구' AND '올림픽'
    ORDER BY BOOKNAME
;

-- 출판사 나무수 ~ 삼성당 출판한 책 조회(출판사명으로 정렬)
SELECT * FROM BOOK
    WHERE PUBLISHER BETWEEN '나무수' AND '삼성당'
    ORDER BY PUBLISHER
;
------------------------
-- 대한미디어, 이상미디어에서 출판한 책 목록 조회(출판사명, 책제목 정렬)
SELECT * FROM BOOK
    WHERE PUBLISHER IN ('대한미디어', '이상미디어')
    ORDER BY PUBLISHER, BOOKNAME
;
--====================================
-- LIKE : ' % ',  ' _ ' 부호와 함께
-- % : 전체(모든것)를 의미
-- _(언더바) : 문자 하나에 대하여 모든것을 의미
-----------------------
SELECT * FROM BOOK
    WHERE PUBLISHER LIKE '%미디어' -- 출판사명이 '미디어'로 끝나는 출판사
;
-- '야구'로 시작하는 책 조회
SELECT * FROM BOOK
    WHERE BOOKNAME LIKE '야구%' -- '야구'로 시작하는 책 제목
;
-- 책 제목에 '단계' 단어가 있는 책 목록 
SELECT * FROM BOOK
    WHERE BOOKNAME LIKE '%단계%' -- '단계' 단어가 책제목에 있으면(어디에 있든지) 
;
-- 책 제목에 '구' 문자가 있는 책 목록 조회
SELECT * FROM BOOK
    WHERE BOOKNAME LIKE '%구%';
----------------------------------------
-- 책 제목에 '구' 문자가 2번째에 있는 책 조회
SELECT * FROM BOOK
    WHERE BOOKNAME LIKE '_구%';
-- 책 제목 중 2, 3번째 글자가 '구의'인 책 목록 
SELECT * FROM BOOK
    WHERE BOOKNAME LIKE '_구의%';
------------------------------------------
-- LIKE 사용시 동작 결과 확인
CREATE TABLE TEST_LIKE (
            ID NUMBER PRIMARY KEY,
            NAME VARCHAR2 (30)
);
INSERT INTO TEST_LIKE VALUES (1, '홍길동');
INSERT INTO TEST_LIKE VALUES (2, '홍길동2');
INSERT INTO TEST_LIKE VALUES (3, '홍길동구');
INSERT INTO TEST_LIKE VALUES (4, '홍길동대문');
INSERT INTO TEST_LIKE VALUES (5, '홍길동2다');
INSERT INTO TEST_LIKE VALUES (6, '김홍길동');
INSERT INTO TEST_LIKE VALUES (7, '김홍길동만');
INSERT INTO TEST_LIKE VALUES (8, '김만홍길동');
INSERT INTO TEST_LIKE VALUES (9, '김홍길동만이다');
COMMIT;

SELECT * FROM TEST_LIKE;
SELECT * FROM TEST_LIKE WHERE NAME = '홍길동';
SELECT * FROM TEST_LIKE WHERE NAME LIKE '홍길동';
SELECT * FROM TEST_LIKE WHERE NAME LIKE '홍길동%';
SELECT * FROM TEST_LIKE WHERE NAME LIKE '홍길동2%';
SELECT * FROM TEST_LIKE WHERE NAME LIKE '%홍길동%';
SELECT * FROM TEST_LIKE WHERE NAME LIKE '_홍길동_'; --반드시 5글자
SELECT * FROM TEST_LIKE WHERE NAME LIKE '_홍길동_%'; --최소 5글자 이상
SELECT * FROM TEST_LIKE WHERE NAME LIKE '_홍길동%'; -- 최소 4글자 이상
SELECT * FROM TEST_LIKE WHERE NAME LIKE '__홍길동%'; --최소 5글자 이상




