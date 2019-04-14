package com.wudi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.wudi.bean.TubiaoBean;
import com.wudi.interceptor.AdminInterceptor;
import com.wudi.model.CustomerModel;
import com.wudi.model.CustomerTypeModel;
import com.wudi.model.RoleModel;
import com.wudi.model.TeamModel;
import com.wudi.model.TeamersModel;
import com.wudi.model.UserModel;
/**
 * 
 * @author ljp
 *
 */

@Before(AdminInterceptor.class)
public class AdminController extends Controller {
	/**
	 *  功能：登录
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	@Clear(AdminInterceptor.class)
	public void login() {
		String username = getPara("username");
		String password = getPara("password");
		// 如果不正确，就提示什么不正确？
		// 如果正确，就正常显示系统页面
		UserModel m = UserModel.findByLogin(username);
		// 判断用户名和密码是否正确
		if (m != null) {
			if (m.getPassword().equals(password)) {
				setAttr("result", 0);// 可以登录
				setCookie("cname",m.getUsername(), 36000);
				setSessionAttr("user", m);
			} else {
				setAttr("result", 1);// 密码错误
			}
		} else {
			setAttr("result", 2);// 用户名不存在
		}
		renderJson();
	}

	/**
	 *  功能：退出系统
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	@Clear(AdminInterceptor.class)
	public void outLogin() {
		removeCookie("username");
		removeSessionAttr("user");
		redirect("/admin");
	}

	/**
	 *  功能：主页
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void index() {
		setAttr("user", getSessionAttr("user"));
		renderFreeMarker("index.html");
	}

	/**
	 *  功能：首页
	 *  修改时间：2019年3月20日22:47:23
	 *  作者： xiao
	 */
	public void main() {
		render("main.html");
	}
	/**
	 *  功能：打开修改密码页面
	 *  修改时间：2019年3月21日20:47:23
	 *  作者： xiao
	 */
	public void openUppassword() {
		setAttr("user", getSessionAttr("user"));
		renderFreeMarker("userinfo/uppassword.html");
	}
	/**
	 *  功能：打开修改用户密码页面
	 *  修改时间：2019年3月22日22:47:23
	 *  作者： xiao
	 */
	public void openUpdateUserPassword() {
		String id = getPara("id");
		UserModel m = UserModel.getById(id);
		setAttr("user", m);
		renderFreeMarker("userinfo/uppassword.html");
	}
	/**
	 *  功能：保存修改密码
	 *  修改时间：2019年3月21日20:47:23
	 *  作者： xiao
	 */
	public void updatePassword() {
		String id=getPara("id");
		String password=getPara("password");
		boolean result=UserModel.updatePassword(id, password);
		setAttr("result", result);
		//情况cookie
		removeCookie("username");
		removeSessionAttr("user");
		renderJson();
	}
	/**
	 * 获取用户权限
	 */
	public void getPermission() {
		setAttr("user", getSessionAttr("user"));
		renderJson();
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
	
	
	
	/**
	 * 打开用户信息界面
	 */
	public void userinfo() {
		render("userinfo/userinfoInfo.html");
	}
	/**
	 * 获取用户信息
	 */
	public void getUserInfoList() {
		String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<UserModel> list = UserModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}
	/**
	 * 审核用户
	 */
	public void checkUser() {
		String id=getPara("id");
		boolean result = UserModel.checkUser(id);
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 修改用户角色
	 */
	public void openUpateUserRole() {
		String id=getPara("id");
		UserModel user=UserModel.getById(id);
		setAttr("user", user);
		renderFreeMarker("userinfo/updUserRole.html");
	}
	/**
	 * 保存修改用户角色
	 */
	public void upateUserRole() {
		String id=getPara("id");
		String role_id=getPara("role_id");
		boolean result = UserModel.upateUserRole(id,role_id);
		setAttr("result", result);
		renderJson();
	}
	public void getRoleListForSelect() {
		List<RoleModel> list=RoleModel.getList();
		setAttr("list", list);
		renderJson();
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
	
	/**
	 * 
	* @Title: openRoles
	* @Description:打开角色列表页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openRoles() {
		render("role/roleinfo.html");
	}
	/**
	 * 
	* @Title: getRoleList
	* @Description:获取角色信息列表
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getRoleList() {
		String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<RoleModel> list = RoleModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}
	/**
	 * 
	* @Title: openRoleAdd
	* @Description:打开添加角色页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openRoleAdd() {
		render("role/roleAdd.html");
	}
	/**
	 * 
	* @Title: saveRole
	* @Description:添加保存数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveRole() {
		Map<String,Integer> p=new HashMap<String,Integer>();
		
		String name=getPara("name");
		
		for(int i=100;i<105;i++) {
			String k="c"+i;
			String v=getPara(k);
			if(v==null) {
				p.put(k, 0);
			}else {
				p.put(k, 1);
			}
		}
		boolean result =RoleModel.save(name,JsonKit.toJson(p));
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: openRoleEdit
	* @Description:打开编辑页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openRoleEdit() {
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("role/roleEdit.html");
		
	}
	/**
	 * 
	* @Title: getRoleModel
	* @Description:获取编辑页面数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getRoleModel() {
		String id=getPara("id");
		RoleModel m=RoleModel.getModelById(id);
		setAttr("m", m);
		renderJson();
	}
	/**
	 * 
	* @Title: saveRole
	* @Description:添加保存数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void updateRole() {
		Map<String,Integer> p=new HashMap<String,Integer>();
		
		String id=getPara("id");
		
		for(int i=100;i<105;i++) {
			String k="c"+i;
			String v=getPara(k);
			if(v==null) {
				p.put(k, 0);
			}else {
				p.put(k, 1);
			}
		}
		boolean result =RoleModel.updatePermission(id,JsonKit.toJson(p));
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 打开客户类型页面
	 */
	public void openCustomerTypes() {
		render("customer/customerTypeInfo.html");
	}
	public void getCustomerTypeList() {
		String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<CustomerTypeModel> list = CustomerTypeModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}
	/**
	 * 
	* @Title: openCustomerTypeAdd
	* @Description:打开添加客户类型页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openCustomerTypeAdd() {
		render("customer/customerTypeAdd.html");
	}
	/**
	 * 
	* @Title: saveCustomerType
	* @Description:添加保存数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void saveCustomerType() {
		
		String name=getPara("name");
		String type_no=getPara("type_no");
		boolean result =CustomerTypeModel.save(name,type_no);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 
	* @Title: openCustomerTypeEdit
	* @Description:打开编辑页面
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void openCustomerTypeEdit() {
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("customer/customerTypeEdit.html");
		
	}
	/**
	 * 
	* @Title: getCustomerTypeModel
	* @Description:获取编辑页面数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void getCustomerTypeModel() {
		String id=getPara("id");
		CustomerTypeModel m=CustomerTypeModel.getById(id);
		setAttr("m", m);
		renderJson();
	}
	/**
	 * 
	* @Title: updateCustomerType
	* @Description:添加保存数据
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	public void updateCustomerType() {
		String id=getPara("id");
		String name=getPara("name");
		String type_no=getPara("type_no");
		boolean result =CustomerTypeModel.update(id,name,type_no);
		// 返回结果
		setAttr("result", result);
		renderJson();
	}
	/**
	 * 打开团队信息页面
	 */
	public void openTeams() {
		render("team/teaminfo.html");
	}
	public void getTeamsList() {
		String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<TeamModel> list = TeamModel.getList(page, limit, key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}
	/**
	 * 打开团队成员信息页面
	 */
	public void openTeamDetail() {
		String id=getPara("id");
		setAttr("id", id);
		renderFreeMarker("team/teamDetail.html");
	}
	public void getTeamDetailList() {
		String team_id=getPara("id");
		String key = getPara("key");
        int limit=getParaToInt("limit");
        int page=getParaToInt("page");
        Page<TeamersModel> list = TeamersModel.getList(page, limit, team_id,key);
        setAttr("code", 0);
        setAttr("msg", "你好！");
        setAttr("count", list.getTotalRow());
        setAttr("data", list.getList());
        renderJson();
	}
}
