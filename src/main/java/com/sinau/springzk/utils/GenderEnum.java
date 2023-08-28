package com.sinau.springzk.utils;

public enum GenderEnum {
	
	MALE("MALE"),
	FEMALE("FEMALE");
	
	private String value;
	GenderEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	} 	
	
}
