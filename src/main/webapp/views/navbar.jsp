<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
	<a class="navbar-brand" href="/">SGCC</a> <a class="navbar-toggler"
		type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02"
		aria-expanded="false" aria-label="Toggle navigation" href="<%= request.getContextPath() %>/"> <span
		class="navbar-toggler-icon"></span>
	</a>

	<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Login</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/register">Cadastro</a>
			</li>
		</ul>
	</div>
</nav>