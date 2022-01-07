package br.java.a05_validacaoformatacao.validadores;

import com.google.android.material.textfield.TextInputLayout;

public abstract class ValidadorPadrao {

    private static final String CAMPO_OBRIGATORIO = "campo obrigatório";
    private final TextInputLayout inputLayout;

    public ValidadorPadrao(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
    }

    @SuppressWarnings("ConstantConditions")
    protected boolean validaCampoObrigatorio() {
        String texto = inputLayout.getEditText().getText().toString();

        if (texto.isEmpty()) {
            this.inputLayout.setError(CAMPO_OBRIGATORIO);

            return false;
        }
        removeErro();

        return true;
    }
    public void removeErro() {
        this.inputLayout.setError(null);
        this.inputLayout.setErrorEnabled(false);
    }

    @SuppressWarnings("unused")
    public abstract boolean valida();

}
