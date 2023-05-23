package gocharges

import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class PaymentService {


    public Payment save(PaymentAdapter adapter) {
        validateSave(adapter)

        Payment payment = new Payment()
        payment.payer = findPayerByCpfCnpj(adapter.payerCpfCnpj)
        payment.billingType = adapter.billingType
        payment.dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(adapter.dueDate)
        payment.value = new BigDecimal(adapter.value)
        payment.status = "PENDENTE"

        if(!payment.save(failOnError:true)){
            throw new BusinessException("Erro inesperado")
        }

        return payment
    }

    private void validateSave(PaymentAdapter adapter) {
        if (adapter.payerCpfCnpj.isBlank() || adapter.billingType.isBlank() || adapter.dueDate.isBlank() ||
                adapter.value.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }

    private Payer findPayerByCpfCnpj(String payerCpfCnpj) {
        Payer payer = Payer.findByCpfCnpj(payerCpfCnpj)
        if (!payer) {
            throw new BusinessException("Pagador não encontrado")
        }

        return payer
    }

    private Payer findPayerById(Long payerId) {
        Payer payer = Payer.findById(payerId)
        if (!payer) {
            throw new BusinessException("Pagador não encontrado")
        }

        return payer
    }

    public List<Payment> list() {
        return Payment.list()
    }
}
