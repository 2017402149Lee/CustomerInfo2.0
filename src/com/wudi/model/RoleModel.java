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
	public static RoleModel getPermissionBy(String id) {
		String sql="select * from "+tableName+" where id= ?";
		return dao.findFirst(sql,id);
	}
	
	public static boolean save(String role_id) {
		RoleModel m = new RoleModel();
		m.setId(role_id);
		m.setName("普通用戶");
//		if(role_id.equals("1")) {
//			name = "普通用户";
//		}else if(role_id.equals("2")) {
//			name = "客服人员";
//		}else {
//			name = "Boss";
//		}
		m.setPermission("1");		
		return m.save();
	}
}
