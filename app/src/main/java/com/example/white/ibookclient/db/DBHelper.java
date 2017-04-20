package com.example.white.ibookclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * 重写了SQLiteOpenHelper类，用来建立数据库，还有表，自己还应该好好学习一下。
 */
public class DBHelper extends SQLiteOpenHelper{
	// 数据库名
	private static final String DATABASE_NAME = "mydate";
	// 版本号
	private static final int DATABASE_VERSION = 1;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS mybook(ids INTEGER PRIMARY KEY autoincrement,title text,content text,times text)");
	}

	// 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
