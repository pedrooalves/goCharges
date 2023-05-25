package gocharges.customer

import gocharges.Customer
import grails.gorm.DetachedCriteria

class CustomerRepository {

    public static DetachedCriteria<Customer> query(Map search) {
        DetachedCriteria<Customer> query = Customer.where {
            if(search.containsKey("cpfCnpj")) {
                eq("cpfCnpj", search.cpfCnpj)
            }

            if(!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if(search.containsKey("email")) {
                eq("email", search.email)
            }

            if(search.containsKey("id")) {
                eq("id", search.id)
            }
        }

        return query
    }
}