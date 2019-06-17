package com.wudi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.template.expr.ast.Map;
import com.wudi.util.StringUtil;

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
		return get("team_id");
	}
	public void setTeam_id(String team_id) {
		set("team_id",team_id);
	}

	
	public static TeamersModel findByUd(String user_id) {

		return dao.findFirst("select * from " + tableName + " where user_id = ? ", user_id);
	}
	
	public static TeamersModel findByUTd(String user_id,String team_id) {//查找该二级会员是否已在该团队

		return dao.findFirst("select * from " + tableName + " where user_id = ? and team_id = ?", user_id,team_id);
	}
	public static List<TeamersModel> gainTeam_id(String user_id) {

		return dao.find("select team_id from " + tableName + " where user_id = ? ", user_id);
	}
	public static TeamersModel getTeam_id(String user_id) {

		return dao.findFirst("select * from " + tableName + " where user_id = ? ", user_id);
	}
	public static TeamersModel getTypeByTeam_id(String team_id) {

		return dao.findFirst("select * from " + tableName + " where team_id = ? ", team_id);
	}
	
	public static TeamersModel findLevel(String team_id) {

		String sql = "select a.*,b.level from "+tableName+" a , "+UserModel.tableName+" b where a.user_id = b.id and b.level = 2 and a.team_id =? ";
		return dao.findFirst(sql,team_id);
	}
	public static TeamersModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	/**
	 * 根据团队id查找同意团队的队员
	 */
	public static Page<TeamersModel> getList(int pageNumber, int pageSize, String team_id,String key) {
		String sele_sql = "select a.*,b.username,b.phone,b.level,b.sex ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" a inner join ");
		from_sql.append(UserModel.tableName).append(" b on a.user_id=b.id ").append(" where a.team_id=? ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append(" and a.name like '%" + key + "%' ");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString(),team_id);
	}
	/**
	 * 队长的Teamers保存
	 * @param phone
	 * @param team_id
	 * @return
	 */
	public static boolean saveForCaptain(String user_id,String team_id) {
		String remark ="";
		TeamersModel m = new TeamersModel();
		m.setId(StringUtil.getId());
		m.setCreate_time(new Date());
		m.setTeam_id(team_id);
		m.setType(1);
		m.setUser_id(user_id);
		m.setRemark("默认");
		return m.save();
	}
	/**
	 * 保存
	 */
	public static boolean saveTeamers(String user_id,String team_id) {
		String remark ="";
		TeamersModel m = new TeamersModel();
		m.setId(StringUtil.getId());
		m.setCreate_time(new Date());
		m.setTeam_id(team_id);
		m.setType(0);
		m.setUser_id(user_id);
		m.setRemark("默认");
		return m.save();
	}
	/**
	 * 保存
	 */
	public static boolean addTeamers(String user_id,String team_id,int type) {
		TeamersModel m = new TeamersModel();
		m.setId(StringUtil.getId());
		m.setCreate_time(new Date());
		m.setTeam_id(team_id);
		m.setType(type);
		m.setUser_id(user_id);
		return m.save();
	}
	/**
	 * 
	 * @param user_id
	 * @return
	 */
	public static boolean delById(String user_id) {
		try {
			String delsql = "DELETE FROM " + tableName + " WHERE user_id=?";
			int iRet = Db.update(delsql, user_id);
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
	 * @TODO：根据团队id获取所有队员
	 * @author ljp
	 */
	public static List<TeamersModel> findList(String team_id){
		String sql = "select b.id,b.username,b.sex,b.phone,b.level from "+tableName+" a LEFT JOIN "+UserModel.tableName+" b on a.user_id=b.id where a.team_id = ?";
		return dao.find(sql,team_id);
	}
	
	
	public static List<TeamersModel> getTaemList(String user_id){
		List<TeamersModel> m = TeamersModel.gainTeam_id(user_id);
		List<TeamersModel> list = null;
		List<TeamersModel> resList = new ArrayList<TeamersModel>();
		for(TeamersModel t:m ) {
			 list= dao.find("select id,name,remark,create_time from "+TeamModel.tableName+" where id = ? ",t._getAttrValues());
			 resList.addAll(list);
		}
		return resList;
	}
	public static boolean delTeamer(String user_id,String team_id) {
		try {
			String delsql = "DELETE FROM " + tableName + " WHERE user_id=? and team_id=?";
			int iRet = Db.update(delsql, user_id,team_id);
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
	
	public static boolean delByTeam_id(String team_id) {
		try {
			String delsql = "delete from "+tableName+" where team_id = ? ";
			int iRet = Db.update(delsql,team_id);
			if(iRet>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
