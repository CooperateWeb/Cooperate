package com.cooperate.fly.service.user.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.service.user.UserHelper;

@Service("UserHelper")
public class UserHelperImpl implements UserHelper{
	
	@Autowired
	private UserMapper userMapper;
	
	Logger log=Logger.getLogger(getClass());

	/**
	 * 增加一个用户
	 */
	@Override
	public void addUser(User user) {
		userMapper.insert(user);		
	}

	@Override
	public User loginUser(String userName,String password) {
		User user=null;
		user=userMapper.selectByUserName(userName);
		if(user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	@Override
	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	

}
