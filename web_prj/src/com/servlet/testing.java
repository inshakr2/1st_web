package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testing")
public class testing extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO_TMP dao_TMP = new DAO_TMP();
		
		response.setContentType("text/html; charset=UTF-8");
		String sentence = request.getParameter("sentence");
		String sentence_id = request.getParameter("sentence_id");
		
		PrintWriter out = response.getWriter();
		
		out.println(sentence);
		out.println(sentence_id);
		
		dao_TMP.Update(sentence_id, sentence);
		
		response.sendRedirect("read_v2.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
