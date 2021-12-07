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
<jsp:include page="../../header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>


	<jsp:include page="../navbar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../sidebar.jsp" />

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">${condominium.name}</h1>
						<p class="lead">${condominium.description}</p>
						<p class="lead">
							Código de acesso: <b>${condominium.code}</b>
						</p>
						<hr class="my-4">
						<p>Último condomínio: R$ ${reportLastFeeValue.value} -
							(Fechamento: ${reportLastFeeValue.title})</p>
						<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/condominiumfees?id_condominium=${condominium.id}"
							role="button">Períodos de taxas</a> <a
							class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/residents?id_condominium=${condominium.id}"
							role="button">Moradores</a> <a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/commomareas?id_condominium=${condominium.id}"
							role="button">Áreas Comuns</a> <a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/entries?idcondominium=${condominium.id}"
							role="button">Solicitações de entrada</a> <a
							class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/update?id=${condominium.id}"
							role="button">Editar condomínio</a>
					</div>
				</div>
				<c:if test="${acceptedBookings.size() > 0 }">
					<div class="row mt-3">
						<div class="col-12">
							Próxima(s) ${acceptedBookings.size() } reserva(s) de área comum
							<div class="row mt-3">
								<c:forEach items="${acceptedBookings }" var="booking">
									<div class="col-3">
										<div class="card" style="width: 18rem;">
											<div class="card-body">
												<h5 class="card-title">
													<fmt:formatDate value="${booking.bookingDate}"
														pattern="dd/MM/yyyy" />
												</h5>
												<h6 class="card-subtitle mb-2 text-muted">${booking.commomAreaName }</h6>
												<p class="card-text">${booking.residentName }</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<hr class="my-4">
						<div class="col-12"></div>
					</div>
				</c:if>
				<c:if test="${feesDueDate.size() > 0 }">
					<div class="row mt-3">
						<div class="col-12">
							Próximo(s) ${feesDueDate.size() } vencimento(s)
							<div class="row mt-3">
								<c:forEach items="${feesDueDate }" var="fee">
									<div class="col-3">
										<div class="card" style="width: 18rem;">
											<div class="card-body">
												<h5 class="card-title">
													<fmt:formatDate value="${fee.dueDate}"
														pattern="dd/MM/yyyy" />
												</h5>
												<h6 class="card-subtitle mb-2 text-muted">${fee.description }</h6>
												<p class="card-text">${booking.value }</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<hr class="my-4">
						<div class="col-12"></div>
					</div>
				</c:if>
				<div class="row mt-3">
					<div class="col-12 col-md-6">
						<div class="card">
							<div class="card-header">Variação dos períodos</div>
							<div class="card-body">
								<canvas style="" id="reportByClosingDateChart"></canvas>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-6">
						<div class="card">
							<div class="card-header">Média por tipo de taxa</div>
							<div class="card-body">
								<table class="table">
									<thead class="thead-dark">
										<tr>
											<th scope="col">Taxa</th>
											<th scope="col">Valor médio</th>
											<th scope="col">Quantidade</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${reportAverageFeeType}" var="report">
											<tr>
												<td>${report.title }</td>
												<td><fmt:formatNumber value="${report.value }"
														type="currency" /></td>
												<td>${report.quantity }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-12 col-md-6">
						<div class="card">
							<div class="card-header">Valor total por tipo</div>
							<div class="card-body">
								<canvas style="" id="reportByFeeTypeChart"></canvas>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script>
		var reportByClosingDateChart = document.getElementById('reportByClosingDateChart').getContext('2d');
		var chart = new Chart(reportByClosingDateChart, {
			
			type : 'line',
			
			data : {

				labels : [<c:forEach items="${reportByClosingDate}" var="report">
				'${report.title}',
				</c:forEach>],
				datasets : [ {
					label : 'Valor de condomínio pela data de vencimento',
					borderColor : 'rgb(255, 99, 132)',
					data : [ <c:forEach items="${reportByClosingDate}" var="report">
					${report.value },
					</c:forEach> ]
				} ]
			},

			
			options : {}
		});
		var reportByFeeTypeChart = document.getElementById('reportByFeeTypeChart').getContext('2d');
		var chart = new Chart(reportByFeeTypeChart, {
			
			type : 'bar',
			
			data : {

				labels : [<c:forEach items="${reportByFeeType}" var="report">
				'${report.title}',
				</c:forEach>],
				datasets : [ {
					label : 'Valor de taxas pelo tipo de taxa',
					borderColor : 'rgb(255, 99, 132)',
					backgroundColor : 'rgb(255, 99, 132)',
					data : [ <c:forEach items="${reportByFeeType}" var="report">
					${report.value },
					</c:forEach> ]
				} ]
			},

			
			options : {}
		});
	</script>
		<jsp:include page="../../result.jsp"></jsp:include>
</body>
</html>