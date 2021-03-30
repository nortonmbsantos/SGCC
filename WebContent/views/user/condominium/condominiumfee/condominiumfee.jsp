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
						<c:otherwise><span class="badge badge-info">Período fechado</span></c:otherwise>
						</c:choose>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
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
								<td><a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/fee?id_fee=${fee.id}"><i
										class="fas fa-eye"></i></a> <c:if
										test="${!condominiumFee.finished }">
										<a class="btn btn-light"
											href="${pageContext.request.contextPath}/user/condominium/fee/form?id_condominium_fee=${fee.idCondominiumFee}&id_fee=${fee.id}"><i
											class="fas fa-edit"></i></a>
									</c:if></td>
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
			</main>
		</div>
	</div>

</body>
</html>