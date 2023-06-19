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
        console.log(tab.href.split("#")[1]);
    }
}

$(document).ready(function(){
    var myAccountController = new MyAccountController();
    myAccountController.init();
});