package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class NewsDetailsActivity extends AppCompatActivity {
    private NewsInfo.ResultBean.DataBean dataBean;

    private Toolbar toolbar;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webView);

        dataBean = (NewsInfo.ResultBean.DataBean) getIntent().getSerializableExtra("dataBean");

        if(null!=dataBean){
            toolbar.setTitle(dataBean.getTitle());
            webView.loadUrl(dataBean.getUrl());
        }

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}