<%@page import="java.util.Random"%>
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
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="jumbotron">
						<h1 class="display-4">${condominium.name}</h1>
						<p class="lead">
							Fechamento:
							<fmt:formatDate value="${condominiumFee.closingDate}"
								pattern="dd/MM/yyyy" />
						</p>
						<hr class="my-4">
						<c:choose>
							<c:when test="${!condominiumFee.finished }">
								<a class="btn btn-primary"
									href="${pageContext.request.contextPath}/user/condominium/fee/form?id_condominium_fee=${condominiumFee.id}&id_fee=0">Nova
									taxa</a>
								<a class="btn btn-primary"
									href="${pageContext.request.contextPath}/user/condominium/condominiumfee/closing?id_condominium_fee=${condominiumFee.id}">Fechar
									período</a>
							</c:when>
							<c:otherwise>
								<span class="badge badge-info">Período fechado</span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<c:if test="${!condominiumFee.finished }">
								<th scope="col">Editar</th>
							</c:if>
							<th scope="col">Descrição</th>
							<th scope="col">Valor</th>
							<th scope="col">Data de vencimento</th>
							<th scope="col">Data de pagamento</th>
							<th scope="col">Parcelas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fees}" var="fee">
							<tr>
								<c:if test="${!condominiumFee.finished }">
									<td><a class="btn btn-light"
										href="${pageContext.request.contextPath}/user/condominium/fee/form?id_condominium_fee=${fee.idCondominiumFee}&id_fee=${fee.id}"><i
											class="fas fa-edit"></i></a></td>
								</c:if>
								<td>${fee.description }</td>
								<td><fmt:formatNumber value="${fee.value }" type="currency" /></td>
								<td><fmt:formatDate value="${fee.dueDate}"
										pattern="dd/MM/yyyy" /></td>
								<td><c:choose>
										<c:when test="${null != fee.payDate}">
											<fmt:formatDate value="${fee.payDate}" pattern="dd/MM/yyyy" />
										</c:when>
										<c:otherwise>Falta pagamento</c:otherwise>
									</c:choose></td>
								<td>${fee.currentInstallment}/${fee.installments}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row mt-3">
					<div class="col-6">
						<div class="card">
							<div class="card-header">Médias do
								condomínio</div>
							<div class="card-body">
								<table class="table">
									<thead class="thead-dark">
										<tr>
											<th scope="col">Taxa</th>
											<th scope="col">Valor</th>
											<th scope="col">Descrição</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${reportsCondominiumFeeType}" var="report">
											<tr>
												<td>${report.title }</td>
												<td><fmt:formatNumber value="${report.value }"
														type="currency" /></td>
												<td>${report.description }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>


							</div>
						</div>
					</div>
					<div class="col-6">
						<div class="card">
							<div class="card-header">Valor por tipo</div>
							<div class="card-body">
								<canvas style="" id="reportSumByFeeType"></canvas>
							</div>
						</div>
					</div>
				</div>

			</main>
		</div>
	</div>
	<script>
		var reportSumByFeeType = document.getElementById('reportSumByFeeType').getContext('2d');
		var chart = new Chart(reportSumByFeeType, {
			// The type of chart we want to create
			type : 'pie',
			// The data for our dataset
			data : {

				labels : [<c:forEach items="${reportSumByFeeType}" var="report">
				'${report.title}',
				</c:forEach>],
				datasets : [ {
					backgroundColor: [<c:forEach items="${reportSumByFeeType}" var="report">
					"rgb(<%=new Random().nextInt(255)%>, <%=new Random().nextInt(255)%>, <%=new Random().nextInt(255)%>)",
					</c:forEach>],
					data : [ <c:forEach items="${reportSumByFeeType}" var="report">
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