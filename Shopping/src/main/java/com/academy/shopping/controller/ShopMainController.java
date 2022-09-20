package com.academy.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//쇼핑몰 메인에 대한 요청 처리
@Controller
public class ShopMainController {
	
	@GetMapping("/shop")
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView("shop/index");
		return mav;
	}
}
