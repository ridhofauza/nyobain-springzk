package com.sinau.springzk.viewmodel;

import java.util.ArrayList;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;

import com.sinau.springzk.dao.User;

public class MainListVM {
	
	private ArrayList<User> userData = new ArrayList<User>();
	private String name;
	private String gender;

//	@Init
//	public void init(@ExecutionArgParam("userData") ArrayList<User> paramUserData) {
//		userData = paramUserData;
//		System.out.println("User Data:"+userData);
//	}
	
	@Init
	public void init(@BindingParam("params") Map<String, Object> params) {
//		userData = paramUserData;
//		System.out.println("User Data:"+userData);
		
		userData = (ArrayList<User>) params.get("userData");
		System.out.println(userData);
		System.out.println("test list");
	}

	public ArrayList<User> getUserData() {
		return userData;
	}

	public void setUserData(ArrayList<User> userData) {
		this.userData = userData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}	
	
	
}
