<%@ page import="shared.FlashMessageType" %>
<div class="toast js-feedback-toast ${flash.type == FlashMessageType.SUCCESS ? 'bg-gogreen' : 'bg-danger'}"
     style="position: absolute; top: 1rem; right: 1rem;" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
    <div class="toast-body text-white font-weight-bold">
        ${flash.message}
    </div>
</div>