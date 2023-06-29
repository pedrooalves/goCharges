function InputMasKController() {
    var _this = this;
    _this.reference = $(".js-container");
    _this.cpfCnpjInput = _this.reference.find(".js-cpf-cnpj-input");
    _this.mobilePhoneInput = _this.reference.find(".js-mobile-phone-input");
    _this.postalCodeInput = _this.reference.find(".js-postal-code-input");
    _this.valueInput = _this.reference.find(".js-value-input")

    _this.init = function() {
        _this.setCpfCnpjMask();
        _this.setMobilePhoneMask();
        _this.setPostalCodeMask();
        _this.setValueMask();
    };

    _this.setCpfCnpjMask = function() {
        _this.cpfCnpjInput.on("keypress", function() {
           var cpfMask = "000.000.000-00";
           var cnpjMask = "00.000.000/0000-00";
           var mask = (this.value.length >= 14) ? cnpjMask : cpfMask;

           _this.cpfCnpjInput.mask(mask);
        })
    };

    _this.setMobilePhoneMask = function() {
       _this.mobilePhoneInput.mask("(00) 00000-0000")
    };

    _this.setPostalCodeMask = function() {
        _this.postalCodeInput.mask("00000-000");
    };

    _this.setValueMask = function() {
        _this.valueInput.mask('0000000000,00', {reverse: true});
    };
};

$(document).ready(function() {
    var inputMasKController = new InputMasKController();
    inputMasKController.init();
});