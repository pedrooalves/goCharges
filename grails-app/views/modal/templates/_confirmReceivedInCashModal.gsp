<div class="modal fade js-confirm-received-in-cash-modal" id="receivedInCashModal" tabindex="-1" role="dialog" aria-labelledby="receivedInCashModal">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="font-weight-bold">Confirmar recebimento em dinheiro</h2>
            </div>

            <div class="modal-body">
                <h2 class="text-center">Deseja confirmar que o pagamento desta cobrança foi feito em dinheiro?</h2>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    Cancelar
                </button>

                <g:form url="[controller: 'payment', action: 'confirmReceivedInCash']" method="PUT">
                    <button class="btn btn-primary js-confirm-received-in-cash-modal-btn-value" type="submit" name="id">
                        Confirmar
                    </button>
                </g:form>
            </div>
        </div>
    </div>
</div>