package jrl.acdat.acdat_tema1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ejercicio2 extends Activity implements OnClickListener {

    static double PULGADA = 2.54;
    double pulgadas = 0.0;
    double centimetros = 0.0;
    EditText caja;
    Button convertir;
    Button volver;
    Toast mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        caja = (EditText)findViewById(R.id.edtCentimetros);
        convertir = (Button)findViewById(R.id.btnConvertir);
        convertir.setOnClickListener(this);
        volver = (Button)findViewById(R.id.btnVolver);
        volver.setOnClickListener(this);
    }

    private double CalcularPulgadas(double cm) {
        if (cm < 0)
            cm = 0;
        return cm / 2.54;
    }

    @Override
    public void onClick(View v) {
        if(v == volver) {
            finish();
        }
        if(v == convertir) {
            try {
                String n;       // Variable usada para escribir el plural de la palabra "equivale" dentro del mensaje
                centimetros = Double.valueOf(caja.getText().toString());
                pulgadas = CalcularPulgadas(centimetros);
                if(centimetros > 1)
                    n = "n";
                else
                    n = "";
                mensaje = Toast.makeText(getApplicationContext(), centimetros + " cm equivale" + n + " a " + String.format("%.2f", pulgadas) + " pulgadas", Toast.LENGTH_LONG);
                mensaje.show();
            }
            catch (Exception e) {
                mensaje = Toast.makeText(getApplicationContext(), "Introduzca una cantidad en centímetros", Toast.LENGTH_SHORT);
                mensaje.show();
            }
        }
    }
}