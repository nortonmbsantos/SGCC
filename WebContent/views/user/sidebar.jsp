       
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <nav class="col-md-2 d-none d-md-block bg-light sidebar" style="height: 100%;position: fixed;">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/user/dashboard">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
                  Dashboard <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/condominiuns">
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                  Condomínios
                </a>
              </li>
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Condomínios registrados</span>
              <a class="d-flex align-items-center text-muted" href="${pageContext.request.contextPath}/user/condominium/new" title="Adicionar Condomínio">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-plus-circle"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="16"></line><line x1="8" y1="12" x2="16" y2="12"></line></svg>
              </a>
            </h6>
            <ul class="nav flex-column mb-2">
			<c:forEach items="${condominiuns}" var="condominium">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/user/condominium?id=${condominium.id}">
                  ${condominium.name } 
                </a>
              </li>
			</c:forEach>

            </ul>
          </div>
        </nav>