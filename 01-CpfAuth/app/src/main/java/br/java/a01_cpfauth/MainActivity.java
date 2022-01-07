package br.java.a01_cpfauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.java.a01_cpfauth.mascara.Mascara;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private Spinner spinner;
    private String cpf;
    private TextView tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(Mascara.insert(Mascara.CPF_MASC, editText));

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJson();
                cpf = editText.getText().toString().replace(".", "")
                        .replace(" ", "")
                        .replace("-", "");
            }
        });
        spinner = findViewById(R.id.option);
        tipo = findViewById(R.id.tipo);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinner.getSelectedItem().toString();
                editText.setText(text);
                cpf = editText.getText().toString().replace(".", "")
                        .replace(" ", "")
                        .replace("-", "");

                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        tipo.setText(R.string.regular);
                        break;
                    case 4:
                    case 5:
                        tipo.setText(R.string.suspensa);
                        break;
                    case 6:
                    case 7:
                        tipo.setText(R.string.pendente_regul);
                        break;
                    case 8:
                    case 9:
                        tipo.setText(R.string.cancelada_mult);
                        break;
                    case 10:
                    case 11:
                        tipo.setText(R.string.nula);
                        break;
                    case 12:
                    case 13:
                        tipo.setText(R.string.cancelada_ofic);
                        break;
                    case 14:
                    case 15:
                        tipo.setText(R.string.falecido);
                        break;

                    default:
                        tipo.setText(R.string.invalido);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getJson() {

    }
}