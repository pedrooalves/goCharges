package shared.documentos

class CPF extends DocumentoIdentificador {
    String numero
    CPF(String numero) {
        validaCPF(numero)
        this.numero = numero
    }
    String getNumero() {
        return this.numero
    }
    void setNumero(String numero) {
        validaCPF(numero)
        this.numero = numero
    }
    private boolean validarCpf(String cpfRecebido) {
        if(cpfRecebido == null || !(cpfRecebido.matches(/\d{11}/))) {
            return false
        }
        int[] multiplicadores = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}
        String cpfCalculado = loopCalculo(cpfRecebido.substring(0, 9), multiplicadores)
        return cpfCalculado == cpfRecebido
    }
    void validaCPF(String numero) {
        if (!(validarCpf(numero))) {
            throw new IllegalArgumentException("CPF inválido")
        }
    }
}