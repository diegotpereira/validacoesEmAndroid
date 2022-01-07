package br.java.a05_validacaoformatacao.validadores;

import com.google.android.material.textfield.TextInputLayout;

import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_cpf;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_email;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_telefone;

public class Validador extends ValidadorPadrao{

    private final TextInputLayout inputLayout;
    private  final int inputLayoutId;

    public Validador(TextInputLayout inputLayout) {
        super(inputLayout);
        this.inputLayout = inputLayout;
        this.inputLayoutId = inputLayout.getId();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean valida() {
        String texto = inputLayout.getEditText().getText().toString();

        if (texto.isEmpty()) return super.validaCampoObrigatorio();

        switch (inputLayoutId) {
            case formulario_cadastro_cpf:
                return new ValidadorCpf(inputLayout).valida();

            case formulario_cadastro_telefone:
                return new ValidadorTelefone(inputLayout).valida();

            case formulario_cadastro_email:
                return new ValidadorEmail(inputLayout).valida();

            default:
                return true;
        }
    }

    public void limpaCampo() {
        super.removeErro();

        switch (inputLayoutId) {
            case formulario_cadastro_cpf:
                new ValidadorCpf(inputLayout).removeErro();
                break;

            case formulario_cadastro_telefone:
                new ValidadorTelefone(inputLayout).removeErro();
                break;

            default:
                break;
        }
    }
}
