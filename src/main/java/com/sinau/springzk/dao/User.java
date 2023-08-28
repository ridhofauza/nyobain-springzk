package com.sinau.springzk.dao;

public class User {
	private Integer id;
	private String name;
	private String gender;
	
	public User() {}
	
	public User(Integer id, String name, String gender) {
		this.id=id;
		this.name=name;
		this.gender=gender;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setGender(String gender) {
		this.gender=gender;
	}
	
	public String getGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "[ Id: " + getId() + ", Name: "+ getName() +", Gender: "+ getGender() +" ]";
	}
	
}
