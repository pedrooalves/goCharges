package gocharges

import java.sql.Date
import java.sql.Timestamp

class FormatTagLib {

    static namespace = "formatTagLib"

    def brazilDateNotation = { Map attrs ->
        out << dateNotation("dd/MM/yyyy", attrs.date)
    }

    def isoDateNotation = { Map attrs ->
        out << dateNotation("yyyy-MM-dd", attrs.date)
    }

    private String dateNotation(String format, Timestamp date) {
        return g.formatDate(format: format, date: date)
    }
}
