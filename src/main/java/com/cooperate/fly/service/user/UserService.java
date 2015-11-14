package com.cooperate.fly.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cooperate.fly.bo.User;

public interface UserService {
	
	void save(User user)throws UserExistsException;
	
	void update(User user);
	
	void delete(int userId);
	
	User findByUserId(int UserId);
	
	List<User> findAll();
	
	Page<User> findPage(Pageable pageRequest);
	Page<User> findPage(Map<String, Object>params,Pageable pageRequest);
	
	void updatePassword(int userId,String newPassword );
	
	void resetPassword(int userId);
	
	User loadUserByUserNameAndPassword(String userName,String password);
}
