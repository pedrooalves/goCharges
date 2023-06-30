function FilterController() {
    var _this = this;
    _this.reference = $(".js-filter-list");
    _this.filterList = _this.reference.find(".js-filter");
    _this.search = _this.reference.find(".js-filter-search");

    _this.init = function() {
        _this.search.on("click", function() {
            _this.searchFilters();
        });
    }

    _this.searchFilters = function() {
        var paramsStr = [];
        _this.filterList.each(function() {
            if(this.value !== "") {
                paramsStr += `${this.name}=${this.value}&`
            }
        });
        $.post(`${_this.reference.attr("data-url")}/filterSearch`, paramsStr, function( data ) {
            $(".js-table").html(data)
        });
    }
}

$(document).ready(function(){
    var filterController = new FilterController();
    filterController.init();
});