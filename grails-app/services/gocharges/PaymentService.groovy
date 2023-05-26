package gocharges

import gocharges.payment.PaymentRepository
import gocharges.payer.PayerRepository
import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        payment.save(failOnError:true)

        return payment
    }

    public List<Payment> list() {
        return PaymentRepository.query([includeDeleted: false]).list()
    }

    public Payment update(Long id, PaymentAdapter adapter) {
        Payment payment = PaymentRepository.query([id: id]).get()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        return payment
    }

    public void delete(Long id) {
        Payment payment = PaymentRepository.query([id: id]).get()
        payment.deleted = true

        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (params.payerCpfCnpj.isBlank() || params.billingType.isBlank() || params.dueDate.isBlank() ||
                params.value.isBlank()) {
            throw new BusinessException("É preciso preencher todos os campos")
        }
    }

    public static void setOverdue(Long id) {
        Payment payment = PaymentRepository.query([id: id]).get()
        payment.status = PaymentStatus.OVERDUE

        println(id + " " + payment.status)

        payment.save(failOnError: true)
    }
}