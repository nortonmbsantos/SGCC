<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="paginacao" class="br.edu.utfpr.sgcc.config.Paginacao"/>
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
						<h1 class="display-4">Moradores do condomínio
							${condominium.name}</h1>
						<hr class="my-4">
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath}/user/condominium/resident/new?idCondominium=${condominium.id}">Novo
							Morador</a>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Visualizar</th>
							<th scope="col">Nome</th>

							<th scope="col">Ativo</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${residents}" var="resident">
							<tr>
								<td><a class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/resident?id_resident=${resident.idResident}"
									title="Visualizar Morador"><i class="fas fa-eye"></i></a> <a
									class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/warnings?id_resident=${resident.idResident}"
									title="Multas do morador"><i class="fas fa-list"></i></a> <a
									class="btn btn-light"
									href="${pageContext.request.contextPath}/user/condominium/warning/new?id_resident=${resident.idResident}&id_condominium=${condominium.id}"
									title="Multar morador"><i class="fas fa-angry"></i></a> <c:choose>
										<c:when test="${resident.active == true}">

											<a class="btn btn-light"
												href="${pageContext.request.contextPath}/user/condominium/resident/block?id_resident=${resident.idResident}&id_condominium=${resident.idCondominium}"
												title="Bloquear morador"><i class="fas fa-minus-circle"></i></a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-light"
												href="${pageContext.request.contextPath}/user/condominium/resident/unblock?id_resident=${resident.idResident}&id_condominium=${resident.idCondominium}"
												title="Desbloquear morador"><i class="fas fa-plus-circle"></i></a>
										</c:otherwise>
									</c:choose></td>
								<td>${resident.resident.firstName }</td>
								<td><c:choose>
										<c:when test="${resident.active}">
											<span class="badge badge-success">Ativo</span>
										</c:when>
										<c:otherwise>
											<span class="badge badge-danger">Inativo</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

<div> ${paginacao.geraPaginacao(currentPage, totalPages, "residents", "page", map)}</div>

			</main>
		</div>
	</div>
	<jsp:include page="../../../result.jsp"></jsp:include>
</body>
</html>