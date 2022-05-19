package com.example.webvievdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);;
        web.setWebViewClient(new Callback());
        web.loadUrl("https://jreigpas.github.io/myapp/");
        //web.loadUrl("https://www.elmundo.es/");

        //String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
        //web.loadData(data, "text/html", "UTF-8");

        webSettings.setDomStorageEnabled(true);
        web.addJavascriptInterface(new JavaScriptInterface(this), "Android");

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
            toast.show();

            return "";
        }
    }

}