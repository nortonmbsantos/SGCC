<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:if test="${result != null}">
		<script type="text/javascript">
			new Noty({
				type : '${result.result}',
				layout : 'bottomCenter',
				text : '${result.message}',
				timeout: 3000
			}).show();
		</script>
	</c:if>
	
	<script type="text/javascript">
			new Noty({
				type : 'success',
				layout : 'bottomCenter',
				text : 'Login efetuado com sucesso',
				timeout: 3000
			}).show();
		</script>