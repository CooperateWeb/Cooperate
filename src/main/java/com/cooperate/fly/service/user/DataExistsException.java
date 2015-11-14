package com.cooperate.fly.service.user;

public class DataExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3195100417725894115L;
	
	public DataExistsException(){
		super();
	}
	
	public DataExistsException(String message){
		super(message);
	}

}
