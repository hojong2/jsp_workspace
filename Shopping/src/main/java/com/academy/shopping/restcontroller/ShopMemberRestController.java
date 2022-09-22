package com.academy.shopping.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.member.MemberServiceImpl;
import com.academy.shopping.model.util.Message;

@Controller
public class ShopMemberRestController {
	
	@Autowired
	private MemberServiceImpl memberServiceImpl;
	
	@PostMapping("/member")
	public ResponseEntity regist(Member member) {
		memberServiceImpl.insert(member);
		
		//memberService.insert(member)
		//HttpStatus 100 작업진행중, 200 성공, 300 추가작업필요 ,400 자원없음, 500 심각오류
		Message message = new Message(1,"가입성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/member/{customer_id}")
	public ResponseEntity<Message> getId(@PathVariable("customer_id") String customer_id){
		memberServiceImpl.selectCustomerId(customer_id);
		System.out.println("검증할 아이디는 "+customer_id);
		Message message = new Message(1, customer_id+"는 사용가능 합니다.");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Message> handleException(MemberException e){
		
		Message message = new Message(0, e.getMessage());
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
	
}
