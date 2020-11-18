package com.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToOrg {
	
	public static String Convert(String tag_sentence) throws IOException {
		
		String orgSentence = tag_sentence.toString();
		
		Pattern p = Pattern.compile("<(.[^<]*):\\w{3}>");
        Matcher m = p.matcher(tag_sentence);

        while (m.find()) {
                String tag = m.group(0);
                String org = m.group(1);
                orgSentence = orgSentence.replace(tag, org);
            }
        
        return orgSentence;
   	}
}

