package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.admin.TopCategoryService;

//쇼핑몰 메인에 대한 요청 처리
@Controller
public class ShopMainController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@GetMapping("/shop")
	public ModelAndView getMain(HttpServletRequest request) {
		//카테고리 가져오기
		//신상품 및 각정 기획상품등 진열
		ModelAndView mav = new ModelAndView("shop/index");
		
		
		//아래 코드는 앞으로 AOP를 이용하여 Aspect에게 맡김
		//List topCategoryList = topCategoryService.selectAll();
		//mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
}
