package com.cooperate.fly.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.bo.UserRole;
import com.cooperate.fly.service.user.RoleMenuService;
import com.cooperate.fly.service.user.SysMenuService;
import com.cooperate.fly.service.user.UserRoleService;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.web.util.EasyUITreeNode;
import com.cooperate.fly.web.util.Result;
import com.cooperate.fly.web.util.WebFrontHelper;

@Controller
@RequestMapping("/role")
@SessionAttributes(Constant.USER_SESSION_KEY)
public class UserRoleController extends BaseController {
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private SysMenuService sysMenuService;
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<UserRole> listAll() {
		List<UserRole> roles = this.userRoleService.findAll();
		return roles;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Result add(UserRole role) {

		this.userRoleService.save(role);
		return new Result();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Result update(UserRole role) {

		this.userRoleService.update(role);
		return new Result();
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(@RequestParam(value="roleId", required=true) int roleId) {
		this.userRoleService.delete(roleId);
		return new Result();
	}
	
	@RequestMapping(value="/menu")
	@ResponseBody
	public List<EasyUITreeNode> menu(@RequestParam(value="roleId", required=true) int roleId) {
		
		List<Integer> roleMenus = this.roleMenuService.findMenuIdsByRoleId(roleId);
		
		EasyUITreeNode node = WebFrontHelper.buildTreeForEasyuiTree(this.sysMenuService.findAll(), roleMenus);	
		
		return node.getChildren();
	}
	
	@RequestMapping(value="/saveMenu")
	@ResponseBody
	public Result saveMenu(
			@RequestParam(value="roleId", required=true) int roleId, 
			@RequestParam(value="menuId", required=true) List<Integer> menuIds) {
		
		this.roleMenuService.save(roleId, menuIds);
		return new Result();
	}
	
	// 角色列表，除非超级管理员， 否则没有chao'guan
	@RequestMapping("/listNoRoot")
	@ResponseBody
	public List<UserRole> listNoRoot(
			@ModelAttribute(Constant.USER_SESSION_KEY) User user) {

		if (user == null) {
			return null;
		}
		
		List<UserRole> roles = this.userRoleService.findAll();
		if (user.getRoleId()!=0) {
			List<UserRole> noRootRoles = new ArrayList<UserRole>(roles.size()-1);
			for (UserRole role : roles) {
				if (role.getRoleId()==0) 
					continue;
				noRootRoles.add(role);
			}
			return noRootRoles;
		}
		return roles;
	}
}
