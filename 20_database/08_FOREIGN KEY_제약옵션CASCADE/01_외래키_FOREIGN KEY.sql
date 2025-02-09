SELECT * FROM DEPT;
/* ************************
--테이블 생성시 제약조건 설정
--컬럼을 정의하면서 컬럼레벨에서 제약조건 지정
--외래키(FOREIGN KEY) 지정으로 관계 설정
--형태 : 컬럼명 REFERENCES 대상테이블(대상컬럼)
***************************/
--컬럼레벨에서 제약조건(외래키) 설정
CREATE TABLE EMP01 (
    EMPNO NUMBER PRIMARY KEY,
    ENAME VARCHAR2(30) NOT NULL,
    JOB VARCHAR2(10),
    DEPTNO VARCHAR2(10) REFERENCES DEPT(ID) --외래키 설정
);
-----
SELECT * FROM EMP01;
INSERT INTO EMP01 (EMPNO, ENAME, JOB, DEPTNO)
VALUES (1111, '홍길동', '직무1', '10');

--ORA-02291: integrity constraint (MADANG.SYS_C007146) violated
--     - parent key not found
INSERT INTO EMP01 (EMPNO, ENAME, JOB, DEPTNO)
VALUES (2222, '홍길동2', '직무2', '40'); --DEPT 테이블에 없는 데이터는 입력못함
--===================================
--데이터사전 테이블 USER_CONS_COLUMNS, USER_CONSTRAINTS 사용
--제약조건 조회 SQL
SELECT * FROM USER_CONS_COLUMNS;
SELECT * FROM USER_CONSTRAINTS;
SELECT A.TABLE_NAME, A.COLUMN_NAME, A.CONSTRAINT_NAME,
       B.CONSTRAINT_TYPE, 
       DECODE(B.CONSTRAINT_TYPE,
              'P', 'PRIMARY KEY',
              'U', 'UNIQUE',
              'C', 'CHECK OR NOT NULL',
              'R', 'FOREIGN KEY') CONSTRAINT_TYPE_DESC
  FROM USER_CONS_COLUMNS A, USER_CONSTRAINTS B
 WHERE A.TABLE_NAME = B.TABLE_NAME
   AND A.CONSTRAINT_NAME = B.CONSTRAINT_NAME
   AND A.TABLE_NAME LIKE 'EMP%'
;
--======================================
--테이블 레벨 방식에서 제약조건 지정
CREATE TABLE EMP02 (
    EMPNO NUMBER,
    ENAME VARCHAR2(30) NOT NULL,
    JOB VARCHAR2(10),
    DEPTNO VARCHAR2(10),
    
    PRIMARY KEY (EMPNO), --기본키(PRIMARY KEY) 설정
    FOREIGN KEY (DEPTNO) REFERENCES DEPT(ID)
);
SELECT * FROM EMP02;
INSERT INTO EMP02 (EMPNO, ENAME, JOB, DEPTNO)
VALUES (1111, '홍길동', '직무1', '10');

--ORA-02291: integrity constraint (MADANG.SYS_C007146) violated
--     - parent key not found
INSERT INTO EMP02 (EMPNO, ENAME, JOB, DEPTNO)
VALUES (2222, '홍길동2', '직무2', '40');
---------------------------------------------
--제약조건명을 명시적으로 선언해서 사용
--형태 : CONSTRAINT 제약조건이름 적용제약조건
CREATE TABLE EMP03 (
    EMPNO NUMBER,
    ENAME VARCHAR2(30) CONSTRAINT EMP03_ENAME_NN NOT NULL,
    JOB VARCHAR2(10),
    DEPTNO VARCHAR2(10),
    
    CONSTRAINT EMP03_EMPNO_PK PRIMARY KEY (EMPNO),
    CONSTRAINT EMP03_DEPTNO_FK FOREIGN KEY (DEPTNO) REFERENCES DEPT(ID)
);
INSERT INTO EMP03 (EMPNO, ENAME, JOB, DEPTNO)
VALUES (1111, '홍길동', '직무1', '10');

