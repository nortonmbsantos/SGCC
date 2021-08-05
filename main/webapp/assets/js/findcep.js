$("#findcep").on("click", function(){
	$("#street").val("");
	$("#streetNumber").val("");
	$("#numberComplement").val("");
	$("#neighborhood").val("");
	$("#city").val("");
	$("#state").val("");
  var cep = $("#cep").val();
  $.getJSON("https://viacep.com.br/ws/"+ cep +"/json", function(dados) {
    $("#street").val(dados.logradouro);
    $("#city").val(dados.localidade);
    $("#neighborhood").val(dados.bairro);
    $("#state").val(dados.uf);
  });
});
