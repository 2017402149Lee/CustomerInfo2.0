package com.wudi.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class UserModel extends Model<UserModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "user";
	public static final UserModel dao = new UserModel();
	
	public String getId() {
		return get("id");
	}

	public void setId(String id) {
		set("id", id);
	}
	public String getUsername() {
		return get("username");
	}
	public void setUsername(String username) {
		set("username", username);
	}
	public int getSex() {
		return get("sex");
	}
	public void setSex(int sex) {
		set("sex", sex);
	}
	public String getPassword() {
		return get("password");
	}
	public void setPassword(String password) {
        set("password",password);
	}
	public String getPhone() {
		return get("phone");
	}
	public void setPhone(String phone) {
		set("phone",phone);
	}
	public int getLevel() {
		return get("level");
	}
	public void setLevel(int level) {
		set("level",level);
	}
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status",status);
	}
	public String getRole_id() {
		return get("role_id");
	}
	public void setRole_id(String role_id) {
		set("role_id",role_id);
	}
	
	
	
	
	public static UserModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<UserModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	/**
	 * 
	 * 注册用户 保存用户信息
	 * @author 张志强 
	 * @param username 
	 * @param password 
	 * @param sex
	 * @param phone
	 * @param level
	 * @param status
	 * @return
	 */
	public  boolean saveUserinfo(String username,String password,  String phone, String role_id, int sex,int level,int status) {
		UserModel m=new UserModel();
		m.setUsername(username);
		m.setPassword(password);
		m.setSex(sex);
		m.setPhone(phone);
		m.setLevel(level);
		m.setStatus(status);//开始注册用户还没有审核传0，添加管理员传1
		m.setRole_id(role_id);
		m.setId(StringUtil.getId());
		return m.save();
	}
	

	/**
	 * 查询号码
	 * @param phone_no
	 * @return
	 */
	public UserModel getphone(String phone) {
		String selectsql ="SELECT * FROM " + tableName +  " WHERE phone=?";
		return dao.findFirst(selectsql,phone);
		
	}
	
	/**
	 * 根据号码查找客户所有信息
	 * @param phone_no
	 * @return
	 */
	public List<UserModel> getUserAllInfo(String phone) {
		UserModel m=new UserModel();
		String selectsql = "SELECT * FROM user WHERE phone=?";
		List<UserModel> list = m.find(selectsql,phone);
	
		return list;
	}
	
	/**
	 * 查询号码
	 * @param phone_no
	 * @return
	 */
	public static UserModel findByPhone(String phone) {
		String selectsql = "SELECT * FROM " + tableName + " WHERE phone=?";
		return dao.findFirst(selectsql,phone);
		
	}
	
	
	
}
