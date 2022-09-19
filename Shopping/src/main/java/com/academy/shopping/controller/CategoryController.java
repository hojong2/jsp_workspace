package com.academy.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
	//관리자 - 카테고리 관리 메인 요청
	@GetMapping("/admin/category/list")
	public ModelAndView categoryMain() {
		ModelAndView mav = new ModelAndView("admin/category/main");
		
		return mav;
	}
}
