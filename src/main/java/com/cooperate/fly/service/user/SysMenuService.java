package com.cooperate.fly.service.user;

import java.util.List;

import com.cooperate.fly.bo.SysMenu;

public interface SysMenuService {
	
	List<SysMenu> findAll();
	
	void save(SysMenu menu);
	
	void update(SysMenu menu);
	
	void delete(int menuId);
	
	List<SysMenu> findByChildId(List<Integer> menuIds);
}
