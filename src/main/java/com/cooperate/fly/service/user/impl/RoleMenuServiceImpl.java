package com.cooperate.fly.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cooperate.fly.bo.RoleMenu;
import com.cooperate.fly.mapper.RoleMenuMapper;
import com.cooperate.fly.service.user.RoleMenuService;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{
	
	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public void save(RoleMenu rm) {
		this.roleMenuMapper.insert(rm);
	}

	@Override
	public void save(int roleId, int menuId) {
		if (roleId<=0) {
			return;
		}
		RoleMenu rm=new RoleMenu();
		rm.setRoleId(roleId);
		rm.setMenuId(menuId);
		this.save(rm);
	}

	/**
	 * 保存时先删除用户的权限菜单，在重新插入
	 * 需要完整的事务控制
	 */
	@Override
	@Transactional
	public void save(int roleId, List<Integer> menuIds) {
		if (roleId<=0) {
			return;
		}
		this.roleMenuMapper.deleteByRoleId(roleId);
		
		for(Integer menuId:menuIds){
			this.save(roleId, menuId);
		}
	}

	@Override
	public List<Integer> findMenuIdsByRoleId(int roleId) {
		return this.roleMenuMapper.selectMenuIdByRoleId(roleId);
	}
	

}
