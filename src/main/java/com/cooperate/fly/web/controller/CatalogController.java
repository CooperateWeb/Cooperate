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

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.bo.User;
import com.cooperate.fly.bo.UserRole;
import com.cooperate.fly.service.model.ModelDesign;
import com.cooperate.fly.service.user.RoleMenuService;
import com.cooperate.fly.service.user.SysMenuService;
import com.cooperate.fly.service.user.UserRoleService;
import com.cooperate.fly.util.Constant;
import com.cooperate.fly.web.util.EasyUITreeNode;
import com.cooperate.fly.web.util.Result;
import com.cooperate.fly.web.util.WebFrontHelper;

@Controller
@RequestMapping("/catalog")
@SessionAttributes(Constant.USER_SESSION_KEY)
public class CatalogController extends BaseController {
	
	@Resource
	private ModelDesign modelDesign;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Catalog> listAll() {
		List<Catalog> catalogs = this.modelDesign.getCatalogNodes();
		return catalogs;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Result add(String nodeName,int parentId) {
		this.modelDesign.createNoneLeafCatalogNode(nodeName, parentId);
		return new Result();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Result update(Catalog catalog) {

		this.modelDesign.updateCatalogNode(catalog);
		return new Result();
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Result delete(@RequestParam(value="catalogId", required=true) int catalogId) {
		this.modelDesign.deleteCatalogNode(catalogId);
		return new Result();
	}
}
