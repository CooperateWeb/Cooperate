package com.cooperate.fly.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.bo.Message;
import com.cooperate.fly.bo.ModelInfo;
import com.cooperate.fly.bo.PackageInfo;
import com.cooperate.fly.bo.SysMenu;
import com.cooperate.fly.bo.User;
import com.cooperate.fly.mapper.MessageMapper;
import com.cooperate.fly.mapper.ModelInfoMapper;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.service.model.ModelDesign;
import com.cooperate.fly.service.operator.DataOperate;
import com.cooperate.fly.service.user.RoleMenuService;
import com.cooperate.fly.service.user.SysMenuService;
import com.cooperate.fly.service.user.UserService;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.web.util.CatalogNode;
import com.cooperate.fly.web.util.EasyUITreeNode;
import com.cooperate.fly.web.util.MessageNode;
import com.cooperate.fly.web.util.PackageDesignNode;
import com.cooperate.fly.web.util.Result;
import com.cooperate.fly.web.util.WebFrontHelper;
import com.google.gson.Gson;

@Controller
public class AccoutController {
	
	private static Logger log=Logger.getLogger(AccoutController.class);
	@Resource
	private UserService userService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private RoleMenuService roleMenuService;
	@Resource
	private ModelDesign modelDesign;
	@Resource
	private DataOperate dataOperate;
	@Resource
	private UserMapper usermapper;
	@Resource
	private ModelInfoMapper modelinfomapper;
	@Resource
	private MessageMapper messagemapper;
	
	private static final int DAY_MSECS = 1000*60*60*24;
	
