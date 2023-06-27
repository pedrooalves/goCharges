function MyAccountController() {
    _this = this;
    _this.reference = $(".js-my-account");
    _this.tabs = _this.reference.find(".js-link-tab");

    _this.init = function() {
        _this.tabs.on("click", function() {
            _this.tabs.removeClass("active");
            this.classList.add("active");
            _this.buildForm(this);
        });
    }

    _this.buildForm = function(tab) {
        _this.reference.find(".js-set-hidden").hide();
        _this.reference.find(`.js-${tab.getAttribute("data-type")}`).show();
    }
}

$(document).ready(function(){
    var myAccountController = new MyAccountController();
    myAccountController.init();
});