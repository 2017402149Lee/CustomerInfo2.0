package com.wudi.controller;

import com.jfinal.core.Controller;
/**
 * 
 * @author ljp
 *
 */

public class AdminController extends Controller{

	public void index() {
		renderFreeMarker("index.html");
	}
}