	/*
	@RequestMapping(value="/hehe",method=RequestMethod.GET)
	@ResponseBody
	public Result hehe(){
		return new Result("hhehe");
	}
	*/
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(HttpSession session,Model model){
		User user=(User) session.getAttribute(Constant.USER_SESSION_KEY);
		if(user==null){
			return "login";
		}
		
		//如果roleId为1 那么为系统管理员；
		//如果roleId为2那么为数据区管理员
		//如果roleId为3那么为工程师；
		//如果roleId为4那么为浏览的领导
		
		//数据区管理员进行模型策划
		int roleId=user.getRoleId();
		if(roleId==2){
			List<Catalog> catalogs=modelDesign.getCatalogNodes();
			CatalogNode root=WebFrontHelper.buildTreeForEasyuiTree(catalogs);
			List nodelist=new LinkedList<CatalogNode>();
			nodelist.add(root);
			
			PackageDesignNode pkRoot=WebFrontHelper.buildTreeForEasyuiTree(Constant.getPackageDesigns());
			model.addAttribute("catalogTreeJson", new Gson().toJson(nodelist));
			model.addAttribute("packageDesignTreeJson",new Gson().toJson(pkRoot.getChildren()));
			System.out.println("=========="+pkRoot.getChildren().size()+pkRoot.getChildren().get(0).getText());
			return "model-design";
		}
		else if(roleId==3){
			List<Catalog> catalogs=modelDesign.getCatalogNodes();
			CatalogNode root=WebFrontHelper.buildTreeForEasyuiTree(catalogs);
			List nodelist=new LinkedList<CatalogNode>();
			nodelist.add(root);
			model.addAttribute("catalogTreeJson", new Gson().toJson(nodelist));
			build_dataTree(user,model);
			getMessage(user,model);
			return "operate";
		}
		
		return "grant-tips";
		
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestParam(value="userName",required=true) String userName,@RequestParam(value="password",required=true)String password,HttpSession session){
		User user=this.userService.loadUserByUserNameAndPassword(userName, password);
		if(user!=null){
			log.info("登录成功：{}"+user);
			session.setAttribute(Constant.USER_SESSION_KEY, user);
			return new Result();
		}else{
			return new Result("用户名密码不匹配");
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		if(session!=null){
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@RequestMapping("checkSession")
	@ResponseBody
	public Result checkSession(HttpSession session){
		if(session.getAttribute(Constant.USER_SESSION_KEY)!=null){
			return new Result();
		}
		return new Result(false);
	}
	
	public void build_dataTree(User user,Model model){
		user = usermapper.selectByUserName(user.getUserName());
		String package_id = user.getPackageId();
		String[] package_id_arr = package_id.split(",");
		int[] model_id = new int[package_id_arr.length];
		for(int m=0;m<model_id.length;m++){
			model_id[m] = -1;
		}
		int index = 0;
		List<PackageInfo> list_package = new ArrayList<PackageInfo>();
		List<Integer> list_id = new ArrayList<Integer>();
		for(int i=1;i<package_id_arr.length;i++){
			PackageInfo apackage = dataOperate.getPackageById(Integer.parseInt(package_id_arr[i]));
			list_package.add(apackage);
			list_id.add(apackage.getId());
			boolean flag = true;
			for(int j=0;j<model_id.length;j++){
				if(model_id[j]==apackage.getModelId()){
					flag = false;
				}
			}
			if(flag==true){
				model_id[index++] = apackage.getModelId();
			}
		}
		int model_index = 0;
		List<EasyUITreeNode> nodelist = new LinkedList<EasyUITreeNode>();
		while(model_id[model_index]!=-1){
			ModelInfo amodel = modelinfomapper.selectByPrimaryKey(model_id[model_index]);
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(model_index+1);
			node.setText(amodel.getName());
			node.setParentId(0);
			for(int i=0;i<list_package.size();i++){
				if(list_package.get(i).getModelId()==model_id[model_index]){
					EasyUITreeNode son_node = new EasyUITreeNode();
					son_node.setId(list_package.get(i).getId()*10);
					son_node.setText(list_package.get(i).getName());
					son_node.setParentId(model_index+1);
					son_node.nodeIsLeaf();
					node.addChild(son_node);
				}
			}
			node.nodeIsLeaf();
			model_index ++;
			nodelist.add(node);
		}
		model.addAttribute("mydataTreeJson", new Gson().toJson(nodelist));
	}
	
	public void getMessage(User user,Model model){
		List<Message> list_message = messagemapper.getMessageOfUser(user.getId());
		Date today = new Date();
		List<MessageNode> list_msgNode = new LinkedList<MessageNode>();
		MessageNode root1 = new MessageNode();
		MessageNode root2 = new MessageNode();
		MessageNode root3 = new MessageNode();
		root1.setId(1);
		root1.setParentId(0);
		root1.setText("今天");
		root2.setId(2);
		root2.setParentId(0);
		root2.setText("昨天");
		root3.setId(3);
		root3.setParentId(0);
		root3.setText("更早");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MessageNode node = null;
		for(int i=0;i<list_message.size();i++){
			Message msg = list_message.get(i);
			if(msg.getDate().getTime()/DAY_MSECS==today.getTime()/DAY_MSECS){
				node = new MessageNode();
				node.setId(msg.getId()*10);
				node.setParentId(1);
				String text = (msg.getMessage().length()<6)?msg.getMessage()+"     "+sdf.format(msg.getDate()):
					msg.getMessage().substring(0, 6)+"..."+"    "+sdf.format(msg.getDate());
				if(msg.getIs_read()==0)
					node.setText("[未读]"+text);
				else
					node.setText("[已读]"+text);
				root1.addChild(node);
			}else if(msg.getDate().getTime()/DAY_MSECS==today.getTime()/DAY_MSECS-1){
				node = new MessageNode();
				node.setId(msg.getId()*10);
				node.setParentId(2);
				String text = (msg.getMessage().length()<6)?msg.getMessage()+"     "+sdf.format(msg.getDate()):
					msg.getMessage().substring(0, 6)+"..."+"    "+sdf.format(msg.getDate());
				if(msg.getIs_read()==0)
					node.setText("[未读]"+text);
				else
					node.setText("[已读]"+text);
				root2.addChild(node);
			}else{
				node = new MessageNode();
				node.setId(msg.getId()*10);
				node.setParentId(3);
				String text = (msg.getMessage().length()<6)?msg.getMessage()+"     "+sdf.format(msg.getDate()):
					msg.getMessage().substring(0, 6)+"..."+"    "+sdf.format(msg.getDate());
				if(msg.getIs_read()==0)
					node.setText("[未读]"+text);
				else
					node.setText("[已读]"+text);
				root3.addChild(node);
			}
			node.setAttributes(msg);
			node.nodeIsLeaf();
		}
		list_msgNode.add(root1);
		list_msgNode.add(root2);
		list_msgNode.add(root3);
		model.addAttribute("msgTreeJson", new Gson().toJson(list_msgNode));
	}
}
