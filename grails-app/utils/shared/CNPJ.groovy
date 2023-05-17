package shared

import java.lang.reflect.Array

class CNPJ extends DocumentoIdentificador {

    String numero

    CNPJ(String numero) {
        validaCNPJ(numero)
        this.numero = numero
    }

    String getNumero() {
        return this.numero;
    }

    void setNumero(String numero) {
        validaCNPJ(numero)
        this.numero = numero
    }

    private boolean validarCalculoCnpj(String cnpjRecebido) {
        if(cnpjRecebido == null || !(cnpjRecebido.matches(/\d{14}/))) {
            return false
        }
        int[] multiplicadores = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}
        String cnpjCalculado = loopCalculo(cnpjRecebido.substring(0, 12), multiplicadores)

        return cnpjCalculado == cnpjRecebido
    }

    void validaCNPJ(String numero) {
        if (!(validarCalculoCnpj(numero))) {
            throw new IllegalArgumentException("CNPJ inválido")
        }
    }
}
