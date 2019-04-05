package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 会员等级Model
 * @author 韦
 *
 */
public class LevelRecordModel extends Model<LevelRecordModel>{
	private static final long serialVersionUID = 1L;
	public static final String tableName = "levelRecord";
	public static final LevelRecordModel dao = new LevelRecordModel();
	
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	
	public String getBefore() {
		return get("before");
	}
	public void setBefore(String before) {
		set("before", before);
	}
	
	public String getAfter() {
		return get("after");
	}
	public void setAfter(String after) {
		set("after", after);
	}
	
	public String getCreate_time() {
		return get("create_time");
	}
	public void setCreate_time(String create_time) {
		set("create_time", create_time);
	}
	
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark) {
		set("remark", remark);
	}
	
	public String getUser_id() {
		return get("user_id");
	}
	public void setUser_id(String user_id) {
		set("user_id", user_id);
	}
	
}
