package com.cooperate.fly.service.operator.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.util.Utils;

public class GetMessageThread extends Thread {

	@Autowired
	UserMapper usermapper;
	
	private String userName;
	public GetMessageThread(String user_name){
		userName = user_name;
	}
	public void run(){
		while(true){
			try {
				User user = usermapper.selectByUserName(userName);
				String message = user.getMessage();
				if(message!=""){
					Utils.showMessage(message);
					usermapper.deleteMessageByUserName(userName);
				}
				sleep(1000*60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
