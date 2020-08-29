<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Welcome to "${appName}" App
	<form action="/appointment/save" method="post">
		<table>
			<tr>
				<td>Name: <input type="text" name="name" />
				</td>
			</tr>
			<tr>
				<td>Email: <input type="text" name="email" />
				</td>
			</tr>
			<tr>
				<td>Phone: <input type="text" name="phoneNumber" />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>