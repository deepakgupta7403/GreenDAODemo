package com.app.greendaodemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.app.greendaodemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewNoteActivity extends AppCompatActivity {
    RecyclerView rv_view_notes;
    FloatingActionButton fab_add_notes;
    LinearLayout ll_no_notes_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        setViews();

    }

    private void setViews() {
        rv_view_notes = (RecyclerView) findViewById(R.id.rv_view_notes);
        fab_add_notes = (FloatingActionButton) findViewById(R.id.fab_add_notes);
        ll_no_notes_found = (LinearLayout) findViewById(R.id.ll_no_notes_found);
    }
}