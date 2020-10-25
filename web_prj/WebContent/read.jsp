<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.List"%>

<%@page import="com.servlet.LoadUtils" %>
<%@page import="com.servlet.Inspect" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>


<html>
	<head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>read</title>
	
	<style>
	table {
	  border-collapse: collapse;
	  border-spacing: 0;
	  width: 100%;
	  border: 1px solid #ddd;
	}
	
	th, td {
	  text-align: center;
	  padding: 16px;
	}
	
	th:first-child, td:first-child {
	  text-align: left;
	}
	
	tr:nth-child(even) {
	  background-color: #f2f2f2
	}
	
	.fa-check {
	  color: green;
	}
	
	.fa-remove {
	  color: red;
	}
	</style>
	
	</head>
<body>
	<%
	String saveDir = "E:\\Git\\1st_web\\upload";
	int maxSize = 1024 * 1024 * 100;
	String encType = "UTF-8";

	MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
			new DefaultFileRenamePolicy());
	
	File file = multipartRequest.getFile("file");
	%>

<%--
	이름 :<%=multipartRequest.getParameter("name")%><br> 
	파일 :<%=multipartRequest.getParameter("file")%><br>
 --%>
 
	업르드 파일명 : <%=multipartRequest.getFilesystemName("file")%><br> 
	원래 파일명 : <%=multipartRequest.getOriginalFileName("file")%><br>
	<br>

	
	<form action="inspect" method="post">
		<input type="submit" value="proceed">
	</form>
	
	
	<table>
	  <tr>
	    <th style="width:50%">Sentence</th>
	    <th>Result</th>
	  </tr>

	<%
		String path = "E:\\Git\\1st_web\\upload\\" + multipartRequest.getFilesystemName("file");
		
		List<List<String>> list = LoadUtils.Load(path);
		for (int i = 0; i < list.size(); i++) {
			List<String> line = list.get(i);
			for (int j = 0; j < line.size(); j++) {
				String sen = line.get(j);
				sen = sen.replaceAll("<", "&lt;");
				sen = sen.replaceAll(">", "&gt;");
				System.out.println(sen);
	%>
				<tr>
	    		<td><%=sen%></td>
	<%
				String res = Inspect.Start(line.get(j));
	%>
	    		<td><i class="<%=res%>"></i></td>
  				</tr>
	<%
		}
	System.out.println();
		}
	%>
	</table>

</body>
</html>