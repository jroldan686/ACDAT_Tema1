package jrl.acdat.acdat_tema1;


import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Ejercicio3 extends Activity implements View.OnClickListener {

    TextView cafes, tiempo;
    Button menos, mas, comenzar, resetear, volver;
    Switch ascendente;
    int contadorCafes, contadorTiempo;
    MyCountDownTimer miContador;
    MediaPlayer mp;
    AlertDialog.Builder popup;
    long milisegundos;
    boolean esTerminado, esFinalApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        cafes = (TextView) findViewById(R.id.txvContador);
        tiempo = (TextView) findViewById(R.id.txvTiempo);
        menos = (Button) findViewById(R.id.btnMenos);
        menos.setOnClickListener(this);
        mas = (Button) findViewById(R.id.btnMas);
        mas.setOnClickListener(this);
        comenzar = (Button) findViewById(R.id.btnComenzar);
        comenzar.setOnClickListener(this);
        mp = MediaPlayer.create(this, R.raw.silbato);
        resetear = (Button)findViewById(R.id.btnResetear);
        resetear.setOnClickListener(this);
        volver = (Button)findViewById(R.id.btnVolver);
        volver.setOnClickListener(this);
        ascendente = (Switch)findViewById(R.id.swtAscendente);
        popup = new AlertDialog.Builder(this);

        ponerCafes(0);
        ponerTiempo(5);
        esTerminado = false;
        esFinalApp = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("CONTADORCAFES", contadorCafes);
        outState.putInt("CONTADORTIEMPO", contadorTiempo);
        outState.putLong("MILISEGUNDOS", milisegundos);
        outState.putBoolean("ESTERMINADO", esTerminado);
        if(esTerminado)
            outState.putString("PAUSATERMINADA", String.valueOf(tiempo.getText()));
        try {
            mp.stop();
            miContador.cancel();
        } catch (Exception ex) {
            // Excepción producida porque el contador no ha sido iniciado
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        contadorCafes = savedInstanceState.getInt("CONTADORCAFES");
        contadorTiempo = savedInstanceState.getInt("CONTADORTIEMPO");
        milisegundos = savedInstanceState.getLong("MILISEGUNDOS");
        esTerminado = savedInstanceState.getBoolean("ESTERMINADO");
        cafes.setText(String.valueOf(contadorCafes));
        if(!esTerminado) {
            menos.setEnabled(false);
            mas.setEnabled(false);
            comenzar.setEnabled(false);
            miContador = new MyCountDownTimer(milisegundos, 1000);
            miContador.start();
        } else {
            tiempo.setText(savedInstanceState.getString("PAUSATERMINADA"));
        }
    }

    private void ponerTiempo(int num) {
        if(num > 99)
            num = 99;
        if(num < 1)
            num = 1;
        contadorTiempo = num;
        if(num < 10)
            tiempo.setText("0" + num + ":00");
        else
            tiempo.setText(num + " :00");
    }

    private void ponerCafes(int num) {
        contadorCafes = num;
        cafes.setText(String.valueOf(num));
    }

    @Override
    public void onClick(View v) {
        if(v == volver) {
            try {
                mp.stop();
                miContador.cancel();
            } catch (Exception ex) {
                // Excepción producida porque el contador no ha sido iniciado
            } finally {
                finish();
            }
        }
        if(v == menos) {
            ponerTiempo(contadorTiempo - 1);
        }
        if(v == mas) {
            ponerTiempo(contadorTiempo + 1);
        }
        if(v == comenzar) {
            esTerminado = false;
            menos.setEnabled(false);
            mas.setEnabled(false);
            comenzar.setEnabled(false);
            miContador = new MyCountDownTimer(contadorTiempo * 60 * 1000, 1000);
            miContador.start();
        }
        if(v == resetear) {
            contadorCafes = 0;
            ponerCafes(contadorCafes);
            if(esFinalApp) {
                ponerTiempo(5);
                menos.setEnabled(true);
                mas.setEnabled(true);
                comenzar.setEnabled(true);
                esFinalApp = false;
            }
        }
    }

    public class MyCountDownTimer extends CountDownTimer {

        private long tiempoTotal;

        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
            tiempoTotal = startTime;
        }
        @Override
        public void onTick(long millisUntilFinished) {
            String min;
            String seg;
            int minutos;
            int segundos;
            milisegundos = millisUntilFinished;
            if(ascendente.isChecked())
            {
                minutos = (int)(((tiempoTotal - millisUntilFinished) / 1000) / 60);
                segundos = (int)(((tiempoTotal - millisUntilFinished) / 1000) % 60);
            } else {
                minutos = (int) ((millisUntilFinished / 1000) / 60);
                segundos = (int) ((millisUntilFinished / 1000) % 60);
            }
            if(minutos < 10)
                min = "0" + String.valueOf(minutos);
            else
                min = String.valueOf(minutos);
            if(segundos < 10)
                seg = "0" + String.valueOf(segundos);
            else
                seg = String.valueOf(segundos);
            tiempo.setText(min + ":" + seg);
        }
        @Override
        public void onFinish() {
            esTerminado = true;
            tiempo.setText("Pausa terminada!!");
            if(contadorCafes < 9) {
                ponerCafes(contadorCafes + 1);
                menos.setEnabled(true);
                mas.setEnabled(true);
                comenzar.setEnabled(true);
            }
            else {
                esFinalApp = true;
                popup.setTitle("Aplicación finalizada");
                popup.setMessage("Fin!!");
                popup.setPositiveButton("Cerrar", null);
                popup.show();
                mp.stop();
            }
            mp.start();
        }
    }
}