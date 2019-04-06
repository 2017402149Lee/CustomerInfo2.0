package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;

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
	
	
	public static LevelRecordModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<LevelRecordModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
}
