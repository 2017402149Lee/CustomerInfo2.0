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
		String sele_sql = "select a.* ,b.name as rolename ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append(" from ").append(tableName).append(" a INNER JOIN ").append(RoleModel.tableName).append(" b on a.role_id=b.id");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  a.name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	public static List<UserModel>getXls(String role_id){
		List<UserModel> list;
		if(role_id.equals("001")) {
			list = dao.find("select * from "+tableName+" where role_id='001'");
		}else {
			list = dao.find("select * from "+tableName+" where role_id='2'");
		}
		return list;
	}
	
	/**
	 * 
	 * ע���û� �����û���Ϣ
	 * @author ��־ǿ 
	 * @param username 
	 * @param password 
	 * @param sex
	 * @param phone
	 * @param level
	 * @param status
	 * @return
	 */
	public static boolean saveUserinfo(String username,String password, String phone, int sex) {
		UserModel m=new UserModel();
		m.setUsername(username);
		m.setPassword(password);
		m.setSex(sex);
		m.setPhone(phone);
		m.setLevel(0);
		m.setStatus(0);
		m.setRole_id("1555138505019");
		m.setId(StringUtil.getId());
		return m.save();
	}
	

	/**
	 * ��ѯ����
	 * @param phone_no
	 * @return
	 */
	public UserModel getphone(String phone) {
		String selectsql ="SELECT * FROM " + tableName +  " WHERE phone=?";
		return dao.findFirst(selectsql,phone);
		
	}
	/**
	 * @author ljp
	 * @param phone
	 * @return
	 */
	public static UserModel loginByPhone(String phone) {
		String sql = "select a.*,b.name as rolename,b.permission from "+tableName+" a LEFT JOIN "+RoleModel.tableName+" b ON a.role_id=b.id where a.phone = ?";
		return dao.findFirst(sql,phone);
	}
	/**
	 * @author ljp
	 */
	
//	public static List<?> queryTeamCustomerListByUT(String user_id,String Team_id,int status){
//		Stirng sql = "select * FROM customer where user_id in (SELECT user_id from teamers where team_id='1554896316282') and `status`=1;"
//	}
	/**
	 * ���ݺ�����ҿͻ�������Ϣ
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
	 * ��ѯ����
	 * @param phone_no
	 * @return
	 */
	public static UserModel findByPhone(String phone) {
		String selectsql = "SELECT * FROM " + tableName + " WHERE phone=?";
		return dao.findFirst(selectsql,phone);
		
	}
	/**
	 * 审核用户
	 * @param id
	 * @return
	 */
	public static boolean checkUser(String id) {
		UserModel m=dao.findById(id);
		m.setStatus(1);//1:正常，0未审核,-1异常
		return m.update();
	}
	
	public static boolean upateUserRole(String id,String role_id) {
		UserModel m=dao.findById(id);
		m.setRole_id(role_id);
		return m.update();
	}
	/**
	 * 用户登录
	 * 把用户的基本信息，包括权限一起拿
	 * @param phone
	 * @return
	 */
	public static UserModel findByLogin(String phone) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(tableName).append(" a INNER JOIN ");
		sql.append(RoleModel.tableName).append(" b on a.role_id=b.id ");
		sql.append(" where a.phone=?");
		return dao.findFirst(sql.toString(),phone);
		
	}
	/**
	 *  功能：修改密码
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public static boolean updatePassword(String id,String password){
		UserModel m=getById(id);
		m.setPassword(password);
		return m.update();
	}
	public static boolean updateLevel(String id){
		UserModel m=getById(id);
		m.setLevel(1);
		return m.update();
	}
	public static boolean updateLevel(String id,int level){
		UserModel m=getById(id);
		m.setLevel(level);
		return m.update();
	}
	
}
