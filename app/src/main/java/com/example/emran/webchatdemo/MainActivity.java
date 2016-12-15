package com.example.emran.webchatdemo;

import android.app.Activity;
import android.content.Context;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class MainActivity extends AppCompatActivity {
    // declare ui
    private WebView webView;
    private NumberProgressBar pBarWebView;
    private String dataUrl = "https://www.youtube.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        pBarWebView = (NumberProgressBar) findViewById(R.id.pBarWebView);
        pBarWebView.setMax(100);

        webView = (WebView) findViewById(R.id.webView);

        // set web view property
        webView.setWebViewClient(new MyWebViewClient(pBarWebView));
        webView.setWebChromeClient(new MyWebChromeClient());

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.loadUrl(dataUrl);

    }

    public void setValue(int value) {
        this.pBarWebView.setProgress(value);
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            MainActivity.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }

    }
}
