package com.goldenray.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText edtDado;
    TextView txtInfo;
    Button btnEnviar, btnRecuperar;
    static final String  chave ="dados";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializadorComponentes();
        txtInfo.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPref =getPreferences(Context.MODE_PRIVATE);

        //eventos click
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dadoaSalvar = edtDado.getText().toString();
                edtDado.setText(" ");
                if(dadoaSalvar.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Campo nulo",Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(chave, dadoaSalvar);
                    editor.commit();
                }
            }
        });
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dadoRecuperado = sharedPref.getString(chave,"0");// o Zero é caso não consiga recuperar
                if(dadoRecuperado.equals("0")){
                    Toast.makeText(getApplicationContext(),"Sem dados salvos",Toast.LENGTH_SHORT).show();
                }else {
                    txtInfo.setText(dadoRecuperado);
                    txtInfo.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void inicializadorComponentes(){
        edtDado = findViewById(R.id.edtDado);
        txtInfo = findViewById(R.id.txtInfo);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnRecuperar =findViewById(R.id.btnRecuperar);
    }

}