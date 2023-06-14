package gocharges

import shared.Utils
import java.sql.Timestamp

class FormatTagLib {

    static namespace = "formatTagLib"

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

    private String dateNotation(String format, Timestamp date) {
        return g.formatDate(format: format, date: date)
    }
}