package com.example.android_new.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android_new.entity.UserInfo;

public class UserDbHelper extends SQLiteOpenHelper {
    private static UserDbHelper sHelper;
    private static final String DB_NAME = "user_info.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public UserDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static UserDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new UserDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table user_table(_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "password text," +      //密码
                "register_type integer" +       // 注册类型   0---用户   1---管理员
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public int register(String username, String password, int register_type) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("password", password);
        values.put("register_type", register_type);
        String nullColumnHack = "values(null,?,?,?)";
        //执行
        int insert = (int) db.insert("user_table", nullColumnHack, values);
        db.close();
        return insert;
    }

    /**
     * 登录  根据用户名查找用户
     */
    @SuppressLint("Range")
    public UserInfo login(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        UserInfo userInfo = null;
        String sql = "select _id,username,password,register_type  from user_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int register_type = cursor.getInt(cursor.getColumnIndex("register_type"));
            userInfo = new UserInfo(_id, name, password, register_type);
        }
        cursor.close();
        db.close();
        return userInfo;
    }

    /**
     * 根据用户唯一 _id来修改密码
     */
    public int updatePwd(int _id, String password) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("password", password);
        // 执行SQL
        int update = db.update("user_table", values, " _id=?", new String[]{_id+""});
        // 关闭数据库连接
        db.close();
        return update;

    }
}
