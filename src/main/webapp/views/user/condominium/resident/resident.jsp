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
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>


	<jsp:include page="../../navbar.jsp" />
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<c:choose>
						<c:when test="${resident != null }">
							<div class="jumbotron">
								<h1 class="display-4">${resident.resident.firstName}</h1>
								<p class="lead">${condominium.name}</p>
								<hr class="my-4">
								<p class="lead">${resident.resident.email}</p>
							</div>
				</div>
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation"><a
						class="nav-link active" id="home-tab" data-toggle="tab"
						href="#home" role="tab" aria-controls="home" aria-selected="true">Multas
							e advertências</a></li>
					<li class="nav-item" role="presentation"><a class="nav-link"
						id="profile-tab" data-toggle="tab" href="#profile" role="tab"
						aria-controls="profile" aria-selected="false">Reservas de
							áreas comuns</a></li>
				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						<c:if test="${warnings.isEmpty() }">Este morador não possui multas ou advertências</c:if>
						<div class="mt-5 row">
							<c:forEach items="${warnings}" var="warning">
								<div class="mb-3 col-md-3 col-12 mr-1">
									<div class="card border-danger " style="max-width: 18rem;">
										<div class="card-header">
											<c:choose>
												<c:when test="${warning.value > 0}">R$ ${warning.value}</c:when>
												<c:otherwise>Advertência</c:otherwise>
											</c:choose>
										</div>
										<div class="card-body text-danger">
											<h5 class="card-title">
											<fmt:formatDate value="${warning.warningDate }" pattern="dd/MM/yyyy"/></h5>
											<p class="card-text">${warning.description }</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="tab-pane fade" id="profile" role="tabpanel"
						aria-labelledby="profile-tab">

						<c:if test="${bookigns == null}">Este morador não possui reservas </c:if>
					</div>

				</div>

				</c:when>
				<c:otherwise>
					<div class="jumbotron">
						<h1 class="display-4">Falha ao localizar morador</h1>
						<p class="lead">${condominium.name }</p>
						<hr class="my-4">
						<p class="lead">Morador não encontrado ou não pertencente a
							este condomínio</p>

					</div>
				</c:otherwise>
				</c:choose>


			</main>
		</div>
	</div>
</body>
</html>