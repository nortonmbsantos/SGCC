<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formulário de cadastro</h1>
			<p class="lead">Preencha o formulário com os dados</p>
			<hr class="my-4">
			<p>Campos marcados com * são considerados obrigatórios</p>
			<form:form action="register" modelAttribute="user">
				<div class="form-group">
					<label for="userName">Usuário*</label>
					<form:input path="userName" cssClass="form-control" id="userName" />
					<form:errors path="userName" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="firstName">Primeiro Nome*</label>
					<form:input path="firstName" cssClass="form-control" id="firstName" />
					<form:errors path="firstName" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="firstName">Email*</label>
					<form:input path="email" cssClass="form-control" id="email" />
					<form:errors path="email" cssStyle="color: #ff0000;" />
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
				
				<div class="form-group"><label>Eu sou: </label>
					<label> <form:radiobutton path="roles"
							value="ROLE_USER" /> Síndico 
					</label>
					<label> <form:radiobutton path="roles"
							value="ROLE_RESIDENT" /> Morador
					</label>
					<form:errors path="roles" cssStyle="color: #ff0000;" />
				</div>				
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Cadastrar</button>
				</div>
			</form:form>
		</div>
	</div>
	
	<jsp:include page="result.jsp" />
</body>
</html>