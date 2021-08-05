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
			<form:form action="register" modelsAttribute="user">
				<div class="row">
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="userName">Usuário*</label>
							</div>
							<form:input path="userName" cssClass="form-control" id="userName" />
							<form:errors path="userName" cssStyle="color: #ff0000;" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="firstName">Primeiro Nome*</label>
							</div>
							<form:input path="firstName" cssClass="form-control"
								id="firstName" />
							<form:errors path="firstName" cssStyle="color: #ff0000;" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="firstName">Email*</label>
							</div>
							<form:input path="email" cssClass="form-control" id="email" />
							<form:errors path="email" cssStyle="color: #ff0000;" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="password">Senha*</label>
							</div>
							<form:password path="password" cssClass="form-control"
								id="password" />
							<form:errors path="password" cssStyle="color: #ff0000;" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="confirmPassword">Confirma Senha*</label>
							</div>
							<form:password path="confirmPassword" cssClass="form-control"
								id="confirmPassword" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label>Eu sou: *</label>
							</div>
							<label> 
							<form:radiobutton path="roles" value="ROLE_USER" />
								Síndico
							</label>
							 <label class="ml-3"> 
							 <form:radiobutton path="roles"
									value="ROLE_RESIDENT" /> Morador
							</label>
						</div>
						<form:errors path="roles" cssStyle="color: #ff0000;" />

					</div>
					<div class="col-12 mt-3">
						<div class="input-group">
							<button type="submit" class="btn btn-primary float-rigth">Cadastrar</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>

	<jsp:include page="result.jsp" />
</body>
</html>