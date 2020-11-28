package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inspect {

	static List<String> Loadtag() throws IOException {
		
		List<String> tag = new ArrayList<String>();
		
		String path = Inspect.class.getResource("/tag.json").getPath();
		//String path = "E:\\Git\\1st_web\\web_prj\\src\\config\\tag_con";
		
		List<List<String>> list = LoadUtils.Load(path);
		for(int i=0; i<list.size(); i++) {
			List<String> line = list.get(i);
			for(int j=0; j<line.size(); j++) {
				
				String text = line.get(j).trim();
				Pattern p = Pattern.compile("\"B-[a-zA-Z]{3}");
				
				if(text.startsWith("\"B-")) {
					Matcher m = p.matcher(text);
					m.find();
					tag.add(m.group().replace("\"B-", ""));
					}
				}
		}
		return tag;
	}
	
	
	static boolean CountClamp(String sentence) throws IOException {
		int left = 0 ;
		int right = 0 ;
		boolean result = false;
		
		// < , > °¹¼ö ºñ±³
		for(int i=0; i < sentence.length(); i++) {
			if(sentence.charAt(i) == '<') {
				left++;
			}
		}
		for(int i=0; i < sentence.length(); i++) {
			if(sentence.charAt(i) == '>') {
				right++;
			}
		}	
		if(left == right) {
			result = true;
		}
		
		return result;
		
	}
	
	static List<String> TagMatcher(String sentence) throws IOException {
		
		Pattern p = Pattern.compile("<.[^<]*?:[a-zA-Z°¡-ÆR\\s]*>");
        Matcher m = p.matcher(sentence);
        List<String> result = new ArrayList<String>();
        while(m.find()) {
        	result.add(m.group());
        }
		return result;
	}
	
	static boolean IsinTag(String extract_tag) throws IndexOutOfBoundsException, IOException {
		
		List<String> tag = Loadtag();

		boolean result = false;

		Pattern p = Pattern.compile(":.*>");
        Matcher m = p.matcher(extract_tag);
        m.find();
        String testing = m.group().substring(1,4);

        for (int j=0; j<tag.size(); j++){
            if (testing.equals(tag.get(j))) {
            	result = true;
            }
        }
        
		
		return result;
	}
	
	
	public static String Start(String sentence) throws IOException {
		
		String result = "fa fa-check";
		if(CountClamp(sentence) == true) {
			List<String> temp_tag = TagMatcher(sentence);
			for(String extract_tag : temp_tag) {
				
				System.out.println(extract_tag);
				if (IsinTag(extract_tag) == false) {
					result = "fa fa-remove";
					break;
				}
			}
		} else {
			result = "fa fa-remove";
		}
		return result;
	}
		
}


