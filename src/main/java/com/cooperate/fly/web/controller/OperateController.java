package com.cooperate.fly.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooperate.fly.bo.DataInfo;
import com.cooperate.fly.bo.DataValue;
import com.cooperate.fly.bo.Message;
import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.bo.PackageVersion;
import com.cooperate.fly.bo.User;
import com.cooperate.fly.mapper.MessageMapper;
import com.cooperate.fly.mapper.ModelInfoMapper;
import com.cooperate.fly.mapper.PackageVersionMapper;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.service.model.ModelDesign;
import com.cooperate.fly.service.operator.DataOperate;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.web.util.EasyUITreeNode;
import com.cooperate.fly.web.util.Result;
import com.google.gson.Gson;

@Controller
public class OperateController {
	@Resource
	private ModelDesign modelDesign;
	@Resource
	private DataOperate dataOperate;
	@Resource
	private PackageVersionMapper pvmapper;
	@Resource
	private UserMapper usermapper;
	@Resource
	private MessageMapper messagemapper;
	@Resource
	private ModelInfoMapper modelinfomapper;
	
	private static Logger log=Logger.getLogger(AccoutController.class);
	
	@RequestMapping(value="/hehe",method=RequestMethod.POST)
	@ResponseBody
	public Result hehe(@RequestParam(value="package_id",required=true) String id, Model model,HttpServletResponse response){
		int package_id = Integer.parseInt(id);
		List<PackageVersion> list = dataOperate.getPackageVersions(package_id);
		String s = "";
		for(int i=0;i<list.size();i++){
			Date time = list.get(i).getSubmitTime();
			// time to yyyy-MM-dd hh-mm-ss
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			s += "#" + format.format(time);
		}
		PackageInfo the_package = dataOperate.getPackageById(package_id);
		Result result = new Result();
		result.setList1(list);
		result.setMessage(the_package.getName()+s);
		return result;
	}
	
	@RequestMapping(value="/haha",method=RequestMethod.POST)
	@ResponseBody
	public Result haha(HttpServletRequest request){
		int version_id = Integer.parseInt(request.getParameter("version_id"));
		int package_id = Integer.parseInt(request.getParameter("package_id"));
		List<DataInfo> data_info_list = dataOperate.getDataOfPackage(package_id);
		List<DataValue> data_value_list = dataOperate.getDataValueOfVersion(version_id);
		Result result = new Result();
		result.setList1(data_info_list);
		result.setList2(data_value_list);
		return result;
	}
	
	@RequestMapping(value="checkMessage")
	@ResponseBody
	public Result getMessage(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute(Constant.USER_SESSION_KEY);
		user = usermapper.selectByUserName(user.getUserName());
		int user_id = user.getId();
		List<Message> message_unread_list = messagemapper.getMessageUnread(user_id);
		
		Result result = new Result();
		if(message_unread_list.size()!=0){
			result.setSuccessful(true);
			result.setMessage("您有未读消息");
		}else{
			result.setSuccessful(false);
		}
		return result;
	}
	
	@RequestMapping(value="/getDependency",method=RequestMethod.POST)
	@ResponseBody
	public Result getDependency(@RequestParam(value="package_id",required=true) String id){
		int package_id = Integer.parseInt(id);
		List<PackageInfo> up_package_list = dataOperate.getUpPackages(package_id);
		List<PackageVersion> up_version_list = new ArrayList<PackageVersion>();
		for(int i=0;i<up_package_list.size();i++){
			up_version_list.addAll(dataOperate.getPackageVersions(up_package_list.get(i).getId()));
		}
		Result result = new Result();
		result.setList1(up_version_list);
		result.setList2(up_package_list);
		return result;
	}
	
	@RequestMapping(value="/submit_caogao",method=RequestMethod.POST)
	@ResponseBody
	public Result submit_caogao(@RequestParam(value="package_id",required=true) String id,@RequestParam(value="parent_id",required=true) String parent_id){
		int package_id = Integer.parseInt(id);
		String package_name = dataOperate.getPackageById(package_id).getName();
		int ok = dataOperate.createVersion(package_id, parent_id);
		//get caogao id
		int caogao_id = dataOperate.getCaogao(package_id).getId();
		dataOperate.initCaogaoValue(caogao_id,package_id);
		Result result = new Result();
		result.setMessage(package_name+","+caogao_id);
		return result;
	}
	
	@RequestMapping(value="/edit_caogao",method=RequestMethod.POST)
	@ResponseBody
	public Result edit_caogao(@RequestParam(value="package_id",required=true) String id,@RequestParam(value="version_id",required=true) String version_id){
		int package_id = Integer.parseInt(id);
		int versionId = Integer.parseInt(version_id);
		List<DataInfo> data_info_list = dataOperate.getDataOfPackage(package_id);
		List<DataValue> data_value_list = dataOperate.getDataValueOfVersion(versionId);
		Result result = new Result();
		result.setList1(data_info_list);
		result.setList2(data_value_list);
		return result;
	}
	
	@RequestMapping(value="/update_caogao",method=RequestMethod.POST)
	@ResponseBody
	public Result update_caogao(@RequestParam(value="input",required=true) String input,
			@RequestParam(value="data_info",required=true) String data_info,@RequestParam(value="package_id",required=true) String id){
		int package_id = Integer.parseInt(id); 
		int caogao_id = dataOperate.getCaogao(package_id).getId();
		dataOperate.updateCaogaoValue(input, data_info, caogao_id);
		Result result = new Result();
		return result;
	}
	
	@RequestMapping(value="/commit_caogao",method=RequestMethod.POST)
	@ResponseBody
	public Result commit_caogao(@RequestParam(value="package_id",required=true) String id){
		int package_id = Integer.parseInt(id); 
		dataOperate.commitCaogao(package_id);
		List<PackageVersion> list = dataOperate.getPackageVersions(package_id);
		String s = "";
		for(int i=0;i<list.size();i++){
			Date time = list.get(i).getSubmitTime();
			// time to yyyy-MM-dd hh-mm-ss
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			s += "#" + format.format(time);
		}
		PackageInfo the_package = dataOperate.getPackageById(package_id);
		String sid = the_package.getSid();
		if(sid!=null){
			String[] sid_arr = sid.split(",");
			for(int k=1;k<sid_arr.length;k++){
				int user_id = dataOperate.getPackageById(Integer.parseInt(sid_arr[k])).getDirectorId();
				Message message = new Message();
				message.setIs_read(0);
				message.setUser_id(user_id);
				message.setMessage(the_package.getName()+"更新！");
				messagemapper.insertMessageByUserId(message);
			}
		}
		Result result = new Result();
		result.setList1(list);
		result.setMessage(the_package.getName()+s);
		return result;
	}
	
	@RequestMapping(value="/setMessageRead",method=RequestMethod.POST)
	@ResponseBody
	public Result setMessageRead(@RequestParam(value="id",required=true) String id){
		int msg_id = Integer.parseInt(id); 
		messagemapper.setMessageRead(msg_id);
		return new Result();
	}
	
}
