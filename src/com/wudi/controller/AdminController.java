package com.wudi.controller;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.bean.TubiaoBean;
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
	
	public void getCustomerNum() {
		String type=getPara("type");
		List<CustomerModel> Num = CustomerModel.getCustomerByType(type);
		setAttr("row", Num.size());
		renderJson();
	}
	public void getTubiaoinfo() {
		List<TubiaoBean> chengjiao=new ArrayList<TubiaoBean>();//成交
		List<TubiaoBean> weichengjiao=new ArrayList<TubiaoBean>();//未成交
		
		for(int i=1;i<=12;i++) {//从本年的第一个月开始
			int c_num=0;//成交的数量
			int w_num=0;//未成交的数量
			List<CustomerModel> list=CustomerModel.getListByCYeanMonth(i);//获取客户信息列表
			for(CustomerModel m:list) {
				
				if(m.getStatus()==6) {//找到成交的客户信息
					c_num++;
				}else {
					w_num++;
				}
			}
			TubiaoBean b=new TubiaoBean();
			b.setName("已成交");
			b.setValue(c_num);
			chengjiao.add(b);
			TubiaoBean a=new TubiaoBean();
			a.setName("未成交");
			a.setValue(w_num);
			weichengjiao.add(a);
		}
		
		setAttr("chengjiao", chengjiao);
		setAttr("weichengjiao", weichengjiao);
		renderJson();
	}
	public void delCustomerModel() {
		String id= getPara("id");
		boolean result = CustomerModel.delById(id);
		setAttr("result", result);
		renderJson();
	}
}
