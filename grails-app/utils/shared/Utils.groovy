package shared

import grails.util.Holders

import java.text.NumberFormat
import java.text.SimpleDateFormat

public class Utils {

    static def messageSource = Holders.grailsApplication.mainContext.getBean("messageSource")

    public static String removeNonNumeric(String text) {
        if (!text) return null
        return text?.replaceAll("\\D+", "")
    }

    public static String getMessageProperty(String code, String[] args) {
        messageSource.getMessage(code, args, null)
    }

    public static getCurrencyWithoutMonetarySimbol(BigDecimal value) {
        Locale localeBR = new Locale("pt", "BR")
        NumberFormat numberFormat = NumberFormat.getInstance(localeBR)
        numberFormat.setMinimumFractionDigits(2)

        return numberFormat.format(value)
    }

    public static getBrazilDateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy")

        return simpleDateFormat.format(date)
    }
}
