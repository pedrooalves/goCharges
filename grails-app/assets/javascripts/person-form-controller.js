function PersonFormController() {
    var _this = this;
    _this.reference = $(".js-customer-create");

    _this.init = function() {
        var postalCodeUtils = new PostalCodeUtils();
        postalCodeUtils.init();

        _this.buildCustomerCreateEmail();
    }

    _this.buildCustomerCreateEmail = function() {
        if (_this.isCustomerCreate()) {
            var email = _this.reference.find(".js-email")
            email.attr("readonly", true);
        }
    }

    _this.isCustomerCreate = function() {
        return _this.reference.length > 0
    }
}

$(document).ready(function(){
    var personFormController = new PersonFormController();
    personFormController.init();
});