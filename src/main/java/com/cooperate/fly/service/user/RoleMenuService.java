package com.cooperate.fly.service.user;

import java.util.List;

import com.cooperate.fly.bo.RoleMenu;

public interface RoleMenuService {
	
	void save(RoleMenu rm);
	
	void save(int roleId,int menuId);
	
	void save(int roleId,List<Integer> menuIds);
	
	List<Integer> findMenuIdsByRoleId(int roleId);
}
