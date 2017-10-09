package jrl.acdat.acdat_tema1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio5 extends Activity implements OnClickListener {

    boolean puntoAct = false;
    boolean op2Act = false;
    double op1 = 0.0;
    double op2 = 0.0;
    char operador;
    String resultado = "0";
    String texto = "0";
    Button reiniciar, punto, igual, suma, resta, producto, division;
    Button cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve;
    Button volver;
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio5);

        salida = (TextView)findViewById(R.id.txvSalida);
        reiniciar = (Button)findViewById(R.id.btnReiniciar);
        reiniciar.setOnClickListener(this);
        punto = (Button)findViewById(R.id.btnPunto);
        punto.setOnClickListener(this);
        igual = (Button)findViewById(R.id.btnIgual);
        igual.setOnClickListener(this);
        suma = (Button)findViewById(R.id.btnSuma);
        suma.setOnClickListener(this);
        resta = (Button)findViewById(R.id.btnResta);
        resta.setOnClickListener(this);
        producto = (Button)findViewById(R.id.btnProducto);
        producto.setOnClickListener(this);
        division = (Button)findViewById(R.id.btnDivision);
        division.setOnClickListener(this);
        cero = (Button)findViewById(R.id.btnCero);
        cero.setOnClickListener(this);
        uno = (Button)findViewById(R.id.btnUno);
        uno.setOnClickListener(this);
        dos = (Button)findViewById(R.id.btnDos);
        dos.setOnClickListener(this);
        tres = (Button)findViewById(R.id.btnTres);
        tres.setOnClickListener(this);
        cuatro = (Button)findViewById(R.id.btnCuatro);
        cuatro.setOnClickListener(this);
        cinco = (Button)findViewById(R.id.btnCinco);
        cinco.setOnClickListener(this);
        seis = (Button)findViewById(R.id.btnSeis);
        seis.setOnClickListener(this);
        siete = (Button)findViewById(R.id.btnSiete);
        siete.setOnClickListener(this);
        ocho = (Button)findViewById(R.id.btnOcho);
        ocho.setOnClickListener(this);
        nueve = (Button)findViewById(R.id.btnNueve);
        nueve.setOnClickListener(this);

        volver = (Button)findViewById(R.id.btnVolver);
        volver.setOnClickListener(this);

        suma.setEnabled(true);
        resta.setEnabled(true);
        producto.setEnabled(true);
        division.setEnabled(true);
        igual.setEnabled(false);
    }

    public double leerNumero(String cadena) {
        return Double.parseDouble(cadena);
    }

    public String formatearNumero(double numero) {
        if(String.valueOf(numero).length()>=18) {
            return String.format("%6.10g", numero);
        } else {
            if (numero == 0 || Double.valueOf(String.format("%.0f", numero)) / numero == 1)
                return String.format("%.0f", numero);
            else
                return String.valueOf(numero);
        }
    }

    public String calcular(double op1, char operador, double op2) {
        Toast mensaje;
        switch(operador) {
            case '+': return String.valueOf(op1 + op2);
            case '-': return String.valueOf(op1 - op2);
            case '*': return String.valueOf(op1 * op2);
            case '/':
                if (op2 == 0) {
                    mensaje = Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT);
                    mensaje.show();
                    return "0";
                } else {
                    return String.valueOf(op1 / op2);
                }
            default:
                mensaje = Toast.makeText(this, "Operador no válido", Toast.LENGTH_SHORT);
                mensaje.show();
                return "0";
        }
    }

    @Override
    public void onClick(View v) {
        if (v == volver) {
            finish();
        }
        if (v == reiniciar) {
            puntoAct = false;
            op2Act = false;
            resultado = "0";
            texto = "0";
            op1 = 0;
            op2 = 0;
            salida.setText(texto);
        }
        if (v == suma || v == resta || v == producto || v == division || v == igual) {
            puntoAct = false;
            if (v == suma) {
                operador = '+';
            }
            if (v == resta) {
                operador = '-';
            }
            if (v == producto) {
                operador = '*';
            }
            if (v == division) {
                operador = '/';
            }
            if (v == igual) {
                resultado = calcular(op1, operador, op2);
                salida.setText(formatearNumero(Double.valueOf(resultado)));
                op1 = Double.valueOf(resultado);
            }
            op2Act = !op2Act;
            texto = "0";
        }

        // Números

        if (v == cero) {
            if(!texto.equals("0") && !texto.equals(""))
                texto += "0";
            salida.setText(texto);
        }
        if (v == uno) {
            if (texto.equals("0"))
                texto = "";
            texto += "1";
            salida.setText(texto);
        }
        if (v == dos) {
            if (texto.equals("0"))
                texto = "";
            texto += "2";
            salida.setText(texto);
        }
        if (v == tres) {
            if (texto.equals("0"))
                texto = "";
            texto += "3";
            salida.setText(texto);
        }
        if (v == cuatro) {
            if (texto.equals("0"))
                texto = "";
            texto += "4";
            salida.setText(texto);
        }
        if (v == cinco) {
            if (texto.equals("0"))
                texto = "";
            texto += "5";
            salida.setText(texto);
        }
        if (v == seis) {
            if (texto.equals("0"))
                texto = "";
            texto += "6";
            salida.setText(texto);
        }
        if (v == siete) {
            if (texto.equals("0"))
                texto = "";
            texto += "7";
            salida.setText(texto);
        }
        if (v == ocho) {
            if (texto.equals("0"))
                texto = "";
            texto += "8";
            salida.setText(texto);
        }
        if (v == nueve) {
            if (texto.equals("0"))
                texto = "";
            texto += "9";
            salida.setText(texto);
        }
        if (v == punto) {
            if (!puntoAct) {
                texto += ".";
                puntoAct = true;
            }
            salida.setText(texto);
        }
        if (op2Act) {
            op2 = leerNumero(texto);
            suma.setEnabled(false);
            resta.setEnabled(false);
            producto.setEnabled(false);
            division.setEnabled(false);
            igual.setEnabled(true);
        } else {
            if(Double.valueOf(resultado) == 0) {
                op1 = leerNumero(texto);
            }
            suma.setEnabled(true);
            resta.setEnabled(true);
            producto.setEnabled(true);
            division.setEnabled(true);
            igual.setEnabled(false);
        }
    }
}