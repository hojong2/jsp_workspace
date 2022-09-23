package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.admin.TopCategoryService;

@Controller
public class ShopPaymentController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	//장바구니 목록 요청
	@GetMapping("/shop/cart/list")
	public ModelAndView getCartList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/payment/cart");
		List list= topCategoryService.selectAll();
		mav.addObject(list);
		return mav;
	}
}
