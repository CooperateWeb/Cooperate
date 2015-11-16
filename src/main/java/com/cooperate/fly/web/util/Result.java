package com.cooperate.fly.web.util;

import java.util.List;

public class Result {

	private boolean successful=true;
	private String message;
	private Object data;
	private List<?> list1;
	private List<?> list2;
	public List<?> getList1() {
		return list1;
	}


	public void setList1(List<?> list) {
		this.list1 = list;
	}


	public List<?> getList2() {
		return list2;
	}


	public void setList2(List<?> list2) {
		this.list2 = list2;
	}


	public Result(){
	}
	

	public Result(boolean successful){
		this(successful,null);
	}
	
	public Result(boolean successful,String msg){
		this.successful=successful;
		this.message=msg;
	}
	
	public Result(String msg){
		this(false,msg);
	}
	
	public boolean isSuccessful(){
		return successful;
	}
	
	public void setSuccessful(boolean success){
		this.successful=success;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
