package com.cooperate.fly.web.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.cooperate.fly.bo.Catalog;
import com.cooperate.fly.bo.PackageDesign;
import com.cooperate.fly.bo.SysMenu;

public class WebFrontHelper {
	
	/**
	 * 创建目录结构树
	 * @param catalogs
	 * @param idsForChecked
	 * @return
	 */
	public static CatalogNode buildTreeForEasyuiTree(List<Catalog> catalogs, List<Integer> idsForChecked) {
		
		Map<Integer, CatalogNode> map = new LinkedHashMap<Integer, CatalogNode>();

		CatalogNode root = new CatalogNode();
		root.setId(0);
		root.setText("智能主模型型号");
		map.put(root.getId(), root);
		root.getChildren();
		
		for (Catalog catalog : catalogs) {
			CatalogNode node = new CatalogNode();
			node.setId(catalog.getId());
			node.setText(catalog.getName());
			node.setParentId(catalog.getParentId() < 0 ? 0 : catalog.getParentId());
			node.setAttributes(catalog);
			map.put(node.getId(), node);
		}
		
		for (Map.Entry<Integer, CatalogNode> entry : map.entrySet()) {
			CatalogNode node = entry.getValue();
			if(node.getId() != 0){
				map.get(node.getParentId()).addChild(node);
			}
		}
		
		for (Map.Entry<Integer, CatalogNode> entry : map.entrySet()) {
			CatalogNode node = entry.getValue();
			node.nodeIsLeaf();
		}
				
		if (idsForChecked != null && idsForChecked.size() > 0) {
			for (Map.Entry<Integer, CatalogNode> entry : map.entrySet()) {
				EasyUITreeNode node = entry.getValue();
			
				if (idsForChecked.contains(node.getId())) {
					node.setChecked(true);
				}
			}
		}
		
	
		
		
		return root;
	}
	
	public static CatalogNode buildTreeForEasyuiTree(List<Catalog> catalogs) {
		return buildTreeForEasyuiTree(catalogs, null);
	}
	
	
public static PackageDesignNode buildTreeForEasyuiTree(List<PackageDesign> packs, List<String> idsForChecked) {
		
		Map<Integer, PackageDesignNode> map = new LinkedHashMap<Integer, PackageDesignNode>();
		PackageDesignNode root = new PackageDesignNode();
		root.setId(0);
		root.setText("Root");
		map.put(0, root);
		
		for (PackageDesign pack : packs) {
			PackageDesignNode node = new PackageDesignNode();
			node.setId(pack.getId());
			node.setText(pack.getName());
			node.setParentId(pack.getParentId());
			node.setAttributes(pack);
			map.put(node.getId(), node);
		}
		
		for (Map.Entry<Integer, PackageDesignNode> entry : map.entrySet()) {
			PackageDesignNode node = entry.getValue();
			
			if (node.getId()==0) {
				continue;
			}
			
			Integer parentId = node.getParentId();
			
			if (parentId <0) {
				parentId = 0;
			}
			
			map.get(parentId).addChild(node);
		}
		
		
				
		if (idsForChecked != null && idsForChecked.size() > 0) {
			for (Map.Entry<Integer, PackageDesignNode> entry : map.entrySet()) {
				PackageDesignNode node = entry.getValue();
			
				if (idsForChecked.contains(node.getId())) {
					node.setChecked(true);
				}
			}
		}

		return root;
	}
	
	public static PackageDesignNode buildTreeForEasyuiTree(List<PackageDesign> packageDesigns) {
		return buildTreeForEasyuiTree(packageDesigns, null);
	}
	
	
public static SysMenuNode buildTreeForEasyuiTree(List<SysMenu> menus, List<Integer> menuIdsForChecked) {
		
		Map<Integer, SysMenuNode> map = new LinkedHashMap<Integer, SysMenuNode>();
		SysMenuNode root = new SysMenuNode();
		root.setId(0);
		root.setText("Root");
		map.put(0, root);
		
		for (SysMenu menu : menus) {
			SysMenuNode node = new SysMenuNode();
			node.setId(menu.getMenuId());
			node.setText(menu.getMenuName());
			node.setParentId(menu.getParentMenuId());
			node.setAttributes(menu);
			
			map.put(node.getId(), node);
		}
		
		for (Map.Entry<Integer, SysMenuNode> entry : map.entrySet()) {
			SysMenuNode node = entry.getValue();
			
			if (node.getId()==0) {
				continue;
			}
			
			Integer parentId = node.getParentId();
			
			if (parentId <0) {
				parentId = 0;
			}
			
			map.get(parentId).addChild(node);
		}
		
				
		if (menuIdsForChecked != null && menuIdsForChecked.size() > 0) {
			for (Map.Entry<Integer, SysMenuNode> entry : map.entrySet()) {
				SysMenuNode node = entry.getValue();
			
				if (menuIdsForChecked.contains(node.getId())) {
					node.setChecked(true);
				}
			}
		}

		return root;
	}
	
	public static SysMenuNode buildTreeForEasyuiTree(List<SysMenu> menus) {
		return buildTreeForEasyuiTree(menus, null);
	}
	
public static SysMenu buildMenuTree(List<SysMenu> menus) {
		
		Map<Integer, SysMenu> menuMap = new LinkedHashMap<Integer, SysMenu>();
		SysMenu rootMenu = new SysMenu();
		rootMenu.setMenuId(0);
		rootMenu.setMenuName("Root");
		menuMap.put(0, rootMenu);
		
		for (SysMenu menu : menus) {
			menuMap.put(menu.getMenuId(), menu);
		}
		
		for (SysMenu menu : menus) {
			Integer parentMenuId = menu.getParentMenuId();
			if (parentMenuId <0) {
				parentMenuId = 0;
			} 
			
			menuMap.get(parentMenuId).addChild(menu);
		}

		return rootMenu;
	}
}
