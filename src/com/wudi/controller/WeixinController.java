package com.wudi.controller;

import com.jfinal.core.Controller;
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

}
