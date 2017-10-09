package jrl.acdat.acdat_tema1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Ejercicio3 extends Activity implements OnClickListener {

    EditText url;
    Button ir;
    Button volver;
    WebView wv;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        url = (EditText)findViewById(R.id.edtURL);
        ir = (Button)findViewById(R.id.btnIr);
        ir.setOnClickListener(this);
        wv = (WebView) this.findViewById(R.id.wbvWeb);
        volver = (Button)findViewById(R.id.btnVolver);
        volver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == volver) {
            finish();
        }
        if (v == ir) {
            i = new Intent(this, Web.class);
            i.setData(Uri.parse(url.getText().toString()));
            startActivity(i);
        }
    }
}