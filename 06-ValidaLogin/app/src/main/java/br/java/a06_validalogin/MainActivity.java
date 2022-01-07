package br.java.a06_validalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOGIN_PADRAO = "admin";
    private static final String SENHA_PADRAO = "password";

    private String loginDigitado;
    private String senhaDigitada;

    private EditText campoLogin;
    private EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void validarLogin(View view) {
        capturarDados(view);

        if (!LOGIN_PADRAO.equals(loginDigitado)) {
            mostrarMensagem("Login Incorreto, tente novamente");

        } else if (!SENHA_PADRAO.equals(senhaDigitada)) {
            mostrarMensagem("Senha Incorreto");
        } else {
            mostrarMensagem("Login efetuado com sucesso");
        }
    }

    private void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void capturarDados(View view) {
        obterFormulario(view);
        loginDigitado = campoLogin.getText().toString();
        senhaDigitada = campoSenha.getText().toString();
    }

    private void obterFormulario(View view) {
        campoLogin = (EditText) findViewById(R.id.et_login);
        campoSenha = (EditText) findViewById(R.id.et_senha);
    }

    public void  limparFormulario(View view) {
        obterFormulario(view);
        campoLogin.setText("");
        campoSenha.setText("");
    }
}