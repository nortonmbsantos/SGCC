<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<jsp:include page="../header.jsp" />
</head>
<body>
<jsp:include page="../navbar.jsp"/>

	<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formul�rio de acesso para moradores</h1>
			<p class="lead">Preencha o formul�rio com o login e senha</p>
			<hr class="my-4">
			<p>Campos marcados com * s�o considerados obrigat�rios</p>


			<form:form action="login" modelAttribute="resident">
				<div class="form-group">
					<label for="email">Usu�rio/Login</label>
					<form:input path="login" cssClass="form-control" id="email" />
				</div>
				<div class="form-group">
					<label for="password">Senha</label>
					<form:password path="password" cssClass="form-control"
						id="password" />
				</div>
				<small id="emailHelp" class="form-text text-muted">N�o
					compartilharemos suas informa��es com ningu�m.</small>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>

		</div>
	</div>
</body>
</html>