package com.example.webvievdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView web;
    Button myButton;
    EditText myEditText;

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
        web.addJavascriptInterface(new JavaScriptInterface(this), "Native");

        Log.i(TAG, "Pasa por aqui 1");

        myButton = (Button)findViewById(R.id.button1);
        myEditText = (EditText)findViewById(R.id.editTextTextPersonName);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable textInput = myEditText.getText();
                Toast.makeText(MainActivity.this, "este es un ejemplo", Toast.LENGTH_SHORT).show();
                web.evaluateJavascript(
                        "getFromNative(\"" + textInput + "\")",
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
        TextView myTextView;
        JavaScriptInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public String getFromReact(String texto) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, texto, duration);
            Log.i(TAG, "Informacion que nos llega de React " + texto);
            toast.show();
            myTextView = (TextView) findViewById(R.id.textView);
            myTextView.setText(texto);

            return "";
        }
    }

}