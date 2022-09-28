package com.academy.shopping.exception;

public class FileException extends RuntimeException{
	
	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable e) {
		super(msg,e);
	}
	
	public FileException(Throwable e) {
		super(e);
	}
}
