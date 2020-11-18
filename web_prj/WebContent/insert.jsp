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
	ArrayList<DTO_TMP> tmp = dao_TMP.select();
	%>
	<fieldset>
	<legend><h2>Convert 결과</h2></legend>
	<ul>
	<%
	for (int i = 0; i < tmp.size(); i++) {
	  	DTO_TMP dto_TMP = tmp.get(i);
	  	String tag_sentence = dto_TMP.getSentence();
	  	
	  	String orgSentence = ToOrg.Convert(tag_sentence);
	%>
		<li> <%= orgSentence%><br>
		
	<%} %>
	</ul>
	</fieldset>
	

</body>
</html>