package gocharges.validator

import gocharges.exception.BusinessException

class CpfCnpjValidator {

     public static void validate(String cpfCnpj) {
         Integer cpfCnpjLength = cpfCnpj.length()
         if(cpfCnpjLength != 11 && cpfCnpjLength != 14){
             throw new BusinessException("Informe um tamanho de CPF / CNPJ correto.")
         }
    }
}
