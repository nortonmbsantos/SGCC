<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<fmt:setLocale value="pt-BR"/>
<fmt:setTimeZone value="GMT-0"/>
	<jsp:include page="../../navbar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />


			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					
					<div class="jumbotron">
						<h1 class="display-4">Taxas do condomínio ${condominium.name}</h1>
						<hr class="my-4">
						<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/user/condominium/condominiumfee/form?id_condominium=${condominium.id}&id=0">Novo período</a>
					</div>
				</div>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col"></th>
							<th scope="col">Data fechamento</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${condominiumFees}" var="fee">
							<tr>
								<td>
								<a class="btn btn-light" title="Visualizar período"
									href="${pageContext.request.contextPath}/user/condominium/condominiumfee?idCondominiumFee=${fee.id}"><i
										class="fas fa-eye" ></i></a>
										<c:if test="${!fee.finished }"><a class="btn btn-light" title="Adicionar taxa neste período"
									href="${pageContext.request.contextPath}/user/condominium/fee/form?id_condominium_fee=${fee.id}&id_fee=0"><i
										class="fas fa-folder-plus" ></i></a></c:if>
										</td>
								<td><fmt:formatDate value="${fee.closingDate}" pattern="dd/MM/yyyy"/> </td>
								<td>
								<c:choose >
								<c:when test="${fee.finished }"><span class="badge badge-info">Fechado</span></c:when>
								<c:otherwise><span class="badge badge-secondary">Aberto</span></c:otherwise>
								</c:choose>
								
								
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>

<div> ${paginacao.geraPaginacao(currentPage, totalPages, "condominiumfees", "page", map)}</div>

			</main>
		</div>
	</div>
	
	<jsp:include page="../../../result.jsp"></jsp:include>
</body>
</html>