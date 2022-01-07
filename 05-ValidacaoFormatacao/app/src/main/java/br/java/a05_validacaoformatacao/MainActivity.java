package br.java.a05_validacaoformatacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_cadastrar;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_cpf;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_email;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_nome_completo;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_senha;
import static br.java.a05_validacaoformatacao.R.id.formulario_cadastro_telefone;

import br.java.a05_validacaoformatacao.validadores.Validador;

public class MainActivity extends AppCompatActivity {

    private static final String APPBAR = "Cadastro";
    private static final String CADASTRO_REALIZADO_COM_SUCESSO = "Cadastro realizado com sucesso!";
    private Set<TextInputLayout> inputLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(APPBAR);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Um HashSet é implementado como uma tabela hash (na verdade uma instância de HashMap
        // é usada como apoio), uma matriz na qual os elementos são armazenados em uma posição
        // derivada de seu conteúdo.
        inputLayouts = new HashSet<>();

        configuraCampo(formulario_cadastro_nome_completo);
        configuraCampo(formulario_cadastro_cpf);
        configuraCampo(formulario_cadastro_telefone);
        configuraCampo(formulario_cadastro_email);
        configuraCampo(formulario_cadastro_senha);
        configuraBotao();
    }

    @SuppressWarnings("ConstantConditions")
    private void configuraCampo(int inputLayoutId) {
        final TextInputLayout inputLayout = findViewById(inputLayoutId);
        final EditText campo = inputLayout.getEditText();
        inputLayouts.add(inputLayout);
        campo.setOnFocusChangeListener((v, hasFocus) -> quandoMudaDeFoco(inputLayout, hasFocus));
    }
    private void quandoMudaDeFoco(TextInputLayout inputLayout, boolean hasFocus) {
        Validador validador = new Validador(inputLayout);

        if (hasFocus) {
            validador.limpaCampo();
        } else {
            validador.valida();
        }

    }

    private void configuraBotao() {
        Button cadastrar = findViewById(formulario_cadastro_cadastrar);
        cadastrar.setOnClickListener(v -> quandoClicado());
    }

    private void quandoClicado() {
        final List<Boolean> validacaoBemSucedida = new ArrayList<>();

        validaTodosOsCampos(validacaoBemSucedida);

        if (!validacaoBemSucedida.contains(false)) {
            Toast.makeText(this, CADASTRO_REALIZADO_COM_SUCESSO, Toast.LENGTH_SHORT).show();
        }
    }
    private void validaTodosOsCampos(List<Boolean> validacaoBemSucedida) {
        for (TextInputLayout inputLayout : inputLayouts) {
            final Validador validador = new Validador(inputLayout);
            final boolean ehValido = validador.valida();
            validacaoBemSucedida.add(ehValido);

            if (ehValido) {
                validador.removeErro();
            }
        }
    }
}