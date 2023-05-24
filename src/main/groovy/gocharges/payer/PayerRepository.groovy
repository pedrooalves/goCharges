package gocharges.payer

import gocharges.Payer
import grails.gorm.DetachedCriteria

class PayerRepository {
    public static DetachedCriteria<Payer> query(Map search) {
        DetachedCriteria<Payer> query = Payer.where {
            if(search.containsKey("cpfCnpj")) {
                eq("cpfCnpj", search.cpfCnpj)
            }

            if(!Boolean.valueOf(search.includeDeleted)) {
                println("includeDeleted")
                eq("deleted", false)
            }
        }
        return query
    }
}
