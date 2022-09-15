package com.academy.springdb.exception;

//뉴스와 관련된 예외정보를 처리할 나만의 예외 객체
//sun에서 이미 명칭이 정해지고, try~catch 처리를 강제하는 예외를 강요된 예외라 하고, 
//개발자가 원하는 예외를 재정의하고, 강요되지 않은 예외를 RuntimeException이라 한다.

public class CommentsException extends RuntimeException{
	
	public CommentsException(String msg) {
		super(msg);
	}
	
	public CommentsException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public CommentsException(Throwable e) {
		super(e);
	}
}
