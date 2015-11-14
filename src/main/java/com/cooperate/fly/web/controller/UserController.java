package com.cooperate.fly.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.service.user.UserService;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.util.PwdHelper;
import com.cooperate.fly.web.util.Result;

@Controller
@RequestMapping("/user")
@SessionAttributes(Constant.USER_SESSION_KEY)
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<User> list(){
		return this.userService.findAll();
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Map<String, Object> page(@RequestParam(value="page",defaultValue="1")int pageNo,
									@RequestParam(value="rows",defaultValue="20")int pageSize,
									@ModelAttribute(Constant.USER_SESSION_KEY)User user){
		pageNo=pageNo>0?pageNo-1:pageNo;
		Pageable pageRequest=(Pageable) new PageRequest(pageNo, pageSize);
		Page<User> page=null;
		
		if(user==null){
			return null;
		}
		
		//系统管理员才能查看全部用户，其他用户只能查看自己机构内部的用户
		if (user.getRoleId()==0) {
			page=this.userService.findPage(pageRequest);
		}else {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("groupId", user.getGroupId());
			page=this.userService.findPage(params,pageRequest);
		}
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("total", page.getTotalElements());
		data.put("rows", page.getContent());
		return data;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(User user) throws Exception{
		this.userService.save(user);
		return new Result();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(User user) throws Exception{
		this.userService.update(user);
		return new Result();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam("userId")int userId){
		this.userService.delete(userId);
		return new Result();
	}
	
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Result updatePassword(
			@RequestParam("userId")int userId,
			@RequestParam(value="oldPassword",defaultValue="")String oldPassword,
			@RequestParam(value="newPassword",defaultValue="")String newPassword){
		oldPassword=oldPassword.trim();
		newPassword=newPassword.trim();
		if(oldPassword.isEmpty()){
			throw new IllegalArgumentException("缺少旧密码");
		}
		if (newPassword.isEmpty()) {
			throw new IllegalArgumentException("缺少新密码");
		}
		if (newPassword.equals(oldPassword)) {
			throw new IllegalArgumentException("新旧密码不能相同");
		}
		User user=this.userService.findByUserId(userId);
		if(user !=null && PwdHelper.encryptPassword(oldPassword).equals(user.getPassword())){
			this.userService.updatePassword(userId, newPassword.trim());
			return new Result();
		}
		return new Result("原密码输入错误");
	}
	
	@RequestMapping("/resetPassword")
	@ResponseBody
	public Result resetPassword(@RequestParam("userId")int userId){
		this.userService.resetPassword(userId);
		return new Result();
	}
}
