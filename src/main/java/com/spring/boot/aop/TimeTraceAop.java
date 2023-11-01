package com.spring.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP를 사용할 수 있게 하는 어노테이션
//@Component 빈 주입을 위해 SpringConfig에서 주입 하였기 때문에 컴포넌트 어노테이션 생략하였음
public class TimeTraceAop {

/*
 * @Around("execution(* com.spring.boot..*(..))") 으로 설정하였을 때는
 * SpringConfig에서 Bean을 주입하였기 때문에 순환 참조에 대한 문제가 발생하였다 그 이유는
 * SpringConfig에 있는 TimeTraceAop 메서드도 AOP를 처리하기 때문이다 그래서
 * SpringConfig를 target으로 지정하기 않도록 제외 시켜주면 된다.  
 */
	
	// @Around를 통해 AOP를 적용시킬 곳을 설정할 수 있음.
	@Around("execution(* com.spring.boot..*(..)) && !target(com.spring.boot.SpringConfig)")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		
		long start = System.currentTimeMillis();
		System.out.println("START : " + joinPoint.toString()); // 어떤 메서드인지 확인 가능
		
		try {
			
			return joinPoint.proceed(); // 다음 메소드로 진행이 된다.
			
		} finally {
			
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms"); 
		}
		
		
	}
	
}
