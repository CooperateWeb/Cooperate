package com.cooperate.fly.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysMenu {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer parentMenuId;

    private Date updateTime;
    
    private List<SysMenu> children = new ArrayList<SysMenu>();
    
    public void addChild(SysMenu menu) {
		if (! this.children.contains(menu)) {
			this.children.add(menu);
		}
	}
    
    public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}