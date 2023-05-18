package shared

class Email {
    private String endereco
    Email(String endereco) {
        validaEnderecoEmail(endereco)
        this.endereco = endereco
    }
    void setEndereco(String endereco) {
        validaEnderecoEmail(endereco)
        this.endereco = endereco
    }
    String getEndereco() {
        return this.endereco
    }
    static void validaEnderecoEmail(String endereco) {
        if (endereco == null || !(endereco.matches(/[a-zA-Z0-9.'_%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,4}/))) {
            throw new IllegalArgumentException("Email inválido")
        }
    }
}