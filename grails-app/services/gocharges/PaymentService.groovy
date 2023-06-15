package gocharges

import gocharges.mail.MailBuilder
import gocharges.payment.PaymentRepository
import gocharges.payer.PayerRepository
import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import gocharges.payment.enums.PaymentBillingType
import gocharges.payment.enums.PaymentStatus
import grails.gorm.transactions.Transactional
import shared.Utils

@Transactional
class PaymentService {

    PaymentMessageService paymentMessageService

    public Payment save(PaymentAdapter adapter, Customer customer) {
        Payment payment = new Payment()
        payment.publicId = UUID.randomUUID().toString().replace("-", "")
        payment.payer = PayerRepository.query([id: adapter.payerId, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value
        payment.customer = customer

        payment.save(failOnError: true)
        paymentMessageService.sendMail(MailBuilder.buildNewPaymentMessage(payment, customer))
        return payment
    }

    public List<Payment> list(Map params, Customer customer) {
        return PaymentRepository.query(params + [customer: customer]).list()
    }

    public Payment update(Long id, PaymentAdapter adapter, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()
        payment.payer = PayerRepository.query([id: adapter.payerId, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        if (!payment.payer) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Pagador"))

        return payment.save(failOnError: true)
    }

    public void delete(Long id, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()

        if (!payment) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Cobrança"))

        payment.deleted = true
        payment.save(failOnError: true)
    }

    public void confirmReceivedInCash(Long id) {
        Payment payment = PaymentRepository.query([id: id]).get()

        if (!payment) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Cobrança"))
        if (payment.status != PaymentStatus.PENDING) throw new BusinessException(Utils.getMessageProperty("default.not.pending.payment.message", null))

        payment.status = PaymentStatus.RECEIVED
        payment.billingType = PaymentBillingType.CASH
        payment.paymentDate = new Date()
        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (!params.payerId) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Pagador"))
        if (!params.billingType) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Forma de pagamento"))
        if (!params.dueDate) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Data de vencimento"))
        if (!params.value) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Valor"))
    }

    public void setAsOverdue() {
        Date today = new Date()
        List<Long> paymentIdList = PaymentRepository.query([
                "dueDate[le]": today,
                status: PaymentStatus.PENDING,
                includeDeleted: true,
                ignoreCustomer: true
        ]).property("id").list()

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

    public Payment buildReceipt(String publicId) {
        Payment payment = PaymentRepository.query([publicId: publicId, ignoreCustomer: true]).get()

        if (!payment) throw new BusinessException(Utils.getMessageProperty("default.not.found.message", "Cobrança"))

        return payment
    }
}