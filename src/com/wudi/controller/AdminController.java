package com.wudi.controller;

import com.jfinal.core.Controller;
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
}
