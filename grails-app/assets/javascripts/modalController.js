function receivedInCashAlert(paymentId) {
    document.getElementById("customAlert").innerHTML = `
                <div class="modal fade" id="receivedInCashAlert" tabindex="-1" role="dialog" aria-labelledby="receivedInCashAlert">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                <h2 class="font-weight-bold">Confirmar Pagamento</h2>
                                <h2 class="text-center">Deseja confirmar que o pagamento desta cobrança foi feito em dinheiro?</h2>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cancelar
                                </button>
                                <a href="/payment/confirmReceivedInCash/${paymentId}" class="btn btn-primary text-decoration-none" role="button">
                                    Confirmar
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
    `;
}