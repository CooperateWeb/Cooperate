package com.cooperate.fly.bo;

import java.util.ArrayList;
import java.util.List;

public class PackageDesign {

	private int id;
	private String name;
	private int parentId;
	private List<PackageDesign> children=new ArrayList<PackageDesign>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<PackageDesign> getChildren() {
		return children;
	}
	public void setChildren(List<PackageDesign> children) {
		this.children = children;
	}
	
	public void addChild(PackageDesign pack) {
		if (! this.children.contains(pack)) {
			this.children.add(pack);
		}
	}
}
