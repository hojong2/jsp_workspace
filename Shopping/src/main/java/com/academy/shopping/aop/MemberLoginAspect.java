package com.academy.shopping.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.admin.TopCategoryService;

//횡단적 관심사항에 대한 공통코드를 작성해놓은 객체 (하나의 관점으로 둘 예정)
public class MemberLoginAspect {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable, MemberException{
		

		Object returnObj=null;
		System.out.println("살아있어요");
		//세션을 꺼내자
		Object[] args= joinPoint.getArgs();
		HttpServletRequest request=null;
		for(int i=0; i<args.length;i++) { //요청 객체 잡아내기
			Object arg=args[i];
			if(arg instanceof HttpServletRequest) {
				request=(HttpServletRequest)arg;
			}
			
		}
		String uri=request.getRequestURI();
		HttpSession session=null;
		ModelAndView mav = null;
		if(
				uri.equals("/shop") ||
				uri.equals("/shop/member/registform") ||
				uri.equals("/shop/member/loginform") ||
				uri.equals("/shop/product") ||
				uri.equals("/shop/product/view")
		) {//로그인 하지 않고도 접근 가능한 uri
			returnObj = joinPoint.proceed();
			if(returnObj instanceof ModelAndView) {
				mav=(ModelAndView)returnObj;
			}
		}else {
			//세션체크
			session=request.getSession();
			if(session.getAttribute("member")==null) {
				throw new MemberException("회원 로그인이 필요한 서비스입니다.");
			}else {
				returnObj = joinPoint.proceed();
				if(returnObj instanceof ModelAndView) {
					mav=(ModelAndView)returnObj;
				}
			}
		}
		if(mav!=null) {
			List list = topCategoryService.selectAll();
			mav.addObject("topCategoryList",list);
			returnObj=mav;
		}
		return returnObj;
	}
}
