document.getElementById("formulario-cliente").addEventListener("submit", function(evento){   
    evento.preventDefault() // Evita que submit faça sair da página.
    var formulario = document.querySelectorAll("#formulario-cliente [name]");
    var aviso = document.getElementById("aviso");
    var book = {};    
    var regexp = /^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}/;     // Expressão regular para conferir a digitação do CPF:
    var checkin = true;
    pattern = new RegExp(regexp);

    formulario.forEach(function(field, index){
        if (field.getAttribute("type") == "radio") {            // Verifica se o campo é do tipo radio select     
            if(field.checked){
                book[field.name] = field.value;
                // Trate aqui o teu código radio
            }    
        } else if (field.getAttribute("type") == "checkbox") {  // Verifica se o campo é do tipo checbox  
            if(field.checked){
                book[field.name] = field.value;
                // Trate aqui o teu código checbox
            } 
        } else if (field.getAttribute("type") == "date") {  // Verifica se o campo é do tipo data
                book[field.name] = field.value;
        } else {            
            if(field.name == "complemento"){
                // Preenchimento de complemento é opcional.
            }else if(field.value == ""){
                field.setAttribute("style", "border: 2px solid red; color: red;"); // Todos os campos vazios são marcados de vermelho.
                aviso.innerHTML  = "Favor preencher campos marcados em vermelho.";
                checkin = false;
            }else if(field.name == "cpf"){ // Verifica se há um campo com o nome: cpf.
                if(testaCPF(field.value)){
                	field.setAttribute("style", "border: 2px solid blue; color: black;");
                }else{
                	checkin = false;
                }								
            }else{
                field.setAttribute("style", "border: 2px solid blue; color: black;");
                aviso.innerHTML  = "";
            }
            book[field.name] = field.value;
        }
    });
    if(checkin){
    	// Se o formulário estiver OK, enviar para cadastro.
        localStorage.setItem("users", JSON.stringify(book)); //Guarda os dados do armazenamento local do navegador.
        // Gravar cliente
    	
    	// console.log(JSON.stringify(book));
        
        $.ajax({				
    		type : "POST",
    		headers: {"Authorization": window.sessionStorage.getItem('token')},
    		url : "/cliente",
    		contentType: "application/json; charset=utf-8",
    		data : JSON.stringify(book),
    		success: function () {
    			window.location.href = "/index";    			
            },
            error: function (erro, textStatus, xhr) {
            	alert("Erro: "+ erro.status + textStatus + "Falha ao tentar adicionar cliente" + book + "!");
            }
    	});    	
    }
    

    function testaCPF(cpf){
    	
    	// Algoritmo de digito verificador:
		cpf = cpf.replace(/[^\d]+/g,'');
        if (cpf.length != 11 ||
            // Elimina CPFs invalidos conhecidos
				cpf == "00000000000" || 
				cpf == "11111111111" || 
				cpf == "22222222222" || 
				cpf == "33333333333" || 
				cpf == "44444444444" || 
				cpf == "55555555555" || 
				cpf == "66666666666" || 
				cpf == "77777777777" || 
				cpf == "88888888888" || 
				cpf == "99999999999"){
                    alert("CPF de testes não são permitidos!");
					return false;
        }else{;
            // Valida primeiro digito	
            var add = 0;	
            var teste = true;
            for (i=0; i < 9; i ++){
                add += parseInt(cpf.charAt(i)) * (10 - i);	
            }
            var rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)
                rev = 0;	
            if (rev != parseInt(cpf.charAt(9))){
                teste = false;
            }
            // Valida segundo digito	
            add = 0;	
            for (i = 0; i < 10; i ++){		
                add += parseInt(cpf.charAt(i)) * (11 - i);	
            }
            rev = 11 - (add % 11);	
            if (rev == 10 || rev == 11)	
                rev = 0;	
            if (rev != parseInt(cpf.charAt(10))) 
                teste = false;
            if(!teste){
                alert("CPF inválido!");
            }
			return teste;
        }
    }
});