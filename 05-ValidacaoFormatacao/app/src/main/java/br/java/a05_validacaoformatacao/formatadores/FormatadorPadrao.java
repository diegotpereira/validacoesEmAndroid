package br.java.a05_validacaoformatacao.formatadores;

@SuppressWarnings("unused")
public interface FormatadorPadrao {

    String formata(String textoNaoFormatado);

    String desformata(String textoFormatado);
}
