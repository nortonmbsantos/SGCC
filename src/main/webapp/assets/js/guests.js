$("#addGuest").on("click", function() {
	addGuest({ label: "", value: "", cpf: "", phone:""  });
});

function addGuest(data){
	var currentId = $("#guestlist #guest").last().attr("data-num");

    var id;
    if (isNaN(currentId)) {
        id = 0;
    } else {
        id = parseInt(currentId) + 1;
    }

    $("#guestlist")
        .append("<div id=\"guest\" data-num=\"" + id + "\" class=\"row\">" +
            "<div class=\"col-12 col-md-1 col-lg-1\"><div class=\"input-group\"><div class=\"col-12\"><label>&nbsp;</label>" +
            "<input id=\"guests" + id + ".id\" name=\"guests[" + id + "].id\" class=\"form-control\" type=\"hidden\" value=\""+ data.id +"\">" +
            "</div>" +
            "<button id=\"removeGuest\" type=\"button\" class=\"btn btn-sm btn-danger\"><i class=\"fa fa-minus\"></i></button>" +
            "</div>" +
            "</div>" +
            "<div class=\"col-12 col-md-4 col-lg-4\">" +
            "<div class=\"input-group\">" +
            "<div class=\"col-12\">" +
            "<label for=\"firstName\">Nome</label>" +
            "</div>" +
            "<input id=\"guests" + id + ".name\" name=\"guests[" + id + "].name\" class=\"form-control\" type=\"text\" value=\""+ data.value +"\">" +
            "</div>" +
            "</div>" +
            "<div class=\"col-12 col-md-4 col-lg-4\">" +
            "<div class=\"input-group\">" +
            "<div class=\"col-12\">" +
            "<label for=\"firstName\">Cpf</label>" +
            "</div>" +
            "<input id=\"guests" + id + ".cpf\" name=\"guests[" + id + "].cpf\" class=\"form-control\" type=\"text\" value=\""+ data.cpf +"\">" +
            "</div>" +
            "</div>" +
            "<div class=\"col-12 col-md-3 col-lg-3\">" +
            "<div class=\"input-group\">" +
            "<div class=\"col-12\">" +
            "<label for=\"firstName\">Telefone</label>" +
            "</div>" +
            "<input id=\"guests" + id + ".phone\" name=\"guests[" + id + "].phone\" class=\"form-control\" type=\"text\" value=\""+ data.phone +"\">" +
            "</div>" + "</div>" + "</div>");
	
}

$(document).on("click", "#removeGuest", function(e) {
    e.preventDefault();
    $(this).parent('div').parent('div').parent('div').remove();
});

function autoCompleteGuests(availableGuests) {
   
    $( "#searchGuest" ).autocomplete({
      source: availableGuests,
      delay: 0,
      focus: function( event, ui ) {
          $( "#searchGuest" ).val( ui.item.label );
          return false;
      },
      select: function( event, ui ) {
          addGuest(ui.item);
          $("#searchGuest").val("");
          return false;
        } 
    }).autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( "<div>" + item.label + "<br>" +"CPF: " + item.cpf + "</div>" )
        .appendTo( ul );
    };
  } 
