package com.example.android_new;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_new.entity.TitleInfo;
import com.example.android_new.entity.UserInfo;
import com.example.android_new.searchactivity.SearchActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private String[] titles = {"推荐", "国内", "国际", "娱乐", "体育", "军事", "体育", "汽车", "科技"};
    private List<TitleInfo> titles = new ArrayList<>();
    private TabLayout tab_layout;

    private ViewPager2 viewPager;

    private ImageView btn_open_drawerLayout;

    private ImageView scan;

    private EditText editText;

    private TextView btnSearch;
    private DrawerLayout drawerLayout;
    private NavigationView nav_view;
    private TextView tv_username;
    private TextView tv_mark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(intent);
        //初始化title数据
        titles.add(new TitleInfo("推荐","top"));
        titles.add(new TitleInfo("国内","guonei"));
        titles.add(new TitleInfo("国际","guoji"));
        titles.add(new TitleInfo("娱乐","yule"));
        titles.add(new TitleInfo("体育","tiyu"));
        titles.add(new TitleInfo("军事","junshi"));
        titles.add(new TitleInfo("科技","keji"));
        titles.add(new TitleInfo("财经","caijing"));
        titles.add(new TitleInfo("游戏","youxi"));
        titles.add(new TitleInfo("汽车","qiche"));
        titles.add(new TitleInfo("健康","jiankang"));
        //初始化控件
        viewPager = findViewById(R.id.viewPager);
        tab_layout = findViewById(R.id.tab_layout);
        btn_open_drawerLayout = findViewById(R.id.btn_open_drawerLayout);

        editText = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        scan = findViewById(R.id.scan);

        drawerLayout = findViewById(R.id.drawer_layout);

        nav_view = findViewById(R.id.nav_view);


        tv_username = nav_view.getHeaderView(0).findViewById(R.id.tv_username);
        tv_mark = nav_view.getHeaderView(0).findViewById(R.id.tv_mark);

        nav_view.setNavigationItemSelectedListener(item -> {
            if(item.getItemId()==R.id.nav_history){
                Intent intent13 = new Intent(MainActivity.this, HistoryListActivity.class);
                startActivity(intent13);
            }else if(item.getItemId()==R.id.nav_update_pwd){
                //判断是否登录
                UserInfo userInfo = UserInfo.getUserInfo();
                if(null!=userInfo){
                    startActivity(new Intent(MainActivity.this,UpdatePwdActivity.class));
                }else {
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();;
                }

            } else if (item.getItemId()==R.id.nav_about_app) {
                Intent intent13 = new Intent(MainActivity.this,AboutApp.class);
                startActivity(intent13);
            }else if (item.getItemId()==R.id.nav_exit){
                UserInfo userInfo = UserInfo.getUserInfo();
                if(null!=userInfo){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("温馨提示")
                            .setMessage("确认是否退出登录")
                            .setPositiveButton("确认", (dialog, which) -> {
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                UserInfo.setUserInfo(null);
                            })
                            .setNegativeButton("取消", (dialog, which) -> {

                            })
                            .show();
                }else{
                    Toast.makeText(MainActivity.this,"请先登录~~",Toast.LENGTH_SHORT).show();
                }
            } else if (item.getItemId()==R.id.nav_talk) {
                Intent intent13 = new Intent(MainActivity.this,TicTacToeActivity.class);
                startActivity(intent13);
            }else if (item.getItemId()==R.id.nav_update) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("版本1.0.0")
                        .setMessage("已经是最新版")
                        .setPositiveButton("确认", (dialog, which) -> {
                           Intent intent1 =  new Intent(MainActivity.this,AboutApp.class);
                           startActivity(intent1);
                        })
                        .setNegativeButton("取消", (dialog, which) -> {
                        })
                        .show();
            } else if (item.getItemId() == R.id.nav_downloadpc) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("桌面端")
                        .setMessage("正在开发中~~~~")
                        .setPositiveButton("确认", (dialog, which) -> {
                        })
                        .setNegativeButton("取消", (dialog, which) -> {
                        })
                        .show();
            } else if (item.getItemId() == R.id.nav_start) {
                Intent intentStart = new Intent(MainActivity.this,StartShowActivity.class);
                startActivity(intentStart);
            } else if (item.getItemId() == R.id.nav_back) {
                Intent intentBack = new Intent(MainActivity.this,ScanActivity.class);
                startActivity(intentBack);
            }else if (item.getItemId() == R.id.nav_ewm) {
                Intent intentEwm = new Intent(MainActivity.this,ScanActivity.class);
                startActivity(intentEwm);
            }
            return true;
        });

        btn_open_drawerLayout.setOnClickListener(v -> drawerLayout.open());

//        editText.setOnClickListener(v -> {
//                    Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
//                    startActivity(intent2);
//                }
//        );
        btnSearch.setOnClickListener(v -> {
            String searchText = editText.getText().toString();
            if(TextUtils.isEmpty(searchText)){
                Toast.makeText(MainActivity.this,"请输入搜索内容",Toast.LENGTH_SHORT).show();
                return;
            }
            if(searchText.length()<2){
                Toast.makeText(MainActivity.this,"搜索内容太短",Toast.LENGTH_SHORT).show();
                return;
            }
            if(searchText.length()>10){
                Toast.makeText(MainActivity.this,"搜索内容太长",Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
            intent2.putExtra("searchText",searchText);
            startActivity(intent2);
        });

        scan.setOnClickListener(v -> {
            Intent intent3 = new Intent(MainActivity.this, ScanActivity.class);
            startActivity(intent3);
        });

        //设置adapter
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                //创建 NewsTabFragment页面
                String title = titles.get(position).getPy_title();
//                String title = titles[position];
                return TabNewsFragment.newInstance(title);
            }

            @Override
            public int getItemCount() {
                return getTitle().toString().length();
            }
        });

        //tab_layout点击事件
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //设置viewPager选中当前页
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager和tab_layout关联在一起
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, (tab, position) -> {
            tab.setText(titles.get(position).getTitle());
//                TabLayout.Tab tab1 = tab.setText(titles);
//                tab.setText(titles[position]);
        });

        //这几话不能少
        tabLayoutMediator.attach();

        tv_mark.setOnClickListener(v -> {
            Intent intent12 = new Intent(MainActivity.this, GexinQianmin.class);
            startActivity(intent12);
        });
    }

    protected void onResume(){
        super.onResume();

        UserInfo userInfo = UserInfo.getUserInfo();
        if(null!=userInfo){
            tv_username.setText(userInfo.getUsername());
            tv_mark.setText(userInfo.getMark());
        }
        else{
            tv_username.setText("请登录");
            tv_mark.setText("");
            //登录点击事件
            tv_username.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            });
        }
    }

    public void onBackPressed(){
        AlertDialog dialog;
        dialog = new AlertDialog.Builder(this).setTitle("News").setIcon(R.mipmap.ewm).setMessage("是否退出应用？").setPositiveButton("确定", (dialog12, which) -> {
            dialog12.dismiss();
            finish();
        }).setNegativeButton("取消", (dialog1, which) -> dialog1.dismiss()).create();
        dialog.show();
    }
}