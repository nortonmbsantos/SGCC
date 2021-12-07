/**
 * 
 */


$(document).on("click", ".remove-warning-button", function (event) {
	var id = $(this).data("id");
	var desc = $(this).data("desc");
	
	var n = new Noty({
        layout: 'center',
        modal: 'true',
        text: "Deseja remover a multa/advertencia " + desc +"?",
        buttons: [
            Noty.button('Cancelar', 'btn btn-link', function () {
                n.close();
            }),
            Noty.button('Remover', 'btn btn-danger', function () {
            	$("#remove-warning-"+id).submit();
                n.close();
            })
        ]
    }).show();
	return false;
});