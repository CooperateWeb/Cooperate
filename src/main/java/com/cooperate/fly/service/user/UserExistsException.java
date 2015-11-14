package com.cooperate.fly.service.user;

public class UserExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8731664908856710247L;

	public UserExistsException(){
		super();
	}
	
	public UserExistsException(String message,Throwable cause,boolean enableSuppression,boolean writeableStackTrace){
		super(message,cause,enableSuppression,writeableStackTrace);
	}
	
	public UserExistsException(String message,Throwable cause){
		super(message,cause);
	}
	
	public UserExistsException(String message){
		super(message);
	}
	
	public UserExistsException(Throwable cause){
		super(cause);
	}
}
