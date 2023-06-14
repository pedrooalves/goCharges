function searchCep(cep) {
    cep = cep.replace(/\D/g, '');
    if (validateCep(cep)) {
        loadingAutoComplete('...');

        var script = document.createElement('script');
        script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=fillInputs';

        document.body.appendChild(script);
    } else {
        clearInputs();
        alert("Formato de CEP inválido.");
    }
}

function clearInputs() {
    document.getElementById('address').value=("");
    document.getElementById('province').value=("");
    document.getElementById('city').value=("");
    document.getElementById('state').value=("");
}

function fillInputs(json) {
    if (!("erro" in json)) {
        document.getElementById('address').value=(json.logradouro);
        document.getElementById('province').value=(json.bairro);
        document.getElementById('city').value=(json.localidade);
        document.getElementById('state').value=(json.uf);
    } else {
        clearInputs();
        alert("CEP não encontrado.");
    }
}

function validateCep(cep) {
    var validatePattern = /^[0-9]{8}$/;
    return validatePattern.test(cep) ? true : false
}

function loadingAutoComplete(string) {
    document.getElementById('address').value=string;
    document.getElementById('province').value=string;
    document.getElementById('city').value=string;
    document.getElementById('state').value=string;
}