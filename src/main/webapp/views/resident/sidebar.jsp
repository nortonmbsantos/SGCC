
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar"
	style="height: 100%; position: fixed;">
	<div class="sidebar-sticky">
		<ul class="nav flex-column">
			<li class="nav-item"><a class="nav-link active"
				href="${pageContext.request.contextPath}/resident/dashboard"> <i
					class="fas fa-lg fa-home"></i> Página inicial <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/resident/update"> <i
					class="fas fa-lg fa-user"></i> Meus dados
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/resident/condominiuns">
					<i class="fas fa-lg fa-building"></i> Meus condomínios
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/resident/condominium/booking/list">
					<i class="fas fa-lg fa-book-open"></i> Minhas reservas
			</a></li>
		</ul>
	</div>
</nav>