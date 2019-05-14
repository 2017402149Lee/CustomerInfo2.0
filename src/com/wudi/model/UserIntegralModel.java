package com.wudi.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
import com.wudi.util.Util;

/**
 * ����Model
 * @author 
 *
 */
public class UserIntegralModel extends Model<UserIntegralModel>{
	private static final long serialVersionUID = 1L;
	public static final String tableName = "userintegral";
	public static final UserIntegralModel dao = new UserIntegralModel();
	
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
	
	public int getTotal() {
		return get("total");
	}
	public void setTotal(int total) {
		set("total", total);
	}
	
	public Date getUpdata_time() {
		return get("updata_time");
	}
	public void setUpdata_time(Date date) {
		set("updata_time", date);
	}
public static boolean InitIntegra(String user_id) {
		
		UserIntegralModel m = new UserIntegralModel();
		m.setId(StringUtil.getId());
		m.setTotal(0);
		m.setUpdata_time(new Date());
		m.setUser_id(user_id);
		return m.save();
		
	}

	
	public static Page<UserIntegralModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	public static boolean saveIntegraForCap(String user_id) {//队长加积分
			
					UserIntegralModel m = UserIntegralModel.findById(user_id);
				    int integra = m.getTotal();
					m.setTotal(integra+2);
					m.setUpdata_time(new Date());
					return m.update();
		}
	public static boolean saveIntegraForSelf(String user_id) {//自己加积分
		
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra+10);
		m.setUpdata_time(new Date());
		return m.update();
}
	public static boolean updateintegraForCap(String user_id) {//成交时给队长加积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
		int integra = m.getTotal();
		m.setTotal(integra+10);
		return m.update();
	}

	public static boolean updateintegra(String user_id) {//成交时自己加积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
		int integra = m.getTotal();
		m.setTotal(integra+30);
		return m.update();
	}
	public static boolean cleanintegra(String user_id) {
		UserIntegralModel m = UserIntegralModel.findById(user_id);
		m.setTotal(0);
		return m.update();
	}
	
	public static UserIntegralModel findById(String user_id) {
		return dao.findFirst("select * from " + tableName + " where user_id = ? ", user_id);
	}
	public static UserIntegralModel getIntegraById(String user_id) {
		return dao.findFirst("select total from " + tableName + " where user_id = ? ", user_id);
	}
	public static UserIntegralModel getId(String id) {
		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
}
