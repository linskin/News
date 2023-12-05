package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;

    private SharedPreferences msharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username =findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        msharedPreferences = getSharedPreferences("user",MODE_PRIVATE);

        findViewById(R.id.zhucebar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"请输入完整的用户名和密码",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor edit = msharedPreferences.edit();
                    edit.putString("username",username);
                    edit.putString("password",password);

                    edit.commit();
                    Toast.makeText(RegisterActivity.this,"注册成功，请登录！",Toast.LENGTH_SHORT).show();
                    finish();
                }




            }
        });
    }
}