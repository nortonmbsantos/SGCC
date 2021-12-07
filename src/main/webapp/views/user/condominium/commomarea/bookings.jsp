<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<h1 class="display-4">Reservas pendentes em
							${commomArea.name}</h1>
						<hr class="my-4">
						<h6>${condominium.name}</h6>

					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Aceitar/Recusar</th>
							<th scope="col">Data</th>
							<th scope="col">Morador</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pending}" var="p">
							<tr>
								<td>
									<div class="row" style="margin-left: 10px;">
										<form action="booking/accept" id="accept-booking-${p.id }" method="POST">
											<input type="hidden" name="id" value="${p.id}">
										</form>
										<form action="booking/refuse" id="refuse-booking-${p.id }" method="POST">
											<input type="hidden" name="id" value="${p.id}">
										</form>

										<a class="btn btn-success text-light accept-booking-button" data-id="${p.id}" data-name="${p.residentName}" data-date="<fmt:formatDate value="${p.bookingDate }"
										pattern="dd/MM/yyyy" />"> <i class="fas fa-check"
											title="Aceitar"></i>
										</a> 
										<a class="btn btn-danger text-light refuse-booking-button" data-id="${p.id}" data-name="${p.residentName}" data-date="<fmt:formatDate value="${p.bookingDate }"
										pattern="dd/MM/yyyy" />"> <i
											class="fas fa-times" title="Recusar"></i>
										</a>
									</div>
								</td>
								<td><fmt:formatDate value="${p.bookingDate }"
										pattern="dd/MM/yyyy" /></td>
								<td>${p.residentName }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">Reservas aceitas em
							${commomArea.name}</h1>
						<hr class="my-4">
						<h6>${condominium.name}</h6>

					</div>
				</div>
				<hr>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Cancelar</th>
							<th scope="col">Data</th>
							<th scope="col">Morador</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${accepted}" var="p">
							<tr>
								<td>
									<div class="row" style="margin-left: 10px;">
										<form action="booking/refuse" method="POST">
											<input type="hidden" name="id" value="${p.id}">
											<button class="btn btn-danger">
												<i class="fas fa-times" title="Recusar"></i>
											</button>
										</form>
									</div>
								</td>
								<td><fmt:formatDate value="${p.bookingDate }"
										pattern="dd/MM/yyyy" /></td>
								<td>${p.residentName }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</main>
		</div>
	</div>
	<jsp:include page="../../../result.jsp" />
</body>
</html>