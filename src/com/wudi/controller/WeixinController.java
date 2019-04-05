package com.wudi.controller;

import java.util.List;
import com.jfinal.core.Controller;
import com.wudi.model.UserModel;

/**
 * 
 * @author ljp
 *
 */


public class WeixinController extends Controller{
	/**
	 * 默认获取数据的地方
	 */
	public void index() {
		setAttr("result", "你好，无敌小团队微信小程序路径！");
		renderJson();
	}
	


	/**
	 * 用户注册入口
	 * 
	 * @author wei
	 * @Description: TODO 录入用户注册信息 给微信端发送提示信息
	 */
	public void saveUserinfo() {
		String username = getPara("username");
		String password = getPara("password");
		int sex = getParaToInt("sex");
		if (sex==1) {
			sex = 1;		//男
		} else {
			sex = 0;		//女
		}
		String phone = getPara("phone");
		int code = 0; // 0注册不成功 1用户已经存在 2注册成功
		String info = "注册不成功";
		UserModel m = new UserModel().getphone(phone);
		if (m != null) {
			code = 1;
			info = "用户已经存在";
		} else {
			boolean result = new UserModel().saveUserinfo(username, password, phone, "001",sex, 0, 0);
			if (result) {
				code = 2;
				info = "注册成功";
			}
		}
		setAttr("code", code);
		setAttr("info", info);
		renderJson();
	}
	
	
	/**
	 * 用户登录入口
	 * 
	 * @Description: TODO 根据 phone_no&user_password判断 给微信端返回用户或管理员所有信息
	 * @author 张志强
	 */
	public void userLogin() {
		String phone = getPara("phone");
		String password = getPara("password");
		int code = 0; // 返回给前端做判断登录是否成功 0 密码错误 1密码正确 2 用户不存在，3表示用户未通过审核
		String role_id = " ";// 用户类型 001普通用户，003客服人員，003是管理員
		String info = "用户不存在";
		List<?> list = null;
		// 查询用户表phone_no字段
		UserModel m = new UserModel().getphone(phone);
		if (m != null) {
			role_id = m.getRole_id();
			if (m.getPassword().equals(password)) {
//				if (m.getStatus()==0) {
//					code = 3;
//					info = "未审核用户";
//				} else {
					list = new UserModel().getUserAllInfo(phone);
					code = 1;
					info = "密码正确";
				
			} else {
				code = 0;
				info = "密码错误";
			}
		} else {
			code = 2;
			info = "用户不存在";
		}
		setAttr("code", code);
		setAttr("info", info);
		setAttr("role_id", role_id);
		setAttr("data", list);
		renderJson();
	}
	
}
