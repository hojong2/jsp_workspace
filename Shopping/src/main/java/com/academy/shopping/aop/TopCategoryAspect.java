package com.academy.shopping.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.admin.TopCategoryService;

/*
 * 쇼핑몰에서 상위카테고리는 어디서건 보여줄 정보이므로, 어플리케이션의 횡단적 관심사항에 해당된다.
 * 따라서 상위 카테고리 목록을 가져오는 코드를 별도의 객체로 정의하여 AOP의 Aspect로 정의해놓고
 * 필요할때마다 이 코드를 관여시키자
 */
public class TopCategoryAspect {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	//이 메서드는, 쇼핑몰의 상위카테고리를 필요로하는 모든 메서드에서 공통적으로 동작할 예정
	public Object getCategoryList(ProceedingJoinPoint joinPoint) {
		Object target= joinPoint.getTarget();
		System.out.println("호출하려던 객체는 "+target.getClass().getName());
		
		System.out.println(joinPoint.getSignature());
		Object returnObj=null;
		try {
			returnObj=joinPoint.proceed();  //대신 메서드 호출
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//원래의 요청 대신에 Aspect가(프록시=대리)가 컨트롤러의 메서드를 대신 호출하고, 반환된 ModelAndView에
		//정보를 심어본다.
		
		if(returnObj instanceof ModelAndView) {  //ModelAndView 자료형이면
			ModelAndView mav = (ModelAndView)returnObj;
			List list= topCategoryService.selectAll();
			mav.addObject("topCategoryList", list);
			returnObj=mav;
		}
		return returnObj;  //DispatcherServlet에게 반환되며, 이때 DispatcherServlet은 viewReosolver에게 jsp 페이지를 얻기위한 해석을 맡김
	}
}
