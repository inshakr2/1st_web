<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
<body>
	<fieldset>
		<legend> <h2>Introduce</h2> </legend>
		<h3>웹 서비스 개발 프로젝트</h3>
		개체명 인식 문장 검사 웹 서비스
		
	</fieldset>
	
	<table>
		<tr>
			<td style="padding-left: 1200px;">
				<form action="select" method="post">
					<input type="submit" value="DB 조회" name="select">
					
				</form>
			</td>
			<td style="padding: 20px;">
				<form action="upload.jsp" method="post">
					<input type="submit" value="시작하기">
				</form>
			</td>
		</tr>
	</table>
	
	
	

</body>
</html>