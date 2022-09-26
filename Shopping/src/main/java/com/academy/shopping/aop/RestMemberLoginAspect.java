package com.academy.shopping.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.ResponseEntity;

import com.academy.shopping.exception.MemberException;

public class RestMemberLoginAspect {
	String tag=this.getClass().getName();
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable, MemberException {
		Object returnObj=null;
		System.out.println("Rest 방식 회원 로그인 판단에 관여함");
		System.out.println(tag+"호출하려던 메서드" + joinPoint.getSignature().getName());
		HttpServletRequest request =null;
		HttpSession session=null;
		Object[] args=joinPoint.getArgs();
		for(Object arg:args) {
			System.out.println(tag+"매개변수"+arg);
			if(arg instanceof HttpServletRequest) {
				request=(HttpServletRequest)arg;
			}
		}
		ResponseEntity entity=null;
		//제외될 명단을 작성하기 위한 uri조사
		String uri=request.getRequestURI();
		if(uri.equals("/rest/member/login")) {
			returnObj=joinPoint.proceed();
			if(returnObj instanceof ResponseEntity) {
				entity=(ResponseEntity)returnObj;
				System.out.println("엔티티 반환"+entity);
			}
		}else {
			session=request.getSession();
			
			if(session.getAttribute("member")==null) {
				throw new MemberException("회원 로그인이 필요한 서비스입니다(rest)");
			}else {
				returnObj = joinPoint.proceed();
				if(returnObj instanceof ResponseEntity) {
					entity=(ResponseEntity)returnObj;
					System.out.println("엔티티 반환"+entity);
				}
			}
		}
		
		
		return returnObj;
	}
}
