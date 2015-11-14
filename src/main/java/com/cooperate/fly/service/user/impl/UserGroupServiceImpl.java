package com.cooperate.fly.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cooperate.fly.bo.UserGroup;
import com.cooperate.fly.mapper.UserGroupMapper;
import com.cooperate.fly.service.user.DataExistsException;
import com.cooperate.fly.service.user.UserExistsException;
import com.cooperate.fly.service.user.UserGroupService;
import com.mchange.lang.ThrowableUtils;

@Service
public class UserGroupServiceImpl implements UserGroupService{
	
	@Resource
	private UserGroupMapper userGroupMapper;

	@Override
	public void save(UserGroup group) throws DataExistsException {
		if(group.getGroupName()==null || "".equals(group.getGroupName())){
			throw new IllegalArgumentException("group name required");
		}
		
		if(group.getGroupNo()==null){
			throw new IllegalArgumentException("group No. required");
		}
		
		UserGroup uGroup=this.userGroupMapper.selectByGroupName(group.getGroupName());
		
		if(uGroup != null){
			throw new DataExistsException("group name exists");
		}
		
		this.userGroupMapper.insert(group);
	}

	@Override
	public void updata(UserGroup group) {
		if(group==null){
			return;
		}
		if(group.getGroupName()==null||group.getGroupName().isEmpty()){
			throw new IllegalArgumentException("group name required");
		}
		
		if(group.getGroupId()==null){
			throw new IllegalArgumentException("group id required");
		}
		
		this.userGroupMapper.updateByPrimaryKeySelective(group);
	}

	@Override
	public void delete(int groupId) {
		this.userGroupMapper.deleteByPrimaryKey(groupId);
	}

	@Override
	public List<UserGroup> findAll() {
		return this.userGroupMapper.selectAll();
	}

	@Override
	public UserGroup findByGroupId(int groupId) {
		return this.userGroupMapper.selectByPrimaryKey(groupId);
	}

}
