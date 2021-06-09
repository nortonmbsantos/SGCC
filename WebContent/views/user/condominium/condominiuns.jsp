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
				
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">Condom�nios</h1>
						<p class="lead">Sua lista de condom�nios</p>
						<hr class="my-4">
						<p></p>
						<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/new"
							role="button">Novo condom�nio</a> 
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Nome</th>
							<th scope="col">Descri��o</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${condominiuns}" var="condominium">
							<tr>
								<td>
								<a class="btn btn-light" title="Visualizar condom�nio" href="${pageContext.request.contextPath}/user/condominium?id=${condominium.id}"><i class="fas fa-eye"></i></a>
								<a class="btn btn-light" title="Lista de moradores" href="${pageContext.request.contextPath}/user/condominium/residents?id_condominium=${condominium.id}"><i class="fas fa-user"></i></a>
								<a class="btn btn-light" title="Lista de moradores" href="${pageContext.request.contextPath}/user/condominium/update?id=${condominium.id}"><i class="fas fa-edit"></i></a>
								</td>
								<td>${condominium.name }</td>
								<td>${condominium.description }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</main>
		</div>
	</div>
</body>
</html>