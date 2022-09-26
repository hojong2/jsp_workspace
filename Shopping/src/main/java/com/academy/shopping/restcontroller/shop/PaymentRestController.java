package com.academy.shopping.restcontroller.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Cart;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.util.Message;

@RestController
public class PaymentRestController {
	//장바구니 등록 요청
	@PostMapping("/cart")
	public ResponseEntity<Message> registCart(HttpServletRequest request, Cart cart){
		System.out.println(cart.getProduct_name());
		cart.setQuantity(1);
		//고객이 넘긴 상품 1개를 DB에 넣지 않고, 메모리에 저장한다
		//session 장점: table 컬럼에 존재하지 않는 속성도 담을 수 있다. 즉 자유롭다.
		HttpSession session=request.getSession();
		session.setAttribute(Integer.toString(cart.getProduct_id()), cart);//세션은 Map 자료형이므로, 순서가 없다 따라서 넣을때 유일한 구분값인 key를 부여하자
		
		//응답메시지 생성
		Message message = new Message(1, "장바구니에 상품이 담겼습니다.");
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	
}
