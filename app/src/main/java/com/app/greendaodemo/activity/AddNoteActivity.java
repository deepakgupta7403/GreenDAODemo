package com.app.greendaodemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.greendaodemo.R;
import com.app.greendaodemo.Utility.MyUtils;
import com.app.greendaodemo.databasehelper.NoteOperation;
import com.app.greendaodemo.model.NoteModel;
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
        onClick();

    }

    private void setViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tiet_title = (TextInputEditText) findViewById(R.id.tiet_title);
        tiet_description = (TextInputEditText) findViewById(R.id.tiet_description);
        btn_save = (Button) findViewById(R.id.btn_save);

        setSupportActionBar(toolbar);
        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(MyUtils.getStringResources(mContext, R.string.add_notes));
        getSupportActionBar().setHomeButtonEnabled(true);



        MyUtils.clearFocusFromTextInputEditText(tiet_title);
        MyUtils.clearFocusFromTextInputEditText(tiet_description);
    }


    public void onClick() {

        tiet_title.setOnClickListener(v -> {
            MyUtils.enableFocusFromTextInputEditText(tiet_title);
        });

        tiet_description.setOnClickListener(v -> {
            MyUtils.enableFocusFromTextInputEditText(tiet_description);
        });

        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        btn_save.setOnClickListener(v -> {
            if (TextUtils.isEmpty(tiet_title.getText().toString().trim()) && TextUtils.isEmpty(tiet_description.getText().toString().trim())) {
                Toast.makeText(mContext, "Both field should not be empty!", Toast.LENGTH_SHORT).show();
            } else {
                NoteModel noteModel = new NoteModel();
                if (!TextUtils.isEmpty(tiet_title.getText().toString().trim())) {
                    noteModel.setTitle(tiet_title.getText().toString().trim());
                }
                if (!TextUtils.isEmpty(tiet_description.getText().toString().trim())) {
                    noteModel.setDescription(tiet_description.getText().toString().trim());
                }

                noteModel.setCreatedAt(System.currentTimeMillis());
                noteModel.setLastUpdatedAt(System.currentTimeMillis());
                noteModel.setIsActivated(true);
                NoteOperation.insertNoteData(noteModel);
                finish();
            }
        });
    }

}
