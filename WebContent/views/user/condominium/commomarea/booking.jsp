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
<jsp:include page="../../../header.jsp" />
</head>
<body>
	<jsp:include page="../../navbar.jsp" />
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<div class="jumbotron">
						<h1 class="display-4">Formulário de confirmação de reserva
							para área comum ${commomArea.name }</h1>
						<hr class="my-4">
						<p>Campos marcados com * são considerados obrigatórios</p>
						<form:form action="accept" modelAttribute="booking">
							<form:hidden path="id" cssClass="form-control"
								value="${commomArea.id}" />
							<button type="submit" class="btn btn-primary">Aceitar</button>
						</form:form>
						<form:form action="refuse" modelAttribute="booking">
							<form:hidden path="id" cssClass="form-control"
								value="${commomArea.id}" />
							<button type="submit" class="btn btn-danger">Recusar</button>
						</form:form>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>