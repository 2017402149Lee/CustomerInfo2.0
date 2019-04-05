package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class TeamersModel extends Model<TeamersModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "teamers";
	public static final TeamersModel dao = new TeamersModel();
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
	public int getType() {
		return get("type");
	}
	public void setType(int type) {
		set("type",type);
	}
	public String getTeam_id() {
		return ("team_id");
	}
	public void satTeam_id(String team_id) {
		set("team_id",team_id);
	}

	
	public static TeamersModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<TeamersModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
}
