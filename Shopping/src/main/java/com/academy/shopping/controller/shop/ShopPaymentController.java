package com.academy.shopping.controller.shop;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.admin.TopCategoryService;
import com.academy.shopping.model.domain.Cart;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.Product;

@Controller
public class ShopPaymentController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	//장바구니 목록 요청
	@GetMapping("/shop/cart/list")
	public ModelAndView getCartList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/payment/cart");

		//로그인 한 후, 장바구니에 물건 담기가 성공했다는 것은 세션에 객체가 담겨져 있다는 의미다
		//따라서 세션에 들어있는 Product 출력하자
		HttpSession session=request.getSession();
		
		//순서가 없는 Map에서 객체들을 반복문으로 꺼내는 방법
		Enumeration<String> en= session.getAttributeNames();
		List cartList= new ArrayList();
		while(en.hasMoreElements()) {  //요소가 있는 동안
			String key=en.nextElement();
			Object obj=session.getAttribute(key);
			if(obj instanceof Cart) {
				Cart cart = (Cart)obj;
				System.out.println("상품 정보: "+cart);
				cartList.add(cart);
			}
			
		}
		mav.addObject("cartList",cartList);
		return mav;
	}
	
	@PostMapping("/shop/cart/update")
	public ModelAndView update(HttpServletRequest request) {
		String[] product_id=request.getParameterValues("product_id");
		String[] quantity=request.getParameterValues("quantity");
		HttpSession session=request.getSession();
		for(int i=0; i<product_id.length;i++) {
			System.out.println("수정할 장바구니의 제품은 " +product_id[i]+" 수량은 " + quantity[i]);
			//세션에 들어있는 Cart 객체를 찾아내어, 사용자가 수정한 수량을 반영하자
			Cart cart=(Cart)session.getAttribute(product_id[i]);
			cart.setQuantity(Integer.parseInt(quantity[i]));
		}
		ModelAndView mav = new ModelAndView("redirect:/shop/cart/list");
		return mav;
	}
	
	@GetMapping("/shop/cart/delete")
	public ModelAndView delete(HttpServletRequest request, int product_id) {
		HttpSession session=request.getSession();
		session.removeAttribute(Integer.toString(product_id));  //키 값을 이용하여 Map에서 제거
		
		ModelAndView mav = new ModelAndView("redirect:/shop/cart/list");
		return mav;
	}
	
	//결제 페이지 보여주기
	@GetMapping("/shop/checkout")
	public ModelAndView checkout(HttpServletRequest request) {
		
		ModelAndView mav = new  ModelAndView("shop/payment/checkout");
		HttpSession session= request.getSession();
		//회원 정보
		//장바구니 목록
		Enumeration<String> en = session.getAttributeNames();  //순서없는 Map의 key값을 추출하여 늘여놓음
		Member member = null;
		List<Cart> cartList = new ArrayList();
		while(en.hasMoreElements()) {
			String key=en.nextElement();
			Object obj=session.getAttribute(key);
			if(obj instanceof Member) {
				member = (Member)obj;
			}else if(obj instanceof Cart) {
				cartList.add((Cart)obj);
			}
		}
		mav.addObject("member", member);
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	@PostMapping("/shop/pay")
	public ModelAndView pay(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//구매자 정보
		//배송 정보
		//결제 정보
		return mav;
	}
	
}
