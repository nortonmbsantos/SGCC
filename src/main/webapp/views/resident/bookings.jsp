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
<jsp:include page="../header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="sidebar.jsp" />
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<c:choose>
							<c:when test="${condominium != null }">
								<h1 class="display-4">Reservas em ${condominium.name}</h1>
							</c:when>
							<c:otherwise>
								<h1 class="display-4">Minhas reservas</h1>

							</c:otherwise>
						</c:choose>

					</div>
				</div>

				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col"></th>
							<th scope="col">Local</th>
							<th scope="col">Data</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookings}" var="p">
							<tr>
								<td><a class="btn btn-light"
									href="${pageContext.request.contextPath}/resident/condominium/booking/new?idCondominium=${p.commomArea.condominium.id}&idBooking=${p.id}"><i
										class="fas fa-eye" title="Visualizar período"></i></a></td>
								<td><c:choose>
										<c:when test="${condominium != null }">
								${p.commomArea.name }
							</c:when>
										<c:otherwise>
								${p.commomArea.name } : ${p.commomArea.condominium.name }

							</c:otherwise>
									</c:choose></td>
								<td><fmt:formatDate value="${p.bookingDate }"
										pattern="dd/MM/yyyy" /></td>

								<td><c:choose>
										<c:when test="${p.statusDate == null}">
											<span class="badge badge-primary">Pendente</span>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${p.status}">
													<span class="badge badge-success">Aceito</span>

												</c:when>
												<c:otherwise>
													<span class="badge badge-danger">Recusado</span>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</main>
		</div>
	</div>
</body>
</html>