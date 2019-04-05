package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class UserModel extends Model<UserModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "user";
	public static final UserModel dao = new UserModel();
	
	public String getId() {
		return get("id");
	}

	public void setId(String id) {
		set("id", id);
	}
	public String getUsername() {
		return get("username");
	}
	public void setUsername(String username) {
		set("name", username);
	}
	public int getSex() {
		return get("sex");
	}
	public void setSex(int sex) {
		set("sex", sex);
	}
	public String getPassword() {
		return get("password");
	}
	public void setPassword(String password) {
        set("password",password);
	}
	public String getPhone() {
		return get("phone");
	}
	public void setPhone(String phone) {
		set("phone",phone);
	}
	public int getLevel() {
		return get("level");
	}
	public void setLevel(int level) {
		set("level",level);
	}
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status",status);
	}
	public String getRole_id() {
		return get("role_id");
	}
	public void set(int role_id) {
		set("role_id",role_id);
	}
	
	
	
	
	public static UserModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<UserModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
}
