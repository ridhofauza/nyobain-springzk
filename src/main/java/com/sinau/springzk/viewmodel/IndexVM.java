package com.sinau.springzk.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import com.sinau.springzk.dao.User;
import com.sinau.springzk.service.UserServiceImpl;

public class IndexVM {
	private int count;
	private ArrayList<User> users = new ArrayList<User>();
	private Map<String, Object> params = new HashMap<String, Object>();
	
	@WireVariable
	private UserServiceImpl userService;

	@Init
	public void init() {
		users = userService.findAll();
		params.put("userData", users);
		
		count = 100;
	}

	@Command
	@NotifyChange("count")
	public void cmd() {
		++count;
	}
	
	@Command("changePage")
	public void onChangePage(@ContextParam(ContextType.VIEW) Window window) {
		//Show Modal
//		Window winIdentifyUpdate = (Window) Executions.createComponents("/mainlist.zul", window, params);
//		winIdentifyUpdate.doModal();
		
		//Redirect Page
//		Executions.sendRedirect("/mainlist.zul");
	}

	public int getCount() {
		return count;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
