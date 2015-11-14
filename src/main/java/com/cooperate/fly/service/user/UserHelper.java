package com.cooperate.fly.service.user;

import com.cooperate.fly.bo.User;

public interface UserHelper {
	public void addUser(User user);
	
	/**
	 * �ṩ��¼����
	 * @param userName
	 * @param pwd
	 * @return
	 */
	User loginUser(String userName,String pwd);

	User getUserById(int id);
}
