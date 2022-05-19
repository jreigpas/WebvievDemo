package com.example.webvievdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView web;
    Button myButton;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);;
        web.setWebViewClient(new Callback());
        web.loadUrl("https://jreigpas.github.io/myapp/");

        webSettings.setDomStorageEnabled(true);
        web.addJavascriptInterface(new JavaScriptInterface(this), "Android");

        Log.i(TAG, "Pasa por aqui 1");

        myButton = (Button)findViewById(R.id.button1);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "este es un ejemplo", Toast.LENGTH_SHORT).show();
                web.evaluateJavascript(
                        "changeText(\"" + "INFORMACION DESDE ANDROID" + "\")",
                        null);

                Log.i(TAG, "Pasa por aqui 2");

            }
        });

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }

    public class JavaScriptInterface {
        Context mContext;
        JavaScriptInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public String getFromAndroid(String texto) {

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, texto, duration);
            Log.i(TAG, "Informacion que nos llega de React " + texto);
            toast.show();

            return "";
        }
    }

}