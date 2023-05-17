package shared

abstract class DocumentoIdentificador {

    protected String loopCalculo(String documentoCalculado, int[] multiplicadores) {
        double soma1 = 0
        double soma2 = 0
        int resultadoParaCalculoDoDigito
        int digito1
        int digito2

        for (i in 0..<multiplicadores.size() - 1) {
            soma1 += Integer.parseInt(documentoCalculado[i]) * multiplicadores[i + 1]
            soma2 += Integer.parseInt(documentoCalculado[i]) * multiplicadores[i]
        }

        resultadoParaCalculoDoDigito = 11 - soma1 % 11
        digito1 = resultadoParaCalculoDoDigito >= 10 ? 0 : resultadoParaCalculoDoDigito
        soma2 += digito1 * 2
        resultadoParaCalculoDoDigito = 11 - soma2 % 11
        digito2 = resultadoParaCalculoDoDigito >= 10 ? 0 : resultadoParaCalculoDoDigito
        documentoCalculado += digito1.toString() + digito2.toString()


        return documentoCalculado
    }
}
