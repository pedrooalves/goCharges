package gocharges

import gocharges.payment.PaymentRepository
import gocharges.payer.PayerRepository
import gocharges.exception.BusinessException
import gocharges.payment.adapter.PaymentAdapter
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class PaymentService {

    SpringSecurityService springSecurityService

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()
        Customer customer = springSecurityService.getCurrentUser().customer
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value
        payment.customer = customer

        payment.save(failOnError:true)
        return payment
    }

    public List<Payment> list() {
        Customer customer = springSecurityService.getCurrentUser().customer
        return PaymentRepository.query([includeDeleted: false, customer: customer]).list()
    }

    public Payment update(Long id, PaymentAdapter adapter) {
        Customer customer = springSecurityService.getCurrentUser().customer
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()
        payment.payer = PayerRepository.query([cpfCnpj: adapter.payerCpfCnpj, customer: customer]).get()
        payment.billingType = adapter.billingType
        payment.dueDate = adapter.dueDate
        payment.value = adapter.value

        if(!payment.payer) throw new BusinessException("Pagador não encontrado")

        payment.save(failOnError: true)

        return payment
    }

    public void delete(Long id) {
        Customer customer = springSecurityService.getCurrentUser().customer
        Payment payment = PaymentRepository.query([id: id, customer: customer]).get()

        if (!payment) throw new BusinessException("Cobrança não encontrada")

        payment.deleted = true
        payment.save(failOnError: true)
    }

    public static void validate(Map params) {
        if (params.payerCpfCnpj.isBlank()) throw new BusinessException("É preciso selecionar um pagador")
        if (params.billingType.isBlank()) throw new BusinessException("É preciso selecionar uma forma de pagamento")
        if (params.dueDate.isBlank()) throw new BusinessException("É preencher o campo data")
        if (params.value.isBlank()) throw new BusinessException("É preciso preencher o campo valor")
    }
}