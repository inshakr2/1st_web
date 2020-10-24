<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.List"%>

<%@page import="com.servlet.LoadUtils" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>load</title>
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

	<table>
		<tr>
			<td style="padding: 10px;">
				<form action="inspect" method="post">
					<input type="submit" value="inspect">
					
				</form>
			</td>
			<td style="padding: 10px;">
				<form action="Org.jsp" method="post">
					<input type="submit" value="To Org">
				</form>
			</td>
		</tr>
	</table>

	<%
		String path = "E:\\Git\\1st_web\\upload\\" + multipartRequest.getFilesystemName("file");
		
		List<List<String>> list = LoadUtils.Load(path);
		for (int i = 0; i < list.size(); i++) {
			List<String> line = list.get(i);
			for (int j = 0; j < line.size(); j++) {
				System.out.print(line.get(j) + ",");
	 %>
	
	<%=line.get(j)%>,
	<%
		}
	System.out.println();
	%>
	<br>
	<%
		}
	%>

</body>
</html>