package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class UserintegralDetailModel extends Model<UserintegralDetailModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "userintegralDetail";
	public static final UserintegralDetailModel dao = new UserintegralDetailModel();
	public String getId() {
		return get("id");
	}

	public void setId(String id) {
		set("id", id);
	}
	public String getIntergral_id() {
		return get("Intergral_id");
	}
	public void setIntergral_id(String intergral_id) {
		set("intergral_id", intergral_id);
	}
	public int getNumber() {
		return get("number");
	}
	public void setSex(int number) {
		set("number", number);
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

	
	public static UserintegralDetailModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<UserintegralDetailModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
}
