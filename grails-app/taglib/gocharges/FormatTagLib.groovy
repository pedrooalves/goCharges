package gocharges

import shared.Utils
import java.sql.Timestamp

class FormatTagLib {

    static namespace = "FormatTagLib"

    def brazilDateNotation = { Map attrs ->
        out << dateNotation("dd/MM/yyyy", attrs.date)
    }

    def billingTypeNotation = { Map attrs ->
        String messageCode = "BillingType." + attrs.billingType.toString()
        out << Utils.getMessageProperty(messageCode, null)
    }

    private String dateNotation(String format, Timestamp date) {
        return g.formatDate(format: format, date: date)
    }
}