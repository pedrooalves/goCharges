<g:form url="[controller: 'payer', action: 'edit']" method="POST">
    <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-warning ml-3">
        <asset:image src="pencil.svg"/>
    </button>
</g:form>

<g:if test="${payer.canDelete()}">
    <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-danger ml-3 js-btn-confirm-delete js-btn-open-modal">
        <asset:image src="trash.svg"/>
    </button>
</g:if>
<g:else>
    <g:form url="[controller: 'payer', action: 'restore']" method="POST">
        <button type="submit" name="id" value="${payer.id}" class="btn btn-outline-primary ml-3">
            <asset:image src="restore.svg"/>
        </button>
    </g:form>
</g:else>