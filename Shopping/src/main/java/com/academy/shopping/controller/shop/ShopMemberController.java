package com.academy.shopping.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.admin.TopCategoryService;
import com.academy.shopping.model.domain.Member;

@Controller
public class ShopMemberController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@GetMapping("/shop/member/registform")
	public ModelAndView getRegistFrom(HttpServletRequest request) {
		List topCategoryList=topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/join");
		mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
	
	@GetMapping("/shop/member/loginform")
	public ModelAndView getLoginForm(HttpServletRequest request) {
		List topCategoryList=topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/login");
		mav.addObject("topCategoryList", topCategoryList);
		return mav;
	}
	
	@GetMapping("/shop/member/logout")
	public ModelAndView logout(HttpServletRequest request) {
		//세션을 무효화 시킴, 이 시점부터는 세션에 담았던 모든 정보를 참조할 수 없게 된다
		HttpSession session = request.getSession();
		session.invalidate();  //세션 초기화
		
		ModelAndView mav = new ModelAndView("redirect:/shop");
		return mav;
	}
	
	
}
