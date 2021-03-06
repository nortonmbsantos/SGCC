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

					<div class="jumbotron">
						<h1 class="display-4">${condominium.name} : ${commomarea.name }</h1>
						<p class="lead">Taxa de reserva: <fmt:formatNumber value="${commomarea.bookingFee}" type="currency" currencyCode="pt-BR" /> </p>
						<hr class="my-4">
						 
						  <a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/user/condominium/condominiumfees?id_condominium=${condominium.id}"
							role="button">Reservas passadas</a> 
					</div>
				</div>
				<!--
				Tabela de proximas reservas para aceite/recusas
				 <table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Descri��o</th>
							<th scope="col">Valor</th>
							<th scope="col">Data de vencimento</th>
							<th scope="col">Data de pagamento</th>
							<th scope="col">Parcelas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fees}" var="fee">
							<tr>
								<td><a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/fee/fee?id=${fee.id}"><i
										class="fas fa-eye"></i></a></td>
								<td>${fee.description }</td>
								<td>${fee.value }</td>
								<td>${format.format(fee.dueDate.time)}</td>
								<td><c:choose>
										<c:when test="${null != fee.payDate}">${format.format(fee.payDate.time)}</c:when>
										<c:otherwise>Falta pagamento</c:otherwise>
									</c:choose></td>
								<td>${fee.currentInstallment}/${fee.installments}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>-->
				<!--
				Tabela de proximas reservas j� aceitas
				 <table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Descri��o</th>
							<th scope="col">Valor</th>
							<th scope="col">Data de vencimento</th>
							<th scope="col">Data de pagamento</th>
							<th scope="col">Parcelas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fees}" var="fee">
							<tr>
								<td><a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/fee/fee?id=${fee.id}"><i
										class="fas fa-eye"></i></a></td>
								<td>${fee.description }</td>
								<td>${fee.value }</td>
								<td>${format.format(fee.dueDate.time)}</td>
								<td><c:choose>
										<c:when test="${null != fee.payDate}">${format.format(fee.payDate.time)}</c:when>
										<c:otherwise>Falta pagamento</c:otherwise>
									</c:choose></td>
								<td>${fee.currentInstallment}/${fee.installments}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table> -->
			</main>
		</div>
	</div>

</body>
</html>