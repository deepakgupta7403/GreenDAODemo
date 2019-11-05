package com.app.greendaodemo.Utility;

import android.content.Context;

public class MyUtils {

    public static String getStringResources(Context context, int stringId) {
        return context.getResources().getString(stringId);
    }
}
