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
<jsp:include page="../header.jsp" />
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>


	<jsp:include page="navbar.jsp" />
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="sidebar.jsp" />

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

					<div class="container">
						<div class="jumbotron">
							<h1 class="display-4">Mudança de senha</h1>
							<p class="lead">Para atualizar preencha o formulário a senha
								nova</p>
							<hr class="my-4">
							<p>Campos marcados com * são considerados obrigatórios</p>
							<form:form action="updatepassword"
								modelAttribute="userUpdatePassword">

								<div class="row">
									<div class="col-12 col-md-12 col-lg-12">
										<div class="input-group">
											<div class="col-12">
												<label for="password">Senha atual <form:errors
														path="password" cssStyle="color: #ff0000;" />
												</label>
											</div>
											<form:password path="password" cssClass="form-control"
												id="password" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="newPassword">Nova Senha <form:errors
														path="newPassword" cssStyle="color: #ff0000;" />
												</label>
											</div>
											<form:password path="newPassword" cssClass="form-control"
												id="newPassword" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="confirmPassword">Confirma nova senha <form:errors
														path="confirmPassword" cssStyle="color: #ff0000;" />
												</label>
											</div>
											<form:password path="confirmPassword" cssClass="form-control"
												id="confirmPassword" />
										</div>
									</div>

									<div class="col-12 mt-3">
										<div class="input-group">
											<button type="submit" class="btn btn-primary">Atualizar</button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>



	<script src="${pageContext.request.contextPath}/assets/js/findcep.js"></script>
	<jsp:include page="../result.jsp" />
</body>
</html>