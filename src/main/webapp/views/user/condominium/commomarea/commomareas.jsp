<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="paginacao" class="br.edu.utfpr.sgcc.config.Paginacao" />
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
						<h1 class="display-4">Areas comuns de ${condominium.name}</h1>

						<form action="commomareas" method="GET">

							<input type="text" name="filter" value="${filter}" placeholder="Buscar área comum"
								class="form-control col-6"> <input type="hidden"
								name="id_condominium" value="${condominium.id}">
							<button type="submit" class="btn btn-primary">Filtrar</button>
						</form>
						<form action="commomareas" method="GET">
							<input type="hidden" name="id_condominium"
								value="${condominium.id}">
							<button type="submit" class="btn btn-danger">Limpar
								Filtro</button>
						</form>
						<hr class="my-4">
						<a class="btn"
							href="${pageContext.request.contextPath}/user/condominium/commomarea/new?id_condominium=${condominium.id}">Nova
							Área Comum</a>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Nome</th>
							<th scope="col">Taxa de reserva</th>
							<th scope="col">Reservas pendentes</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${commomAreas}" var="area">
							<tr>
								<td><a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/commomarea?id=${area.id}"><i
										class="fas fa-eye"></i></a> <a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/commomarea/update?id=${area.id}"><i
										class="fas fa-edit"></i></a> <a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/commomarea/bookings?id_commom_area=${area.id}"><i
										class="fas fa-book-open"></i></a></td>
								<td>${area.name }</td>
								<td><fmt:formatNumber value="${area.bookingFee }"
										type="currency" currencyCode="BRL" /></td>
								<c:set var="count" value="0"></c:set>
								<c:forEach items="${pendingBookings}" var="p">
									<c:if test="${p.title == area.id}">
										<c:set var="count" value="${p.value}"></c:set>
									</c:if>
								</c:forEach>
								<td><fmt:formatNumber type="number" maxIntegerDigits="3"
										value="${count}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div>${paginacao.geraPaginacao(currentPage, totalPages, "commomareas", "page", map)}</div>
			</main>
		</div>
	</div>
</body>
</html>