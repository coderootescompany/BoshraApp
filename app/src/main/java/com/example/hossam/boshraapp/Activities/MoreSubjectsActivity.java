package com.example.hossam.boshraapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.hossam.boshraapp.R;

public class MoreSubjectsActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_subjects);

        //webView.findViewById(R.id.more_webview);
        //webView.loadUrl("https://stackoverflow.com/questions/30180957/send-post-request-with-params-using-retrofit");
    }
}
