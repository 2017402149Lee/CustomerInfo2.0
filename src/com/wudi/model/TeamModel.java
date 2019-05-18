package com.wudi.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
/**
 * 
 * @author ljp
 *
 */

public class TeamModel extends Model<TeamModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "team";
	public static final TeamModel dao = new TeamModel();
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
	public String getUser_id() {
		return get("user_id");
	}
	public void setUser_id(String user_id) {
		set("user_id", user_id);
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
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status",status);
	}
	
	/**
	 * 查看团队名称是否存在
	 * @param name
	 * @return
	 */
	public static boolean isExit(String name,String captainId) {
		String sql = "select * from "+tableName+" where name = ? or user_id=?";
		TeamModel m=dao.findFirst(sql,name,captainId);
		boolean result=false;
		if(m!=null) {//存在
			result=true;
		}
		return result;
	}
	/**
	 * 根据队长找团队信息
	 * @param user_id
	 * @return
	 */
	public static TeamModel findCaptain(String captainId) {
		String sql = "select * from "+tableName+" where user_id = ?";
		return dao.findFirst(sql,captainId);
	}
	public static TeamModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	public static TeamModel findcapUser_idById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	public static boolean saveTeam(String name,String user_id,String remark) {
		TeamModel m = new TeamModel();
		m.setCeate_time(new Date());
		m.setName(name);
		m.setUser_id(user_id);
		m.setRemark(remark);
		m.setStatus(1);
		m.setId(StringUtil.getId());
		return m.save();
		
	}
	public static boolean createTeam(String name,String user_id,String remark) {
		boolean result=false;
		TeamModel m = new TeamModel();
		m.setCeate_time(new Date());
		m.setName(name);
		m.setUser_id(user_id);
		m.setRemark(remark);
		m.setStatus(1);
		m.setId(StringUtil.getId());
		//找到队长的信息
		UserModel captain = UserModel.getById(user_id);
		if(captain!=null) {
			//保存团队型
			result=m.save();
			//将队长信息添加到团队里面
			TeamersModel.addTeamers(captain.getId(), m.getId(), 1);
			
		}
		return result;
		
	}
	/**
	 * 
	 */
	public static Page<TeamModel> getList(int pageNumber, int pageSize, String key) {
		StringBuffer sql = new StringBuffer();
		String sele_sql=" select a.*,b.username as captain  ";
		sql.append(" from ").append(tableName).append(" a ").append(" inner join ");
		sql.append(UserModel.tableName).append(" b on a.user_id=b.id ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			sql.append("where  a.name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, sql.toString());
	}
}
