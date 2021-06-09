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
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="../sidebar.jsp" />

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">${condominium.name}</h1>
						<p class="lead">${condominium.description}</p>
						<hr class="my-4">
						<p>�ltimo condom�nio: R$ ${reportLastFeeValue.value} -
							(Fechamento: ${reportLastFeeValue.title})</p>
						<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/condominiumfees?id_condominium=${condominium.id}"
							role="button">Per�odos de taxas</a> <a
							class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/residents?id_condominium=${condominium.id}"
							role="button">Moradores</a> <a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/commomareas?id_condominium=${condominium.id}"
							role="button">�reas Comuns</a> <a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/entries?idcondominium=${condominium.id}"
							role="button">Solicita��es de entrada</a> <a
							class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/update?id=${condominium.id}"
							role="button">Editar condom�nio</a>


					</div>
				</div>
				<div class="row mt-3">
					<div class="col-12 col-md-6">
						<div class="card">
							<div class="card-header">Varia��o dos per�odos</div>
							<div class="card-body">
								<canvas style="" id="reportByClosingDateChart"></canvas>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-6">
						<div class="card">
							<div class="card-header">M�dia por tipo de taxa</div>
							<div class="card-body">
								<table class="table">
									<thead class="thead-dark">
										<tr>
											<th scope="col">Taxa</th>
											<th scope="col">Valor m�dio</th>
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
					<div class="col-12">
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
			// The type of chart we want to create
			type : 'line',
			// The data for our dataset
			data : {

				labels : [<c:forEach items="${reportByClosingDate}" var="report">
				'${report.title}',
				</c:forEach>],
				datasets : [ {
					label : 'Valor de condom�nio pela data de vencimento',
					borderColor : 'rgb(255, 99, 132)',
					data : [ <c:forEach items="${reportByClosingDate}" var="report">
					${report.value },
					</c:forEach> ]
				} ]
			},

			// Configuration options go here
			options : {}
		});
		var reportByFeeTypeChart = document.getElementById('reportByFeeTypeChart').getContext('2d');
		var chart = new Chart(reportByFeeTypeChart, {
			// The type of chart we want to create
			type : 'bar',
			// The data for our dataset
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

			// Configuration options go here
			options : {}
		});
	</script>
</body>
</html>