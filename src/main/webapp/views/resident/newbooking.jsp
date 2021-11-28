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
<jsp:include page="../header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container-fluid pt-5">
		<div class="row">
			<jsp:include page="sidebar.jsp" />


			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
				<div class="chartjs-size-monitor"
					style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div class="chartjs-size-monitor-expand"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 1000000px; height: 1000000px; left: 0; top: 0"></div>
					</div>
					<div class="chartjs-size-monitor-shrink"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
					</div>
				</div>
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formulário de solicitação de reserva de
				área comum em ${condominium.name }</h1>
			<p class="lead">Preencha o formulário com os dados</p>
			<hr class="my-4">
			<p>Campos marcados com * são considerados obrigatórios</p>
			<form:form action="add" modelAttribute="bookingRequest">
				<div class="row">
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="firstName">Data solicitada*</label>
							</div>
							<form:input type="date" path="bookingDate"
								cssClass="form-control" id="booking_date" />
								<form:errors path="bookingDate" cssStyle="color: #ff0000;" />
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="firstName">Área comum*</label>
							</div>
							<form:select cssClass="form-control" path="idCommomArea"
								id="id_commom_area">
								<c:forEach items="${commomareas}" var="area">
									<form:option value="${area.id}">${area.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-12">
						<hr class="my-4">
						<label for="firstName">Convidados &nbsp;&nbsp;
							<button id="addGuest" type="button"
								class="btn btn-sm btn-success">
								<i class="fa fa-plus"></i>
							</button>
						</label>
						<div class="">
							<hr class="my-4" />
							<div class="input-group mb-3">
								<span class="input-group-text" id="search-guest">Buscar
									convidado</span><input id="searchGuest" class="form-control" aria-describedby="search-guest">
							</div>
						</div>
					</div>
				</div>

				<div id="guestlist" class="overflow-auto p-3 mb-3"
					style="max-height: 400px;">
					<c:forEach items="${bookingRequest.guests }" varStatus="s">
						<div id="guest" data-num="${s.count - 1}" class="row">
							<div class="col-12 col-md-1 col-lg-1">
								<div class="input-group">
									<div class="col-12">
										<label>&nbsp;</label>
									</div>
									<button id="removeGuest" type="button"
										class="btn btn-sm btn-danger">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="col-12 col-md-4 col-lg-4">
								<div class="input-group">
									<form:hidden path="guests[${s.index}].id" class="form-control" />
									<div class="col-12">
										<label for="firstName">Nome</label>
									</div>
									<form:input path="guests[${s.index}].name" class="form-control" cssErrorClass="form-control is-invalid" />
								</div>
							</div>
							<div class="col-12 col-md-4 col-lg-4">
								<div class="input-group">
									<div class="col-12">
										<label for="firstName">Cpf</label>
									</div>
									<form:input path="guests[${s.index}].cpf" class="form-control" cssErrorClass="form-control is-invalid" />
								</div>
							</div>
							<div class="col-12 col-md-3 col-lg-3">
								<div class="input-group">
									<div class="col-12">
										<label for="firstName">Telefone</label>
									</div>
									<form:input path="guests[${s.index}].phone"
										class="form-control" cssErrorClass="form-control is-invalid" />
								</div>
							</div>
							<div class="col-12 col-md-1 col-lg-1">
								<div class="input-group">
									
								</div>
							</div>
							<div class="col-12 col-md-4 col-lg-4">
								<div class="input-group">
							<form:errors path="guests[${s.index}].name" cssStyle="color: #ff0000;" />
								</div>
							</div>
							<div class="col-12 col-md-4 col-lg-4">
								<div class="input-group">
							<form:errors path="guests[${s.index}].cpf" cssStyle="color: #ff0000;" />
						
								</div>
							</div>
							<div class="col-12 col-md-3 col-lg-3">
								<div class="input-group">
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="col-12">
					<button type="submit" class="btn btn-primary mt-3">Cadastrar</button>
				</div>
			</form:form>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/guests.js"></script>
	<script>
		var availableGuests = [
			
	    	${guestForScript}
		
	    ];
		autoCompleteGuests(availableGuests);
		</script>


				</div>

			</main>
		</div>

	</div>
	<jsp:include page="../result.jsp"></jsp:include>
</body>
</html>