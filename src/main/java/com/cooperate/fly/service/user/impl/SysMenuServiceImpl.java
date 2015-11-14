package com.cooperate.fly.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cooperate.fly.bo.SysMenu;
import com.cooperate.fly.mapper.RoleMenuMapper;
import com.cooperate.fly.mapper.SysMenuMapper;
import com.cooperate.fly.mapper.UserRoleMapper;
import com.cooperate.fly.service.user.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	
	@Resource
	private SysMenuMapper sysMenuMapper;
	
	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public List<SysMenu> findAll() {
		return sysMenuMapper.selectAll();
	}

	@Override
	public void save(SysMenu menu) {
		if(menu==null){
			return;
		}
		if(menu.getMenuName()==null|| menu.getMenuName().isEmpty()){
			throw new IllegalArgumentException("menu name required");
		}
		if(menu.getParentMenuId()!=null && "".equals(menu.getParentMenuId()==0)){
			menu.setParentMenuId(null);;
		}
		this.sysMenuMapper.insert(menu);
	}

	//TODO:判断menu之前数据库中是否已经存在
	@Override
	public void update(SysMenu menu) {
		if(menu==null)
			return;
		if(menu.getMenuId()==null){
			throw new IllegalArgumentException("men id required");
		}
		if(menu.getMenuName()==null||"".equals(menu.getMenuName())){
			throw new IllegalArgumentException("menu name required");
		}
		this.sysMenuMapper.updateByPrimaryKey(menu);
	}

	@Override
	public void delete(int menuId) {
		if(menuId<=0)
			return;
		this.sysMenuMapper.deleteByPrimaryKey(menuId);
		
		//删除菜单需要删除对应的角色赋予的菜单
		this.roleMenuMapper.deleteByMenuId(menuId);
	}

	@Override
	public List<SysMenu> findByChildId(List<Integer> menuIds) {
		
		return this.sysMenuMapper.selectByChildId(menuIds);
	}

}
