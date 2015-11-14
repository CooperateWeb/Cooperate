package com.cooperate.fly.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	
	//用户管理界面
	@RequestMapping("/user")
	public String sysUser(){
		return "system/user";
	}
	
	//角色管理页面
	@RequestMapping("/role")
	public String sysRole(){
		return "system/role";
	}
	
	//菜单管理页面
	@RequestMapping("/menu")
	public String sysMenu(){
		return "system/menu";
	}
	
	//群组管理页面
	@RequestMapping("/group")
	public String sysGroup(){
		return "system/group";
	}
	
	//修改密码页面
	@RequestMapping("/password")
	public String resetPwd(){
		return "system/password";
	}
	
	//数据包信息描述中数据项的定义
	@RequestMapping("/catalog")
	public String packageDesign(){
		return "system/packageDesign";
	}
}
