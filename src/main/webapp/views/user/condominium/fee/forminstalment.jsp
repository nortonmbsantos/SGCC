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
						<form:form action="forminstalment" modelAttribute="fee">
							<form:hidden path="idCondominiumFee" cssClass="form-control"
								value="${fee.idCondominiumFee}" />
							<form:hidden path="description" cssClass="form-control"
								value="Nova parcela" />
							<form:hidden path="id" value="${fee.id}" />
							<form:hidden path="idFeeType" value="0" />
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
								<label for="father">Taxa referente*</label>
								<form:select cssClass="form-control" path="father" 
									id="father">
									<c:forEach items="${fathers}" var="father">
										<form:option value="${father.father}">${father.description}</form:option>
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
</html>ml>