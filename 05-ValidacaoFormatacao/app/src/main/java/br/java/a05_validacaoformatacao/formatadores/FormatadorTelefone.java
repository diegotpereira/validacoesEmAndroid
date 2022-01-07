package br.java.a05_validacaoformatacao.formatadores;

public class FormatadorTelefone implements FormatadorPadrao{

    private static final String REGEX_TELEFONE_FORMATO = "([0-9]{2})([0-9]{4,5})([0-9]{4})";
    private static final String TELEFONE_FORMATADO = "($1) $2-$3";
    private static final String REGEX_CARACTERES_NAO_DIGITOS = "\\D+";
    private static final String ESPACO_VAZIO = "";

    @Override
    public String formata(String textoNaoFormatado) {

        if (!textoNaoFormatado.isEmpty()) {

            return textoNaoFormatado.replaceAll(REGEX_TELEFONE_FORMATO, TELEFONE_FORMATADO);
        }
        return textoNaoFormatado;
    }

    @Override
    public String desformata(String textoFormatado) {

        if (!textoFormatado.isEmpty()) {
            return textoFormatado.replaceAll(REGEX_CARACTERES_NAO_DIGITOS, ESPACO_VAZIO);
        }
        return textoFormatado;
    }
}
