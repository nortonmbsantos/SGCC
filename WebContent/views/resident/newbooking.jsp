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
<jsp:include page="../header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formulário de solicitação de reserva de
				área comum</h1>
			<p class="lead">Preencha o formulário com os dados</p>
			<hr class="my-4">
			<p>Campos marcados com * são considerados obrigatórios</p>
			<form:form action="add" modelAttribute="booking">
				<form:hidden path="id_resident" cssClass="form-control"
					id="id_resident" value="${resident.id }" />
				<div class="form-group">
					<label for="firstName">Data solicitada*</label>
					<form:input type="date" path="booking_date" cssClass="form-control"
						id="booking_date" />
				</div>
				<label for="firstName">Área comum*</label>
				<form:select cssClass="form-control" path="id_commom_area" id="id_commom_area">
					<c:forEach items="${commomareas}" var="area">
						<form:option value="${area.id}">${area.name}</form:option>
					</c:forEach>
				</form:select>
				<button type="submit" class="btn btn-primary mt-3">Cadastrar</button>
			</form:form>
		</div>
	</div>
</body>
</html>