package com.academy.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	//로그인 폼 요청 처리
	@GetMapping("/admin/loginform")
	public ModelAndView getForm() {
		//WEB-INF/views/ .jsp
		return new ModelAndView("admin/loginform");
	}
	
	//관리자 등록 폼 요청 처리
	@GetMapping("/admin/registform")
	public ModelAndView getRegistForm() {
		//WEB-INF/views/ .jsp
		return new ModelAndView("admin/regist");
	}
	
	//관리자 메인 페이지 요청 처리
	@GetMapping("/admin/main")
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
}
