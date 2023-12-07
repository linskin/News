package com.example.android_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_new.db.UserDbHelper;
import com.example.android_new.entity.UserInfo;

public class GexinQianmin extends AppCompatActivity {
    private EditText et_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesin_qianmin);

        et_mark =findViewById(R.id.gx_editText);

        findViewById(R.id.gexin).setOnClickListener(v -> finish());

        //修改签名
        findViewById(R.id.gx_fabu).setOnClickListener(v -> {
            String mark = et_mark.getText().toString();
            if(TextUtils.isEmpty(mark)){
                Toast.makeText(GexinQianmin.this,"请输入有效的个性签名后发布",Toast.LENGTH_SHORT).show();
            }else {
                UserInfo userInfo = UserInfo.getUserInfo();
                int row = UserDbHelper.getInstance(GexinQianmin.this).updateQM(userInfo.getUsername(),mark);
                if(row>0){
                    Toast.makeText(GexinQianmin.this,"个性签名发布成功，请退出登录后重新查看！！！",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(GexinQianmin.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(GexinQianmin.this,"个性签名发布失败，请重新尝试",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}