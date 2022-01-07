package br.java.a05_validacaoformatacao.validadores;

import com.google.android.material.textfield.TextInputLayout;

public class ValidadorEmail {

    private static final String REGEX_EMAIL_VALIDO = ".+@.+\\..+";
    private static final String INSIRA_UM_EMAIL_VALIDO = "Insira um email válido";
    private final TextInputLayout inputLayout;

    public ValidadorEmail(TextInputLayout inputLayout) {
        this.inputLayout = inputLayout;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean valida() {
        String email = inputLayout.getEditText().getText().toString();

        if (email.matches(REGEX_EMAIL_VALIDO)) {

            return true;
        }
        inputLayout.setError(INSIRA_UM_EMAIL_VALIDO);

        return false;
    }
}