INSERT INTO EMP03 (EMPNO, ENAME, JOB, DEPTNO)
VALUES (2222, '홍길동2', '직무2', '40');

--=============================================
--기본키(PRIMARY KEY) 설정시 복합키 사용
CREATE TABLE HSCHOOL (
    HAK NUMBER(1), --학년
    BAN NUMBER(2), --반
    BUN NUMBER(2), --번호
    NAME VARCHAR2(30),
    CONSTRAINT HSCHOOL_HAK_BAN_BUN_PK PRIMARY KEY (HAK, BAN, BUN)
);
INSERT INTO HSCHOOL VALUES (1,1,1,'홍길동');
INSERT INTO HSCHOOL VALUES (1,1,2,'홍길동2');
INSERT INTO HSCHOOL VALUES (1,2,1,'홍길동3');
INSERT INTO HSCHOOL VALUES (2,1,1,'홍길동4');

--ORA-00001: unique constraint (MADANG.HSCHOOL_HAK_BAN_BUN_PK) violated
INSERT INTO HSCHOOL VALUES (1,1,1,'강감찬');
--==========================================
/* **** 제약조건 추가, 삭제 ************
--제약조건 추가
ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약형태 (컬럼명);

--제약조건 삭제
ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명;
*************************************/
--EMP01 테이블의 PRIMARY KEY 삭제 : SYS_C007145 
ALTER TABLE EMP01 DROP CONSTRAINT SYS_C007145;

--EMP01 테이블에 PRIMARY KEY 추가
--ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약형태 (컬럼명);
ALTER TABLE EMP01 ADD CONSTRAINT EMP01_EMPNO_PK PRIMARY KEY (EMPNO);
-------------
INSERT INTO EMP01 VALUES (1111, '홍길동2', '직무1', '10'); --unique constraint (MADANG.EMP01_EMPNO_PK) violated
ALTER TABLE EMP01 DROP CONSTRAINT EMP01_EMPNO_PK; --PK 삭제

INSERT INTO EMP01 VALUES (1111, '홍길동2', '직무1', '10'); --중복데이터 입력

--EMPNO에 중복데이터 또는 NULL값이 존재하면 PK 제약조건을 만들 수 없음
--해결방법) 데이터를 수정처리 - 중복데이터 삭제 및 수정해서 중복 없게
---- NULL값이 있는 경우 NULL이 아닌 값으로 수정처리 또는 데이터 삭제
ALTER TABLE EMP01 ADD CONSTRAINT EMP01_EMPNO_PK PRIMARY KEY (EMPNO);
UPDATE EMP01 SET EMPNO = 2222 WHERE ENAME = '홍길동2';
ALTER TABLE EMP01 ADD CONSTRAINT EMP01_EMPNO_PK PRIMARY KEY (EMPNO);

--===============================
/* **** 제약조건 활성화 또는 비활성화 *********
-- 제약조건이 설정되어 있는 것을 적용해제 또는 적용
ALTER TABLE 테이블명 DISABLE CONSTRAINT 제약이름; --비활성화(적용해제)
ALTER TABLE 테이블명 ENABLE CONSTRAINT 제약이름; --활성화(적용)
*************************/
--EMP01에 있는 외래키(FOREIGN KEY : SYS_C007146) 설정 비활성화
ALTER TABLE EMP01 DISABLE CONSTRAINT SYS_C007146; --외래키 비활성화 처리

INSERT INTO EMP01 VALUES (3333, '홍길동3', '직무3', '40');
--EMP01에 있는 외래키(FOREIGN KEY : SYS_C007146) 설정 활성화
ALTER TABLE EMP01 ENABLE CONSTRAINT SYS_C007146;
UPDATE EMP01 SET DEPTNO = 30 WHERE DEPTNO = '40'; --외래키 설정 위배 데이터 수정
ALTER TABLE EMP01 ENABLE CONSTRAINT SYS_C007146;
--===================================
