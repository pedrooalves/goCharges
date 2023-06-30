function TableController() {
    var _this = this;
    _this.reference = $(".js-container");
    _this.table = _this.reference.find(".js-table");
    _this.tableRow = _this.reference.find(".js-table-row");
    _this.pagination = _this.reference.find(".js-pagination");
    _this.previous;
    _this.next;
    _this.currentPage = 0;
    _this.numPages = 0;
    _this.rowsShown = 10;

    _this.init = function() {
        _this.bindTableRow();
        _this.bindPagination();
    };

    _this.bindTableRow = function() {
        _this.tableRow.on("click", function() {
            window.open(this.getAttribute("data-url"), "_self");
        });
    };

    _this.unbindTableRow = function() {
        _this.tableRow.off("click");
    };

    _this.bindPagination = function() {
        var rowsTotal = _this.tableRow.length;
        _this.numPages = rowsTotal/_this.rowsShown;

        _this.previous = _this.reference.find(".js-previous");
        _this.previous.css("cursor", "not-allowed");

        for (i = 0; i < _this.numPages; i++) {
            var pageNum = i + 1;
            _this.pagination.append (`<li class="page-item"><a class="page-link js-page-link" rel="${i}" href="#">${pageNum}</a></li>`);
        }

        _this.pagination.append('<li class="page-item"><a class="page-link js-next" href="#">Próximo</a></li>');
        _this.next = _this.reference.find(".js-next");
        if (_this.numPages < 2) _this.next.css("cursor", "not-allowed");

        _this.tableRow.hide();
        _this.tableRow.slice(0, _this.rowsShown).show();
        _this.reference.find(".js-page-link[rel='0']").addClass("pagination-active");


        _this.reference.find(".js-previous").on('click', function() {
            _this.previousPage();
        });

        _this.reference.find(".js-next").on('click', function() {
            _this.nextPage();
        });

        _this.reference.find(".js-page-link").on('click', function() {
            _this.currentPage = parseInt($(this).attr('rel'));
            _this.loadRows();
        });
    };

    _this.previousPage = function() {
        if (_this.currentPage - 1 < 0) return;

        _this.currentPage -= 1;
        _this.loadRows();
    };

    _this.nextPage = function() {
        if (_this.currentPage + 1 >= _this.numPages) return;

        _this.currentPage += 1;
        _this.loadRows();
    };

    _this.loadRows = function() {
        _this.setButtons();

        var startItem = _this.currentPage * _this.rowsShown;
        var endItem = startItem + _this.rowsShown;

        _this.tableRow.css('opacity','0.0').hide().slice(startItem, endItem).css('display','table-row').animate({opacity:1}, 300);
        $(".js-page-link").removeClass('pagination-active');
        $(`.js-page-link[rel='${_this.currentPage}']`).addClass('pagination-active');
    };

    _this.setButtons = function() {
       var nextCursor = _this.currentPage + 1 >= _this.numPages ? "not-allowed" : "pointer";
       var previousCursor = _this.currentPage - 1 < 0 ? "not-allowed" : "pointer";
       _this.next.css("cursor", nextCursor);
       _this.previous.css("cursor", previousCursor);
    };
};

$(document).ready(function() {
    var tableController = new TableController();
    tableController.init();
});