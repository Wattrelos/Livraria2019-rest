class ScheduleController{
    constructor(){
        this.formListaProdutos = document.getElementById("listaProdutos");    
        this._text = "";    // Variável para a montagem do formulário  
        this.inject();      // Chamada de função.
    }

    inject(){
        // JSON simulator:======================================================
        // Cria um objeto JSON que normamente seria adquirido de um banco de dados.
        var listaProdutos = [
                       ["img/BookMockup.jpg","Nome do primeiro produto", "Em estoque", "1", "124.90"],
                       ["img/BookMockup.jpg","Nome do segundo produto",  "Em estoque", "3", "98.90"],
                       ["img/BookMockup.jpg","Nome do terceiro produto", "Em estoque", "2", "74.50"]
                    ]
        ;
        // end JSON -----------------------------------------------------------  
        // Monta a estrutura do formulário:
        this.insertProduct(listaProdutos);
        // Mostra o fornulário no navegador:        
        console.log(listaProdutos);
        this.formListaProdutos.innerHTML =  this._text;
    }
    insertProduct(listaProdutos){

        this._text += `<table class="table table-striped">
        <thead>
            <tr>
                <th scope="col"> </th>
                <th scope="col">Produto</th>
                <th scope="col">Disponibilidade</th>
                <th scope="col" class="text-center">Quantidade</th>
                <th scope="col" class="text-right">Preço</th>
                <th> </th>
            </tr>
        </thead>
        <tbody>`;
        var i;
        for (i = 0; i < 3; i++) {
            this._text += `<tr>
                <td><img src="${listaProdutos[i][0]}" alt="Capa do livro" width="60" height="80"></td>
                <td>${listaProdutos[i][1]}</td>
                <td>${listaProdutos[i][2]}</td>
                <td><input class="form-control" type="text" value="${listaProdutos[i][3]}" /></td>
                <td class="text-right">R$${listaProdutos[i][4]}</td>
                <td class="text-right"><button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
            </tr> `;
        }
        this._text += `<tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Subtotal</td>
            <td class="text-right">R$ 255,90</td>
            </tr>
            <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Frete</td>
            <td class="text-right">R$ 6,90</td>
            </tr>
            <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><strong>Total</strong></td>
            <td class="text-right"><strong>R$ 346,90</strong></td>
            </tr>
            </tbody>
            </table>`;


    }
}