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
			<h1 class="display-4">Esqueci minha senha</h1>
			<p class="lead">Preencha o formulário com o seu usuário</p>
			<p class="lead">Será enviado ao seu email um link para recuperação de senha</p>
			<hr class="my-4">

			<c:if test="${not empty errorMessge}">
				<div style="color: red; font-weight: bold; margin: 30px 0px;font-size: 15px;">${errorMessge}</div>
			</c:if>
			<form name='password' action="password" method='POST'>
						<input class="form-control" type='hidden' name='confirmation'>
				<table>
					<tr>
						<td>Nova senha:</td>
						<td><input class="form-control" type='password' name='password'></td>
					</tr>
					<tr>
						<td>Confirme nova senha:</td>
						<td><input class="form-control" type='password' name='confirmPassword'></td>
					</tr>
					<tr>
						<td colspan='2'><input class="btn" name="submit"
							type="submit" value="Enviar" /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
</body>
</html>