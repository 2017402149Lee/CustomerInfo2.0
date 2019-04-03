package com.wudi.controller;

import java.util.ResourceBundle.Control;

import com.jfinal.core.Controller;

public class AdminController extends Controller{

	public void index() {
		renderFreeMarker("index.html");
	}
}
