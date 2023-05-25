package gocharges

import gocharges.payment.PaymentRepository
import gocharges.payer.PayerRepository
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
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        if(!payment.save(failOnError:true)){
            throw new BusinessException("Erro inesperado")
        }

        return payment
    }

    public List<Payment> list() {
        return PaymentRepository.query([includeDeleted: false]).list()
    }

    public Payment update(Long id, PaymentAdapter adapter) {
        validate(adapter)

        Payment payment = PaymentRepository.query([id: id]).get()

        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj]).get()
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

    public void delete(Long id) {
        Payment payment = PaymentRepository.query([id: id]).get()
        payment.deleted = true

        payment.save(failOnError: true)
    }
}
