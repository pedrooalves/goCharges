package gocharges.controller.base

import gocharges.Customer
import gocharges.auth.User
import gocharges.exception.BusinessException
import grails.plugin.springsecurity.SpringSecurityService
import shared.FlashMessageType

abstract class BaseController {

    SpringSecurityService springSecurityService

    public Customer getCurrentCustomer() {
        return springSecurityService.getCurrentUser().customer
    }

    public User getCurrentUser() {
        return (User) springSecurityService.getCurrentUser()
    }

    def handleBusinessException(BusinessException businessException) {
        flash.message = businessException.getMessage()
        flash.type = FlashMessageType.ERROR
        redirect(uri: request.getHeader('referer'))
    }

    def handleException(Exception exception) {
        flash.message = "Erro inesperado, tente novamente mais tarde"
        flash.type = FlashMessageType.ERROR
        redirect(uri: request.getHeader('referer'))
    }
}