<%@ page import="gocharges.payment.enums.PaymentStatus"%>
<g:if test="${payment.canConfirm()}">
    <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-dark ml-3 js-btn-confirm-received-in-cash js-btn-open-modal">
        <asset:image src="cash-stack.svg"/>
    </button>
</g:if>

<g:if test="${payment.status.isReceived()}">
    <g:form name="receiptButton" url="[controller: 'paymentReceipt', action: 'index']" method="GET">
        <button type="submit" name="publicId" value="${payment.publicId}" class="btn btn-outline-success ml-3">
            <asset:image src="receipt.svg"/>
        </button>
    </g:form>
</g:if>

<g:form name="updateButton" url="[controller: 'payment', action: 'edit']" method="POST">
    <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-warning ml-3">
        <asset:image src="pencil.svg"/>
    </button>
</g:form>

<g:if test="${payment.canDelete()}">
    <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-danger ml-3 js-btn-confirm-delete js-btn-open-modal">
        <asset:image src="trash.svg"/>
    </button>
</g:if>
<g:else>
    <g:form name="restoreButton" url="[controller: 'payment', action: 'restore']" method="POST">
        <button type="submit" name="id" value="${payment.id}" class="btn btn-outline-primary ml-3">
            <asset:image src="restore.svg"/>
        </button>
    </g:form>
</g:else>