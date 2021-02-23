<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="ISO-8859-1">
<title>Painel de controle</title>
<jsp:include page="../../header.jsp" />
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="../sidebar.jsp" />


			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<div class="jumbotron">
			<h1 class="display-4">Formulário de cadastro para condomínio</h1>
			<p class="lead">Preencha o formulário com os dados</p>
			<hr class="my-4">
			<p>Campos marcados com * são considerados obrigatórios</p>
			<form:form action="update" modelAttribute="condominium">
			<form:hidden path="idUser" cssClass="form-control" value="${user.id}"/>
			<form:hidden path="id" cssClass="form-control" value="${condominium.id}"/>
				<div class="form-group">
					<label for="email">Nome*</label>
					<form:input path="name" cssClass="form-control" id="name" value="${condominium.name }"/>
					<form:errors path="name" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="description">Descrição*</label>
					<form:input path="description" cssClass="form-control"
						id="description" value="${condominium.description }"/>
					<form:errors path="description" cssStyle="color: #ff0000;" />
				</div>
				<button type="submit" class="btn btn-primary">Atualizar</button>
			</form:form>
		</div>
	
				</div>
			</main>
		</div>
	</div>
</body>
</html>