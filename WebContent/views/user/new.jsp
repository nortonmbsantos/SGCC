<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar Admin</title>
<jsp:include page="../header.jsp" />
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formulário de cadastro para síndicos</h1>
			<p class="lead">Preencha o formulário com os dados</p>
			<hr class="my-4">
			<p>Campos marcados com * são considerados obrigatórios</p>
			<form:form action="add" modelAttribute="admin">
				<div class="form-group">
					<label for="email">E-mail*</label>
					<form:input path="email" cssClass="form-control" id="email" />
					<form:errors path="email" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="firstName">Primeiro Nome*</label>
					<form:input path="firstName" cssClass="form-control" id="firstName" />
					<form:errors path="firstName" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="lastName">Sobrenome*</label>
					<form:input path="lastName" cssClass="form-control" id="lastName" />
					<form:errors path="lastName" cssStyle="color: #ff0000;" />
				</div>
				<!-- <div class="form-group">
					<label for="birthdate">Data de nascimento</label>
					<form:input path="birthdate" cssClass="form-control" id="birthdate" />
				</div>-->
				<div class="form-group">
					<label for="document">cpf/cnpj</label>
					<form:input path="document" cssClass="form-control" id="document" />
					<form:errors path="document" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="password">Senha*</label>
					<form:password path="password" cssClass="form-control"
						id="password" />
					<form:errors path="password" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="confirmPassword">Confirma Senha*</label>
					<form:password path="confirmPassword" cssClass="form-control"
						id="confirmPassword" />
				</div>
				<button type="submit" class="btn btn-primary">Cadastrar</button>
			</form:form>
		</div>
	</div>
</body>
</html>