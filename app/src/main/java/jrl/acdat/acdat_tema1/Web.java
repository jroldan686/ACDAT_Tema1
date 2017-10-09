package jrl.acdat.acdat_tema1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends Activity {

    WebView navegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent i = this.getIntent();
        navegador = (WebView)findViewById(R.id.wbvWeb);
        navegador.loadUrl("https://" + i.getDataString());
    }
}
