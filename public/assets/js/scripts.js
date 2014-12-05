// scripts da aplicação

$(function($) {
	// Quando o formulário for enviado, essa função é chamada 
	$("#formEpStatus").submit(function() {
		// Colocamos os valores de cada campo em uma váriavel para facilitar a manipulação 
		var id = $("#idEpisodio").val(); 
		// Exibe mensagem de carregamento 
		//$("#status").html("<img src='loader.gif' alt='Enviando' />"); 
		// Fazemos a requisão ajax com o arquivo envia.php e enviamos os valores de cada campo através do método POST 
		$.post('/episodio/status', {id: id }, function(resposta) { 
			// Quando terminada a requisição 
			// Exibe a div status $("#status").slideDown(); 
			// Se a resposta é um erro 
			if (resposta != false) { 
				// Exibe o erro na div 
				$("#status-ep").html(resposta); } 
			// Se resposta for false, ou seja, não ocorreu nenhum erro 
			else { 
				// Exibe mensagem de sucesso 
				$("#status-ep").html("Mensagem enviada com sucesso!"); 
				// Coloca a mensagem no div de mensagens 
				$("#status-ep").prepend("<strong>"+ nome +"</strong> disse: <em>" + ok!!!! + "</em><br />"); 
				// Limpando todos os campos 
				//$("#nome").val("");
				//$("#email").val("");
				//$("#mensagem").val("");
			}
		});
	});
});
