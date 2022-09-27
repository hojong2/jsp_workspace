package com.academy.shopping.exception;

public class PayMethodException extends RuntimeException{
	public PayMethodException(String msg) {
		super(msg);
	}
	
	public PayMethodException(String msg, Throwable e) {
		super(msg,e);
	}
	
	public PayMethodException(Throwable e) {
		super(e);
	}
}
