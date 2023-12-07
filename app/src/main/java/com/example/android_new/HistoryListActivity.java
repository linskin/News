package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_new.adapter.NewsListAdapter;
import com.example.android_new.db.HistoryDbHelper;
import com.example.android_new.entity.HistoryInfo;
import com.example.android_new.entity.NewsInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HistoryListActivity extends AppCompatActivity {

    private final List<NewsInfo.ResultBean.DataBean> dataBeanList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        Toolbar toolbar = findViewById(R.id.ls_toolbar);

        toolbar.setOnClickListener(v -> finish());


        RecyclerView recyclerView = findViewById(R.id.ls_recyclerView);

        //初始化适配器
        NewsListAdapter newsListAdapter = new NewsListAdapter(this);
        recyclerView.setAdapter(newsListAdapter);

        //获取数据
        List<HistoryInfo> historyInfoList = HistoryDbHelper.getInstance(HistoryListActivity.this).queryHistoryListData(null);

        Gson gson = new Gson();
        for (int i = 0; i < historyInfoList.size(); i++) {

            dataBeanList.add(gson.fromJson(historyInfoList.get(i).getNew_json(),NewsInfo.ResultBean.DataBean.class));
        }
        //设置数据
        newsListAdapter.setListData(dataBeanList);

        //recyclerView点击事件
        newsListAdapter.setmOnItemClickListener((dataBean, position) -> {
            //跳转详情页
            Intent intent = new Intent(HistoryListActivity.this,NewsDetailsActivity.class);
            //传递对象时候该类实现serialized
            intent.putExtra("dataBean",dataBean);
            startActivity(intent);
        });


    }
}