<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.List"%>
<%@page import='java.util.ArrayList' %>
<%@page import="com.servlet.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>read</title>
		
		<style>
		
		body {font-family: Arial, Helvetica, sans-serif;}
		* {box-sizing: border-box;}
	
		/* The popup form - hidden by default */
		.form-popup {
		  display: none;
		  position: fixed;
		  bottom: 0;
		  right: 15px;
		  border: 3px solid #f1f1f1;
		  z-index: 9;
		}
		
		/* Add styles to the form container */
		.form-container {
		  max-width: 1000px;
		  padding: 10px;
		  background-color: white;
		}
		
		/* Full-width input fields */
		.form-container input[type=text], .form-container input[type=password] {
		  width: 100%;
		  padding: 15px;
		  margin: 5px 0 22px 0;
		  border: none;
		  background: #f1f1f1;
		}
		
		/* When the inputs get focus, do something */
		.form-container input[type=text]:focus, .form-container input[type=password]:focus {
		  background-color: #ddd;
		  outline: none;
		}
		
		/* Set a style for the submit/login button */
		.form-container .btn {
		  background-color: #4CAF50;
		  color: white;
		  padding: 16px 20px;
		  border: none;
		  cursor: pointer;
		  width: 100%;
		  margin-bottom:10px;
		  opacity: 0.8;
		}
		
		/* Add a red background color to the cancel button */
		.form-container .cancel {
		  background-color: red;
		}
		
		/* Add some hover effects to buttons */
		.form-container .btn:hover, .open-button:hover {
		  opacity: 1;
		}
		
		
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
		String uploadParam = getServletContext().getInitParameter("UploadLocation");
		String saveDir = getServletContext().getRealPath(uploadParam);
		int maxSize = 1024 * 1024 * 100;
		String encType = "UTF-8";
		DAO_TMP dao_TMP = new DAO_TMP();
		
		boolean isUpdate = request.getContentType() != null;
		// upload = multipart... 
		// update = null
		
		if(isUpdate){
			dao_TMP.Delete(); // Initialize
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDir, maxSize, encType,
					new DefaultFileRenamePolicy());
			File file = multipartRequest.getFile("file");
			String path = getServletContext().getRealPath(uploadParam)
								+ "/" + multipartRequest.getFilesystemName("file");
			System.out.println(path);
		/* 	String path = "E:\\Git\\1st_web\\upload\\" 
							+ multipartRequest.getFilesystemName("file"); */
			
	
			List<List<String>> list = LoadUtils.Load(path);
			for (int i = 0; i < list.size(); i++) {
				List<String> line = list.get(i);
				for (int j = 0; j < line.size(); j++) {
					dao_TMP.Insert(Integer.toString(i+1) ,line.get(j));
				}
			}
		}
		
		ArrayList<DTO_TMP> tmp = dao_TMP.select();
		%>
		
		
		<%--
		업르드 파일명 : <%=multipartRequest.getFilesystemName("file")%><br> 
		--%>		
		<form action="insert.jsp" method="post">
			<input type="submit" value="proceed">
		</form>
	
	
		<table>
		  <tr>
		  	<th>No</th>
		    <th style="width:50%">Sentence</th>
		    <th>Result</th>
		    <th>Update</th>
		  </tr>
		  	<%
			  for (int i = 0; i < tmp.size(); i++) {
			  	DTO_TMP dto_TMP = tmp.get(i);
				int sen_id = dto_TMP.getId();
				String sen_org = dto_TMP.getSentence();
				String res = Inspect.Start(sen_org);
			%>
			<tr>
			<td><%=sen_id %></td>
			<td><%=sen_org.replaceAll("<", "&lt;").
						   replaceAll(">", "&gt;")%></td>
			<td><i class="<%=res %>"></i></td>
			<td>
				<button class="open-button" onclick="openForm(<%=sen_id %>)">수정하기</button>
			    		<div class="form-popup" id="myForm<%=sen_id %>">
						  <form action="update" method="get" class="form-container">
						    <h1>Update</h1>
						
						    <label for="NewSentence"><b>수정할 문장을 입력하세요</b></label>
						    <input type="text" value="<%=sen_org%>" name="sentence" required>
						    <input type="hidden" value="<%=sen_id %>" name="sentence_id">
						
						    <button type="submit" class="btn">수정하기</button>
						    <button type="button" class="btn cancel" onclick="closeForm(<%=sen_id %>)">닫기</button>
						  </form>
						</div>
				
					<form action="update" method="get">
						<button>삭제하기</button>
						<input type="hidden" value="<%=sen_id %>" name="sentence_id">
					</form>
			</td>
			</tr>
			<%
				System.out.println();
				}
		  	
			%>
			
		</table>
		<script>
			function openForm(id) {
			  document.getElementById("myForm"+id).style.display = "block";
			  
			}
			
			function closeForm(id) {
			  document.getElementById("myForm"+id).style.display = "none";
			}
		</script>
	</body>
</html>