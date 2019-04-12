package com.wudi.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.wudi.model.CustomerModel;
import com.wudi.model.NewsModel;
import com.wudi.model.RoleModel;
import com.wudi.model.TeamModel;
import com.wudi.model.TeamersModel;
import com.wudi.model.UserModel;
import com.wudi.util.StringUtil;

/**
 * 
 * @author ljp
 *
 */


public class WeixinController extends Controller{
	/**
	 * 微信路径
	 */
	public void index() {
		setAttr("result", "你好无敌小团队！");
		renderJson();
	}
	/**
	 * 用戶注册接口
	 */
	public void userRegister() {
		int code = -1;
		String username = getPara("username");
		String phone = getPara("phone");
		String password = getPara("password");
		int sex = getParaToInt("sex");
		UserModel p = UserModel.findByPhone(phone);
		 if(p != null) {
			code = -1 ;//2是用户已存在
		}
		else {
			boolean result = UserModel.saveUserinfo(username, password, phone, sex);
			code = 0;
			setAttr("result", result);
			if(result) {
				UserModel m = UserModel.findByPhone(phone);
				boolean saveRole = RoleModel.save(m.getRole_id());
			}
		}
		setAttr("code", code);
		renderJson();
	}

	/**
	 * @author ljp
	 * @TODO 用戶登录
	 */
	
