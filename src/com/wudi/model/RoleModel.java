package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;

public class RoleModel extends Model<RoleModel>{
	private static final long serialVersionUID = 1L;
	private static final String tableName = "role";
	public static final RoleModel dao = new RoleModel();
	
	public String getId() {
		return("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	
	public String getName() {
		return("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	
	public String getPermission() {
		return("permission");
	}
	public void setPermission(String permission) {
		set("permission", permission);
	}
	
}
