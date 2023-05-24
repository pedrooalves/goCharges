package gocharges

import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import gocharges.payment.enums.PaymentBillingType
import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter adapter) {
        validate(adapter)

        Payment payment = new Payment()
        payment.payer = findPayerByCpfCnpj(adapter.payerCpfCnpj)
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        if(!payment.save(failOnError:true)){
            throw new BusinessException("Erro inesperado")
        }

        return payment
    }

    public List<Payment> list() {
        return Payment.list()
    }

    public Payment update(Long id, PaymentAdapter adapter) {
        validate(adapter)

        Payment payment = Payment.get(id)

        payment.payer = findPayerByCpfCnpj(adapter.payerCpfCnpj)
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        return payment
    }

    private void validate(PaymentAdapter adapter) {
        if (adapter.payerCpfCnpj.isBlank()) {
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

    public Payer findPayerById(Long payerId) {
        Payer payer = Payer.findById(payerId)
        if (!payer) {
            throw new BusinessException("Pagador não encontrado")
        }

        return payer
    }

    public Payment findById(Long id) {
        return Payment.get(id)
    }
}
