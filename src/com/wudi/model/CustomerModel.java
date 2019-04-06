package com.wudi.model;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.mysql.fabric.xmlrpc.base.Data;
import com.wudi.util.StringUtil;


/**
 * �ͻ���ѧ������ϢModel
 * @author 
 *
 */
public class CustomerModel extends Model<CustomerModel>{
	private static final long serialVersionUID = 1L;
	public static final String tableName = "customer";
	public static final CustomerModel dao = new CustomerModel();
	
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

	public int getSex() {
		return get("sex");
	}
	public void setSex(int sex) {
		set("sex", sex);
	}

	public int getAge() {
		return get("age");
	}
	public void setAge(int age) {
		set("age", age);
	}

	
	public String getTel() {
		return get("tel");
	}
	public void setTel(String tel) {
		set("tel", tel);
	}


	public int getDisclose() {
		return get("disclose");
	}

	public void setDisclose(int disclose) {
		set("disclose", disclose);
	}

	public String getnNation() {
		return get("nation");
	}

	public void setNation(String nation) {
		set("nation", nation);
	}

	

	public String getAddr() {
		return get("addr");
	}
	public void setAddr(String addr) {
		set("addr", addr);
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

	
	public Date getCreate_time() {
		return get("create_time");
	}
	public void setCreate_time(Data create_time) {
		set("create_time", create_time);
	}

	public int getStatus() {
		return get("status");
	} 
	public void setStatus(int status) {
		set("status", status);
	}
	
	public String getUpdate_time() {
		return get("update_time");
	}
	public void setUpdate_time(Data update_time) {
		set("update_time", update_time);
	}
	
	public String getType() {
		return get("type");
	}
	public void setType(String type) {
		set("type", type);
	}
	
	public String getOtherInfo() {
		return get("otherinfo");
	}
	public void setOtherInfo(String otherinfo) {
		set("otherinfo", otherinfo);
	}
	

	/**
	 * 分页查询显示，就是查找
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param key
	 * @return
	 */
	
	public static Page<CustomerModel> getList(int pageNumber, int pageSize, String key, String type) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" where type='").append(type).append("' ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("and name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	public static List<CustomerModel> getCustomerByType(String type){
		String sql="select * from"+tableName+"where type = ?";
		return dao.find(sql,type);
	}
	public static CustomerModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<CustomerModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	
	/**
	 * ����
	 */
	public static boolean save(String name, int sex, String tel, int disclose, int age, String nation,
			String addr, String remark, String user_id, Data create_time, int status, Data update_time, String type, String otherinfo) {
		CustomerModel model = new CustomerModel();
		model.setId(StringUtil.getId());
		model.setName(name);
		model.setSex(sex);
		model.setTel(tel);
		model.setDisclose(disclose);
		model.setAge(age);
		model.setNation(nation);
		model.setAddr(addr);
		model.setRemark(remark);
		model.setUser_id(user_id);
		model.setCreate_time(create_time);
		model.setStatus(status);
		model.setUpdate_time(update_time);
		model.setType(type);
		model.setOtherInfo(otherinfo);
		return model.save();
	}

	/**
	 * ����
	 * 
	 * @param model
	 * @return
	 */
	public static boolean save(CustomerModel model) {
		return model.save();
	}
}
