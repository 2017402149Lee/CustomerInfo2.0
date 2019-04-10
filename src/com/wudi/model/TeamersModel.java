package com.wudi.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
import com.wudi.util.Util;
/**
 * 
 * @author ljp
 *
 */

public class TeamersModel extends Model<TeamersModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "teamers";
	public static final TeamersModel dao = new TeamersModel();
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getPhone() {
		return get("phone");
	}
	public void setPhone(String phone) {
		set("phone", phone);
	}
	public Date getCreate_time() {
		return get("create_time");
	}
	public void setCeate_time(Date create_time) {
        set("create_time",create_time);
	}
	public String getRemark() {
		return get("remark");
	}
	public void setRemark(String remark) {
		set("remark",remark);
	}
	public int getType() {
		return get("type");
	}
	public void setType(int type) {
		set("type",type);
	}
	public String getTeam_id() {
		return ("team_id");
	}
	public void setTeam_id(String team_id) {
		set("team_id",team_id);
	}

	
	public static TeamersModel findByPhone(String phone) {

		return dao.findFirst("select * from " + tableName + " where phone = ? ", phone);
	}
	public static TeamersModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 
	 */
	public static Page<TeamersModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	/**
	 * 保存
	 */
	public static boolean saveTeamers(String phone,String team_id) {
		String remark ="";
		TeamersModel m = new TeamersModel();
		m.setId(StringUtil.getId());
		m.setCeate_time(new Date());
		m.setTeam_id(team_id);
		m.setType(0);
		m.setPhone(phone);
		m.setRemark("你已于"+Util.getCurrentTime()+"加入了团队");
		return m.save();
	}
	public static boolean delById(String phone) {
		try {
			String delsql = "DELETE FROM " + tableName + " WHERE phone=?";
			int iRet = Db.update(delsql, phone);
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
}
