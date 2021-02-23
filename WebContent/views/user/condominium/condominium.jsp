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
						<p>Último condomínio: R$ ${reportLastFeeValue.value} -
							(Fechamento: ${reportLastFeeValue.title})</p>
						 <a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/condominiumfees?id_condominium=${condominium.id}"
							role="button">Períodos de taxas</a>
							<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/residents?id_condominium=${condominium.id}"
							role="button">Moradores</a> 
							<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/commomareas?id_condominium=${condominium.id}"
							role="button">Áreas Comuns</a> 
							<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/entries?idcondominium=${condominium.id}"
							role="button">Solicitações de entrada</a>
							
							<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/update?id=${condominium.id}"
							role="button">Editar condomínio</a> 
							
							
					</div>
				</div>
				<div class="row">
					<div class="col-12 col-md-6">
						<canvas style="" id="reportByClosingDateChart"></canvas>
					</div class="col-12 col-md-6">
					<div>
						<canvas style="" id="reportByFeeTypeChart"></canvas>
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
					label : 'Valor de condomínio pela data de vencimento',
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