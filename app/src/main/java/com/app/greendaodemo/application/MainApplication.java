package com.app.greendaodemo.application;

import android.app.Application;

import com.app.greendaodemo.databasehelper.MyDBOpenHelper;
import com.app.greendaodemo.model.DaoMaster;
import com.app.greendaodemo.model.DaoSession;

public class MainApplication extends Application {
    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        session = new DaoMaster(new MyDBOpenHelper(this,"MyGreenDAODatabase.db").getWritableDb()).newSession();
    }

    public DaoSession getSession() {
        return session;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
