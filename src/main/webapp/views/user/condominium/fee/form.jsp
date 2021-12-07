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
</head>
<body>
<fmt:setTimeZone value="GMT-0"/>
	<jsp:include page="../../navbar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<div class="jumbotron">
						<h1 class="display-4">Formulário de cadastro de taxa para o
							condomínio ${condominium.name } de <fmt:formatDate value="${condominiumFee.closingDate }" pattern="dd/MM/yyyy" /></h1>
						<p class="lead">Preencha o formulário com os dados</p>
						<a
							href="${pageContext.request.contextPath}/user/condominium/fee/forminstalment?id_condominium_fee=${fee.idCondominiumFee }&id_fee=${fee.id}"
							class="btn btn-link">Cadastrar parcela de taxa já cadastrada?
							Clique aqui</a>
						<hr class="my-4">
						<p>Campos marcados com * são considerados obrigatórios</p>
						<form:form action="form" modelAttribute="fee">
							<form:hidden path="idCondominiumFee" cssClass="form-control"
								value="${fee.idCondominiumFee}" />
							<form:hidden path="id" value="${fee.id}" />
							<form:hidden path="father" value="0" />
							<div class="row">
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="description">Descrição*</label>
										</div>
										<form:input path="description" cssClass="form-control"
											id="description" />
										<form:errors path="description" cssStyle="color: #ff0000;" />
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="value">Valor*</label>
										</div>
										<span class="input-group-text" id="basic-addon1">R$</span>
										<form:input path="value" cssClass="form-control" id="value"
										aria-describedby="basic-addon1"	/>
										<form:errors path="value" cssStyle="color: #ff0000;" />
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="payDate">Data de pagamento*</label>
										</div>
										<form:input type="date" path="payDate" cssClass="form-control"
											id="payDate"  />
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-6">
									<div class="form-group">
										<div class="col-12">
											<label for="dueDate">Data de vencimento*</label>
										</div>
										<form:input type="date" path="dueDate" cssClass="form-control"
											id="dueDate" />
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="installments">Total de parcelas*</label>
										</div>
										<form:input path="installments" cssClass="form-control"
											id="installments" />
										<form:errors path="installments" cssStyle="color: #ff0000;" />
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-6">
									<div class="input-group">
										<div class="col-12">
											<label for="feeType">Tipo de taxa*</label>
										</div>
										<form:select cssClass="form-control" path="idFeeType"
											id="feeType">
											<c:forEach items="${feeTypes}" var="feeType">
												<form:option value="${feeType.id}">${feeType.description}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="mt-3 col-12">
									<button type="submit" class="btn btn-primary float-right">Cadastrar</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.maskMoney.min.js"></script>
	<script type="text/javascript">
		$("#value").maskMoney({
			allowNegative : false,
			thousands : '',
			decimal : '.',
			allowZero : true
		});
	</script>
</body>
</html>