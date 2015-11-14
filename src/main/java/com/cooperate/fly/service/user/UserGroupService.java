package com.cooperate.fly.service.user;

import java.util.List;

import com.cooperate.fly.bo.UserGroup;

public interface UserGroupService {
	
	/**
	 * 增加分组，当分组存在则抛出异常
	 * @param group
	 * @throws DataExistsException
	 */
	void save(UserGroup group) throws DataExistsException;
	
	void updata(UserGroup group);
	
	void delete(int groupId);
	
	List<UserGroup> findAll();
	
	UserGroup findByGroupId(int groupId);
}
