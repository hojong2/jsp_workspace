package com.academy.springmvcsimple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springmvcsimple.domain.Emp;
import com.academy.springmvcsimple.model.member.DeptDAO;
import com.academy.springmvcsimple.model.member.EmpDAO;
import com.academy.springmvcsimple.model.member.MemberService;

@Controller
public class MemberController {
	String TAG=this.getClass().getName();
	
	//컨트롤러는 서비스를 통해 업무를 부탁한다
	@Autowired
	private MemberService memberService;  //DI를 구현하기 위해 상위 인터페이스를 보유한다
	
	//사원등록
	@RequestMapping(value="/member/regist", method=RequestMethod.POST)
	public ModelAndView regist(Emp emp) {
		System.out.println(TAG+ ": 사원명" + emp.getEname());
		System.out.println(TAG+ ": 희망급여" + emp.getSal());
		System.out.println(TAG+ ": 부서명" + emp.getDept().getDname());
		
		memberService.regist(emp);
		
		ModelAndView mav = new ModelAndView("redirect:/member/list");
		return mav;
	}
	
	//사원 목록
	@RequestMapping(value="/member/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		List memberList=memberService.selectAll();
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberList", memberList);
		mav.setViewName("member/list");
		return mav;
	}
	//사원 한건
	@RequestMapping(value="/member/detail", method=RequestMethod.GET)
	public ModelAndView select(int empno) {
		Emp emp=memberService.select(empno);
		ModelAndView mav = new ModelAndView("member/detail");
		mav.addObject("emp", emp);
		
		return mav;
	}
}