<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<jsp:include page="result.jsp" />

	<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formul�rio de acesso</h1>
			<p class="lead">Preencha o formul�rio com o usu�rio e senha</p>
			<hr class="my-4">

			<c:if test="${not empty errorMessge}">
				<div style="color: red; font-weight: bold; margin: 30px 0px;font-size: 15px;">${errorMessge}</div>
			</c:if>

			<form name='login' action="login" method='POST'>
				<table>
					<tr>
						<td>Usu�rio:</td>
						<td><input class="form-control" type='text' name='username'></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input class="form-control" type='password'
							name='password' /></td>
					</tr>
					<tr>
						<td colspan='2'><input class="btn" name="submit"
							type="submit" value="Entrar" /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

		</div>
	</div>
</body>
</html>