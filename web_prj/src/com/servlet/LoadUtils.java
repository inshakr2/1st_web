package com.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoadUtils {

	// private static Log logger = LogFactory.getLog(CsvUtils.class);

	public static List<List<String>> Load(String path) throws IOException {
		List<List<String>> list = new ArrayList<List<String>>();
		File csv = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csv));
			Charset.forName("UTF-8");
			String line = "";
			
			while((line=br.readLine()) != null) {
				String[] token = line.split(",");
				List<String> tempList = new ArrayList<String>(Arrays.asList(token));
				list.add(tempList);		
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("Something Problem");
		} finally {
			if(br != null) {br.close();}
		}
		return list;
	}
	
}