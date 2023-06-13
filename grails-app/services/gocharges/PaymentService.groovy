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

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()
        payment.payer = PayerRepository.query([id: adapter.payerId]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        payment.save(failOnError: true)
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

        if (!payment) throw new BusinessException("Cobrança não encontrada")

        payment.deleted = true
        payment.save(failOnError: true)
    }

    public void confirm(Long id) {
        Payment payment = PaymentRepository.query([id: id]).get()

        if (!payment) throw new BusinessException("Cobrança não encontrada")

        payment.status = PaymentStatus.RECEIVED
        payment.paymentDate = new Date()
        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (!params.payerId) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Pagador"))
        if (!params.billingType) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Método de pagamento"))
        if (!params.dueDate) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Data de vencimento"))
        if (!params.value) throw new BusinessException(Utils.getMessageProperty("default.null.message", "Valor"))
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
}