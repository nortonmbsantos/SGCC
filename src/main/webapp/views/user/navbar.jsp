<nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0 shadow sticky-top flex-md-nowrap">
	<a class="navbar-brand" href="#">SGCC</a> 
	<a class="navbar-toggler d-none d-sm-block d-md-none"
		type="button" data-toggle="collapse"
		data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02"
		aria-expanded="false" aria-label="Toggle navigation" href="<%= request.getContextPath() %>/"> <span
		class="navbar-toggler-icon"></span>
	</a>
	<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			<div class="d-none d-sm-block d-md-none">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/user/dashboard">
                  <i class="fas fa-lg fa-home"></i>
                  Página inicial <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/update">
					<i class="fas fa-lg fa-user"></i>                  
					Meus dados
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/condominiuns">
					<i class="fas fa-lg fa-building"></i>                  
					Meus condomínios
                </a>
              </li>
		<li class="nav-item text-nowrap"><a class="nav-link"
			href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Sair</a></li>
		</ul>
		</div>
	</div>
		
	<ul class="navbar-nav d-none d-md-block d-lg-block">
		<li class="nav-item text-nowrap"><a class="nav-link"
			href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Sair</a></li>
	</ul>
</nav>