	public void userLogin() {
		String phone = getPara("phone");
		String password = getPara("password");	
		int code = -1;
		UserModel data = UserModel.loginByPhone(phone);
		RoleModel p = RoleModel.getPermissionBy(data.getRole_id());
		if(!phone.equals(data.getPhone())) {
			code = 1; 
		}else if(!password.equals(data.getPassword())) {
			code = 2;
		}else if(data.getPassword().equals(password)&&phone.equals(data.getPhone())) {
			if(data.getStatus()==0) {//status 0为未审核，1为审核
				code = -1;
			}
				code =0;
				setAttr("data", data);
			}
		setAttr("permission", p.getPermission());
		setAttr("code", code);
		renderJson();
}
		
	
	/**
	 * @author ljp
	 * @TODO 登出
	 */
	public void outLogin() {
		String phone = getPara("phone");
		int code;
		UserModel user = UserModel.findByPhone(phone);
		if(!user.getPhone().equals(phone)) {
			code = -1;
		}else {
			code = 0;
		}
		setAttr("code", code);
		renderJson();
	}
	/**@author ljp
	 * 说明：code：返回编码，0：添加成功，-1：添加失败
	 * @TODO: 添加客户信息
	 */
	public void addCustomerInfo() {
		int code = -1;
		String addr = "";
		String remark = "";
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String tel = getPara("tel");
		int disclose = getParaToInt("disclose");
		int age = getParaToInt("age");
		addr = getPara("addr");
		remark = getPara("remark");
		String user_id = getPara("user_id");
		String nation = getPara("nation");
		String type = getPara("type");
		String otherinfo =getPara("otherinfo");
		boolean result = CustomerModel.save(name, sex, tel, disclose, age, nation, addr, remark, user_id, type, otherinfo);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
	}
	
	
	/**
	 * @author ljp
	 * @TODO：修改客户信息
	 * code：返回编码，0：更新成功，-1：更新失败
	 */
	public void updateCustomerInfo() {
		int code = -1;
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String tel = getPara("tel");
		int disclose = getParaToInt("disclose");
		int age = getParaToInt("age");
		String addr = getPara("addr");
		String remark = getPara("remark");
		String id = getPara("id");
		String nation = getPara("nation");
		String type = getPara("type");
		String otherinfo =getPara("otherinfo");
		boolean result = CustomerModel.update(id, name, sex, tel, disclose, age, nation, addr, remark, type, otherinfo);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
	}
	/**
	 * @author ljp
	 * @TODO：查询单个客户信息
	 * @param：id
	 */
	public void queryCustomerInfo() {
		String id = getPara("id");
		int code = -1;
		CustomerModel data = CustomerModel.findCustomerById(id);
		if(data != null) {
			code = 0;
			setAttr("data", data);
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
		
	}
	/**
	 * @author ljp
	 * @TODO 根据ID删除
	 */
	public void delCustomerInfo() {
		String id = getPara("id");
		int code = -1;
		boolean result = CustomerModel.delById(id);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
	}
	/**
	 * @author ljp
	 * @TODO: 创建团队
	 */
	public void createTeam() {
		int code = -1;
		String name = getPara("name");
		String user_id = getPara("user_id");
		String remark = getPara("remark");
		TeamModel t = TeamModel.getByName(name);
		TeamModel u = TeamModel.findUser_id(user_id);
		if(t != null) {
			code = -1;
		}else if(u != null) {
			code = 2;
		}else {
			boolean result = TeamModel.saveTeam(name, user_id, remark);

			setAttr("result", result);
			if(result) {
				
				boolean data = TeamersModel.saveForCaptain(user_id,u.getId());//自动添加队长到teamers表
				
				if(data) {
					boolean news = NewsModel.saveNews(user_id, name);
					code = 0;
					setAttr("data", data);
				}
			}
		}
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 获取自己客户信息列表接口
	 * @param user_id,type
	 */
	public void queryCustomerList() {
		String user_id = getPara("user_id");
		int code = -1;
		List<CustomerModel> list = CustomerModel.queryCustomerList(user_id);
		if(list != null) {
			code = 0;
			setAttr("data", list);
		}else {
			code = -1;
		}
		
		renderJson();
	}
	/**
	 * @author ljp
	 * 邀请加入团队接口
	 */
	
	public void addTeamer() {
		int code = -1;
		String user_id = getPara("user_id");
		String team_id =  getPara("team_id");
		TeamersModel data = TeamersModel.findByUd(user_id);
		if(data!=null) {
			code = -1;
		}else {
			boolean result = TeamersModel.saveTeamers(user_id, team_id);
			setAttr("result", result);
			code = 0;
		}
		setAttr("code", code);
		renderJson();
	}
	
	/**
	 * 删除队员接口
	 * @author ljp
	 */
	public void delTeamer() {
		int code = -1;
		String phone = getPara("phone");
		boolean result = TeamersModel.delById(phone);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 队员退出团队
	 */
	public void exitTeam() {
		int code = -1;
		String user_id = getPara("user_id");
		boolean result = TeamersModel.delById(user_id);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 个人中心界面的接口
	 * @author 王苏黔
	 */
	public void queryTeamCustomerList() {
		String user_id = getPara("user_id");	
		String team_id = getPara("team_id");
		int code = -1;
		
		List<CustomerModel> list = CustomerModel.queryTeamCustomerList(user_id, team_id);
		if(list != null) {
			code = 0;
			setAttr("list", list);
		}
		setAttr("code", code);
		renderJson();
		
	/**
	 * 获取团队信息接口	
	 */
	}
	public void getTeamInfo() {
		String team_id = getPara("team_id");
		String user_id = getPara("user_id");
		int code = -1;
		TeamModel data=null;
		//如果团队id为空
		if(StringUtil.isBlankOrEmpty(team_id)) {
			//再根据user查找，他所在的团队，找出他的团队id
			data = TeamModel.findUser_id(user_id);
		}else {
			data = TeamModel.getById(team_id);
		}
		if(data != null) {
			code = 0;
			List<TeamersModel> result = TeamersModel.findList(team_id);

			setAttr("result", result);
			setAttr("data", data);
		}else {
			code = -1;

		}
		setAttr("code", code);
		setAttr("data", data);
		renderJson();
	}
	/**
	 * 获取消息接口
	 */
	public void getNewsList() {
		String user_id = getPara("user_id");
		int code = -1;
		NewsModel list = NewsModel.getByUser_id(user_id);
		if(list != null) {
			code = 0;
			setAttr("list", list.getContent());
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
		
	}

}
