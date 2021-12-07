<%@page import="java.text.SimpleDateFormat"%>
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
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />


			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">Multas do morador
							${resident.resident.firstName}</h1>
						<hr class="my-4">
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath}/user/condominium/warning/new?id_resident=${resident.idResident}&id_condominium=${resident.idCondominium}">Nova
							Multa</a>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Data</th>
							<th scope="col">Descrição</th>
							<th scope="col">Valor</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${warnings}" var="warning">
							<tr>
								<td>
									<form action="warning/remove" method="POST" class="remove-warning" id="remove-warning-${warning.id}">
										<input type="hidden" name="id" value="${warning.id }">
									</form>
									
									 <a class="btn btn-light remove-warning-button"
									data-id="${warning.id}" data-desc="${warning.description }"
									title="Bloquear morador"><i class="fas fa-minus-circle"></i></a>
								</td>
								<td>${format.format(warning.warningDate) }</td>
								<td>${warning.description }</td>
								<td><c:choose>
										<c:when test="${warning.value > 0}">
											R$ ${warning.value}
										</c:when>
										<c:otherwise>
											<span class="badge badge-danger">Advertência</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</main>
		</div>
	</div>
		<jsp:include page="../../../result.jsp"></jsp:include>
</body>
</html>