package com.wudi.controller;

import com.jfinal.core.Controller;
import com.wudi.model.CustomerModel;
import com.wudi.model.UserModel;

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
	 * �û�ע�����
	 * 
	 * @author wei
	 * @Description: TODO ¼���û�ע����Ϣ ��΢�Ŷ˷�����ʾ��Ϣ
	 */
	public void saveUserinfo() {
		String username = getPara("username");
		String password = getPara("password");
		int sex = getParaToInt("sex");
		if (sex==1) {
			sex = 1;		//��
		} else {
			sex = 0;		//Ů
		}
		String phone = getPara("phone");
		int code = 0; // 0ע�᲻�ɹ� 1�û��Ѿ����� 2ע��ɹ�
		String info = "ע�᲻�ɹ�";
		UserModel m = new UserModel().getphone(phone);
		if (m != null) {
			code = 1;
			info = "�û��Ѿ�����";
		} else {
			boolean result = new UserModel().saveUserinfo(username, password, phone, "001",sex, 0, 0);
			if (result) {
				code = 2;
				info = "ע��ɹ�";
			}
		}
		setAttr("code", code);
		setAttr("info", info);
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
		UserModel user = UserModel.findByPhone(phone);
		UserModel data = UserModel.loginByPhone();
		if(!phone.equals(user.getPhone())) {
			code = 1;
		}else if(!password.equals(user.getPassword())) {
			code = 2;
		}else if(user.getPassword().equals(password)&&phone.equals(user.getPhone())) {
			if(user.getStatus()!=1) {
				code = -1;
			}else {
				code =0;
				setAttr("data", data);
			}
		}
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
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String tel = getPara("tel");
		int disclose = getParaToInt("disclose");
		int age = getParaToInt("age");
		String addr = getPara("addr");
		String remark = getPara("remark");
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
		int status = getParaToInt("status");
		String addr = getPara("addr");
		String remark = getPara("remark");
		String id = getPara("id");
		String nation = getPara("nation");
		String type = getPara("type");
		String otherinfo =getPara("otherinfo");
		boolean result = CustomerModel.update(id, name, sex, tel, disclose, age, nation, addr, remark, type, otherinfo, status);
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
		CustomerModel one = CustomerModel.getById(id);
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
	
//	/**
//	 * ����ͻ���Ϣ xiao
//	 */
//	public void saveOrUpdateCustomer() {
//		String id = getPara("id");
//		String name = getPara("name");
//		int sex = getParaToInt("sex");
//		String tel = getPara("tel");
//		int disclose = getParaToInt("disclose");
//		int age = getParaToInt("age");
//		String addr = getPara("addr");
//		String remark = getPara("remark");
//		String user_id = getPara("user_id");
//		String nation = getPara("nation");
//		String type = getPara("type");
//		int status = getParaToInt("status");
//		String otherinfo =getPara("otherinfo");
//		// ��������
//		 boolean result = CustomerModel.saveOrUpate(id, name, sex, tel, disclose, age, nation, addr, remark, user_id, status, type, otherinfo);
//		setAttr("result", result);
//		renderJson();
//	}

}
