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
	
	public static boolean saveIntegraForCap(String user_id) {//队员给队长加积分
			
					UserIntegralModel m = UserIntegralModel.findById(user_id);
				    int integra = m.getTotal();
					m.setTotal(integra+3);
					m.setUpdata_time(new Date());
					return m.update();
		}
	
	public static boolean deleteIntegraForCap(String user_id) {//队员给队长删积分
		
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra-3);
		m.setUpdata_time(new Date());
		return m.update();
}
	public static boolean saveIntegraForSelf(String user_id) {//队长加的积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra+10);
		m.setUpdata_time(new Date());
		return m.update();
	}
	
	public static boolean deleteIntegraForSelf(String user_id) {//队长删自己积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra-10);
		m.setUpdata_time(new Date());
		return m.update();
	}
	public static boolean saveIntegraForPalyer(String user_id) {//队员加的积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra+7);
		m.setUpdata_time(new Date());
		return m.update();
	}
	
	public static boolean deleteIntegraForPalyer(String user_id) {//队员删积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra-7);
		m.setUpdata_time(new Date());
		return m.update();
	}
	public static boolean saveIntegraForSelfNoTeam(String user_id) {//自己加积分（无团队）
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra+7);
		m.setUpdata_time(new Date());
		return m.update();
	}
	
	public static boolean deleteIntegraForSelfNoTeam(String user_id) {//删积分（无团队）
		UserIntegralModel m = UserIntegralModel.findById(user_id);
	    int integra = m.getTotal();
		m.setTotal(integra-7);
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
		m.setTotal(integra+0);
		return m.update();
	}
	public static boolean addIntegra(String user_id,int total) {//给积分
		UserIntegralModel m = UserIntegralModel.findById(user_id);
		int integra = m.getTotal();
		m.setTotal(integra+total);
		return m.update();
	}
	public static boolean upUserIntegtra(String id, int total) {		//获取到用户的积分，在进行修改
		UserIntegralModel m = UserIntegralModel.findById(id);
		m.setTotal(total);
		return m.update();
	}
	
	public static UserIntegralModel findById(String user_id) {
		return dao.findFirst("select * from " + tableName + " where user_id = ? ", user_id);
	}
	public static UserIntegralModel getIntegraById(String user_id) {
		return dao.findFirst("select total from " + tableName + " where user_id = ? ", user_id);
	}
	public static UserIntegralModel getId(String user_id) {
		return dao.findFirst("select * from " + tableName + " where id = ? ", user_id);
	}
	public static UserIntegralModel getById(String id) {
		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
}
