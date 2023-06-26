<ul class="list-group list-group-horizontal d-flex justify-content-center w-50">
    <g:form name="updateButton" url="[controller: 'customer', action: 'edit']" method="POST">
        <button type="submit" name="id" value="${customer.id}" class="btn btn-outline-warning">
            <asset:image src="pencil.svg"/>
        </button>
    </g:form>
</ul>