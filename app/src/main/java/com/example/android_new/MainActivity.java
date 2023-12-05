package com.example.android_new;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_new.entity.UserInfo;
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
    private DrawerLayout drawerLayout;
    private NavigationView nav_view;
    private TextView tv_username;
    private TextView tv_mark;
    private NavigationView nav_view2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        drawerLayout = findViewById(R.id.drawer_layout);

        nav_view = findViewById(R.id.nav_view);


        tv_username = nav_view.getHeaderView(0).findViewById(R.id.tv_username);
        tv_mark = nav_view.getHeaderView(0).findViewById(R.id.tv_mark);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_history){
                    Intent intent = new Intent(MainActivity.this, HistoryListActivity.class);
                    startActivity(intent);
                }else if(item.getItemId()==R.id.nav_update_pwd){
                    UserInfo userInfo = UserInfo.getUserInfo();
                    if(null!=userInfo){
                        startActivity(new Intent(MainActivity.this,UpdatePwdActivity.class));
                    }else {
                        Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();;
                    }

                } else if (item.getItemId()==R.id.nav_about_app) {
                    Intent intent = new Intent(MainActivity.this,AboutApp.class);
                    startActivity(intent);
                }

                return true;
            }


        });




        btn_open_drawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });


        //设置adapter
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                //创建 NewsTabFragment页面
                String title = titles.get(position).getPy_title();
//                String title = titles[position];
                TabNewsFragment tabNewsFragment = TabNewsFragment.newInstance(title);
                return tabNewsFragment;
            }

            @Override
            public int getItemCount() {
                return getTitle().length();
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
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewPager, new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position).getTitle());
//                TabLayout.Tab tab1 = tab.setText(titles);
//                tab.setText(titles[position]);
            }
        });

        //这几话不能少
        tabLayoutMediator.attach();


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
            tv_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
            });
        }
    }





    public void onBackPressed(){
        AlertDialog dialog;
        dialog = new AlertDialog.Builder(this).setTitle("News").setIcon(R.mipmap.ic_launcher_round).setMessage("是否退出应用？").setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create();
        dialog.show();
    }
}