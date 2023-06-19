function PersonFormController() {
    var _this = this;
    _this.reference = $(".js-customer-update");

    _this.init = function() {
        var postalCodeUtils = new PostalCodeUtils();
        postalCodeUtils.init();

        _this.hideEmailInput();
    }

    _this.hideEmailInput = function() {
        if (_this.isCustomerUpdate()) {
            var email = _this.reference.find(".js-email-div");
            email.attr("hidden", true);
            email.find(".js-email").attr("readonly", true);
        }
    }

    _this.isCustomerUpdate = function() {
        return _this.reference.length > 0;
    }
}

$(document).ready(function(){
    var personFormController = new PersonFormController();
    personFormController.init();
});