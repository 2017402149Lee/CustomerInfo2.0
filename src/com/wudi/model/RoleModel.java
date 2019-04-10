package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * ��ɫModel
 * @author 
 *
 */
public class RoleModel extends Model<RoleModel>{
	public static final long serialVersionUID = 1L;
	public static final String tableName = "role";
	public static final RoleModel dao = new RoleModel();
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	
	public String getName() {
		return get("name");
	}
	public void setName(String name) {
		set("name", name);
	}
	
	public String getPermission() {
		return get("permission");
	}
	public void setPermission(String permission) {
		set("permission", permission);
	}
	
}
