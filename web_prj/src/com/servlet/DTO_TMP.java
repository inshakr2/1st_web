package com.servlet;

public class DTO_TMP {
	
	int sen_id;
	String sen_tag;
	
	public DTO_TMP(int sen_id, String sen_tag) {
		this.sen_id = sen_id;
		this.sen_tag = sen_tag;
	}

	public int getId() {
		return sen_id;
	}

	public String getSentence() {
		return sen_tag;
	}

}
