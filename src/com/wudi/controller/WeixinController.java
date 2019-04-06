package com.wudi.controller;

import java.util.List;

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
	 * Ĭ�ϻ�ȡ���ݵĵط�
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
	 * �û���¼���
	 * 
<<<<<<< HEAD
	 * @Description: TODO ���� phone_no&user_password�ж� ��΢�Ŷ˷����û������Ա������Ϣ
	 * @author ��־ǿ
=======
	 * @Description: TODO ���� phone&password�ж� ��΢�Ŷ˷����û������Ա������Ϣ
	 * @author wei
>>>>>>> branch 'master' of https://github.com/2017402149Lee/CustomerInfo2.0.git
	 */
	public void userLogin() {
		String phone = getPara("phone");
		String password = getPara("password");
		int code = 0; // ���ظ�ǰ�����жϵ�¼�Ƿ�ɹ� 0 ������� 1������ȷ 2 �û������ڣ�3��ʾ�û�δͨ�����
		String role_id = " ";// �û����� 001��ͨ�û���003�ͷ��ˆT��003�ǹ���T
		String info = "�û�������";
		List<?> list = null;
		// ��ѯ�û���phone_no�ֶ�
		UserModel m = new UserModel().getphone(phone);
		if (m != null) {
			role_id = m.getRole_id();
			if (m.getPassword().equals(password)) {
//				if (m.getStatus()==0) {
//					code = 3;
//					info = "δ����û�";
//				} else {
					list = new UserModel().getUserAllInfo(phone);
					code = 1;
					info = "������ȷ";
				
			} else {
				code = 0;
				info = "�������";
			}
		} else {
			code = 2;
			info = "�û�������";
		}
		setAttr("code", code);
		setAttr("info", info);
		setAttr("role_id", role_id);
		setAttr("data", list);
		renderJson();
	}
	
	/*
	 * ��������ҳ��
	 * ���ظ������ĵ�ȫ����Ϣ
	 * @author wei
	 */
	public void getUserAllInfo() {
		String phone = getPara("phone");
		UserModel user = new UserModel().getphone(phone);
		setAttr("user", user);
		renderJson();
	}
	
	
	/**
	 * ����ͻ���Ϣ xiao
	 */
	public void saveOrUpdateCustomer() {
		String id = getPara("id");
		String name = getPara("name");
		int sex = getParaToInt("sex");
		String tel_no = getPara("tel_no");
		int disclose = getParaToInt("disclose");
		int age = getParaToInt("age");
		String work_address = getPara("work_address");
		String comments = getPara("comments");
		String phone_no = getPara("phone_no");
		String nation = getPara("nation");
		String type = getPara("type");
		int status = getParaToInt("status");
		// ��������
//		 boolean result = CustomerModel.saveOrUpate(id, name, sex, tel_no, disclose, age, work_address, comments, phone_no,
//				nation, type, status);
		//setAttr("result", result);
		renderJson();
	}

}
