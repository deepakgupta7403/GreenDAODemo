package com.app.greendaodemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.greendaodemo.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddNoteActivity extends AppCompatActivity {
    Context mContext;
    Toolbar toolbar;
    TextInputEditText tiet_title, tiet_description;
    Button btn_save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mContext = getApplicationContext();
        setViews();
    }

    private void setViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tiet_title = (TextInputEditText) findViewById(R.id.tiet_title);
        tiet_description = (TextInputEditText) findViewById(R.id.tiet_description);
        btn_save = (Button) findViewById(R.id.btn_save);

        setSupportActionBar(toolbar);
        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
