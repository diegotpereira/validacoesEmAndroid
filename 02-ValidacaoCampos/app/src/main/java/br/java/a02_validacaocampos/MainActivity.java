package br.java.a02_validacaocampos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewMain = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewMain.btn_registrar = this.findViewById(R.id.btn_registrar);
        this.mViewMain.btn_registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_registrar) {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(intent);
        }
    }

    static class ViewHolder{
        private Button btn_registrar;
    }
}