<%@page import="com.servlet.*" %>
<%@page import='java.util.ArrayList' %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert</title>
	</head>
<body>
	<%
	DAO_TMP dao_TMP = new DAO_TMP();
	DAO dao = new DAO();
	
	ArrayList<DTO_TMP> tmp = dao_TMP.select();
	%>
	<h2>Main Table insert 완료</h2>
	<form action="select" method="post">
		<input type="submit" value="DB 조회" name="select">
					
	</form>
	<fieldset>
	<legend><h3>Convert 결과</h3></legend>
	<ul>
	<%
	for (int i = 0; i < tmp.size(); i++) {
	  	DTO_TMP dto_TMP = tmp.get(i);
	  	String tagSentence = dto_TMP.getSentence();
	  	
	  	String orgSentence = ToOrg.Convert(tagSentence);
	  	//String sentenceID = Integer.toString(dto_TMP.getId());
	  	dao.Insert(orgSentence, tagSentence);
	%>
		<li> <%= orgSentence%><br>
		
		
	<%} 
	   %>
	</ul>
	</fieldset>
	
	
	

</body>
</html>