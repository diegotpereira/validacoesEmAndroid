package br.java.a04_app_validacep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.java.a04_app_validacep.config.ApiConfig;
import br.java.a04_app_validacep.modelo.Cep;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etCep;
    TextView resultado;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCep = findViewById(R.id.etCEP);
        resultado = findViewById(R.id.resultado);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FinalCep = etCep.getText().toString();

                Call<Cep> call =  new ApiConfig().getCepService().buscarCep(FinalCep);

                call.enqueue(new Callback<Cep>() {
                    @Override
                    public void onResponse(Call<Cep> call, Response<Cep> response) {
                        Cep cep = response.body();
                        resultado.setText(cep.toString());
                    }

                    @Override
                    public void onFailure(Call<Cep> call, Throwable t) {
                        Log.e("CepService", "Erro ao buscar o cep:" + t.getMessage());
                    }
                });
            }
        });
    }
}