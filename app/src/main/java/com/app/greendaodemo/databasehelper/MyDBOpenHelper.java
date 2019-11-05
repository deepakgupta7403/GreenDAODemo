package com.app.greendaodemo.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.app.greendaodemo.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class MyDBOpenHelper extends DaoMaster.OpenHelper {

    public MyDBOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        DaoMaster.dropAllTables(db,true);
        DaoMaster.createAllTables(db,true);
    }
}
