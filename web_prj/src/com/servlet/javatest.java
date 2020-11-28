package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class javatest {

	public static void main(String[] args){
		
		
		InputStream root = Inspect.class.getResourceAsStream("/db.properties");
		String path = javatest.class.getResource("/tag.json").getPath();
		System.out.println(path);
//	
//		List<String> tag = new ArrayList<String>();
//		List<List<String>> list = LoadUtils.Load(path);
//		for (int i = 0; i < list.size(); i++) {
//			List<String> line = list.get(i);
//			for (int j = 0; j < line.size(); j++) {
//
//				String text = line.get(j).trim();
//				Pattern p = Pattern.compile("\"B-[a-zA-Z]{3}");
//
//				if (text.startsWith("\"B-")) {
//					Matcher m = p.matcher(text);
//					m.find();
//					tag.add(m.group().replace("\"B-", ""));
//				}
//			}
//		
//	}
//		return tag;
	}
}


