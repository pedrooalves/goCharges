function PostalCodeUtils() {
    var _this = this;
    _this.reference = $(".js-person-address-form");
    _this.postalCodeInput = _this.reference.find(".js-postal-code");
    _this.address = _this.reference.find(".js-address");
    _this.province = _this.reference.find(".js-province");
    _this.city = _this.reference.find(".js-city");
    _this.state = _this.reference.find(".js-state");

    _this.init = function() {
        _this.postalCodeInput.on("blur", function() {
            _this.postalCode = _this.postalCodeInput.val().replace(/\D/g, '');
            _this.searchCep();
        });
    }

    _this.searchCep = function() {
        $.getJSON(getUrl(), function(json) {
            fillInputs(json);
        });
    }

    var getUrl = function() {
        if (validateCep()) {
            setWarningMessage("");
            setInputs('...');

            return 'https://viacep.com.br/ws/'+ _this.postalCode + '/json/?callback=?';
        } else {
            setInputs("");
            setWarningMessage("CEP inválido.");
        }
    }

    var fillInputs = function(json) {
        if (!("erro" in json)) {
            _this.address.val(json.logradouro);
            _this.province.val(json.bairro);
            _this.city.val(json.localidade);
            _this.state.val(json.uf);
        } else {
            setInputs("");
            setWarningMessage("CEP não encontrado.");
        }
    }

    var setInputs = function(string) {
        _this.reference.find('.js-set').val(string);
    }

    var validateCep = function() {
        var validatePattern = /^[0-9]{8}$/;
        return validatePattern.test(_this.postalCode) ? true : false;
    }

    var setWarningMessage = function(string) {
        _this.reference.find(".js-postal-code-warning").text(string);
    }
}

$(document).ready(function(){
    var postalCodeUtils = new PostalCodeUtils();
    postalCodeUtils.init();
});