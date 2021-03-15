<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="ISO-8859-1">
<title>Painel de controle</title>
<jsp:include page="../../../header.jsp" />
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
						<h1 class="display-4">Taxas do condomínio ${condominium.name}</h1>
						<hr class="my-4">
						<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/user/condominium/condominiumfee/new?id_condominium=${condominium.id}">Novo período</a>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Data fechamento</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${condominiumFees}" var="fee">
							<tr>
								<td>
								<a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/condominiumfee?idCondominiumFee=${fee.id}"><i
										class="fas fa-eye" title="Visualizar período"></i></a>
										<a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/fee/form?id_condominium_fee=${fee.id}&id_fee=0"><i
										class="fas fa-folder-plus" title="Adicionar taxa neste período"></i></a>
										</td>
								<td><fmt:formatDate value="${fee.closingDate}" pattern="dd/MM/yyyy"/></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</main>
		</div>
	</div>
</body>
</html>