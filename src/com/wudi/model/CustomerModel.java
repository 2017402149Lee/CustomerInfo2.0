package com.wudi.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;


/**
 * 客户（学生）信息Model
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
	public void setCreate_time(Date create_time) {
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
	public void setUpdate_time(String update_time) {
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
	
}
