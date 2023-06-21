package gocharges.controller.base

import gocharges.Customer
import gocharges.auth.User
import grails.plugin.springsecurity.SpringSecurityService

abstract class BaseController {

    SpringSecurityService springSecurityService

    public Customer getCurrentCustomer() {
        return springSecurityService.getCurrentUser().customer
    }

    public User getCurrentUser() {
        return (User) springSecurityService.getCurrentUser()
    }
}