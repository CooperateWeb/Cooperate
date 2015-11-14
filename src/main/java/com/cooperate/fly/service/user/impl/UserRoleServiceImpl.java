package com.cooperate.fly.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.NativeWebRequest;

import com.cooperate.fly.bo.UserRole;
import com.cooperate.fly.mapper.RoleMenuMapper;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.mapper.UserRoleMapper;
import com.cooperate.fly.service.user.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Resource
	private UserRoleMapper userRoleMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public List<UserRole> findAll() {
		return userRoleMapper.selectAll();
	}

	@Override
	public void save(UserRole ur) {
		if(ur==null){
			return;
		}
		if(ur.getRoleName()==null || ur.getRoleName().isEmpty()){
			throw new IllegalArgumentException("role name required");
		}
		
		this.userRoleMapper.insert(ur);
	}

	@Override
	public void update(UserRole ur) {
		if(ur==null){
			return;
		}
		if(ur.getRoleId()<=0){
			throw new IllegalArgumentException("role id is Illegal");
		}
		if(ur.getRoleName()==null||ur.getRoleName().isEmpty()){
			throw new IllegalArgumentException("role name required");
		}
		
		this.userRoleMapper.updateByPrimaryKeySelective(ur);
	}

	@Override
	@Transactional
	public void delete(int roleId) {
		if(roleId<=0){
			return;
		}
		this.userRoleMapper.deleteByPrimaryKey(roleId);
		//删除角色需要把用户的角色信息置为null
		this.userMapper.updateUserByRoleNull(roleId);
		
		//删除角色对应的菜单信息
		this.roleMenuMapper.deleteByRoleId(roleId);
	}

}
