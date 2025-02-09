/* ********* 사용자 생성, 삭제 *********
-- 사용자 생성 : CREATE USER
CREATE USER 사용자명(유저명) --"MDGUEST" 
IDENTIFIED BY 비밀번호 --"mdguest"  
DEFAULT TABLESPACE 테이블스페이스 --"USERS"
TEMPORARY TABLESPACE 임시작업테이블스페이스 --"TEMP";

-- 사용할 용량 지정(수정)
ALTER USER "MDGUEST" QUOTA UNLIMITED ON "USERS";

-- 사용자 수정 : ALTER USER 
ALTER USER 사용자명(유저명) IDENTIFIED BY 비밀번호;

-- 권한부여(롤 사용 권한 부여, 롤 부여)
GRANT "CONNECT" TO "MDGUEST" ;
GRANT "RESOURCE" TO "MDGUEST" ;

-- 사용자 삭제 : DROP USER
DROP USER 유저명 [CASCADE];
--CASCADE : 삭제시점에 사용자(유저)가 보유한 모든 데이타 삭제
*************************************/
--(관리자계정)
-- USER SQL
CREATE USER "MDGUEST" IDENTIFIED BY "mdguest"  
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- QUOTAS
ALTER USER "MDGUEST" QUOTA UNLIMITED ON "USERS";

-- ROLES
GRANT "CONNECT" TO "MDGUEST" ;
GRANT "RESOURCE" TO "MDGUEST" ;
-----------------------------------------
--CONNECT, RESOURCE 롤(ROLE)에 있는 권한 확인
SELECT * 
  FROM DBA_SYS_PRIVS
 WHERE GRANTEE IN ('CONNECT', 'RESOURCE')
;
GRANT CONNECT, RESOURCE TO MDGUEST;
--=======================================
/****** 권한 부여(GRANT), 권한 취소(REVOKE) **********************
GRANT 권한 [ON 객체] TO {사용자|ROLE|PUBLIC,.., n} [WITH GRANT OPTION]
--PUBLIC은 모든 사용자에게 적용을 의미

GRANT 권한 TO 사용자; --권한을 사용자에게 부여
GRANT 권한 ON 객체 TO 사용자; -객체에 대한 권한을 사용자에게 부여
-->> WITH GRANT OPTION :객체에 대한 권한을 사용자에게 부여 
-- 권한을 받은 사용자가 다른 사용자에게 권한부여할 권리 포함
GRANT 권한 ON 객체 TO 사용자 WITH GRANT OPTION;
--------------------
-->>>권한 취소(REVOKE)
REVOKE 권한 [ON 객체] FROM {사용자|ROLE|PUBLIC,.., n} CASCADE
--CASCADE는 사용자가 다른 사용자에게 부여한 권한까지 취소시킴
  (확인 및 검증 후 작업)

REVOKE 권한 FROM 사용자; --권한을 사용자에게 부여
REVOKE 권한 ON 객체 FROM 사용자; -객체에 대한 권한을 사용자에게 부여
*************************************************/
--MDGUEST 에게 MADANG.BOOK 사용권한 부여(GRANT)
GRANT SELECT ON MADANG.BOOK TO MDGUEST;

--(MDGUEST실행)SELECT 권한을 받음 BOOK 테이블에 대해서만
SELECT * FROM MADANG.BOOK; --사용가능
UPDATE MADANG.BOOK SET PRICE = 0; --권한없음 UPDATE 못함
DELETE FROM BOOK; --권한없음 DELETE 못함

SELECT * FROM MADANG.CUSTOMER; --사용못함
--======================================
--(SYSTEM) MDGUEST에게 SELECT, UPDATE 권한 부여(CUSTOMER 테이블)
GRANT SELECT, UPDATE ON MADANG.CUSTOMER TO MDGUEST;

--(MDGUEST) SELECT, UPDATE 작업 가능
--MADANG.CUSTOMER 테이블에 SELECT, UPDATE 권한 받은 후
SELECT * FROM MADANG.CUSTOMER; --조회가능
UPDATE MADANG.CUSTOMER 
   SET PHONE = '010-1111-2222'
 WHERE NAME = '박세리'
;
-----------------------
--권한 회수(취소) - REVOKE
--MADANG 유저가 가지고 있는 BOOK 테이블에 대한 SELECT권한 회수
REVOKE SELECT ON MADANG.BOOK FROM MDGUEST;

REVOKE SELECT, UPDATE ON MADANG.CUSTOMER FROM MDGUEST;

