package com.app.greendaodemo.application;

import android.app.Application;

import com.app.greendaodemo.databasehelper.MyDBOpenHelper;
import com.app.greendaodemo.model.DaoMaster;
import com.app.greendaodemo.model.DaoSession;
import com.facebook.stetho.Stetho;

public class MainApplication extends Application {
    private static DaoSession session;
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        session = new DaoMaster(new MyDBOpenHelper(this,"notes.db").getWritableDb()).newSession();
        Stetho.initializeWithDefaults(this);
    }

    public static synchronized MainApplication getInstance() {
        return instance;
    }

    public static synchronized DaoSession getSession() {
        return session;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
