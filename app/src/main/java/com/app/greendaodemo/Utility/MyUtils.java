package com.app.greendaodemo.Utility;

import android.content.Context;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyUtils {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String DATE_TIME_FORMAT_SPACIOUS = "dd-MM-yyyy  HH:mm";
    public static final String DATE_TIME_FORMAT_HRS = "dd-MM-yyyy HH:mm:ss";
    public static final String TIME_12HR_FORMAT = "hh:mm a";
    public static final String TIME_24HR_FORMAT = "HH:mm:ss";

    public static String getStringResources(Context context, int stringId) {
        return context.getResources().getString(stringId);
    }

    public static void clearFocusFromEditText(EditText editText) {
        editText.setFocusableInTouchMode(false);
        editText.setFocusable(false);
    }

    public static void clearFocusFromTextInputEditText(TextInputEditText textInputEditText) {
        textInputEditText.setFocusableInTouchMode(false);
        textInputEditText.setFocusable(false);
    }

    public static void enableFocusFromEditText(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
    }

    public static void enableFocusFromTextInputEditText(TextInputEditText textInputEditText) {
        textInputEditText.setFocusableInTouchMode(true);
        textInputEditText.setFocusable(true);
    }

    public static String getDateTimeFromMillies(long millies) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);
        return dateTimeFormat.format(new Date(millies));
    }

    public static String getDateFromMillies(long millies) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return dateFormat.format(new Date(millies));
    }
}
