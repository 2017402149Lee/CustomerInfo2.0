package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;

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
	public static Page<RoleModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName);
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	public static boolean save(String name,String permission) {
		RoleModel m = new RoleModel();
		m.setId(StringUtil.getId());
		m.setName(name);
		m.setPermission(permission);
		return m.save();
	}
	public static RoleModel getModelById(String id) {
		String sql="select * from "+tableName+" where id=?";
		return dao.findFirst(sql,id);
	}
	public static boolean updatePermission(String id,String permission) {
		RoleModel m = getModelById(id);
		boolean result=false;
		if(m!=null) {
			m.setPermission(permission);
			result=m.update();
		}
		return result;
	}
}
