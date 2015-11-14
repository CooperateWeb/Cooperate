package com.cooperate.fly.service.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.service.user.UserExistsException;
import com.cooperate.fly.service.user.UserService;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.util.PwdHelper;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	private void validateUser(User user){
		if (user.getUserName()==null||user.getUserName().isEmpty()) {
			throw new IllegalArgumentException("user userName required");
		}
		if (user.getRoleId()==null || user.getRoleId()<=0) {
			throw new IllegalArgumentException("user role required");
		}
		
		if (user.getRoleId()!=0) {
			if(user.getGroupId()==null||user.getGroupId()<=0){
				throw new IllegalArgumentException("user group required");
			}
		}
	}
	
	@Override
	public void save(User user) throws UserExistsException {
		if (user==null) {
			return;
		}
		
		this.validateUser(user);
		if(user.getPassword()==null||"".equals(user.getPassword())){
			user.setPassword(PwdHelper.encryptPassword(Constant.DEFAULT_USER_PASSWORD));
		}
		
		if (this.userMapper.countByUserName(user.getUserName())>0) {
			throw new UserExistsException("user name exists");			
		}
		
		this.userMapper.insert(user);
		
	}

	@Override
	public void update(User user) {
		if (user==null) {
			return;
		}
		this.validateUser(user);
		user.setUserName(user.getUserName().trim());
		this.userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void delete(int userId) {
		this.userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public User findByUserId(int UserId) {
		return this.userMapper.selectByPrimaryKey(UserId);
	}

	@Override
	public List<User> findAll() {
		return this.userMapper.selectAll();
	}

	@Override
	public Page<User> findPage(Pageable pageRequest) {
		return this.userMapper.selectPage(pageRequest);
	}

	@Override
	public void updatePassword(int userId, String newPassword) {
		newPassword=PwdHelper.encryptPassword(newPassword);
		this.userMapper.updatePassword(userId, newPassword);
	}

	@Override
	public void resetPassword(int userId) {
		this.updatePassword(userId, Constant.DEFAULT_USER_PASSWORD);
	}

	@Override
	public User loadUserByUserNameAndPassword(String userName, String password) {
		password=PwdHelper.encryptPassword(password);
		return this.userMapper.loadUserByUserNameAndPassword(userName, password);
	}

	@Override
	public Page<User> findPage(Map<String, Object> params, Pageable pageRequest) {
		return this.userMapper.selectPageByParams(params, pageRequest);
	}

}
