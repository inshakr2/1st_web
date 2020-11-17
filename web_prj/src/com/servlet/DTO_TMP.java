package com.servlet;

public class DTO_TMP {
	
	int sen_id;
	String sen_org;
	
	public DTO_TMP(int sen_id, String sen_org) {
		this.sen_id = sen_id;
		this.sen_org = sen_org;
	}

	public int getId() {
		return sen_id;
	}

	public String getOrg() {
		return sen_org;
	}

}
