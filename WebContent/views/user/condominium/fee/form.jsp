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
						<form:form action="form" modelAttribute="fee">
							<form:hidden path="idCondominiumFee" cssClass="form-control"
								value="${fee.idCondominiumFee}" />
							<form:hidden path="id" value="${fee.id}" />
							<div class="form-group">
								<label for="description">Descrição*</label>
								<form:input path="description" cssClass="form-control"
									id="description" />
								<form:errors path="description" cssStyle="color: #ff0000;" />
							</div>
							<div class="form-group">
								<label for="value">Valor*</label>
								<form:input path="value" cssClass="form-control" id="value" />
								<form:errors path="value" cssStyle="color: #ff0000;" />
							</div>
							<div class="form-group">
								<label for="payDate">Data de pagamento*</label>
								<form:input type="date" path="payDate" cssClass="form-control"
									id="payDate" />
							</div>
							<div class="form-group">
								<label for="dueDate">Data de vencimento*</label>
								<form:input type="date" path="dueDate" cssClass="form-control"
									id="dueDate" />
							</div>
							<div class="form-group">
								<label for="installments">Total de parcelas*</label>
								<form:input path="installments" cssClass="form-control"
									id="installments" />
								<form:errors path="installments" cssStyle="color: #ff0000;" />
							</div>
							<div class="form-group">
								<label for="currentInstallment">Parcela atual*</label>
								<form:input path="currentInstallment" cssClass="form-control"
									id="currentInstallment" />
								<form:errors path="currentInstallment"
									cssStyle="color: #ff0000;" />
							</div>
							<div class="form-group">
								<label for="feeType">Taxa referente*</label>
								<form:select cssClass="form-control" path="idFeeType"
									id="feeType">
										<form:option value="0">Nenhuma</form:option>
									<c:forEach items="${fathers}" var="father">
										<form:option value="${father.id}">${father.description}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<div class="form-group">
								<label for="feeType">Tipo de taxa*</label>
								<form:select cssClass="form-control" path="idFeeType"
									id="feeType">
									<c:forEach items="${feeTypes}" var="feeType">
										<form:option value="${feeType.id}">${feeType.description}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<button type="submit" class="btn btn-primary">Cadastrar</button>
						</form:form>
					</div>
				</div>
			</main>
		</div>
	</div>
</body>
</html>