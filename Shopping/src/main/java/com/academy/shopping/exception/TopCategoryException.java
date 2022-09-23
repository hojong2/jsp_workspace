package com.academy.shopping.exception;

public class TopCategoryException extends RuntimeException{
	
	public TopCategoryException(String msg) {
		super(msg);
	}
	
	public TopCategoryException(String msg, Throwable e) {
		super(msg,e);
	}
	
	public TopCategoryException(Throwable e) {
		super(e);
	}
}
