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
							<h1 class="display-4">Meus dados</h1>
							<p class="lead">Para atualizar preencha o formul�rio com seus
								dados</p>
							Deseja alterar sua senha? <a href="updatepassword">Clique aqui</a>
							<hr class="my-4">
							<p>Campos marcados com * s�o considerados obrigat�rios</p> 
							<form:form action="update" modelAttribute="user">

								<div class="row">
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="firstName">Primeiro Nome</label>
											</div>
											<form:input path="firstName" cssClass="form-control"
												id="firstName" />
											<form:errors path="firstName" cssStyle="color: #ff0000;" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">CEP*</label>
											</div>
											<form:input path="cep" cssClass="form-control" id="cep" />
											<form:errors path="cep" cssStyle="color: #ff0000;" />
											<button class="btn btn-small btn-secondary" type="button"
												id="findcep">Buscar cep</button>
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">Rua</label>
											</div>
											<form:input path="street" cssClass="form-control" id="street" />
											<form:errors path="street" cssStyle="color: #ff0000;" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">N�mero</label>
											</div>
											<form:input path="streetNumber" cssClass="form-control"
												id="streetNumber" />
											<form:errors path="streetNumber" cssStyle="color: #ff0000;" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">Complemento</label>
											</div>
											<form:input path="numberComplement" cssClass="form-control"
												id="numberComplement" />
											<form:errors path="numberComplement"
												cssStyle="color: #ff0000;" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">Bairro</label>
											</div>
											<form:input path="neighborhood" cssClass="form-control"
												id="neighborhood" />
											<form:errors path="neighborhood" cssStyle="color: #ff0000;" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">Cidade</label>
											</div>
											<form:input path="city" cssClass="form-control" id="city" />
											<form:errors path="city" cssStyle="color: #ff0000;" />
										</div>
									</div>
									<div class="col-12 col-md-6 col-lg-6">
										<div class="input-group">
											<div class="col-12">
												<label for="cep">Estado</label>
											</div>
											<form:input path="state" cssClass="form-control" id="state" />
											<form:errors path="state" cssStyle="color: #ff0000;" />
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