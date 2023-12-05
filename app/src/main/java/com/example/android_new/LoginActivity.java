package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_new.db.UserDbHelper;
import com.example.android_new.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private SharedPreferences msharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        et_username = findViewById(R.id.log_username);
        et_password = findViewById(R.id.log_password);

        findViewById(R.id.to_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //登录事件
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"请输入正确的用户名和密码",Toast.LENGTH_SHORT).show();

                }else{
//                    UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    msharedPreferences.getString("username",null);
                    String name = msharedPreferences.getString("username",null);
                    String pwd = msharedPreferences.getString("password",null);
                    if(username.equals(name) && password.equals(pwd)){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}