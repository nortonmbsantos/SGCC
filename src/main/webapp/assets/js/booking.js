/**
 * 
 */


$(document).on("click", ".accept-booking-button", function (event) {
	var id = $(this).data("id");
	var name = $(this).data("name");
	var date = $(this).data("date");
	
	var n = new Noty({
        layout: 'center',
        modal: 'true',
        text: "Deseja aceitar a reserva de " + name +" para "+ date +"?",
        buttons: [
            Noty.button('Fechar', 'btn btn-link', function () {
                n.close();
            }),
            Noty.button('Aceitar', 'btn btn-success', function () {
            	$("#accept-booking-"+id).submit();
                n.close();
            })
        ]
    }).show();
	return false;
});

$(document).on("click", ".refuse-booking-button", function (event) {
	var id = $(this).data("id");
	var name = $(this).data("name");
	var date = $(this).data("date");
	
	var n = new Noty({
		layout: 'center',
		modal: 'true',
		text: "Deseja recusar a reserva de " + name +" para "+ date +"?",
		buttons: [
			Noty.button('Fechar', 'btn btn-link', function () {
				n.close();
			}),
			Noty.button('Recusar', 'btn btn-danger', function () {
				$("#refuse-booking-"+id).submit();
				n.close();
			})
			]
	}).show();
	return false;
});