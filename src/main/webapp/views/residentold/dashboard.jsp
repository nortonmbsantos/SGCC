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

	<div class="d-flex justify-content-center mt-5">
		<div class="card" style="width: 800px;">
			<div class="card-body">
				<div class="container-fluid" style="max-width: 800px;">

					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item" role="presentation"><a
							class="nav-link active" id="fees-tab" data-toggle="tab"
							href="#fees" role="tab" aria-controls="fees" aria-selected="true">Meus
								condomínios</a></li>


						<li class="nav-item" role="presentation"><a class="nav-link"
							id="commom-area-tab" data-toggle="tab" href="#commom-area"
							role="tab" aria-controls="commom-area" aria-selected="false">Minhas
								reservas</a></li>


						<li class="nav-item" role="presentation"><a class="nav-link"
							id="warnings-tab" data-toggle="tab" href="#warnings" role="tab"
							aria-controls="warnings" aria-selected="false"> Minhas multas
								e advertências </a></li>
					</ul>

								<a class="btn btn-primary mt-2"
									href="${pageContext.request.contextPath}/resident/condominium/entry/new">Solicitar
									entrada em novo condomínio</a>


					<div class="tab-content" id="myTabContent">
						<div class="tab-pane fade show active" id="fees" role="tabpanel"
							aria-labelledby="fee-tab">
							<c:forEach items="${condominiuns}" var="c">
								<div class="card mt-3 mb-3 mr-1" style="max-width: 18rem;">
									<div class="card-header">${c.name }</div>
									<div class="card-body text-danger">
										<h5 class="card-title">${c.description}</h5>
										<a class="btn btn-success text-white mt-1" href="${pageContext.request.contextPath}/resident/condominium/condominiumfees?idCondominium=${c.id}">Taxas</a> 
										<a class="btn btn-success text-white mt-1" href="${pageContext.request.contextPath}/resident/condominium/booking/new?idCondominium=${c.id}">Reservar área comum</a>
									</div>
								</div>
							</c:forEach>
							<div class="row mt-3">

							</div>
						</div>
						<div class="tab-pane fade  mt-3" id="commom-area" role="tabpanel"
							aria-labelledby="commom-area-tab">
							<c:forEach items="${bookings}" var="booking">
								<div class="card border-danger mb-3 mr-1"
									style="max-width: 18rem;">
									<div class="card-header">
										${booking.commomarea_name }
									</div>
									<div class="card-body text-danger">
										<h5 class="card-title">
											<fmt:formatDate pattern="dd/MM/yyyy" value="${booking.booking_date }" />
										</h5>
										<c:choose>
											<c:when test="${booking.status }"><span class="badge badge-success">Aceito</span></c:when>
											<c:when test="${booking.status_date == null }"><span class="badge badge-secondary">Não respondido</span></c:when>
											<c:otherwise><span class="badge badge-danger">Recusado</span></c:otherwise>
										</c:choose>
										
									</div>
								</div>
							</c:forEach>
						</div>

						<div class="tab-pane fade mt-3" id="warnings" role="tabpanel"
							aria-labelledby="warnings-tab">
							<div class="mt-5 row">
								<c:forEach items="${warnings}" var="warning">
									<div class="card border-danger mb-3 mr-1"
										style="max-width: 18rem;">
										<div class="card-header">
											<c:choose>
												<c:when test="${warning.value > 0}">R$ ${warning.value}</c:when>
												<c:otherwise>Advertência</c:otherwise>
											</c:choose>
										</div>
										<div class="card-body text-danger">
											<h5 class="card-title">
												${format.format(warning.warningDate.time)}</h5>
											<p class="card-text">${warning.description }</p>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../result.jsp"></jsp:include>
</body>
</html>