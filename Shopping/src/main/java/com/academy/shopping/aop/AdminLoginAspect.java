package com.academy.shopping.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

import com.academy.shopping.exception.AdminException;

//관리자모드에서 로그인을 거치지 않고 진행한 요청에 대해
//거부처리
public class AdminLoginAspect {
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable, AdminException {
		Object returnObj=null; //원래 호출하려던 메서드의 호출 후 반환되는 객체(String, ModelAndView)
		
		System.out.println("[나] 강림");
		
		//세션을 얻어와서 해당 세션에 admin 객체가 들어있는지 판단 및 처리
		
		Object[] args=joinPoint.getArgs();  //원래 호출하려던 메서드의 매개변수들
		HttpServletRequest request=null;
		for(int i=0;i<args.length;i++) {
			
			if(args[i] instanceof HttpServletRequest) {
				System.out.println("매개변수 자료형은 "+args);
				request=(HttpServletRequest)args[i];
			}
		}
		
		//매개변수에 요청 객체가 존재한다면
		HttpSession session=null;
		String uri=request.getRequestURI();
		//로그인 필요 유무에 따라 나눈다
		if(uri.equals("/admin/loginform")) {
			returnObj = joinPoint.proceed();
		}else {
			if(request !=null) {
				session=request.getSession();
				
				if(session.getAttribute("admin")==null) {
					System.out.println("인증 필요");
					throw new AdminException("관리자 로그인이 필요합니다.");
				}else {
					//원래 호출하려했던 메서드를 대신 호출해주자
					returnObj = joinPoint.proceed();
				}
			}
		}
		
		
		return returnObj;
	}
}
