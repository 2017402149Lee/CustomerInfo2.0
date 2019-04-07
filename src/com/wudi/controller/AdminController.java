package com.wudi.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.model.CustomerModel;
/**
 * 
 * @author ljp
 *
 */

public class AdminController extends Controller{

	public void index() {
		//setAttr("user", getSessionAttr("user"));
		renderFreeMarker("index.html");
	}
	public void main() {
		render("main.html");
	}
	public void openCuStomers() {
		String type=getPara("type");
		setAttr("type", type);
		renderFreeMarker("customer/customerInfo.html");
	}
	public void queryCustomers() {
		String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        String type=getPara("type"); 
        Page<CustomerModel> list = CustomerModel.getList(page, limit, key,type);
        List<CustomerModel> datalist = CustomerModel.getCustomerByType(type);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        setAttr("xlsdata", datalist);
        renderJson();
	}
	
	/**
	 * 团队页面
	 */
	public void groupinfo() {
		render("groupinfo/groupinfoInfo.html");
	}
	
	
	
	/*
	//打开用户信息界面
	 * */
	public void userinfo() {
		render("userinfo/userinfoInfo.html");
	}
	
	
	/*
	 * @Descripion: 打开管理员信息界面
	 * 
	 */
	public void admininfo() {
		render("admininfo/admininfoInfo.html");
	}
}
