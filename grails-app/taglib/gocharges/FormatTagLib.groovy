package gocharges

import shared.Utils

import javax.swing.text.MaskFormatter
import java.sql.Timestamp
import java.text.NumberFormat

class FormatTagLib {

    static namespace = "formatTagLib"

    def time = { Map attrs ->
        out << dateNotation("HH:mm", attrs.date)
    }

    def brazilDate = { Map attrs ->
        out << dateNotation("dd/MM/yyyy", attrs.date)
    }

    def isoDate = { Map attrs ->
        out << dateNotation("yyyy-MM-dd", attrs.date)
    }

    def billingType = { Map attrs ->
        String messageCode = "BillingType." + attrs.billingType.toString()
        out << Utils.getMessageProperty(messageCode, null)
    }

    def currencyWithoutMonetarySimbol = { Map attrs ->
        Locale localeBR = new Locale("pt", "BR")
        NumberFormat numberFormat = NumberFormat.getInstance(localeBR)
        numberFormat.setMinimumFractionDigits(2)

        if (!attrs.value) {
            out << numberFormat.format(0)
            return
        }

        out << numberFormat.format(attrs.value)
    }

    def cpfCnpj = { Map attrs ->
        MaskFormatter cpfFormat = new MaskFormatter("###.###.###-##")
        MaskFormatter cnpjFormat = new MaskFormatter("##.###.###/####-##")
        cpfFormat.setValueContainsLiteralCharacters(false)
        cnpjFormat.setValueContainsLiteralCharacters(false)

        if (!attrs.cpfCnpj) return

        if (attrs.cpfCnpj.length() == 11) {
            return out << cpfFormat.valueToString(attrs.cpfCnpj)
        }

        if (attrs.cpfCnpj.length() == 14) {
            out << cnpjFormat.valueToString(attrs.cpfCnpj)
        }
    }

    private String dateNotation(String format, Timestamp date) {
        return g.formatDate(format: format, date: date)
    }
}