function PostalCodeUtils() {
    var _this = this;
    _this.reference = $(".js-postal-code-container");
    _this.postalCode = _this.reference.find(".js-postal-code");
    _this.address = _this.reference.find(".js-address");
    _this.province = _this.reference.find(".js-province");
    _this.city = _this.reference.find(".js-city");
    _this.state = _this.reference.find(".js-state");

    _this.init = function() {
        _this.postalCode.on("blur", function() {
            _this.postalCodeValue = _this.postalCode.val().replace(/\D/g, '');
            _this.searchPostalCode();
        });
    }

    _this.searchPostalCode = function() {
        $.getJSON(getUrl(), function(json) {
            fillInputs(json);
        });
    }

    var getUrl = function() {
        if (!validatePostalCode()) {
            setWarningMessage("CEP inválido.");
            return;
        }
        setLoading();
        return 'https://viacep.com.br/ws/'+ _this.postalCodeValue + '/json/?callback=?';
    }

    var fillInputs = function(json) {
        if ("erro" in json) {
            setWarningMessage("CEP não encontrado.");
            return;
        }
        _this.address.val(json.logradouro);
        _this.province.val(json.bairro);
        _this.city.val(json.localidade);
        _this.state.val(json.uf);
    }

    var setLoading = function() {
        setWarningMessage("");
        _this.reference.find('.js-set').val("...");
    }

    var clearFields = function() {
        _this.reference.find('.js-set').val("");
    }

    var validatePostalCode = function() {
        var validatePattern = /^[0-9]{8}$/;
        return validatePattern.test(_this.postalCodeValue) ? true : false;
    }

    var setWarningMessage = function(string) {
        clearFields();
        _this.reference.find(".js-postal-code-warning").text(string);
    }
}

$(document).ready(function(){
    var postalCodeUtils = new PostalCodeUtils();
    postalCodeUtils.init();
});