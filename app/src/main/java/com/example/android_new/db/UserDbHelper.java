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
        db.execSQL("create table user_table(user_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "password text," +      //密码
                "mark text" +       // 格言
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 注册
     */
    public int register(String username, String password, String mark) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("username", username);
        values.put("password", password);
        values.put("mark", mark);
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
        String sql = "select user_id,username,password,mark  from user_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String Mark = cursor.getString(cursor.getColumnIndex("mark"));
            userInfo = new UserInfo(user_id, name, password, Mark);
        }
        cursor.close();
        db.close();
        return userInfo;
    }


    /**
     * 根据用户唯一名字来修改密码
     */
    public int updatePwd(String username, String password) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("password", password);
        // 执行SQL
        int update = db.update("user_table", values, " username=?", new String[]{username});
        // 关闭数据库连接
        db.close();
        return update;

    }
    /**
     * 根据用户唯一名字来修改个性签名
     */
    public int updateQM(String username, String mark) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("mark", mark);
        // 执行SQL
        int update = db.update("user_table", values, " username=?", new String[]{username});
        // 关闭数据库连接
        db.close();
        return update;
    }

}
