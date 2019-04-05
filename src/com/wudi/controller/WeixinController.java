package com.wudi.controller;

import com.jfinal.core.Controller;
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
	
}
