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
	<jsp:include page="../../navbar.jsp" />
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="../../sidebar.jsp" />
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<div class="jumbotron">
						<h1 class="display-4">Formul�rio de cadastro de avisos e
							multas para o morador ${resident.name }</h1>
						<p class="lead">Preencha o formul�rio com os dados</p>
						<hr class="my-4">
						<p>Campos marcados com * s�o considerados obrigat�rios</p>
						<form:form action="new" modelAttribute="warning">
							<form:hidden path="idResident" cssClass="form-control"
								value="${resident.id}" />
							<div class="form-group">
								<label for="description">Descri��o*</label>
								<form:input path="description" cssClass="form-control"
									id="description" />
								<form:errors path="description" cssStyle="color: #ff0000;" />
							</div>
							<div class="form-group">
								<label for="warningDate">Data da multa*</label>
								<form:input type="date" path="warningDate"
									cssClass="form-control" id="warningDate" />
							</div>
							<div class="form-group">
								<label for="value">Valor* (Pode ser zero, caso seja
									apenas um aviso)</label>
								<form:input path="value" cssClass="form-control" id="value" />
							</div>
							<div class="form-group">
								<label for="feeType">Per�odo*</label>
								<form:select cssClass="form-control" path="idCondominiumFee"
									id="feeType">
									<c:forEach items="${condominiumFees}" var="fee">
										<form:option value="${fee.id}"><fmt:formatDate value="${fee.closingDate}" pattern="dd/MM/yyyy" /></form:option>
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