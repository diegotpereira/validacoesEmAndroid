package br.java.a02_validacaocampos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

      private ViewHolder mViewRegistro = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.mViewRegistro.btnSalvar = this.findViewById(R.id.btnSalvar);
        this.mViewRegistro.editEmail = this.findViewById(R.id.editEmail);
        this.mViewRegistro.editCpf = this.findViewById(R.id.editCpf);
        this.mViewRegistro.editNome = this.findViewById(R.id.editNome);
        this.mViewRegistro.editTelefone = this.findViewById(R.id.editTelefone);
        this.mViewRegistro.cbxConfirmar = this.findViewById(R.id.cbxConfirmar);

        this.mViewRegistro.btnSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSalvar) {
            if (validaCampos() == false) {
                MensagemDeErro("Dados incorretos");
            } else {
                MensagemDeErro("Dados Corretos");
            }
        }
    }

    private boolean validaCampos() {
        boolean resultado = false;

        if (resultado = eCampoVazio(this.mViewRegistro.editNome.getText().toString())) {
            this.mViewRegistro.editNome.requestFocus();
        }
        if (resultado = eCpfVazio(this.mViewRegistro.editCpf.getText().toString())) {
            this.mViewRegistro.editCpf.requestFocus();
        }
        if (resultado = eTelefoneVazio(this.mViewRegistro.editTelefone.getText().toString())) {
            this.mViewRegistro.editTelefone.requestFocus();
        }
        if (resultado = !eEmailValido(this.mViewRegistro.editEmail.getText().toString())) {
            this.mViewRegistro.editEmail.setError("Email inválido");
            this.mViewRegistro.editEmail.requestFocus();
        }

        if (!this.mViewRegistro.cbxConfirmar.isChecked()) {
            resultado = false;
        } else {
            resultado = true;
        }

        return resultado;
    }

    private boolean eCampoVazio(String valor) {

        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }

    private boolean eCpfVazio(String valor) {

        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }
    private boolean eTelefoneVazio(String valor) {

        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }
    private boolean eEmailValido(String email) {

        return (!eCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public void MensagemDeErro(String mensagem) {
        AlertDialog.Builder msg = new AlertDialog.Builder(RegistroActivity.this);
        msg.setMessage(mensagem);
        msg.setNeutralButton("Sair", null);
        msg.create();
        msg.show();
    }
    private static class ViewHolder {
        private Button btnSalvar;
        private EditText editNome;
        private EditText editCpf;
        private EditText editTelefone;
        private EditText editEmail;
        private CheckBox cbxConfirmar;
    }
}