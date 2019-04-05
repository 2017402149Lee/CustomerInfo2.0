package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class TeamModel extends Model<TeamModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "team";
	public static final TeamModel dao = new TeamModel();
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
	public String getUser_id() {
		return get("user_id");
	}
	public void setUser_id(String user_id) {
		set("user_id", user_id);
	}
	public String getCreate_time() {
		return get("create_time");
	}
	public void setCeate_time(String create_time) {
        set("create_time",create_time);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark) {
		set("remark",remark);
	}
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status",status);
	}

	
	public static TeamModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<TeamModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
}
