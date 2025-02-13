--상관서브쿼리(메인쿼리와 서브쿼리가 조인되어 동작)
------------------------
--출판사별로 출판사별 평균 도서가격보다 비싼 도서 목록을 구하시오
SELECT * FROM BOOK ORDER BY PUBLISHER;
----굿스포츠 출판사 책 중에서 굿스포츠 출판사 책의 평균 금액보다 비싼 도서
SELECT * FROM BOOK WHERE PUBLISHER = '굿스포츠';
SELECT AVG(PRICE) FROM BOOK WHERE PUBLISHER = '굿스포츠'; --7000
SELECT *
  FROM BOOK 
WHERE PUBLISHER = '굿스포츠' 
    AND PRICE > (SELECT AVG(PRICE) FROM BOOK WHERE PUBLISHER = '굿스포츠')
;
---- 상관 서브 질의로 모든 출판사에 적용
---- 상관서브질의 : 메인 SELECT문의 데이터가 하나 처리될 때마다 메인테이블 값과
------ 비교해서 서브쿼리 문장이 실행되는 형태로 처리
SELECT B.*
             , (SELECT AVG(PRICE) FROM BOOK WHERE PUBLISHER = B.PUBLISHER) PUB_AVG
  FROM BOOK B
WHERE PRICE > (SELECT AVG(PRICE) FROM BOOK 
                                WHERE PUBLISHER = B.PUBLISHER)
;
-----------------------------
--JOIN문으로 처리
--출판사별 평균 도서 가격
SELECT PUBLISHER, AVG(PRICE)
  FROM BOOK
GROUP BY PUBLISHER;
-------------
SELECT * 
  FROM BOOK B, 
                (SELECT PUBLISHER, AVG(PRICE) AVG_PRICE
                    FROM BOOK
                  GROUP BY PUBLISHER
                 ) AVG
WHERE B.PUBLISHER = AVG.PUBLISHER --조인조건
     AND B.PRICE >= AVG.AVG_PRICE
;
----------------
--SELECT 절에 사용된 상관서브쿼리
SELECT O.*
             , (SELECT NAME FROM CUSTOMER WHERE CUSTID = O.CUSTID) 고객명
             , (SELECT BOOKNAME FROM BOOK WHERE BOOKID = O.BOOKID) 도서명
  FROM ORDERS O
;
-------------------
--EXISTS : 존재여부 확인시 사용(있으면 TRUE)
--NOT EXISTS : 존재하지 않을 때 TRUE
SELECT *
  FROM BOOK
WHERE BOOKNAME IN (SELECT BOOKNAME FROM BOOK
                                             WHERE BOOKNAME LIKE '%축구%')
;
----------
SELECT B.* 
  FROM BOOK B
WHERE EXISTS (SELECT * FROM BOOK
                               WHERE B.BOOKNAME LIKE '%축구%')
;
-------------------
--주문내역이 있는 고객의 이름과 전화번호를 구하시오
SELECT * 
  FROM CUSTOMER
WHERE CUSTID IN (SELECT DISTINCT CUSTID FROM ORDERS);
---- EXISTS 사용 - 속도 더 빠름
SELECT *
  FROM CUSTOMER C
WHERE EXISTS (SELECT 1 FROM ORDERS WHERE CUSTID = C.CUSTID);
---- NOT EXISTS
SELECT *
  FROM CUSTOMER C
WHERE NOT EXISTS (SELECT 1 FROM ORDERS WHERE CUSTID = C.CUSTID); 
--====================