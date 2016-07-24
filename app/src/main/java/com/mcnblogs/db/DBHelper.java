package com.mcnblogs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mcnblogs.core.AppContext;
import com.mcnblogs.db.Scheam.BlogDetailTable;
import com.mcnblogs.db.Scheam.BlogInfoTable;

/**
 * 作者：hl
 * 时间:2016/7/22 10:10
 * 说明:数据库操作类
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "CnblogSQLite";
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "cnblog.db";

    private static DBHelper singleton = null;

    //必须要有构造函数
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static synchronized DBHelper getInstance() {
        if (singleton == null) {
            singleton = new DBHelper(AppContext.getInstance());
        }
        return singleton;
    }

    /**
     * 创建sql语句
     * @param db
     *
     */
    private void createTable(SQLiteDatabase db){
        db.execSQL(BlogInfoTable.CREATE_TABLE_SQL);
        db.execSQL(BlogDetailTable.CREATE_TABLE_SQL);
    }

    // 当第一次创建数据库的时候，调用该方法
    public void onCreate(SQLiteDatabase db) {
     //   String sql = "create table stu_table(id int,sname varchar(20),sage int,ssex varchar(10))";
        //输出创建数据库的日志信息
        createTable(db);
        Log.i(TAG, "create Database------------->");
    }

    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //输出更新数据库的日志信息
        Log.i(TAG, "update Database------------->");
    }
}
