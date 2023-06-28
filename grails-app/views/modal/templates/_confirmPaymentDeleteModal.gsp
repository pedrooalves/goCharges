<div class="modal fade js-confirm-delete-modal js-modal" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModal">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="font-weight-bold">Confirmar exclusão de cobrança</h2>
            </div>

            <div class="modal-body">
                <h2 class="text-center">Deseja confirmar a remoção desta cobrança?</h2>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary js-btn-modal-hide">
                    Cancelar
                </button>

                <g:form url="[controller: 'payment', action: 'delete']" method="PUT">
                    <button class="btn btn-gogreen js-btn-modal-value" type="submit" name="id">
                        Confirmar
                    </button>
                </g:form>
            </div>
        </div>
    </div>
</div>