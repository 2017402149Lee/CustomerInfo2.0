package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;

public class ConfigModel extends Model<ConfigModel> {

	/**
	 * @author ljp
	 */
	private static final long serialVersionUID = 1L;
	public static final String tableName = "news";
	public static final ConfigModel dao = new ConfigModel();
	
	public String getId() {
		return("id");
	}
	public void setId(String id) {
		set("id",id);
	}
	public String getKey() {
		return("key");
	}
	public void setKey(String key) {
		set("key",key);
	}
	public String getValue() {
		return("value");
	}
	public void setValue(String value) {
		set("value",value);
	}
	
	public static ConfigModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<ConfigModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
}
