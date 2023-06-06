package shared

import grails.util.Holders

class Utils {

    static def messageSource = Holders.grailsApplication.mainContext.getBean("messageSource")

    public static String removeNonNumeric(String text) {
        if (!text) return null
        return text?.replaceAll("\\D+", "")
    }

    public static String getMessageProperty(String code, String[] args) {
        messageSource.getMessage(code, args, null)
    }
}
