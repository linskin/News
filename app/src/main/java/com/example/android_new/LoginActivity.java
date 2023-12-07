package com.example.android_new;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_new.db.UserDbHelper;
import com.example.android_new.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private boolean is_login;
    private SharedPreferences msharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        et_username = findViewById(R.id.log_username);
        et_password = findViewById(R.id.log_password);
        CheckBox checkbox = findViewById(R.id.checkbox);

        findViewById(R.id.denglubar).setOnClickListener(v -> finish());

        //注册
        findViewById(R.id.to_register).setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        //是否勾选了记住密码
        is_login = msharedPreferences.getBoolean("is_login",false);
        if(is_login){
            String username = msharedPreferences.getString("username",null);
            String password = msharedPreferences.getString("password",null);
            et_username.setText(username);
            et_password.setText(password);
            checkbox.setChecked(true);
        }

        //登录事件
        findViewById(R.id.dl_login).setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();

            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this,"请输入正确的用户名和密码",Toast.LENGTH_SHORT).show();

            }else{
                UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                if(login != null){
                    if(username.equals(login.getUsername()) && password.equals(login.getPassword()) ){
                        SharedPreferences.Editor edit = msharedPreferences.edit();
                        edit.putBoolean("is_login",is_login);
                        edit.putString("username",username);
                        edit.putString("password",password);

                        //不可少
                        edit.apply();
                        UserInfo.setUserInfo(login);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"该账号暂未注册",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}