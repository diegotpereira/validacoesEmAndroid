package br.java.a05_validacaoformatacao.formatadores;

import br.com.caelum.stella.format.CPFFormatter;

public class FormatadorCPF implements FormatadorPadrao{

    private final CPFFormatter formatador;

    public FormatadorCPF() {

        this.formatador = new CPFFormatter();
    }
    @Override
    public String formata(String textoNaoFormatado) {

        if (formatador.canBeFormatted(textoNaoFormatado)) {

            return formatador.format(textoNaoFormatado);
        }

        return textoNaoFormatado;
    }

    @Override
    public String desformata(String textoFormatado) {

        if (formatador.isFormatted(textoFormatado)) {

            return formatador.unformat(textoFormatado);
        }

        return textoFormatado;
    }
}
