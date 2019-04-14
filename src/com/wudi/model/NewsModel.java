package com.wudi.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.util.StringUtil;
import com.wudi.util.Util;
/**
 * 
 * @author ljp
 *
 */

public class NewsModel extends Model<NewsModel>{

	private static final long serialVersionUID = 1L;
	public static final String tableName = "news";
	public static final NewsModel dao = new NewsModel();
	public String getId() {
		return get("id");
	}
	public void setId(String id) {
		set("id", id);
	}
	public String getTitle() {
		return get("title");
	}
	public void setTitle(String title) {
		set("title", title);
	}
	public String getContent() {
		return get("content");
	}
	public void setContent(String content) {
		set("content",content);
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
	public String getRelease_time() {
		return get("release_time");
	}
	public void setRelease_time(String release_time) {
        set("release_time",release_time);
	}
	public int getStatus() {
		return get("status");
	}
	public void setStatus(int status) {
		set("status",status);
	}
	public String getReading() {
		return ("reading");
	}
	public void setReading(String reading) {
		set("reading",reading);
	}

	
	public static NewsModel getById(String id) {

		return dao.findFirst("select * from " + tableName + " where id = ? ", id);
	}
	
	public static NewsModel getByUser_id(String user_id) {

		return dao.findFirst("select * from " + tableName + " where user_id = ? ", user_id);
	}
	/**
	 * 
	 */
	public static Page<NewsModel> getList(int pageNumber, int pageSize, String key) {
		String sele_sql = "select * ";
		StringBuffer from_sql = new StringBuffer();
		from_sql.append("from ").append(tableName).append(" ");
		if (!StringUtil.isBlankOrEmpty(key)) {
			from_sql.append("where  name like '%" + key + "%'");
		}
		return dao.paginate(pageNumber, pageSize, sele_sql, from_sql.toString());
	}
	
	/**
	 * 保存消息
	 */
	public static boolean saveNews(String user_id) {
		NewsModel m = new NewsModel();
		m.setId(StringUtil.getId());
		m.setCeate_time(new Date());
		m.setContent("你已于"+Util.getCurrentTime()+"加入了团队");
		m.setStatus(0);
		m.setUser_id(user_id);
		return m.save();
	}
	/**
	 * 保存消息
	 */
	public static boolean createNews(String title,String content,String user_id,String reading) {
		NewsModel m = new NewsModel();
		m.setId(StringUtil.getId());
		m.setCeate_time(new Date());
		m.setTitle(title);
		m.setContent(content);
		m.setStatus(0);
		m.setUser_id(user_id);
		m.setReading(reading);
		return m.save();
	}
}
