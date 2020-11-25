package com.servlet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Properties;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testing")
public class testing extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
        Properties properties = new Properties();
        FileReader resource = new FileReader(sc.getRealPath(sc.getInitParameter("contextConfigLocation")));
        
        String a = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
        String b = this.getClass().getResource("").getPath();
        String c = this.getClass().getResource("/").getPath();
        String d = this.getClass().getResource("/..").getPath();
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        
        
        String root = this.getClass().getResource("/..").getPath();
    	String path = root.substring(1) + "db.properties";
    	System.out.println(path.
				replaceAll("/", "\\\\"));
        
        try {
            properties.load(resource);
            System.out.println(properties.getProperty("driver"));
            System.out.println(properties.getProperty("id"));
            System.out.println(properties.getProperty("pw"));
            System.out.println(properties.getProperty("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
