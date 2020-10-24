package com.servlet;

public class DTO {
	
	int sen_id;
	String sen_org;
	String sen_tag;
	
	public DTO(int sen_id, String sen_org, String sen_tag) {
		this.sen_id = sen_id;
		this.sen_org = sen_org;
		this.sen_tag = sen_tag;
	}

	public int getId() {
		return sen_id;
	}

	public String getOrg() {
		return sen_org;
	}

	public String getTag() {
		return sen_tag;
	}

}
