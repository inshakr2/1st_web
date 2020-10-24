package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processing {
	
	static List<String> Loadtag() throws IOException {
		
		List<String> tag = new ArrayList<String>();
		
		String path = "E:\\Git\\1st_web\\web_prj\\src\\config";
		List<List<String>> list = LoadUtils.Load(path);
		for(int i=0; i<list.size(); i++) {
			List<String> line = list.get(i);
			for(int j=0; j<line.size(); j++) {
				
				String text = line.get(j).trim();
				Pattern p = Pattern.compile("\"B-[a-zA-Z]{3}");
				
				if(text.startsWith("\"B-")) {
					Matcher m = p.matcher(text);
					m.find();
					tag.add(m.group().replace("B-", ""));
					}
				}
		}
		return tag;
	}
	
	public static String Inspect(String sentence) throws IOException {
		
		List<String> tag = Loadtag();
		
		String path = "E:\\Git\\1st_web\\upload\\";
				
		List<List<String>> list = LoadUtils.Load(path);
		for (int i = 0; i < list.size(); i++) {
			List<String> line = list.get(i);
			for (int j = 0; j < line.size(); j++) {
				String sen = line.get(j);
				
				}
				
			}
		
		
		
		
		return sentence;
	}
		
		
		
		
		
}


