package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateTMP extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO_TMP dao_TMP = new DAO_TMP();
		response.setContentType("text/html; charset=UTF-8");
		
		String sentence = request.getParameter("sentence");
		String sentence_id = request.getParameter("sentence_id");
		
		boolean isDelete = sentence == null;
		
		if(isDelete) {
			dao_TMP.DeleteIndex(sentence_id);
		}else {
			dao_TMP.Update(sentence_id, sentence);
		}
		
		response.sendRedirect("read.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
