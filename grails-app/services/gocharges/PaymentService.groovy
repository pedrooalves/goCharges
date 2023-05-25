package gocharges

import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import gocharges.payment.enums.PaymentBillingType
import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()
        payment.payer = findPayerByCpfCnpj(adapter.payerCpfCnpj)
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        payment.save(failOnError:true)

        return payment
    }

    public List<Payment> list() {
        return Payment.findAll {
            deleted == false
        }
    }

    public Payment update(Long id, PaymentAdapter adapter) {
        Payment payment = Payment.get(id)
        payment.payer = findPayerByCpfCnpj(adapter.payerCpfCnpj)
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        return payment
    }

    private Payer findPayerByCpfCnpj(String payerCpfCnpj) {
        Payer payer = Payer.findByCpfCnpj(payerCpfCnpj)
        if (!payer) {
            throw new BusinessException("Pagador não encontrado")
        }

        return payer
    }

    public Payment findById(Long id) {
        return Payment.get(id)
    }

    public void delete(Long id) {
        Payment payment = Payment.get(id)
        payment.deleted = true

        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (params.payerCpfCnpj.isBlank() || params.billingType.isBlank() || params.dueDate.isBlank() ||
                params.value.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }
}