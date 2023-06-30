package gocharges.customer

import gocharges.Customer
import grails.gorm.DetachedCriteria

class CustomerRepository {

    public static DetachedCriteria<Customer> query(Map search) {
        DetachedCriteria<Customer> query = Customer.where {
            if (search.containsKey("cpfCnpj")) {
                eq("cpfCnpj", search.cpfCnpj)
            }

            if (search.containsKey("cpfCnpj[like]")) {
                like("cpfCnpj", "%" + search.get("cpfCnpj[like]") + "%")
            }

            if (!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if (search.containsKey("email")) {
                eq("email", search.email)
            }

            if (search.containsKey("email[ilike]")) {
                ilike("email", "%" + search.get("email[ilike]") + "%")
            }

            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id))
            }

            if (search.containsKey("id[ne]")) {
                ne("id", Long.valueOf(search.get("id[ne]")))
            }

            if (search.containsKey("name[ilike]")) {
                ilike("name", "%" + search.get("name[ilike]") + "%")
            }

            if (search.containsKey("mobilePhone[like]")) {
                like("mobilePhone", "%" + search.get("mobilePhone[like]") + "%")
            }
        }

        return query
    }
}