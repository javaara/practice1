package com.spring.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service //객체(인스턴스) 생성
@Aspect //포인트컷 + 어드바이스 연결
public class BeforeAdvice {
	/*
	<aop:pointcut id="allPointcut" 
			expression="execution(* com.spring.biz..*Impl.*(..))" />
	<aop:pointcut id="getPointcut" 
			expression="execution(* com.spring.biz..*Impl.get*(..))" />
	*/
	
	//포인트컷 설정 : 명칭은 메소드명을 사용 
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.spring.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	//어드바이스 메소드
	//어드바이스에 동작시점 설정 + 포인트컷 지정
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String methodName = jp.getSignature().getName(); //실행될 메소드명
		Object[] args = jp.getArgs();
		
		String paramStr = "파라미터 없음";
		if (args.length != 0 && args != null) {
			paramStr = args[0].toString();
		}
		System.out.println("[사전처리] " + methodName + " 메소드"
				+ ", args 정보 : " + paramStr);
	}

}
