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
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<div class="jumbotron">
						<h1 class="display-4">Confirmar fechamento de per�odo <fmt:formatDate value="${condominiumFee.closingDate }"  pattern="dd/MM/yyyy"/></h1>
						<p class="lead">Ao fechar o per�odo n�o ser� mais poss�vel
							adicionar taxas e nem multas � este mesmo per�odo</p>
						<hr class="my-4">
						<form:form action="closing" modelAttribute="condominiumFee">
							<form:hidden path="id" value="${condominiumFee.id}" />
							<button type="submit" class="btn btn-primary">Fechar
								condom�nio</button>
						</form:form>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>