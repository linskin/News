package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_new.db.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username =findViewById(R.id.zc_et_username);
        et_password = findViewById(R.id.zc_et_password);


        findViewById(R.id.zhucebar).setOnClickListener(v -> finish());

        findViewById(R.id.zc_register).setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                Toast.makeText(RegisterActivity.this,"请输入完整的用户名和密码",Toast.LENGTH_SHORT).show();
            }else{
                int row = UserDbHelper.getInstance(RegisterActivity.this).register(username,password,"暂无~~~~");
                if(row>0){
                    Toast.makeText(RegisterActivity.this,"注册成功，请登录！！",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}