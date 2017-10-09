package jrl.acdat.acdat_tema1;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Ejercicio1 extends Activity implements View.OnClickListener{

    EditText edtCambio, edtEuros, edtDolares;
    RadioButton rdbADolares, rdbAEuros;
    Button btnConvertir;
    Button btnVolver;
    Toast mensaje;
    double valorCambio;
    Conversion conversor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        edtCambio = (EditText)findViewById(R.id.edtCambio);
        edtDolares = (EditText)findViewById(R.id.edtDolares);
        edtEuros = (EditText)findViewById(R.id.edtEuros);
        rdbAEuros = (RadioButton)findViewById(R.id.rdbAEuros);
        rdbADolares = (RadioButton)findViewById(R.id.rdbADolares);
        btnConvertir = (Button)findViewById(R.id.btnConvertir);
        btnConvertir.setOnClickListener(this);
        btnVolver = (Button)findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

        edtEuros.setEnabled(true);
        edtDolares.setEnabled(false);
        edtDolares.setTextColor(ColorStateList.valueOf(Color.BLACK));

        rdbAEuros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    edtDolares.requestFocus();
                    edtDolares.setEnabled(true);
                    edtEuros.setEnabled(false);
                    edtEuros.setTextColor(ColorStateList.valueOf(Color.BLACK));
                }
            }
        });
        rdbADolares.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    edtEuros.requestFocus();
                    edtEuros.setEnabled(true);
                    edtDolares.setEnabled(false);
                    edtDolares.setTextColor(ColorStateList.valueOf(Color.BLACK));
                }
            }
        });
    }

    public void onClick(View v) {
        if(v == btnVolver) {
            finish();
        }
        if (v == btnConvertir) {
            try {
                valorCambio = Double.valueOf(edtCambio.getText().toString());
                conversor = new Conversion(valorCambio);
            } catch (Exception e) {
                mensaje = Toast.makeText(getApplicationContext(), "Introduzca un valor en Euros para el cambio en Dolares", Toast.LENGTH_SHORT);
                mensaje.show();
            }
            if (rdbADolares.isChecked()) {
                try {
                    String resultado = conversor.convertirADolares(edtEuros.getText().toString());
                    edtDolares.setText(resultado.replace(',','.'));
                }
                catch (Exception e) {
                    mensaje = Toast.makeText(getApplicationContext(), "Introduzca una cantidad en Euros", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
            }
            if (rdbAEuros.isChecked()) {
                try {
                    String resultado = conversor.convertirAEuros(edtDolares.getText().toString());
                    edtEuros.setText(resultado.replace(',','.'));
                }
                catch (Exception e) {
                    mensaje = Toast.makeText(getApplicationContext(), "Introduzca una cantidad en Dolares", Toast.LENGTH_SHORT);
                    mensaje.show();
                }
            }
        }
    }
}
