package com.example.android_new;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_new.adapter.NewsListAdapter;
import com.example.android_new.entity.NewsInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class TabNewsFragment extends Fragment {


    //url在134行
    private RecyclerView recyclerView;

    private NewsListAdapter mNewListAdapter;

    private static final String ARG_PARAM = "title";
    private String title;
    // 保存当前线程的Handler
    private final Handler mHandler = new Handler(Objects.requireNonNull(Looper.myLooper())){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100){
                String data = (String) msg.obj;
                NewsInfo newsInfo = new Gson().fromJson(data, NewsInfo.class);
                if (newsInfo != null && newsInfo.getError_code() == 0){
                    // 逻辑处理
                    if (null!= mNewListAdapter){
                        mNewListAdapter.setListData(newsInfo.getResult().getData());
                    }
                } else {
                    Toast.makeText(getActivity(), "聚合数据调用已达上限，获取数据失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public TabNewsFragment() {

    }

    /**
     * 创建一个新的TabNewsFragment实例
     * @param param 参数用于设置要请求的新闻标题
     * @return TabNewsFragment实例
     */
    public static TabNewsFragment newInstance(String param) {
        TabNewsFragment fragment = new TabNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 将布局文件解析为视图对象
        View rootView = inflater.inflate(R.layout.fragment_tab_news, container, false);
        // 初始化控件
        recyclerView = rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 初始化适配器
        mNewListAdapter = new NewsListAdapter(getActivity());
        // 设置Adapter
        recyclerView.setAdapter(mNewListAdapter);

        // 设置RecyclerView列表点击事件
        mNewListAdapter.setmOnItemClickListener((dataBean, position) -> {
            // 跳转到新闻详情页
            Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
            // 传递对象时需要实现序列化
            intent.putExtra("dataBean", dataBean);
            startActivity(intent);
        });

        // 获取数据
        getHttpData();
    }

    /**
     * 获取新闻数据
     */
    private void getHttpData() {
        // 创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // 构造Request对象

        // 一号url
     String url = "http://v.juhe.cn/toutiao/index?key=35626a6c07a0f3983921f2a7c4d50d2d&type=";

        // 二号url
//     String url = "http://v.juhe.cn/toutiao/index?key=350ba6de270f23a4e055f3192808ab69&type=";
        // 测试用的url
//        String url = "https://www.toutiao.com";
        Request request = new Request.Builder()
                .url(url + title)
                .get()
                .build();
        // 通过OkHttpClient和Request对象来构建Call对象
        Call call = okHttpClient.newCall(request);
        // 通过Call对象的enqueue(Callback)方法来执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Timber.tag("-------------").d("onFailure: %s", e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String data = response.body().string();
                Message message = new Message();
                // 设置一个标识符
                message.what = 100;
                message.obj = data;
                // 发送消息
                mHandler.sendMessage(message);
            }
        });
    }
}
