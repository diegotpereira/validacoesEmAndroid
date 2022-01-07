package br.java.a05_validacaoformatacao.validadores;

import com.google.android.material.textfield.TextInputLayout;

import br.java.a05_validacaoformatacao.formatadores.FormatadorTelefone;

public class ValidadorTelefone {

    private static final String DEVE_CONTER_DEZ_OU_ONZE_DIGITOS =
            "O número de telefone deve conter 10 ou 11 digitos (incluindo DDD)";
    private final TextInputLayout inputLayout;
    private final FormatadorTelefone formatador;

    public ValidadorTelefone(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
        this.formatador = new FormatadorTelefone();
    }

    public void removeErro() {
        String telefoneFormatado = inputLayout.getEditText().getText().toString();
        String telefone = formatador.desformata(telefoneFormatado);
        inputLayout.getEditText().setText(telefone);
    }

    public boolean valida() {
        removeErro();
        String telefone = inputLayout.getEditText().getText().toString();
        final int digitos = telefone.length();

        if (naoEhTelefoneComDDD(digitos)) {
            inputLayout.setError(DEVE_CONTER_DEZ_OU_ONZE_DIGITOS);

            return false;
        }

        formata(telefone);

        return true;
    }

    private boolean naoEhTelefoneComDDD(int digitos) {

        return digitos < 10 || digitos > 11;
    }

    private void formata(String telefone) {
        String telefoneFormatado = formatador.formata(telefone);
        inputLayout.getEditText().setText(telefoneFormatado);
    }
}
