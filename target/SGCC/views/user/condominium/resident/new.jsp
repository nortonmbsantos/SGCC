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
						<h1 class="display-4">Formulário de cadastro de morador para
							o condomínio ${condominium.name }</h1>
						<p class="lead">Preencha o campo com o usuário do morador</p>
						<hr class="my-4">
						<form action="add" method="POST">
							<input type="text" name="residentUserName">
							<input type="hidden" name="idCondominium" value="${condominium.id}">
							<input type="submit">
						</form>
						
					</div>
				</div>
			</main>
		</div>
	</div>
	
	<jsp:include page="../../../result.jsp"></jsp:include>
</body>
</html>