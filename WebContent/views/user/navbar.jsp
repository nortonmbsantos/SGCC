<%@page import="br.edu.utfpr.sgcc.models.MyUserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<nav
	class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
	<a class="navbar-brand col-sm-3 col-md-2 mr-0"
		href="https://getbootstrap.com/docs/4.1/examples/dashboard/#">Olá ${user.firstName }
		</a>
	<ul class="navbar-nav px-3">
		<li class="nav-item text-nowrap"><a class="nav-link"
			href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Sair</a></li>
	</ul>
</nav>