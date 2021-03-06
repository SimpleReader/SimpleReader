package com.umeng.soexample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.umeng.soexample.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chenggong on 2017/5/6
 * 数据库管理类
 */
public class DBManager {

    private static String TAG = DBManager.class.getSimpleName();
    public static final String DB_NAME = "china_city.db"; //数据库名字
    public static final String PACKAGE_NAME = "com.umeng.soexample";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" +
        PACKAGE_NAME;  //在手机里存放数据库的位置(/data/data/com.umeng.soexample/china_city.db)
    private SQLiteDatabase database;
    private static Context mContext;
    private DBManager() {

    }

    public static DBManager getInstance(Context context) {
        mContext=context;
        return DBManagerHolder.sInstance;
    }

    private static final class DBManagerHolder {
        public static final DBManager sInstance = new DBManager();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }


    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    @Nullable
    private SQLiteDatabase openDatabase(String dbfile) {
        Logger.e("dbfile:"+dbfile+"路径"+mContext.getFilesDir());
        try {
            if (!(new File(dbfile).exists())) {
                //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = mContext.getResources().openRawResource(R.raw.china_city); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                int BUFFER_SIZE = 400000;
                byte[] buffer = new byte[BUFFER_SIZE];
                int count;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            return SQLiteDatabase.openOrCreateDatabase(dbfile, null);
        } catch (FileNotFoundException e) {
            Logger.e("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Logger.e("IO exception");
            e.printStackTrace();
        }

        return null;
    }

    public void closeDatabase() {
        if (this.database != null) {
            this.database.close();
        }
    }
}


