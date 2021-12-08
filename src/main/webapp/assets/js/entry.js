/**
 * 
 */


$(document).on("click", ".accept-entry-button", function (event) {
	var id = $(this).data("id");
	var name = $(this).data("name");
	
	var n = new Noty({
		layout: 'center',
		modal: 'true',
		text: "Deseja aceitar a solicitação de " + name +"?",
		buttons: [
			Noty.button('Cancelar', 'btn btn-link', function () {
				n.close();
			}),
			Noty.button('Aceitar', 'btn btn-success', function () {
				$("#accept-entry-"+id).submit();
				n.close();
			})
			]
	}).show();
	return false;
});

$(document).on("click", ".refuse-entry-button", function (event) {
	var id = $(this).data("id");
	var name = $(this).data("name");
	
	var n = new Noty({
		layout: 'center',
		modal: 'true',
		text: "Deseja recusar a solicitação de " + name +"?",
		buttons: [
			Noty.button('Cancelar', 'btn btn-link', function () {
				n.close();
			}),
			Noty.button('Recusar', 'btn btn-danger', function () {
				$("#refuse-entry-"+id).submit();
				n.close();
			})
			]
	}).show();
	return false;
});