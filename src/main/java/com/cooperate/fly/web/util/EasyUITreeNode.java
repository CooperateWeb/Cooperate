package com.cooperate.fly.web.util;

import java.util.ArrayList;
import java.util.List;

public class EasyUITreeNode {

	private int id;
	private String text;
	private String iconCls;
	private int parentId;
	private List<EasyUITreeNode> children = new ArrayList<EasyUITreeNode>();
	private boolean checked;
	private boolean isLeaf;

	public void addChild(EasyUITreeNode node) {
		if (!this.children.contains(node)) {
			this.children.add(node);
		}
	}

	public void nodeIsLeaf() {
		if(this.children.isEmpty()){
			this.isLeaf=true;
		}else{
			this.isLeaf=false;
		}
	}

	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<EasyUITreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUITreeNode> children) {
		this.children = children;
	}
}
