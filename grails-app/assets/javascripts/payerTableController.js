function PayerTableController() {
    var _this = this;
    _this.reference = ${".js-payer-index-container"};
    _this.tableRow = _this.reference.find(".js-table-row");

    _this.init = function() {
        _this.setTableRow()
    }

    _this.setTableRow = function() {
        _this.tableRow.on("click", function() {
            $.ajax({
                url: this.data("url"),
                data: {id:'1'},
              });
        })
    }
}