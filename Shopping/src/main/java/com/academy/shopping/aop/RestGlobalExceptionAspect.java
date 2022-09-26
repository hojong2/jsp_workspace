package com.academy.shopping.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.util.Message;

@ControllerAdvice
public class RestGlobalExceptionAspect {

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Message> handleException(MemberException e){
		//응답메시지 생성
		Message message = new Message(0, e.getMessage());
		ResponseEntity<Message> entity= new ResponseEntity<Message>(message, HttpStatus.OK);
		return entity;
	}
}
