package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.android_new.db.HistoryDbHelper;
import com.example.android_new.entity.NewsInfo;
import com.google.gson.Gson;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        WebView webView = findViewById(R.id.webView);

        NewsInfo.ResultBean.DataBean dataBean = (NewsInfo.ResultBean.DataBean) getIntent().getSerializableExtra("dataBean");

        //设置数据
        if(null!= dataBean){
            toolbar.setTitle(dataBean.getTitle());
            webView.loadUrl(dataBean.getUrl());

            //添加历史记录
            String dataBeanJson = new Gson().toJson(dataBean);
            HistoryDbHelper.getInstance(NewsDetailsActivity.this).addHistory(null, dataBean.getUniquekey(),dataBeanJson);

        }

        toolbar.setOnClickListener(v -> finish());



    }
}