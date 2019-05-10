package com.wudi.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.wudi.model.CustomerModel;
import com.wudi.model.NewsModel;
import com.wudi.model.TeamModel;
import com.wudi.model.TeamersModel;
import com.wudi.model.UserModel;
import com.wudi.util.Util;

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
			code = 1 ;//1是用户已存在
		}else {
			boolean result = UserModel.saveUserinfo(username, password, phone, sex);
			if(result) {
				code = 0;
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
		if(data!=null) {
			if(data.getStatus()==0) {
				code = -1;//-1未审核
			}else {
				if(password.equals(data.getPassword())) {
					code = 0;//0成功
				}else {
					code = 2;//2密码错
				}
			}
		}else {
			code = 1;//1用户不存在
		}
		UserModel user = UserModel.findByPhone(phone);//判断成交量是否大于等于10，是的话就给他升级为1级会员，让他可以创建团队
		if(user!=null) {
			List<CustomerModel> list = CustomerModel.getCJL(user.getId());
			if(list.size()>=10&&user.getLevel()!=1) {
				user.setLevel(1);
				user.update();
			}else {
				String words = "你没有成交10个客户或者你已经是会员。";
				setAttr("words", words);
			}
		}
		setAttr("data", data);
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
		String age = "";
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String tel = getPara("tel");
		int disclose = getParaToInt("disclose");
		age = getPara("age");
		addr = getPara("addr");
		remark = getPara("remark");
		String user_id = getPara("user_id");
		String nation = getPara("nation");
		String type = getPara("type");
		String otherinfo =getPara("otherinfo");
		int status = getParaToInt("status");
		CustomerModel checktel = CustomerModel.findModel(type, tel);
		if(checktel != null) {
			code = -1;
		}else {
		boolean result = CustomerModel.save(name, sex, tel, disclose, age, nation, addr, remark, user_id, type, otherinfo,status);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
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
		String age = getPara("age");
		String addr = getPara("addr");
		String remark = getPara("remark");
		String id = getPara("id");
		String nation = getPara("nation");
		String type = getPara("type");
		String otherinfo =getPara("otherinfo");
		int status = getParaToInt("status");
		boolean result = CustomerModel.update(id, name, sex, tel, disclose, age, nation, addr, remark, type, otherinfo,status);
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
		boolean exit = TeamModel.isExit(name,user_id);
		if(!exit) {//如果不存在，那么就可以创建
			boolean result = TeamModel.createTeam(name, user_id, remark);
			if(result) {
				code = 0;//创建成功
				NewsModel.createNews("创建团队","你已于"+Util.getCurrentTime()+"加入了团队",user_id,user_id);
			}
		}else {
			code = 1;
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
		String type = getPara("type");
		int status = getParaToInt("status");
		int code = -1;
		List<CustomerModel> list = CustomerModel.queryCustomerList(user_id,type,status);
		if(list != null) {
			code = 0;
		}
		setAttr("code", code);
		setAttr("data", list);
		renderJson();
	}
	/**
	 * @author ljp
	 * 邀请加入团队接口
	 */
	
	public void addTeamer() {
		int code = -1;
		String user_id=getPara("user_id");
		String team_id = getPara("team_id");
		String phone =  getPara("phone");
		//检查是否友这个人
		UserModel user=UserModel.findByPhone(phone);
		if(user!=null) {
			//检查一下是否在团队里面
			TeamersModel data = TeamersModel.findByUd(user.getId());
			if(data!=null) {
				code = -1;
			}else {
				//检查一下是否有这个团队
				TeamModel team=TeamModel.getById(team_id);
				if(team!=null) {
					boolean result=TeamersModel.addTeamers(user.getId(), team_id, 0);
					if(result) {
						code = 0;//成功
						NewsModel.createNews("邀请加入团队","你已于"+Util.getCurrentTime()+"被邀请加入了团队",user_id,user.getId());
					}
				}
			}
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
		String user_id = getPara("user_id");
		String team_id=getPara("team_id");
		boolean result = TeamersModel.delTeamer(user_id,team_id);
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
		String team_id=getPara("team_id");
		boolean result = TeamersModel.delTeamer(user_id,team_id);
		if(result) {
			code = 0;
		}else {
			code = -1;
		}
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 查看团队队员成交的信息
	 * @author 王苏黔
	 */
	public void queryTeamCustomerList() {
		String user_id = getPara("user_id");	
		int code = -1;
		List<CustomerModel> list = null;
		TeamersModel m=TeamersModel.findByUd(user_id);
		if(m != null) {
			list = CustomerModel.queryTeamCustomerList(m.getTeam_id());//队长查看成员已成交的客户信息
			code=0;
		}
		setAttr("list", list);
		setAttr("code", code);
		renderJson();
		
	/**
	 * 获取团队信息接口	
	 */
	}
	public void getTeamInfo() {
		String user_id = getPara("user_id");
		int code = -1;
		TeamModel data=null;
		//再根据user查找，他所在的团队，找出他的团队id
		TeamersModel teamer=TeamersModel.findByUd(user_id);
		if(teamer!=null) {
			data = TeamModel.getById(teamer.getTeam_id());
			code = 1;
		}
		setAttr("data", data);
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 获取团队信息及团队成员信息接口	
	 */
	public void getTeamDetailInfo() {
		String user_id = getPara("user_id");
		int code = -1;
		TeamModel data=null;
		List<TeamersModel> list=null;
		//再根据user查找，他所在的团队，找出他的团队id
		TeamersModel teamer=TeamersModel.findByUd(user_id);
		if(teamer!=null) {
			data = TeamModel.getById(teamer.getTeam_id());
			list=TeamersModel.findList(teamer.getTeam_id());
			code = 1;
		}
		setAttr("data", data);
		setAttr("list", list);
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 获取消息接口
	 */
	public void getNewsList() {
		String user_id = getPara("user_id");
		int code = -1;
		List<NewsModel> list = NewsModel.getByReading(user_id);
		if(list != null) {
			code = 0;
		}
		setAttr("list", list);
		setAttr("code", code);
		renderJson();
		
	}
	/**
	 * 管理员获取各个类型的所有客户信息
	 */
	public void queryAllList() {
		String type = getPara("type");
		int code = -1;
		List<CustomerModel> data = CustomerModel.getCustomerByType(type);
		if(data != null) {
			setAttr("data", data);
			code =0;
		}else {
			code = -1;
		}
		
		setAttr("code", code);
		renderJson();
	}
	/**
	 * 个人中心
	 */
	public void userGetCust() {
		String user_id = getPara("user_id");
		String type = getPara("type");
		int code = -1;
		List<CustomerModel> data = CustomerModel.getCustList(user_id,type);
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
	 * 通过查看他的成交量，判断是否能升级成为1级会员，并且升级
	 */
	public void checkLevel() {
		String user_id = getPara("user_id");
		int code = -1;
		UserModel user = UserModel.getById(user_id);//判断成交量是否大于等于10，是的话就给他升级为1级会员，让他可以创建团队
		if(user!=null) {
			List<CustomerModel> list = CustomerModel.getCJL(user.getId());
			if(list.size()>=10&&user.getLevel()!=1) {
				user.setLevel(1);
				user.update();
			}else {
				String words = "你没有成交10个客户或者你已经是会员。";
				setAttr("words", words);
			}
		}else {
			code = 0;
		}
		
		setAttr("code", code);
		renderJson();
	}
	

}
