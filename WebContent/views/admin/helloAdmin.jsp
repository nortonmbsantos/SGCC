<%@page import="br.edu.utfpr.sgcc.models.MyUserDetails"%>
<%@page import="br.edu.utfpr.sgcc.models.User"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
	<h1>Welcome Admin ${user.username} ${user.id}</h1>
	<a href="logout">Logout</a>
</body>
</html>