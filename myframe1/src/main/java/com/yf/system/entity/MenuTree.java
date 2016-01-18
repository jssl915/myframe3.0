package com.yf.system.entity;

import java.util.ArrayList;
import java.util.List;

import com.cykj.grcloud.util.Ognl;
import com.yf.util.Tree;

public class MenuTree extends Tree<MenuTree> {
	public MenuTree(){}
	
	private Integer level;
	
	private String menuUrl;

	public MenuTree(SysMenu r) {
		this.setText(r.getMenuName());
		this.setId(r.getMenuId().toString());
		this.setPid(r.getMenuPid().toString());
		this.setLevel(r.getMenuLevel());
		this.setMenuUrl(r.getMenuUrl());
	}

	public void setChildren(List<SysMenu> MenuList, boolean expanded) {
		if (!Ognl.isEmpty(MenuList)) {
			List<MenuTree> list = new ArrayList<MenuTree>();
			MenuTree rt;
			for (SysMenu r : MenuList) {
				rt = new MenuTree();
				rt.setText(r.getMenuName());
				rt.setId(r.getMenuId().toString());
				rt.setPid(r.getMenuPid().toString());
				rt.setLevel(r.getMenuLevel());
				rt.setMenuUrl(r.getMenuUrl());
				list.add(rt);
			}
			this.setChildren(list);
		}
	}

	public void addChildren(MenuTree r, boolean expanded) {
		if (r != null) {
			if (this.getChildren() == null) {
				this.setChildren(new ArrayList<MenuTree>());
			}
			this.getChildren().add(r);
		}
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

}
