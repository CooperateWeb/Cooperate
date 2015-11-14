package com.cooperate.fly.service.user;

import java.util.List;

import com.cooperate.fly.bo.UserRole;

public interface UserRoleService {
	
	List<UserRole> findAll();
	
	void save(UserRole ur);
	
	void update(UserRole ur);
	
	void delete(int roleId);
	
}
