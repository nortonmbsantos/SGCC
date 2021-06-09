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
<title>Painel de controle do morador</title>
<jsp:include page="../header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container-fluid pt-5">
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

				<div class="jumbotron">
					<h1 class="display-4">Taxas do condomínio ${condominium.name}</h1>
					<hr class="my-4">

				</div>
			</div>
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Descrição</th>
						<th scope="col">Valor</th>
						<th scope="col">Data de vencimento</th>
						<th scope="col">Data de pagamento</th>
						<th scope="col">Parcelas</th>
					</tr>
				</thead>
				<tbody>
					<fmt:setLocale value="pt_BR" />
					<c:forEach items="${fees}" var="fee">
						<tr>
							<td>${fee.description } <span class="badge bg-primary text-white">Taxa</span></td>
							<td><fmt:formatNumber value="${fee.value}" type="currency" /></td>
							<td><fmt:formatDate value="${fee.dueDate}" pattern="dd/MM/yyyy"/></td>
							<td><c:choose>
									<c:when test="${null != fee.payDate}"><fmt:formatDate value="${fee.payDate}" pattern="dd/MM/yyyy"/></c:when>
									<c:otherwise>Falta pagamento</c:otherwise>
								</c:choose></td>
							<td>${fee.currentInstallment}/${fee.installments}</td>
						</tr>
					</c:forEach>
					<c:forEach items="${warnings}" var="w">
						<tr>
							<td>${w.description } <span class="badge bg-danger text-white">Multa</span></td>
							<td><fmt:formatNumber value="${w.value}" type="currency" /></td>
							<td><fmt:formatDate value="${w.warningDate}" pattern="dd/MM/yyyy"/></td>
							<td></td>
							<td>1</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</main>
	</div>

</body>
</html>