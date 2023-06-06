package gocharges

import java.sql.Date
import java.sql.Timestamp

class GoChargesFormatTagLib {

    static namespace = "format"

    def brazilDateNotation = { Map attrs ->
        out << dateNotation("dd/MM/yyyy", attrs.date)
    }

    private String dateNotation(String format, Timestamp date) {
        return g.formatDate(format: format, date: date)
    }
}
