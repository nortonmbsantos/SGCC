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
						<h1 class="display-4">Formulário de cadastro de taxa para o
							condomínio ${condominium.name }</h1>
						<p class="lead">Preencha o formulário com os dados</p>
						<hr class="my-4">
						<p>Campos marcados com * são considerados obrigatórios</p>
						<form:form action="add" modelsAttribute="commomArea">
							<form:hidden path="idCondominium" cssClass="form-control"
								value="${condominium.id}" />
							<div class="row">
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="name">Nome*</label>
										</div>
										<form:input path="name" cssClass="form-control" id="name" />
										<form:errors path="name" cssStyle="color: #ff0000;" />
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="bookingFee">Valor da reserva*</label>
										</div>
										<form:input path="bookingFee" cssClass="form-control"
											id="bookingFee" />
										<form:errors path="bookingFee" cssStyle="color: #ff0000;" />
									</div>
								</div>
								<div class="col-12 mt-3">
									<button type="submit" class="btn btn-primary">Cadastrar</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script	src="${pageContext.request.contextPath}/assets/js/jquery.maskMoney.min.js"></script>
	<script type="text/javascript">
		$("#bookingFee").maskMoney({
			allowNegative : false,
			thousands : '',
			decimal : '.',
			allowZero : true
		});
	</script>
</body>
</html>