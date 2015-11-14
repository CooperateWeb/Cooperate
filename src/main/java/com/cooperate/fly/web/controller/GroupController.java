package com.cooperate.fly.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cooperate.fly.bo.User;
import com.cooperate.fly.bo.UserGroup;
import com.cooperate.fly.mapper.UserGroupMapper;
import com.cooperate.fly.service.user.UserGroupService;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.web.util.Result;

@Controller
@RequestMapping("/group")
@SessionAttributes(Constant.USER_SESSION_KEY)
public class GroupController extends BaseController{
	
	@Resource
	private UserGroupService userGroupService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<UserGroup> list(){
		return this.userGroupService.findAll();
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(UserGroup group)throws Exception{
		this.userGroupService.save(group);
		return new Result();
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(UserGroup group){
		this.userGroupService.updata(group);
		return new Result();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(@RequestParam(value="groupId",required=true)int groupId){
		this.userGroupService.delete(groupId);
		return new Result();
	}
	
	//自己可操作的机构，默认返回自己所在的机构，否则返回所有机构
	@RequestMapping("/ownGroups")
	@ResponseBody
	public List<UserGroup> ownGroups(@ModelAttribute(Constant.USER_SESSION_KEY)User user){
		List<UserGroup> temp=new ArrayList<UserGroup>(1);
		if(user==null){
			return temp;
		}
		
		if(user.getGroupId()>0){
			temp.add(this.userGroupService.findByGroupId(user.getGroupId()));
			return temp;
		}
		return this.userGroupService.findAll();
	}
}
