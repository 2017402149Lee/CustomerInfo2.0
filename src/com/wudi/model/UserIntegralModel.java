package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * »ý·ÖModel
 * @author 
 *
 */
public class UserIntegralModel extends Model<UserIntegralModel>{
	private static final long serialVersionUID = 1L;
	private static final String tableName = "userintegral";
	public static final UserIntegralModel dao = new UserIntegralModel();
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	
	public String getUser_id() {
		return get("user_id");
	}
	public void setUser_id(String user_id) {
		set("user_id", user_id);
	}
	
	public String getTotal() {
		return get("total");
	}
	public void setTotal(String total) {
		set("total", total);
	}
	
	public String getUpdata_time() {
		return get("updata_time");
	}
	public void setUpdata_time(String updata_time) {
		set("updata_time", updata_time);
	}
}
