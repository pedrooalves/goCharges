package shared

import grails.util.Holders

class Utils {

    static def messageSource = Holders.grailsApplication.mainContext.getBean("messageSource")

    public static String getMessageProperty(String code, String[] args) {
        messageSource.getMessage(code, args, null)
    }
}
