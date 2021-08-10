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
			<form:hidden path="idUser" cssClass="form-control" value="${condominium.idUser}"/>
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
				<div class="form-group">
					<label for="cep">CEP*</label>
					<form:input path="cep" cssClass="form-control"
						id="cep" />
					<form:errors path="cep" cssStyle="color: #ff0000;" />
					<button class="btn btn-small btn-secondary" type="button" id="findcep">Buscar cep</button>
				</div>
				<div class="form-group">
					<label for="cep">Rua</label>
					<form:input path="street" cssClass="form-control"
						id="street" />
					<form:errors path="street" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="cep">Número</label>
					<form:input path="streetNumber" cssClass="form-control"
						id="streetNumber" />
					<form:errors path="streetNumber" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="cep">Complemento</label>
					<form:input path="numberComplement" cssClass="form-control"
						id="numberComplement" />
					<form:errors path="numberComplement" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="cep">Bairro</label>
					<form:input path="neighborhood" cssClass="form-control"
						id="neighborhood" />
					<form:errors path="neighborhood" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="cep">Cidade</label>
					<form:input path="city" cssClass="form-control"
						id="city" />
					<form:errors path="city" cssStyle="color: #ff0000;" />
				</div>
				<div class="form-group">
					<label for="cep">Estado</label>
					<form:input path="state" cssClass="form-control"
						id="state" />
					<form:errors path="state" cssStyle="color: #ff0000;" />
				</div>
				
				
				<button type="submit" class="btn btn-primary">Atualizar</button>
			</form:form>
		</div>
	
				</div>
			</main>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/assets/js/findcep.js" ></script>
</body>
</html>