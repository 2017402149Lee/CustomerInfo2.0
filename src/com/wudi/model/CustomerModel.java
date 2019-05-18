package com.wudi.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;


/**
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

	public String getAge() {
		return get("age");
	}
	public void setAge(String age) {
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

	public String getNation() {
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
	
	public Date getUpdate_time() {
		return get("update_time");
	}
	public void setUpdate_time(Date update_time) {
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
		String sele_sql = "select a.*,b.username,c.type as captype ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a left join ").append(UserModel.tableName).append(" b on a.user_id=b.id").append(" left join " ).append(TeamersModel.tableName).append(" c on c.user_id=b.id ");
		from_sql.append(" where a.type='").append(type).append("' and a.status in (1,2,6)");
		//StringBuffer UserSql = new StringBuffer(); 
		//UserSql.append("select type from ").append(TeamersModel.tableName).append(" where user_id = '").append(user_id).append(" '");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" and a.name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}

	
	public static List<CustomerModel> getCustomerByType(String type){
		String sql="select a.*,b.username from "+tableName+" a LEFT JOIN "+UserModel.tableName+" b on a.user_id=b.id WHERE type = ? and a.status between 1 and 2 ";
		return dao.find(sql,type);
	}
	public static CustomerModel findModel(String type,String tel) {
		return dao.findFirst("select * from "+tableName+" where type = ?and tel = ?",type,tel);
	}
	public static CustomerModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	
	public static CustomerModel findBytel(String tel) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", tel);
	}
	public static CustomerModel findCustomerById(String id) {
		String sql = "SELECT a.*,b.username,b.phone from "+tableName+" a LEFT JOIN "+UserModel.tableName+" b ON a.user_id=b.id where a.id=? and a.status between 1 and 2 ";
		return dao.findFirst(sql,id);
	}
	public static List<CustomerModel> getCJL(String user_id){
		String sql = "select * from "+ tableName +" where status = 6 and user_id = ? ";
		return dao.find(sql,user_id);
	}
	
	public static List<CustomerModel> queryCustomerList(String user_id,String type,int status){
			StringBuffer from_sql = new StringBuffer();
			from_sql.append(" select a.*,b.username ,b.phone  ");
			from_sql.append("from ").append(tableName).append(" a LEFT JOIN ").append(UserModel.tableName).append(" b on a.user_id=b.id ");
			from_sql.append(" where a.user_id= '").append(user_id).append(" ' ");
			if (!StringUtil.isBlankOrEmpty(type)) {
				from_sql.append(" and a.type= '").append(type).append("' ");
			}
			if(status!=-1) {
				if(status==-2) {
					from_sql.append(" and a.status<>6");
				}else {
					from_sql.append(" and a.status=").append(status);
				}
			}
		 return dao.find(from_sql.toString());
	}
/**
 * 查找队员客户
 * @author ljp
 * @param user_id
 * @param team_id
 * @return
 */
	public static List<CustomerModel> queryTeamCustomerList(String team_id){
		String sql = "select a.*,b.username from "+tableName+" a LEFT JOIN "+UserModel.tableName+" b on a.user_id=b.id where user_id in (SELECT user_id from "+TeamersModel.tableName+" where team_id=?)";
		return dao.find(sql,team_id);
	}
	
	/**
	 * 
	 */
	public static boolean save(String name, int sex, String tel, int disclose, String age, String nation,
			String addr, String remark, String user_id, String type, String otherinfo,int status) {
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
		model.setCreate_time(new Date());
		model.setStatus(status);
		model.setType(type);
		model.setOtherInfo(otherinfo);
		return model.save();
	}
	
	public static boolean update(String id,String name, int sex, String tel, int disclose, String age, String nation,
			String addr, String remark, String type, String otherinfo,int status) {
		boolean result =false;
		CustomerModel m=CustomerModel.getById(id);
		if(m == null) {
			return result;
		}else {
			m.setName(name);
			m.setSex(sex);
			m.setTel(tel);
			m.setDisclose(disclose);
			m.setAge(age);
			m.setNation(nation);
			m.setAddr(addr);
			m.setRemark(remark);
			m.setType(type);
			m.setOtherInfo(otherinfo);
//	    	if(!StringUtil.isBlankOrEmpty(remark)) {
//    		if (status == 1 &&!m.getRemark().equals(remark)) {// 只有未处理状态并且备注不等于第一次添加的时候才可以修改
//				m.setStatus(2);
////			}
//    	}
			m.setStatus(status);
    	m.setUpdate_time(new Date());
		}
		try {
			result = m.update();
		}catch(Exception e){
			result = false;
		}
		return result;
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
//	public static boolean saveOrUpate(String id,String name, int sex, String tel, int disclose, int age, String nation,
//			String addr, String remark, String user_id, int status,  String type, String otherinfo) {
//	    boolean result = false;
//	    CustomerModel d= CustomerModel.getById(id);
//	    CustomerModel model = CustomerModel.findModel(type, tel);
//	    if(StringUtil.isBlankOrEmpty(id)) {
//	    	if(model ==null) {
//	    	result= save(name,sex,tel,disclose,age,nation,addr,remark,user_id,status,type,otherinfo);
//	    	}
//	    }else {
//	    	if(model != null&&model.getId().equals(id)) {
//	    		model.setName(name);
//	    		model.setSex(sex);
//	    		model.setTel(tel);
//	    		model.setDisclose(disclose);
//	    		model.setAge(age);
//	    		model.setNation(nation);
//	    		model.setAddr(addr);
//	    		model.setRemark(remark);
//	    		model.setUser_id(user_id);
//	    		model.setType(type);
//	    		model.setOtherInfo(otherinfo);
//	    		
//	    	if(!StringUtil.isBlankOrEmpty(remark)) {
//	    		if (status == 1&&!d.getRemark().equals(remark)) {// 只有未处理状态并且备注不等于第一次添加的时候才可以修改
//					status = 2;
//				}
//	    	}
//	    	model.setUpdate_time(new Date());
//	    	model.setStatus(status);
//	    	}
//	    	result = update(id,model);
//	    }
//		return result;
//	}
//	
	public static boolean delById(String id) {
		try {
			String delsql = "DELETE FROM " + tableName + " WHERE id=?";
			int iRet = Db.update(delsql, id);
			if (iRet > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 找出本年的数据
	 * 
	 * @return
	 */
	public static List<CustomerModel> getListByCYeanMonth(int month) {
		StringBuffer sql = new StringBuffer();
		Calendar now = Calendar.getInstance();
		int yean = now.get(Calendar.YEAR);
		// 构造类似2019-01的格式（SELECT * from customer where create_time like '%2019-01%'）
		StringBuffer m = new StringBuffer();
		m.append(yean).append("-");
		if (month < 10) {
			m.append("0").append(month);
		} else {
			m.append(month);
		}
		sql.append("select *  from ").append(tableName);
		sql.append(" where create_time like '%" + m + "%'");

		return dao.find(sql.toString());
	}
	/**
	 * 个人中心的三种Status
	 * @return
	 */
	public static List<CustomerModel> getCustList(String user_id,String type){
		String sql = "select * from "+tableName+" where user_id = ? nad type = ? and status between 1 and 2 ";
		return dao.find(sql,user_id,type);
	}
	
	public static boolean updateRemark(String id,String remark){
		CustomerModel m=getById(id);
		m.setRemark(remark);
		m.setStatus(2);
		return m.update();
	}
	
	public static boolean updateStatus(String id){
		CustomerModel m=getById(id);
		m.setStatus(7);
		return m.update();
	}
	
	public static boolean updateCancel(String id){  	//取消成交
		CustomerModel m=getById(id);
		m.setStatus(2);
		return m.update();
	}
	
}
