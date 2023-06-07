package gocharges

import gocharges.payment.enums.PaymentBillingType

import java.sql.Timestamp

class FormatTagLib {

    static namespace = "FormatTagLib"

    Integer CPF_LENGTH = 11
    Integer CNPJ_LENGTH = 14

    def brazilDateNotation = { Map attrs ->
        out << dateNotation("dd/MM/yyyy", attrs.date)
    }

    def cpfCnpjNotation = { Map attrs ->
        if (attrs.cpfCnpj.length() == CPF_LENGTH) {
            out << attrs.cpfCnpj.substring(0, 3) + "." + attrs.cpfCnpj.substring(3, 6) + "." + attrs.cpfCnpj.substring(6, 9) + "-" +
                    attrs.cpfCnpj.substring(9, 11)
        }

        if (attrs.cpfCnpj.length() == CNPJ_LENGTH) {
            out << attrs.cpfCnpj.substring(0, 2) + "." + attrs.cpfCnpj.substring(2, 5) + "." + attrs.cpfCnpj.substring(5, 8) + "/" +
                    attrs.cpfCnpj.substring(8, 12) + "-" + attrs.cpfCnpj.substring(12, 14)
        }
    }

    def billingTypeNotation = { Map attrs ->
        if (attrs.billingType == PaymentBillingType.BANK_SLIP) {
            out << "Boleto"
        }

        if (attrs.billingType == PaymentBillingType.DEBIT_CARD) {
            out << "Cartão de Débito"
        }

        if (attrs.billingType == PaymentBillingType.PIX) {
            out << "Pix"
        }
    }

    private String dateNotation(String format, Timestamp date) {
        return g.formatDate(format: format, date: date)
    }
}