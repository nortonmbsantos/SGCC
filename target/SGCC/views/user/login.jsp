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
			<h1 class="display-4">Formulário de acesso para síndicos</h1>
			<p class="lead">Preencha o formulário com o login e senha</p>
			<hr class="my-4">
			<p>Campos marcados com * são considerados obrigatórios</p>


			<form:form action="login" modelsAttribute="admin">
				<div class="form-group">
					<label for="email">Usuário/E-mail</label>
					<form:input path="email" cssClass="form-control" id="email" />
				</div>
				<div class="form-group">
					<label for="password">Senha</label>
					<form:password path="password" cssClass="form-control"
						id="password" />
				</div>
				<small id="emailHelp" class="form-text text-muted">Não
					compartilharemos suas informações com ninguém.</small>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>


		</div>
	</div>
</body>
</html>