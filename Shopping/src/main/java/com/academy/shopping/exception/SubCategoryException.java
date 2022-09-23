package com.academy.shopping.exception;

public class SubCategoryException extends RuntimeException{
	
	public SubCategoryException(String msg) {
		super(msg);
	}
	
	public SubCategoryException(String msg, Throwable e) {
		super(msg,e);
	}
	
	public SubCategoryException(Throwable e) {
		super(e);
	}
}
