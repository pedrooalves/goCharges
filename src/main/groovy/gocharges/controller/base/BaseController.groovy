package gocharges.controller.base

import gocharges.Customer
import grails.plugin.springsecurity.SpringSecurityService

public abstract class BaseController {

    SpringSecurityService springSecurityService

    public Customer getCurrentCustomer() {
        return springSecurityService.getCurrentUser().customer
    }
}