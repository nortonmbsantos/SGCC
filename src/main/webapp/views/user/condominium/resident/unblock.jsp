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
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>


	<jsp:include page="../../navbar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">Bloquear morador ${resident.firstName}</h1>
						<p class="lead">${condominium.name}</p>
						<hr class="my-4">
						<form action="unblock" method="POST">
							<input type="hidden" name="id" value="${condominiumResident.id}">
							<input type="hidden" name="idCondominium" value="${condominiumResident.idCondominium}">
							<input type="hidden" name="idResident" value="${condominiumResident.idResident}">
							<button type="submit" class="btn btn-danger">Desbloquear</button>
						</form>						
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>