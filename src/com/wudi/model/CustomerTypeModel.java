package com.wudi.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class CustomerTypeModel extends Model<CustomerTypeModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "customerType";
	public static final CustomerTypeModel dao = new CustomerTypeModel();
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
		set("name",name);
	}
	public String getType_no() {
		return get("type_no");
	}
	public void setType_no(String type_no) {
		set("type_no",type_no);
	}

	
	public static CustomerTypeModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<CustomerTypeModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	public static boolean save(String name,String type_no) {
		CustomerTypeModel m=new CustomerTypeModel();
		m.setId(StringUtil.getId());
		m.setName(name);
		m.setType_no(type_no);
		return m.save();
	}
	public static boolean update(String id,String name,String type_no) {
		CustomerTypeModel m=getById(id);
		m.setName(name);
		m.setType_no(type_no);
		return m.update();
	}
}
