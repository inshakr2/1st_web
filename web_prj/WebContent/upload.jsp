<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Home</title>
	</head>
<body>
	<h3>문장 검사 시작하기</h3>
	Select a file to upload:
	<br />
	<form action='read.jsp' method='post' enctype="multipart/form-data">
		<input type="file" name="file">
		<br><br>
		<input type="submit" value="Proceed">
	</form>
</body>
</html>