package gocharges

import gocharges.payment.PaymentRepository
import gocharges.payer.PayerRepository
import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import grails.gorm.transactions.Transactional

@Transactional
class PaymentService {

    PaymentMessageService paymentMessageService

    public Payment save(PaymentAdapter adapter, Customer customer) {
        Payment payment = new Payment()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value
        payment.customer = customer

        payment.save(failOnError: true)
        paymentMessageService.sendNewPaymentMessage(payment, customer)
        return payment
    }

    public List<Payment> list(Map params, Customer customer) {
        return PaymentRepository.query(params + [customer: customer]).list()
    }

    public Payment update(Long id, PaymentAdapter adapter, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        if (!payment.payer) throw new BusinessException("Pagador não encontrado")

        return payment.save(failOnError: true)
    }

    public void delete(Long id, Customer customer) {
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()

        if (!payment) throw new BusinessException("Cobrança não encontrada")

        payment.deleted = true
        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (params.payerCpfCnpj.isBlank()) throw new BusinessException("É preciso selecionar um pagador")
        if (params.billingType.isBlank()) throw new BusinessException("É preciso selecionar um tipo de recebimento aceito")
        if (params.dueDate.isBlank()) throw new BusinessException("É preencher o campo data")
        if (params.value.isBlank()) throw new BusinessException("É preciso preencher o campo valor")
    }
}