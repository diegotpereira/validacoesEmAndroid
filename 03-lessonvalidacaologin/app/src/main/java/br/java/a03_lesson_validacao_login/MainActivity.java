package br.java.a03_lesson_validacao_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public EditText emailField;
    public EditText passwordField;
    public Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configurar();
    }

    protected void Configurar() {
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        enterButton = findViewById(R.id.enter);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Enter();
            }
        });
    }

    protected void Enter() {
        emailField.setError(null);
        passwordField.setError(null);

        ocultarTeclado();

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        switch (Validar(email, password)) {
            case 10:
                emailField.setError("O campo de e-mail é obrigatório.");
                emailField.requestFocus();
                break;

            case 11:
                emailField.setError("O campo de e-mail parece inválido.");
                emailField.requestFocus();
                break;

            case 20:
                passwordField.setError("O campo de senha é obrigatório.");
                passwordField.requestFocus();
                break;

            case 21:
                passwordField.setError("O campo de senha precisa ter no mínimo de 6 caracteres.");
                passwordField.requestFocus();
                break;

            case 22:
                passwordField.setError("O campo de senha parece inválido.");
                passwordField.requestFocus();
                break;

            default:
                Autenticar(email, password);
                break;
        }
    }

    protected int Validar(String email, String password) {

        if (TextUtils.isEmpty(email)) return 10;

        Pattern emailRegex = Pattern.compile(
                "^[a-z0-9._-]+@([a-z0-9]+\\.)+[a-z0-9]+$",
                Pattern.CASE_INSENSITIVE
        );
        if (emailRegex.matcher(email).matches() == false) return 11;

        if (TextUtils.isEmpty(password)) return 20;

        if (password.length() < 6) return 21;

        if (!password.matches("^[a-zA-Z_0-9]+$")) return 22;

        return 200;
    }
    protected  void Autenticar(String email, String password) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Autenticação");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        if (email.matches("^joao@hotmail\\.com|\\w+@gmail.com$")) {
            builder.setMessage("Login realizado com sucesso!");
        } else {
            builder.setMessage("Usuário inválido, tente novamente.");
        }
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    protected void ocultarTeclado() {
        View focus = getCurrentFocus();
        if (focus == null) return;

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (manager == null) return;

        manager.hideSoftInputFromWindow(focus.getWindowToken(), 0);
    }
}