<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<h1 class="display-4">Solicitações de entrada em
							${condominium.name}</h1>
						<hr class="my-4">
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Aceitar/Recusar</th>
							<th scope="col">Solicitante</th>
							<th scope="col">Data de solicitação</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${entries}" var="e">
							<tr>
								<td>
									<div class="row" style="margin-left: 10px;">
									<form action="accept" method="POST">
										<input type="hidden" name="id" value="${e.id}">
										<button class="btn btn-success">
											<i class="fas fa-check" title="Aceitar"></i>
										</button>
									</form>

									<form action="refuse" method="POST">
										<input type="hidden" name="id" value="${e.id}">
										<button class="btn btn-danger">
											<i class="fas fa-times" title="Recusar"></i>
										</button>
									</form>
									</div>
								</td>
								<td>${e.user.firstName }</td>
								<td><fmt:formatDate value="${e.requestDate }"
										pattern="dd/MM/yyyy" /></td>
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