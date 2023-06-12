package gocharges

import gocharges.payment.PaymentRepository
import gocharges.payer.PayerRepository
import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional
import shared.Utils

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter adapter, Customer customer) {
        Payment payment = new Payment()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value
        payment.customer = customer

        return payment.save(failOnError: true)
    }

    public List<Payment> list(Map params, Customer customer) {
        if (params.payerId) {
            params.put("payer", PayerRepository.query([id: Long.valueOf(params.payerId), customer: customer]).get())
            params.remove("payerId")
        }
        return PaymentRepository.query(params + [customer: customer]).list()
    }

    public Payment update(Long id, PaymentAdapter adapter, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        if (!payment.payer) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Pagador"))

        return payment.save(failOnError: true)
    }

    public void delete(Long id, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()

        if (!payment) throw new BusinessException(Utils.getMessageProperty("default.not.found.message.feminine", "Cobrança"))

        payment.deleted = true
        payment.save(failOnError: true)
    }

    public void confirm(Long id) {
        Payment payment = PaymentRepository.query([id: id, ignoreCustomer: true]).get()

        if (!payment) throw new BusinessException(Utils.getMessageProperty("default.not.found.message.feminine", "Cobrança"))

        payment.status = PaymentStatus.RECEIVED
        payment.paymentDate = new Date()
        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (params.payerCpfCnpj.isBlank()) throw new BusinessException(Utils.getMessageProperty("default.null.message", "CPF / CNPJ"))
        if (params.billingType.isBlank()) throw new BusinessException(Utils.getMessageProperty("default.null.message", "tipo de recebimento aceito"))
        if (params.dueDate.isBlank()) throw new BusinessException(Utils.getMessageProperty("default.null.message", "data"))
        if (params.value.isBlank()) throw new BusinessException(Utils.getMessageProperty("default.null.message", "valor"))
    }

    public void setAsOverdue() {
        Date today = new Date()
        List<Long> paymentIdList = PaymentRepository.query(["dueDate[le]": today, status: PaymentStatus.PENDING, includeDeleted: true]).property("id").list()

        for (Long id : paymentIdList) {
            Payment.withNewTransaction { status ->
                try {
                    Payment payment = Payment.get(id)
                    payment.status = PaymentStatus.OVERDUE
                    payment.save(failOnError: true)
                } catch (Exception exception) {
                    status.setRollbackOnly()
                }
            }
        }
    }

    public Payment restore(Long id, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer, deletedOnly: true]).get()

        if (!payment) throw new BusinessException(Utils.getMessageProperty("default.not.found.message.feminine", "Cobrança"))

        payment.deleted = false
        return payment.save(failOnError: true)
    }
}