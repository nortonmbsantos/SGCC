<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<form:form action="add" modelAttribute="bookingRequest">
				<div class="row">
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="firstName">Data solicitada*</label>
							</div>
							<form:input type="date" path="bookingDate"
								cssClass="form-control" id="booking_date" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="firstName">Área comum*</label>
							</div>
							<form:select cssClass="form-control" path="idCommomArea"
								id="id_commom_area">
								<c:forEach items="${commomareas}" var="area">
									<form:option value="${area.id}">${area.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<hr class="my-4">
						<label for="firstName">Convidados</label>
					</div>
				</div>
				<div id="guestlist" class="row">
					<c:forEach begin="0" end="1" varStatus="s">
						<div class="col-12 col-md-4 col-lg-4">
							<div class="input-group">
								<div class="col-12">
									<label for="firstName">Nome</label>
								</div>
								<form:input path="guests[${s.index}].name" class="form-control" />
							</div>
						</div>
						<div class="col-12 col-md-4 col-lg-4">
							<div class="input-group">
								<div class="col-12">
									<label for="firstName">Cpf</label>
								</div>
								<form:input path="guests[${s.index}].cpf" class="form-control" />
							</div>
						</div>
						<div class="col-12 col-md-4 col-lg-4">
							<div class="input-group">
								<div class="col-12">
									<label for="firstName">Telefone</label>
								</div>
								<form:input path="guests[${s.index}].phone" class="form-control" />
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="col-12">
					<button id="addGuest" type="button" class="btn btn-success">
						<i class="fa fa-plus"></i>
					</button>
				</div>
				<div class="col-12">
					<button type="submit" class="btn btn-primary mt-3">Cadastrar</button>
				</div>
			</form:form>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/guests.js"></script>
</body>
</html>