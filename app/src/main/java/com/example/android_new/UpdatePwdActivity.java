package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_new.db.UserDbHelper;
import com.example.android_new.entity.UserInfo;

public class UpdatePwdActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText et_new_password;
    private EditText et_confirm_password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

        //初始化控件
        et_new_password = findViewById(R.id.xg_et_password);
        et_confirm_password = findViewById(R.id.xg_confirm_password);

        toolbar = findViewById(R.id.xg_toolbar);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //修改密码点击事件
        findViewById(R.id.xg_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_pwd = et_new_password.getText().toString();
                String confirm_pwd = et_confirm_password.getText().toString();

                if(TextUtils.isEmpty(new_pwd) || TextUtils.isEmpty(confirm_pwd)){
                    Toast.makeText(UpdatePwdActivity.this,"修改密码不可为空",Toast.LENGTH_SHORT).show();

                } else if (!new_pwd.equals(confirm_pwd)) {
                    Toast.makeText(UpdatePwdActivity.this,"新密码和确认密码不一致",Toast.LENGTH_SHORT).show();

                }else{
                    UserInfo userInfo = UserInfo.getUserInfo();
                    if(null != userInfo){
                        int row = UserDbHelper.getInstance(UpdatePwdActivity.this).updatePwd(userInfo.getUsername(),new_pwd);
                        if(row>0){
                            Toast.makeText(UpdatePwdActivity.this,"密码修改成功，请重新登录！！！",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdatePwdActivity.this,LoginActivity.class));
                            UserInfo.setUserInfo(null);
                            finish();
                        }else{
                            Toast.makeText(UpdatePwdActivity.this,"修改密码失败，请重新尝试",Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }
        });



    }
}