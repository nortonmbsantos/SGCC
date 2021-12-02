<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<jsp:include page="header.jsp" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<jsp:include page="result.jsp" />

	<div class="mt-5 container">
		<div class="jumbotron">
			<h1 class="display-4">Formulário de acesso</h1>
			<p class="lead">Preencha o formulário com o usuário e senha</p>
			<hr class="my-4">

			<c:if test="${not empty errorMessge}">
				<div
					style="color: red; font-weight: bold; margin: 30px 0px; font-size: 15px;">${errorMessge}</div>

			</c:if>

			<form name='login' action="login" method='POST'>
				<div class="row">
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="username">Usuário</label>
							</div>

							<input class="form-control" type='text' name='userName'>
						</div>
					</div>
					<div class="col-12 col-md-6 col-lg-6">
						<div class="input-group">
							<div class="col-12">
								<label for="confirmPassword">Senha</label>
							</div>

							<input class="form-control" type='password' name='password' />
						</div>
					</div>
					<div class="col-12">
						<div class="input-group">
							<input class="btn" name="submit" type="submit" value="Entrar" />
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</div>
			</form>
			<a href="user/forgotpassword">Esqueci minha senha</a>
		</div>
	</div>
</body>
</html>