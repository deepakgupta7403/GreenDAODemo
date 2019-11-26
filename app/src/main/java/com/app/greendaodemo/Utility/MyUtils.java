package com.app.greendaodemo.Utility;

import android.content.Context;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class MyUtils {

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
}
