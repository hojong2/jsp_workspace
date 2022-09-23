package com.academy.shopping.restcontroller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.util.Message;

@RestController
public class PaymentRestController {
	//장바구니 등록 요청
	@PostMapping("/cart")
	public ResponseEntity<Message> registCart(HttpServletRequest request, Product product){
		System.out.println(product.getProduct_name());
		return null;
	}
}
