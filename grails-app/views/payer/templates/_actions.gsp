<g:form url="[controller: 'payer', action: 'edit']" method="POST">
    <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-warning">
        <asset:image src="pencil.svg"/>
    </button>
</g:form>

<g:if test="${payer.canDelete()}">
    <g:form url="[controller: 'payer', action: 'delete']" method="POST">
        <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-danger ml-3">
            <asset:image src="trash.svg"/>
        </button>
    </g:form>
</g:if>
<g:else>
    <g:form url="[controller: 'payer', action: 'restore']" method="POST">
        <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-primary ml-3">
            <asset:image src="restore.svg"/>
        </button>
    </g:form>
</g:else>