package gocharges.payer

import gocharges.Payer
import grails.gorm.DetachedCriteria

class PayerRepository {

    public static DetachedCriteria<Payer> query(Map search) {
        DetachedCriteria<Payer> query = Payer.where {
            if (!Boolean.valueOf(search.ignoreCustomer) && !search.containsKey("customer")) {
                throw new RuntimeException("O atributo customer é obrigatório para executar a consulta.")
            }

            if (search.containsKey("cpfCnpj")) {
                eq("cpfCnpj", search.cpfCnpj)
            }

            if(search.containsKey("cpfCnpj[ilike]")) {
                ilike("cpfCnpj", "%${search.get("cpfCnpj[ilike]")}%")
            }


            if (search.containsKey("email")) {
                eq("email", search.email)
            }

            if(search.containsKey("email[ilike]")) {
                ilike("email", "%${search.get("email[ilike]")}%")
            }

            if (Boolean.valueOf(search.deletedOnly)) {
                eq("deleted", true)
            } else if (!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id))
            }

            if (search.containsKey("id[ne]")) {
                ne("id", Long.valueOf(search."id[ne]"))
            }

            if (search.containsKey("customer")) {
                eq("customer", search.customer)
            }

            if(search.containsKey("name[ilike]")) {
                ilike("name", "%${search.get("name[ilike]")}%")
            }

            if(search.containsKey("mobilePhone[ilike]")) {
                ilike("mobilePhone", "%${search.get("mobilePhone[ilike]")}%")
            }

            if(search.containsKey("city[ilike]")) {
                ilike("city", "%${search.get("city[ilike]")}%")
            }

            if(search.containsKey("state[ilike]")) {
                ilike("state", "%${search.get("state[ilike]")}%")
            }
        }
        return query
    }
}
