package gocharges.validator

import gocharges.exception.BusinessException

class CpfCnpjValidator {

     public static void validate(String cpfCnpj) {
        if(cpfCnpj.length() != 11 && cpfCnpj.length() != 14){
            throw new BusinessException("Informe um tamanho de CPF / CNPJ correto.")
        }
    }
}
