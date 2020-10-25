package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/select")
public class Select extends HttpServlet {

	private ArrayList<DTO> list;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		String where = request.getParameter("where");
		PrintWriter out = response.getWriter();

		DAO dao = new DAO();
	
		if (where == null) {
			list = dao.select();
		} else {
			list = dao.where(where);	
			out.println("<h5> * 현재 where 절 : " + where + "</h5>");
		}
		
		out.println("<style>"
				+ "table {"
				+ "  border-collapse: collapse;"
				+ "  border-spacing: 0;"
				+ "  width: 100%;"
				+ "  border: 1px solid #ddd;"
				+ "}"
				+ "th, td {"
				+ "  text-align: left;"
				+ "  padding: 8px;"
				+ "}"
				+ "tr:nth-child(even){background-color: #f2f2f2}"
				+ "</style>");
		out.println("<fieldset>");
		out.println("<legend> <strong> 조회 결과 </strong> </legend>");
		out.println("<div style=\"overflow-x:auto;\">");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th> Sentence ID </th>");
		out.println("<th> Origin Sentence </th>");
		out.println("<th> Tagging Sentence </th>");
		out.println("</tr>");
		
		for (int i = 0; i < list.size(); i++) {
			DTO dto = list.get(i);
			int sen_id = dto.getId();
			String sen_org = dto.getOrg();
			String sen_tag = dto.getTag();

			out.println("<tr>");
			out.println("<td>" + sen_id + "</td>");
			out.println("<td>" + sen_org + "</td>");
			out.println("<td>" + sen_tag + "</td>");
			out.println("</tr>");
			
		}
		out.println("</table>");
		out.println("</div>");
		out.println("</fieldset><br>");
		
		out.println("<h4>where 조건절 추가</h4>");
		out.println("<form action=\"select\" method=\"post\">");
		out.println("<textarea id=\"where\" name=\"where\" rows=\"3\" cols=\"40\">"
				+ " column : sen_id / sen_org / sen_tag "
				+ "</textarea>"
				+ "<input type=\"submit\" value=\"질의\">"
				+ "</form>");
		out.println("<form action=home.jsp><input type=\"submit\" value=\"Home\"></form>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
